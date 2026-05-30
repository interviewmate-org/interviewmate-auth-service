package com.interviewmate.authservice.aop.publisher;



import com.interviewmate.authservice.aop.event.BaseAuditEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaAuditPublisher implements AuditPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void publish(BaseAuditEvent event, String topic) {
        // Async publish — never block the main request thread
        CompletableFuture<SendResult<String, Object>> future =
                kafkaTemplate.send(topic, event.getEventId(), event);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                // Log locally only — do NOT re-publish to avoid infinite loop
                log.error("[KafkaAuditPublisher] Failed to publish event {} to topic {}: {}",
                        event.getEventType(), topic, ex.getMessage());
            } else {
                log.debug("[KafkaAuditPublisher] Published {} to {} @ offset {}",
                        event.getEventType(), topic,
                        result.getRecordMetadata().offset());
            }
        });
    }
}