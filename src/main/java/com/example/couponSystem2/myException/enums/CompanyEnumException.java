package com.example.couponSystem2.myException.enums;

import com.example.couponSystem2.myException.ErrorDetails;

public enum CompanyEnumException implements ErrorDetails {
    EMAIL_AND_PASSWORD_ALREADY_EXIST_IN_SYSTEM,
    COMPANY_ID_NOT_FOUND,
    COMPANY_NOT_FOUND,
    COMPANY_ALREADY_EXISTS,
    CANT_UPDATE_COMPANY_ID,
    COMPANY_NAME_OR_EMAIL_ALREADY_EXISTS,
    GET_ALL_COMPANIES_LIST_BY_CATEGORY_IS_EMPTY,
    GET_ALL_COMPANIES_LIST_BY_MAX_PRICE_IS_EMPTY,
    GET_ALL_COMPANIES_LIST_IS_EMPTY
}
