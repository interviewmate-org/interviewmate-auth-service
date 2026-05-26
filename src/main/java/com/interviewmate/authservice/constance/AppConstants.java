package com.interviewmate.authservice.constance;

import java.util.UUID;

public class AppConstants {
    public static final String[] AUTH_PUBLIC_URL = {
            "/api/v2/home",
            "/api/v2/auth/register", "/api/v2/auth/login", "/api/v2/auth/refresh", "/api/v2/auth/logout", "/api/v2/auth/forget-password",
            "/api/v2/auth/reset-password", "/api/v2/auth/verify-otp", "/api/v2/auth/resend-otp",
            "/oauth2/authorization/google", "/oauth2/authorization/github",
            "/login/oauth2/code/google", "/login/oauth2/code/github"
    };

    public static UUID parseUUID(String uuid) {
        return UUID.fromString(uuid);
    }
}
