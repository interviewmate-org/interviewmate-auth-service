package com.interviewmate.authservice.filter;

import com.interviewmate.authservice.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String jwt = extractJwt(request);

        if (jwt != null) {
            try {
                authenticateRequest(jwt, request);
            } catch (Exception e) {
                log.error("JWT authentication error: {}", e.getMessage());
                writeUnauthorizedResponse(response, e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }

    private String extractJwt(HttpServletRequest request) {
        String jwt = extractFromAuthorizationHeader(request);
        if (jwt == null) {
            jwt = extractFromCookies(request);
        }
        return jwt;
    }

    private String extractFromAuthorizationHeader(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    private String extractFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if ("jwt".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    private void authenticateRequest(String jwt, HttpServletRequest request) {
        String email = jwtUtil.extractEmail(jwt);
        String roleString = jwtUtil.extractRole(jwt);

        log.debug("Role from JWT: {}", roleString);

        if (email == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            return;
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        if (Boolean.FALSE.equals(jwtUtil.validatedToken(jwt, userDetails))) {
            return;
        }

        List<SimpleGrantedAuthority> authorities = parseAuthorities(roleString);
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    private List<SimpleGrantedAuthority> parseAuthorities(String roleString) {
        if (roleString.isBlank()) {
            return Collections.emptyList();
        }
        return Arrays.stream(roleString.split(","))
                .map(String::trim)
                .map(this::ensureRolePrefix)
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    private String ensureRolePrefix(String role) {
        if (role.startsWith("ROLE_")) {
            return role;
        }
        return "ROLE_" + role;
    }

    private void writeUnauthorizedResponse(HttpServletResponse response, String message) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        String json = """
                {
                    "error": "UNAUTHORIZED",
                    "message": "%s"
                }
                """.formatted(message);
        response.getWriter().write(json);
    }
}