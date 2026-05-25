package com.interviewmate.authservice.Payload.Response;

public record RefreshTokenResponse(
        String accessToken,
        String refreshToken
) {
}
