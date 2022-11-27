package com.example.couponSystem2.myException.enums;

import com.example.couponSystem2.myException.ErrorDetails;

public enum CompanyEnumException implements ErrorDetails {
//    EMAIL_AND_PASSWORD_ALREADY_EXIST_IN_SYSTEM,
//    COMPANY_ID_NOT_FOUND,
    COMPANY_NOT_FOUND("Company Not Found"),
//    COMPANY_ALREADY_EXISTS,
    CANT_UPDATE_COMPANY_ID("You cannot update company ID"),
    COMPANY_NAME_OR_EMAIL_ALREADY_EXISTS("Company name or email already exists");
//    GET_ALL_COMPANIES_LIST_BY_CATEGORY_IS_EMPTY,
//    GET_ALL_COMPANIES_LIST_BY_MAX_PRICE_IS_EMPTY,
//    GET_ALL_COMPANIES_LIST_IS_EMPTY

    private final String text;
    CompanyEnumException(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
