package com.example.couponSystem2.services;

import com.example.couponSystem2.entities.Category;
import com.example.couponSystem2.entities.Coupon;
import com.example.couponSystem2.entities.Customer;

import java.util.List;

public interface CustomerService {
    void purchaseCoupon(Coupon coupon);
    List<Coupon> getCustomerCoupons();
    List<Coupon> getCustomerCouponByCategory(Category category);
    List<Coupon> getCustomerCouponByMaxPrice(double maxPrice);
    Customer getCustomerDetails();
    List<Coupon> getAllCoupons();

}
