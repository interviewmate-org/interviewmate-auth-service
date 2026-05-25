package com.interviewmate.authservice.controller;

import com.interviewmate.authservice.dto.UserDTO;
import com.interviewmate.authservice.payload.Request.LoginRequest;
import com.interviewmate.authservice.payload.Response.ApiResponse;
import com.interviewmate.authservice.service.AdminService;
import com.interviewmate.authservice.service.OtpService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v2/admin/")
@PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
public class AdminController {
    private final AdminService adminService;
    private final OtpService otpService;

    @GetMapping("auth/users")
    public ResponseEntity<ApiResponse<List<UserDTO>>> getAllNormalUsersOnly(
            @RequestBody LoginRequest loginRequest){
        /*Get all normal users*/
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllNormalUsers(loginRequest));
    }

    @PutMapping("lock-user/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> lockUserById(@PathVariable String id){
        /*Lock User by ID*/
        return ResponseEntity.status(HttpStatus.OK).body(adminService.lockUserById(id));
    }

    @PutMapping("unlock-user/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> unlockUserById(@PathVariable String id){
        /*Unlock User by ID*/
        return ResponseEntity.status(HttpStatus.OK).body(adminService.unlockUserById(id));
    }

    @PutMapping("change-role/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> changeRoleById(@PathVariable String id){
        /*Unlock User by ID*/
        return ResponseEntity.status(HttpStatus.OK).body(adminService.changeRoleById(id));
    }

    @PutMapping("disable-mfa/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> disableMFAAdminById(@PathVariable String id){
        /*Disable that Admin by ID*/
        return ResponseEntity.status(HttpStatus.OK).body(adminService.disableMFAAdminById(id));
    }

    @PostMapping("revoke-token/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> revokeUserRefreshTokenById(@PathVariable String id){
        /*Revoke refresh token for the user by ID*/
        return ResponseEntity.status(HttpStatus.OK).body(adminService.revokeUserRefreshTokenById(id));
    }

    @GetMapping("locked-users")
    public ResponseEntity<ApiResponse<List<UserDTO>>> getAllLockedUsers(){
        /*Locked User*/
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllLockedUsers());
    }

    @PutMapping("disable-user/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> disableUserById(@PathVariable String id){
        /*Disable Users By Id*/
        return ResponseEntity.status(HttpStatus.OK).body(adminService.disableUserById(id));
    }

    @PutMapping("enable-user/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> enableUserById(@PathVariable String id){
        /*Disable Users By Id*/
        return ResponseEntity.status(HttpStatus.OK).body(adminService.disableMFAUserById(id));
    }

    @GetMapping("active-users")
    public ResponseEntity<ApiResponse<List<UserDTO>>> getAllActiveUsers(){
        /*Active User whose refresh tokens are not revoked*/
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllActiveUsers());
    }
}
