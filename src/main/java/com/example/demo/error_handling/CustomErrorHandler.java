package com.example.demo.error_handling;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ConstraintViolationException.class)
    private ResponseEntity<Object> handleSQLDataInsertException(Exception exception, WebRequest request) {
        return handleExceptionInternal(exception, "This username already exists.", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    @ExceptionHandler(value = UserNotFoundException.class)
    private ResponseEntity<Object> userNotFoundException(Exception e, WebRequest webRequest){
        return handleExceptionInternal(e, "User Not Found", new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
    }
}