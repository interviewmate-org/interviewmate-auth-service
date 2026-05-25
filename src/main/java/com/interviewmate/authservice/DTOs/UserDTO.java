package com.interviewmate.authservice.DTOs;

import com.interviewmate.authservice.Enum.Provider;
import com.interviewmate.authservice.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {
    private UUID id;
    private String email;
    private String image;
    private String password;
    private Role role;
    private Provider provider = Provider.LOCAL;
    private String providerId;
    private Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();
}
