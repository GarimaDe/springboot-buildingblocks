package com.example.springboottutorial.springboot100steps.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.Set;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //MethodArgumentNotValidException
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        CustomErrorDetails customErrorDetails = new CustomErrorDetails("From MethodArgumentNotValidException",new Date()
                , ex.getLocalizedMessage());
        return new ResponseEntity<Object>(customErrorDetails,HttpStatus.BAD_REQUEST);
    }

    //HttpRequestMethodNotSupportedException
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrorDetails customErrorDetails = new CustomErrorDetails("Method not allowed. From HttpRequestMethodNotSupportedException",new Date()
                , ex.getLocalizedMessage());
        return new ResponseEntity<Object>(customErrorDetails,HttpStatus.METHOD_NOT_ALLOWED);
    }
    //UserNameNotFoundException
    //When this error occurs this method will get executed.
    @ExceptionHandler(UserNameNotFoundException.class)
    public final ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException ex, WebRequest request)
    {
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(ex.getMessage(),new Date()
                , request.getDescription(false));
        return new ResponseEntity<Object>(customErrorDetails,HttpStatus.NOT_FOUND);

    }

    //ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request)
    {
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(ex.getMessage(),new Date()
                , request.getDescription(false));
        return new ResponseEntity<Object>(customErrorDetails,HttpStatus.NOT_FOUND);

    }
}
