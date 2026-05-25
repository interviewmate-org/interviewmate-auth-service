package com.interviewmate.authservice.payload.response;

import java.time.LocalDateTime;

public record SuccessResponse(
        boolean success,
        String message,
        LocalDateTime timestamp
) {}