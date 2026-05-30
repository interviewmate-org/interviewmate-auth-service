package com.interviewmate.authservice.aop.publisher;



import com.interviewmate.authservice.aop.event.BaseAuditEvent;

public interface AuditPublisher {
    void publish(BaseAuditEvent event, String topic);
}