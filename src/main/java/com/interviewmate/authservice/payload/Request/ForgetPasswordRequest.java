package com.interviewmate.authservice.payload.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ForgetPasswordRequest(
        @Email @NotBlank String email
) {
}
