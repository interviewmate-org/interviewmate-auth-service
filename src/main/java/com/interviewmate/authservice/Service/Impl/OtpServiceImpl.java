package com.interviewmate.authservice.Service.Impl;

import com.interviewmate.authservice.Payload.Response.ApiResponse;
import com.interviewmate.authservice.Service.OtpService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {
    @Override
    public ApiResponse<?> verifyOtp(String otp) {
        return null;
    }

    @Override
    public ApiResponse<?> resendOtp(HttpServletRequest request) {
        return null;
    }
}
