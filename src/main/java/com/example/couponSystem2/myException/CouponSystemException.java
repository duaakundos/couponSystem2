package com.example.couponSystem2.myException;

public class CouponSystemException extends RuntimeException{
    private ErrorDetails errorDetails;


    public  CouponSystemException(ErrorDetails errorDetails){
        this.errorDetails = errorDetails;
    }

    public ErrorDetails getErrorDetails() {
        return errorDetails;
    }
}
