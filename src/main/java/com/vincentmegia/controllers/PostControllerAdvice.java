package com.vincentmegia.controllers;

import com.vincentmegia.exceptions.NoApi;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@RestControllerAdvice
public class PostControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value={IOException.class})
    public ResponseEntity<String> handleIOException(){
        return new ResponseEntity<String>("An error occured reading file, please try later",
                HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return new ResponseEntity<>(new NoApi(), HttpStatus.NOT_FOUND);
    }


}
