package com.interviewmate.authservice.service.Impl;

import com.interviewmate.authservice.constance.AppConstants;
import com.interviewmate.authservice.dto.UserDTO;
import com.interviewmate.authservice.payload.request.LoginRequest;
import com.interviewmate.authservice.payload.response.ApiResponse;
import com.interviewmate.authservice.repository.RefreshTokenRepository;
import com.interviewmate.authservice.repository.UserRepository;
import com.interviewmate.authservice.service.AdminService;
import com.interviewmate.authservice.service.OtpService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final RefreshTokenRepository refreshTokenRepository;
    private final OtpService otpService;

    @Override
    public ApiResponse<List<UserDTO>> getAllNormalUsers(@RequestBody LoginRequest loginRequest) {
        return null;
    }

    @Override
    public ApiResponse<UserDTO> lockUserById(String id) {
        UUID uuid = AppConstants.parseUUID(id);
        return null;
    }

    @Override
    public ApiResponse<UserDTO> unlockUserById(String id) {
        UUID uuid = AppConstants.parseUUID(id);
        return null;
    }

    @Override
    public ApiResponse<UserDTO> revokeUserRefreshTokenById(String id) {
        UUID uuid = AppConstants.parseUUID(id);
        return null;
    }

    @Override
    public ApiResponse<UserDTO> disableMFAAdminById(String id) {
        UUID uuid = AppConstants.parseUUID(id);
        return null;
    }

    @Override
    public ApiResponse<UserDTO> disableMFAUserById(String id) {
        return null;
    }

    @Override
    public ApiResponse<List<UserDTO>> getAllLockedUsers() {
        return null;
    }

    @Override
    public ApiResponse<List<UserDTO>> getAllActiveUsers() {
        return null;
    }

    @Override
    public ApiResponse<UserDTO> changeRoleById(String id) {
        return null;
    }

    @Override
    public ApiResponse<UserDTO> disableUserById(String id) {
        return null;
    }

}