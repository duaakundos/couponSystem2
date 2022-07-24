package com.example.couponSystem2.myException;

public class CouponSystemException extends RuntimeException{
    private ErrorDetails errorDetails;


    public  CouponSystemException(ErrorDetails errorDetails){
        System.out.println("Duaa");
        System.out.println("errorDetails: "+errorDetails);
        System.out.println("this.errorDetails: "+this.errorDetails);
        this.errorDetails = errorDetails;
        System.out.println("After this.errorDetails: "+this.errorDetails);
    }

    public ErrorDetails getErrorDetails() {
        return errorDetails;
    }
}
