package com.example.couponSystem2.services;

import com.example.couponSystem2.entities.Category;
import com.example.couponSystem2.entities.Company;
import com.example.couponSystem2.entities.Coupon;

import java.util.List;

public interface CompanyService {
    Coupon addCoupon(Coupon coupon);
    void updateCoupon(Coupon coupon);
    void deleteCoupon(Coupon coupon);
    List<Coupon> getCompanyCoupons();
    List<Coupon> getCompanyCouponsByCategory(Category category);
    List<Coupon> getCompanyCouponsMaxPrice(double maxPrice);
    Company getCompanyDetails();
}
