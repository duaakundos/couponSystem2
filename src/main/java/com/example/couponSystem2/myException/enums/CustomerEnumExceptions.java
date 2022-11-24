package com.example.couponSystem2.myException.enums;

import com.example.couponSystem2.myException.ErrorDetails;

public enum CustomerEnumExceptions implements ErrorDetails {
//    EMAIL_AND_PASSWORD_ALREADY_EXIST_IN_SYSTEM,
//    CUSTOMER_ID_NOT_FOUND,
    CUSTOMER_NOT_FOUND("Customer not found"),
    CUSTOMER_ALREADY_EXISTS("Customer already exists");
//    GET_ALL_CUSTOMERS_LIST_IS_EMPTY;

    private final String text;
    CustomerEnumExceptions(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
