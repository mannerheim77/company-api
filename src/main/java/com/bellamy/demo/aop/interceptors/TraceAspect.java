package com.bellamy.demo.aop.interceptors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TraceAspect {
    private final Logger logger;

    @Autowired
    public TraceAspect(Logger logger) {
        this.logger = logger;
    }

    @Around("@annotation(com.bellamy.demo.aop.interceptors.Trace)")
    public Object logMetrics(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info(joinPoint.getSignature() + " START ");
        Object proceed = joinPoint.proceed();
        logger.info(joinPoint.getSignature() + " END ");

        return proceed;
    }
}
