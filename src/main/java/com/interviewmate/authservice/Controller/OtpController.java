package com.interviewmate.authservice.Controller;

import com.interviewmate.authservice.Payload.Response.ApiResponse;
import com.interviewmate.authservice.Service.OtpService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v2/auth")
@AllArgsConstructor
public class OtpController {
    private final OtpService otpService;

    @PostMapping("/verify-otp")
    public ResponseEntity<ApiResponse<?>> verifyOtp(@RequestBody String otp){
        return ResponseEntity.status(HttpStatus.OK).body(otpService.verifyOtp(otp));
    }

    @PostMapping("/resend-otp")
    public ResponseEntity<ApiResponse<?>> resendOtp(HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(otpService.resendOtp(request));
    }
}
