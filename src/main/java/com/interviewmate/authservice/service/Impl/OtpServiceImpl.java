package com.interviewmate.authservice.service.Impl;

import com.interviewmate.authservice.payload.response.ApiResponse;
import com.interviewmate.authservice.repository.RefreshTokenRepository;
import com.interviewmate.authservice.repository.UserRepository;
import com.interviewmate.authservice.service.OtpService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final RefreshTokenRepository refreshTokenRepository;
    private final OtpService otpService;


    @Override
    public ApiResponse<?> verifyOtp(String otp) {
        return null;
    }

    @Override
    public ApiResponse<?> resendOtp(HttpServletRequest request) {
        return null;
    }
}