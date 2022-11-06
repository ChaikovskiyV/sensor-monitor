package com.example.sensormonitor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ApplicationNotValidDataException extends RuntimeException {
    private final transient String message;
    private final transient Object notCorrectObject;

    public ApplicationNotValidDataException(String message, Object notCorrectObject) {
        this.message = message;
        this.notCorrectObject = notCorrectObject;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Object getNotCorrectObject() {
        return notCorrectObject;
    }
}