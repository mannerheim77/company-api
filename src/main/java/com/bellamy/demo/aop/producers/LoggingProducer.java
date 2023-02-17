package com.bellamy.demo.aop.producers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class LoggingProducer {
    @Bean
    @Scope("prototype")
    public Logger getLoggerProxy(InjectionPoint ip) {
        Objects.requireNonNull(ip.getMethodParameter());
        return LoggerFactory.getLogger(ip.getMethodParameter().getContainingClass());
    }
}
