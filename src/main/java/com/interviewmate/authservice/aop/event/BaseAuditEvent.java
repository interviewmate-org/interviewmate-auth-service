package com.interviewmate.authservice.aop.event;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseAuditEvent {

    private String eventId;        // auto-generated UUID
    private String traceId;        // from request header (X-Trace-Id)
    private String service;        // always "auth-service"
    private String eventType;      // e.g. API_ENTRY, LOGIN_FAILED
    private LocalDateTime timestamp;

    // Called by subclasses to auto-populate common fields
    protected static void applyDefaults(BaseAuditEvent event, String traceId, String eventType) {
        event.setEventId(UUID.randomUUID().toString());
        event.setTraceId(traceId);
        event.setService("auth-service");
        event.setEventType(eventType);
        event.setTimestamp(LocalDateTime.now());
    }
}
