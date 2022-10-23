package com.example.couponSystem2.controllers;


import com.example.couponSystem2.controllers.main.AdminController;
import com.example.couponSystem2.myException.AuthorizationException;
import com.example.couponSystem2.myException.CouponSystemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = {AdminController.class})
@RestController
public class AdminControllerAdvise {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorDetails> handle(Exception e) {
        ErrorDetails error = new ErrorDetails("Custom Error", e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {CouponSystemException.class})
    public ResponseEntity<ErrorDetails> handle(CouponSystemException e) {
        System.out.println(e.getErrorDetails());
        ErrorDetails error = new ErrorDetails("Custom Error", e.getErrorDetails().toString());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {AuthorizationException.class})
    public ResponseEntity<ErrorDetails> handle(AuthorizationException e) {
        System.out.println("here tomer");
        System.out.println(e.getMessage());
        ErrorDetails error = new ErrorDetails("Custom Error", e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
