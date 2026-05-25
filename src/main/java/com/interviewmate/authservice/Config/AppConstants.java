package com.interviewmate.authservice.Config;

import java.util.UUID;

public class AppConstants {
    public static final String[] AUTH_PUBLIC_URL = {
            "/auth/register", "/auth/login",  "/auth/refresh", "/auth/logout", "/auth/forget-password",
            "/auth/reset-password","/auth/verify-otp", "/auth/resend-otp",
            "/oauth2/authorization/google","/oauth2/authorization/github",
            "/login/oauth2/code/google","/login/oauth2/code/github"
    };

    public static UUID parseUUID(String uuid) {
        return UUID.fromString(uuid);
    }
}
