package com.interviewmate.authservice.payload.Response;

import java.time.LocalDateTime;

public record SuccessResponse(
        boolean success,
        String message,
        LocalDateTime timestamp
) {}