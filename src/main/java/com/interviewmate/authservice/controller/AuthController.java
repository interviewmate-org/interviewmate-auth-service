package com.interviewmate.authservice.controller;


import com.interviewmate.authservice.dto.UserDTO;
import com.interviewmate.authservice.payload.request.ForgetPasswordRequest;
import com.interviewmate.authservice.payload.request.RefreshTokenRequest;
import com.interviewmate.authservice.payload.response.ApiResponse;
import com.interviewmate.authservice.payload.request.LoginRequest;
import com.interviewmate.authservice.payload.response.RefreshTokenResponse;
import com.interviewmate.authservice.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v2/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerAdmin(@RequestBody UserDTO userDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerAdmin(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<RefreshTokenResponse>> loginAdmin(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(authService.loginAdmin(loginRequest));
    }

    @PostMapping("/refresh")
    /*After accepting refreshToken validate the token and then again generate a valid refresh token and access token*/
    public ResponseEntity<ApiResponse<RefreshTokenResponse>> refreshToken(
            @RequestBody(required = false) RefreshTokenRequest userRefreshToken,
            HttpServletResponse response,
            HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(authService.refreshToken(userRefreshToken,request,response));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response){
        authService.logout(request,response);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/forget-password")
    public ResponseEntity<ApiResponse<String>> forgetPassword(
            @RequestBody @Valid ForgetPasswordRequest email
    ){
        /*We can do it via PasswordResetOtp where we can send reset-token*/
        return ResponseEntity.status(HttpStatus.OK).body(authService.forgetPassword(email));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse<String>> resetPassword(
            @RequestBody @Valid @Email @NotBlank String email
    ){
        /*We can do it via PasswordResetOtp where we can send reset-token*/
        return ResponseEntity.status(HttpStatus.OK).body(authService.resetPassword(email));
    }

    @PostMapping("/change-password")
    public ResponseEntity<ApiResponse<String>> changePassword(
            @RequestBody LoginRequest loginRequest,
            @RequestBody String newPassword
    ){
        /*After login user can change password*/
        return ResponseEntity.status(HttpStatus.OK).body(authService.changePassword(loginRequest, newPassword));
    }

    @PostMapping("/enable-mfa")
    public ResponseEntity<ApiResponse<String>> enableMfa(){
        /*Didn't add anything add which is feasible*/
        return ResponseEntity.status(HttpStatus.OK).body(authService.enableMFA());
    }

    @PostMapping("/disable-mfa")
    public ResponseEntity<ApiResponse<String>> disableMfa(){
        /*Didn't add anything add which is feasible*/
        return ResponseEntity.status(HttpStatus.OK).body(authService.disableMFA());
    }

    /*@GetMapping("/permissions")
    public ResponseEntity<ApiResponse<?>> getPermessions(@RequestBody LoginRequest loginRequest){
        *//*Didn't add anything add which is feasible Return authenticated user roles and permissions*//*
        return ResponseEntity.status(HttpStatus.OK)
                .body(authService.getPermissionsForUser(loginRequest));
    }

    @GetMapping("/security-events")
    public ResponseEntity<ApiResponse<?>> securityEvents(){
        *//*Didn't add anything add which is feasible Return recent security-related activities for account*//*
        return ResponseEntity.status(HttpStatus.OK).body(authService.securityEvents());
    }*/

    @PostMapping("/revoke-token")
    public ResponseEntity<ApiResponse<String>> revokeToken(
            @RequestBody RefreshTokenRequest refreshTokenRequest
    ){
        /*Didn't add anything add which is feasible Return recent security-related activities for account*/
        return ResponseEntity.status(HttpStatus.OK).body(authService.revokeToken(refreshTokenRequest));
    }
}
