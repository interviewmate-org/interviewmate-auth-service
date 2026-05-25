package com.interviewmate.authservice.Service;

import com.interviewmate.authservice.Payload.Response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;

public interface OtpService {
    ApiResponse<?> verifyOtp(String otp);

    ApiResponse<?> resendOtp(HttpServletRequest request);
}
