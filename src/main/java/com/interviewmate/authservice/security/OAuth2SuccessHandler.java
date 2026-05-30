package com.interviewmate.authservice.security;

import com.interviewmate.authservice.entity.User;
import com.interviewmate.authservice.enums.Role;
import com.interviewmate.authservice.repository.UserRepository;
import com.interviewmate.authservice.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;


    @Value("${app.oauth2.authorized-redirect-uris[0]}")
    private String defaultFrontendRedirectUri;

    @Override
    public void onAuthenticationSuccess(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                        @NonNull Authentication authentication) throws IOException {

        if (response.isCommitted()) {
            log.warn("response already committed cannot redirect after OAuth2 success.");
            return;
        }

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        // email was normalised into attributes by CustomOAuth2UserService
        assert oAuth2User != null;
        String email = (String) oAuth2User.getAttributes().get("email");

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("OAuth2 user not found in DB after successful login: " + email));

        // reuse your existing JwtUitl create a minimal UserDetails adapter
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password("") // OAuth2 users have no password
                .authorities(user.getRole().name())
                .build();

        String jwt = jwtUtil.generatedToken(userDetails, user.getRole());

        log.info("OAuth2 login successful for [{}] via [{}]", email, user.getProvider());

        // redirect the user's browser to the frontend callback with the jwt
        String targetUrl = UriComponentsBuilder
                .fromUriString(defaultFrontendRedirectUri)
                .queryParam("token", jwt)
                .build().toUriString();

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}