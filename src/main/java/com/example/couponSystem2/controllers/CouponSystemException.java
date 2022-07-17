package com.example.couponSystem2.controllers;

public class CouponSystemException extends RuntimeException{
    private ErrorDetails errorDetails;


    public  CouponSystemException(ErrorDetails errorDetails){
        this.errorDetails = errorDetails;
    }

    public ErrorDetails getErrorDetails() {
        return errorDetails;
    }
}
