package com.interviewmate.authservice.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandling {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandling.class);
}
