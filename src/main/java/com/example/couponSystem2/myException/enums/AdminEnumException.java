package com.example.couponSystem2.myException.enums;

import com.example.couponSystem2.myException.ErrorDetails;

public enum AdminEnumException implements ErrorDetails {
    ADMIN_NOT_FOUND("Admin not Found");

    private final String text;
    AdminEnumException(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
