package com.interviewmate.authservice.Service.Impl;

import com.interviewmate.authservice.DTOs.UserDTO;
import com.interviewmate.authservice.Payload.Request.ForgetPasswordRequest;
import com.interviewmate.authservice.Payload.Request.RefreshTokenRequest;
import com.interviewmate.authservice.Payload.Response.ApiResponse;
import com.interviewmate.authservice.Payload.Request.LoginRequest;
import com.interviewmate.authservice.Payload.Response.RefreshTokenResponse;
import com.interviewmate.authservice.Repository.RefreshTokenRepository;
import com.interviewmate.authservice.Repository.UserRepository;
import com.interviewmate.authservice.Service.AuthService;
import com.interviewmate.authservice.Service.OtpService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final RefreshTokenRepository refreshTokenRepository;
    private final OtpService otpService;

    @Override
    public UserDTO registerAdmin(UserDTO userDTO) {
        return null;
    }

    @Override
    public ApiResponse<RefreshTokenResponse> loginAdmin(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public ApiResponse<RefreshTokenResponse> refreshToken(RefreshTokenRequest body, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {}

    @Override
    public ApiResponse<String> forgetPassword(ForgetPasswordRequest email) {
        return null;
    }

    @Override
    public ApiResponse<String> resetPassword(String email) {
        return null;
    }

    @Override
    public ApiResponse<String> changePassword(LoginRequest loginRequest, String newPassword) {
        return null;
    }

    @Override
    public ApiResponse<String> enableMFA() {
        return null;
    }

    @Override
    public ApiResponse<String> disableMFA() {
        return null;
    }

    @Override
    public ApiResponse<?> getPermissionsForUser(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public ApiResponse<String> revokeToken(RefreshTokenRequest refreshTokenRequest) {
        return null;
    }

    @Override
    public ApiResponse<?> securityEvents() {
        return null;
    }


}
