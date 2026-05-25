package com.interviewmate.authservice.service;

import com.interviewmate.authservice.dto.UserDTO;
import com.interviewmate.authservice.payload.request.ForgetPasswordRequest;
import com.interviewmate.authservice.payload.request.RefreshTokenRequest;
import com.interviewmate.authservice.payload.response.ApiResponse;
import com.interviewmate.authservice.payload.request.LoginRequest;
import com.interviewmate.authservice.payload.response.RefreshTokenResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public interface AuthService {

    UserDTO registerAdmin(UserDTO userDTO);

    ApiResponse<RefreshTokenResponse> loginAdmin(LoginRequest loginRequest);

    ApiResponse<RefreshTokenResponse> refreshToken(RefreshTokenRequest body, HttpServletRequest request, HttpServletResponse response);

    void logout(HttpServletRequest request, HttpServletResponse response);

    ApiResponse<String> forgetPassword(@Valid ForgetPasswordRequest email);

    ApiResponse<String> resetPassword(@Valid @Email @NotBlank String email);

    ApiResponse<String> changePassword(LoginRequest loginRequest, String newPassword);

    ApiResponse<String> enableMFA();

    ApiResponse<String> disableMFA();

    ApiResponse<?> getPermissionsForUser(LoginRequest loginRequest);

    ApiResponse<String> revokeToken(RefreshTokenRequest refreshTokenRequest);

    ApiResponse<?> securityEvents();
}
