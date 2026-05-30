package com.interviewmate.authservice.configuration;

import com.interviewmate.authservice.constance.AppConstants;
import com.interviewmate.authservice.filter.JwtFilter;
import com.interviewmate.authservice.security.OAuth2SuccessHandler;
import com.interviewmate.authservice.security.Oauth2FailureHandler;
import com.interviewmate.authservice.service.Impl.CustomOAuth2UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final Oauth2FailureHandler oauth2FailureHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(sm -> sm
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeHttpRequest ->
                        authorizeHttpRequest.requestMatchers(AppConstants.AUTH_PUBLIC_URL).permitAll()
                                .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex.authenticationEntryPoint(unauthorizedEntryPoint()))

                // OAuth2 login
                .oauth2Login(oauth2 -> oauth2
                        // redirect spring security unauthenticated users
                        .loginPage("/api/v2/auth/login")

                        // endpoint frontend hits to start the Oauth2 flow:
                        // GET /oauth2/authroized/google
                        // GET /oauth2/authrized/github
                        .authorizationEndpoint(endPoint -> endPoint.baseUri("/oauth2/authorize"))

                        // the callback URL registered in Google/GitHub console:
                        // /login/oauth2/code/google
                        // /login/oauth2/code/github
                        .redirectionEndpoint(endPoint -> endPoint.baseUri("/login/oauth2/code/*"))

                        // custom service that creates/updates the local User record
                        .userInfoEndpoint(endPoint -> endPoint.userService(customOAuth2UserService))

                        // redirect frontend with jwt token on success
                        .successHandler(oAuth2SuccessHandler)

                        // redirect frontend with error message on failure
                        .failureHandler(oauth2FailureHandler)

                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    @Bean
    protected AuthenticationEntryPoint unauthorizedEntryPoint() {
        return ((request, response, authException) -> {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            PrintWriter writer = response.getWriter();

            String json = """
                    {
                      "error: "UNAUTHORIZED",
                      "message":%s
                     }
                    """.formatted(authException.getMessage());

            writer.write(json);
            writer.flush();
        });
    }
}
