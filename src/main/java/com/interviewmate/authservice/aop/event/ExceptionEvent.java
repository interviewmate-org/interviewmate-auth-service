package com.interviewmate.authservice.aop.event;



import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ExceptionEvent extends BaseAuditEvent {

    private String exceptionClass;
    private String message;
    private String className;
    private String methodName;
    private String layer;

    public static ExceptionEvent of(String traceId, Throwable ex,
                                     String className, String methodName, String layer) {
        ExceptionEvent event = ExceptionEvent.builder()
                .exceptionClass(ex.getClass().getSimpleName())
                .message(ex.getMessage())
                .className(className)
                .methodName(methodName)
                .layer(layer)
                .build();
        applyDefaults(event, traceId, "EXCEPTION");
        return event;
    }
}