package com.example.demo.exception;


import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Para API REST
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // Para controladores web
    @ExceptionHandler(value = {Exception.class})
    public ModelAndView handleAllExceptions(Exception ex, WebRequest request) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("timestamp", new Date());
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.addObject("details", request.getDescription(false));
        return modelAndView;
    }
}


