package com.example.couponSystem2.myException.enums;

import com.example.couponSystem2.myException.ErrorDetails;

public enum CustomerEnumExceptions implements ErrorDetails {
    EMAIL_AND_PASSWORD_ALREADY_EXIST_IN_SYSTEM,
    CUSTOMER_ID_NOT_FOUND,
    CUSTOMER_NOT_FOUND,
    CUSTOMER_ALREADY_EXISTS,
    GET_ALL_CUSTOMERS_LIST_IS_EMPTY;
}
