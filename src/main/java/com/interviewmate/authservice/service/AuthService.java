package com.interviewmate.authservice.service;

import com.interviewmate.authservice.dto.UserDTO;
import com.interviewmate.authservice.payload.request.LoginRequest;
import com.interviewmate.authservice.payload.response.ApiResponse;

import java.util.List;

public interface AdminService {
    ApiResponse<List<UserDTO>> getAllNormalUsers(LoginRequest loginRequest);

    ApiResponse<UserDTO> lockUserById(String id);

    ApiResponse<UserDTO> unlockUserById(String id);

    ApiResponse<UserDTO> revokeUserRefreshTokenById(String id);

    ApiResponse<UserDTO> disableMFAAdminById(String id);

    ApiResponse<UserDTO> disableMFAUserById(String id);

    ApiResponse<List<UserDTO>> getAllLockedUsers();

    ApiResponse<List<UserDTO>> getAllActiveUsers();

    ApiResponse<UserDTO> changeRoleById(String id);

    ApiResponse<UserDTO> disableUserById(String id);
}