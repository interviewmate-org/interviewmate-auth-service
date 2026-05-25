package com.interviewmate.authservice.payload.request;


public record LoginRequest(
        String email,
        String password
) {}
