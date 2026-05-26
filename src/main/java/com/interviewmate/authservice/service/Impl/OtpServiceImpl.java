package com.interviewmate.authservice.service.Impl;

import com.interviewmate.authservice.dto.UserDTO;
import com.interviewmate.authservice.payload.request.ForgetPasswordRequest;
import com.interviewmate.authservice.payload.request.RefreshTokenRequest;
import com.interviewmate.authservice.payload.response.ApiResponse;
import com.interviewmate.authservice.payload.request.LoginRequest;
import com.interviewmate.authservice.payload.response.RefreshTokenResponse;
import com.interviewmate.authservice.repository.RefreshTokenRepository;
import com.interviewmate.authservice.repository.UserRepository;
import com.interviewmate.authservice.service.AuthService;
import com.interviewmate.authservice.service.OtpService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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