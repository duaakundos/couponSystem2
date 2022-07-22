package com.example.couponSystem2.myException.enums;

import com.example.couponSystem2.myException.ErrorDetails;

public enum CouponEnumException implements ErrorDetails {
    COUPON_ID_NOT_FOUND,
    COUPON_TITLE_ALREADY_EXIST,
    COUPON_NOT_FOUND,
    CANT_PURCHASE_THE_SAME_COUPON_MORE_THAN_ONCE,
    COUPON_OUT_OF_STOCK,
    COUPON_IS_EXPIRED,
    GET_ALL_COUPONS_LIST_IS_EMPTY,
    GET_ALL_COMPANYS_COUPONS_LIST_IS_EMPTY,
    GET_ALL_COMPANYS_COUPONS_LIST_BY_CATEGORY_IS_EMPTY,
    GET_ALL_COPMANYS_COUPONS_LIST_BY_MAX_PRICE_IS_EMPTY,
    GET_ALL_CUSTOMERS_COUPONS_LIST_IS_EMPTY,
    GET_ALL_CUSTOMERS_COUPONS_LIST_BY_CATEGORY_IS_EMPTY,
    GET_ALL_CUSTOMERS_COUPONS_LIST_BY_MAX_PRICE_IS_EMPTY
}