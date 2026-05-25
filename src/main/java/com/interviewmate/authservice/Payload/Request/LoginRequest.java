package com.interviewmate.authservice.Payload.Request;


public record LoginRequest(
        String email,
        String password
) {}
