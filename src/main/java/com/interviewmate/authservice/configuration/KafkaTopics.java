package com.interviewmate.authservice.configuration;



/**
 * Central registry of all Kafka topic names used by the AOP audit pipeline.
 */
public final class KafkaTopics {

    private KafkaTopics() {}
    public static final String APPLICATION_LOGS = "${audit.kafka.topic.application-logs}";
    public static final String SECURITY_AUDIT = "${audit.kafka.topic.security-audit-logs}";
    public static final String PERFORMANCE_METRICS = "${audit.kafka.topic.performance-metrics}";
    public static final String APPLICATION_ERRORS = "${audit.kafka.topic.application-errors}";   
}