package com.interviewmate.authservice.Payload.Response;

import java.time.LocalDateTime;

public record ErrorResponse(
        boolean success,
        String errorCode,
        String message,
        String path,
        LocalDateTime timestamp
){}