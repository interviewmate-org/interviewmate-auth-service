package com.interviewmate.authservice.utils;



/**
 * Thread-local holder for Trace ID propagated from the API Gateway via X-Trace-Id header.
 * Always call clear() in a finally block or filter to prevent memory leaks.
 */
public final class TraceContext {

    private static final ThreadLocal<String> TRACE_ID = new ThreadLocal<>();

    private TraceContext() {}

    public static void set(String traceId) {
        TRACE_ID.set(traceId);
    }

    public static String get() {
        return TRACE_ID.get();
    }

    public static void clear() {
        TRACE_ID.remove();
    }
}