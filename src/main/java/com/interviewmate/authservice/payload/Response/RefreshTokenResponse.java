package com.interviewmate.authservice.payload.Response;

public record RefreshTokenResponse(
        String accessToken,
        String refreshToken
) {
}
