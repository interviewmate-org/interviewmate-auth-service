package com.interviewmate.authservice.dto;

import com.interviewmate.authservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenDTO {
    private UUID id;
    private String tokenHash;
    private User userId;
    private LocalDateTime expiryAt;
    private LocalDateTime createdAt;
    private boolean revoked = false;
    private String replacedByToken;
}
