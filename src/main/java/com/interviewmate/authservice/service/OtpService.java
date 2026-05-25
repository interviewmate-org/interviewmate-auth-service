package com.interviewmate.authservice.service;

import com.interviewmate.authservice.payload.Response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface OtpService {
    ApiResponse<?> verifyOtp(String otp);

    ApiResponse<?> resendOtp(HttpServletRequest request);
}
