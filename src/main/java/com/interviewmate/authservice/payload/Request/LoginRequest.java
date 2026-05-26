package com.interviewmate.authservice.payload.Request;


public record LoginRequest(
        String email,
        String password
) {}
