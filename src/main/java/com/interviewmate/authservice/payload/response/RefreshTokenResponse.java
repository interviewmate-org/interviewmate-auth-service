package com.interviewmate.authservice.payload.response;

public record RefreshTokenResponse(
        String accessToken,
        String refreshToken
) {
}
