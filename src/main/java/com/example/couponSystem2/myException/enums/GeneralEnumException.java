package com.example.couponSystem2.myException.enums;

import com.example.couponSystem2.myException.ErrorDetails;

public enum GeneralEnumException implements ErrorDetails {
    INVALID_PASSWORD_OR_EMAIL("Invalid Password or Email, Try again"),
    SESSION_EXPIRED("Session Expired");

    private final String text;
    GeneralEnumException(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
