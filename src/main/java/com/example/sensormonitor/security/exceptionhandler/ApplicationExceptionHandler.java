package com.example.sensormonitor.security.exceptionhandler;

import com.example.sensormonitor.exception.ApplicationDuplicateException;
import com.example.sensormonitor.exception.ApplicationNotFoundException;
import com.example.sensormonitor.exception.ApplicationNotValidDataException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.example.sensormonitor.exception.ErrorMessages.ACCESS_FORBIDDEN_ERR;
import static com.example.sensormonitor.exception.ErrorMessages.INTERNAL_SERVER_ERR;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ApplicationNotValidDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApplicationErrorObject handleNotValidDataException(ApplicationNotValidDataException e) {
        String errorMessage = e.getNotCorrectObject() instanceof BindingResult ?
                createBindingResultErrorMessage((BindingResult) e.getNotCorrectObject()) :
                createErrorMessage(e.getMessage(), e.getNotCorrectObject());

        return new ApplicationErrorObject(HttpStatus.BAD_REQUEST.value(), errorMessage);
    }

    @ExceptionHandler(ApplicationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApplicationErrorObject handleNotFoundException(ApplicationNotFoundException e) {
        String errorMessage = createErrorMessage(e.getMessage(), e.getObject());

        return new ApplicationErrorObject(HttpStatus.NOT_FOUND.value(), errorMessage);
    }

    @ExceptionHandler(ApplicationDuplicateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApplicationErrorObject handleDuplicateException(ApplicationDuplicateException e) {
        String errorMessage = createErrorMessage(e.getMessage(), e.getObject());

        return new ApplicationErrorObject(HttpStatus.CONFLICT.value(), errorMessage);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApplicationErrorObject handleAccessDeniedException(AccessDeniedException e) {
        return new ApplicationErrorObject(HttpStatus.FORBIDDEN.value(), ACCESS_FORBIDDEN_ERR);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApplicationErrorObject handleInternalServerError(Exception e) {
        return new ApplicationErrorObject(HttpStatus.INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERR);
    }

    private String createErrorMessage(String errorMessage, Object notCorrectData) {
        return String.format(errorMessage, notCorrectData);
    }

    private String createBindingResultErrorMessage(BindingResult bindingResult) {
        return bindingResult.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .reduce("", (s, str) -> String.join(" ", s, str));
    }
}