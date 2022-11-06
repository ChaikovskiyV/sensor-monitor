package com.example.sensormonitor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ApplicationDuplicateException extends RuntimeException {
    private final transient String message;
    private final transient Object object;

    public ApplicationDuplicateException(String message, Object object) {
        this.message = message;
        this.object = object;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Object getObject() {
        return object;
    }
}