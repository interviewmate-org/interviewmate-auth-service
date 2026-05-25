package com.interviewmate.authservice.Payload.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {//T is Generic type Parameter
        private boolean success;
        private String message;
        private T data;
        private String errorCode;
        private String path;
        private LocalDateTime timestamp;
}