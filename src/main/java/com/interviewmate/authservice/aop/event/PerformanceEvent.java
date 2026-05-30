package com.interviewmate.authservice.aop.event;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class PerformanceEvent extends BaseAuditEvent {

    private String className;
    private String methodName;
    private long executionTimeMs;
    private String layer;        // SERVICE or REPOSITORY

    public static PerformanceEvent of(String traceId, String className,
                                       String methodName, long executionTimeMs, String layer) {
        PerformanceEvent event = PerformanceEvent.builder()
                .className(className)
                .methodName(methodName)
                .executionTimeMs(executionTimeMs)
                .layer(layer)
                .build();
        applyDefaults(event, traceId, "METHOD_EXECUTION");
        return event;
    }
}