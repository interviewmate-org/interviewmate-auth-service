package com.interviewmate.authservice.utils;



/**
 * Utility to mask sensitive data before it enters any audit event or log.
 * NEVER log raw passwords, tokens, or emails.
 */
public final class MaskingUtil {

    private MaskingUtil() {}

    /**
     * Masks email: john.doe@example.com -> j*****e@example.com
     */
    public static String maskEmail(String email) {
        if (email == null || !email.contains("@")) return "***";
        String[] parts = email.split("@");
        String local = parts[0];
        if (local.length() <= 2) return "**@" + parts[1];
        return local.charAt(0) + "*****" + local.charAt(local.length() - 1) + "@" + parts[1];
    }

    /**
     * Masks JWT / bearer token: shows first 8 chars then ****
     */
    public static String maskToken(String token) {
        if (token == null || token.length() < 8) return "***";
        return token.substring(0, 8) + "****";
    }

    /**
     * Fully redacts a value — used for passwords, OTPs, secrets.
     */
    public static String redact(String value) {
        return "[REDACTED]";
    }
}