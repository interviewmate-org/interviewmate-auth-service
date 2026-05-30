package com.interviewmate.authservice.filter;



import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.interviewmate.authservice.utils.TraceContext;

import java.io.IOException;
import java.util.UUID;

@Component
@Order(1)   // Must run before any other filter
public class TraceIdFilter implements Filter {

    private static final String TRACE_HEADER = "X-Trace-Id";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String traceId = httpRequest.getHeader(TRACE_HEADER);

        // If gateway didn't send one, generate a fallback (shouldn't happen in prod)
        if (traceId == null || traceId.isBlank()) {
            traceId = UUID.randomUUID().toString();
        }

        TraceContext.set(traceId);
        try {
            chain.doFilter(request, response);
        } finally {
            TraceContext.clear();   // CRITICAL: prevent ThreadLocal memory leak
        }
    }
}