package com.interviewmate.authservice.configuration;



import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Enables Spring AOP with proxy-target-class = true (CGLIB proxies).
 * This is required so AOP works on concrete classes, not just interfaces.
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AopConfig {
    // No additional beans needed — all aspects are auto-detected via @Aspect + @Component
}