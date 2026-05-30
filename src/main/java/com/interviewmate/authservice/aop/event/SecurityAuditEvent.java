package com.interviewmate.authservice.aop.event;



import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class SecurityAuditEvent extends BaseAuditEvent {

    private String userId;
    private String email;       // masked before setting
    private String ipAddress;
    private String reason;
    private String action;      // e.g. LOGIN, LOGOUT, ROLE_CHANGE

    public static SecurityAuditEvent of(String traceId, String eventType,
                                         String userId, String maskedEmail,
                                         String ipAddress, String reason) {
        SecurityAuditEvent event = SecurityAuditEvent.builder()
                .userId(userId)
                .email(maskedEmail)
                .ipAddress(ipAddress)
                .reason(reason)
                .build();
        applyDefaults(event, traceId, eventType);
        return event;
    }
}