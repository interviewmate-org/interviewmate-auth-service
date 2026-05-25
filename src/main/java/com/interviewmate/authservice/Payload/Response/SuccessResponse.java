package com.interviewmate.authservice.Payload.Response;

import java.time.LocalDateTime;

public record SuccessResponse(
        boolean success,
        String message,
        LocalDateTime timestamp
) {}