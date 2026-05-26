package com.interviewmate.authservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "refresh_tokens",indexes = {
        @Index(name = "refresh_token_token_hash_idx", columnList = "token_hash",unique = true),
        @Index(name = "refresh_token_user_id_idx", columnList = "user_id")
})
@Data
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String tokenHash;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User userId;

    @Column(nullable = false)
    private LocalDateTime expiryAt;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private boolean revoked = false;

    private String replacedByToken;
}