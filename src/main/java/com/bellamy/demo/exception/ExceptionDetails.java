package com.bellamy.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ExceptionDetails {
    private final LocalDateTime timestamp;
    private final String message;
    private final String details;
}
