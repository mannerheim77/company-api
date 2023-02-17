package com.bellamy.demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e, WebRequest request) {
        ExceptionDetails details = new ExceptionDetails(LocalDateTime.now(), e.getMessage(), request.getDescription(false));
        e.printStackTrace();
        return new ResponseEntity<>(details, NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception e, WebRequest request) {
        ExceptionDetails details = new ExceptionDetails(LocalDateTime.now(), e.getMessage(), request.getDescription(false));
        e.printStackTrace();
        return new ResponseEntity<>(details, INTERNAL_SERVER_ERROR);
    }
}
