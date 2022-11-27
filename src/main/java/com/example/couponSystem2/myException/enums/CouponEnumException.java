package com.example.couponSystem2.myException.enums;

import com.example.couponSystem2.myException.ErrorDetails;

public enum CouponEnumException implements ErrorDetails {
//    COUPON_ID_NOT_FOUND,
    COUPON_TITLE_ALREADY_EXIST("Coupon title already exists"),
    COUPON_NOT_FOUND("Coupon not found"),
    CANT_PURCHASE_THE_SAME_COUPON_MORE_THAN_ONCE("Purchasing twice isn't possible"),
    COUPON_OUT_OF_STOCK("Sorry :( coupon is out of stock"),
    COUPON_IS_EXPIRED("This coupon is expired");
//    GET_ALL_COUPONS_LIST_IS_EMPTY,
//    GET_ALL_COMPANYS_COUPONS_LIST_IS_EMPTY,
//    GET_ALL_COMPANYS_COUPONS_LIST_BY_CATEGORY_IS_EMPTY,
//    GET_ALL_COPMANYS_COUPONS_LIST_BY_MAX_PRICE_IS_EMPTY,
//    GET_ALL_CUSTOMERS_COUPONS_LIST_IS_EMPTY,
//    GET_ALL_CUSTOMERS_COUPONS_LIST_BY_CATEGORY_IS_EMPTY,
//    GET_ALL_CUSTOMERS_COUPONS_LIST_BY_MAX_PRICE_IS_EMPTY

    private final String text;
    CouponEnumException(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
