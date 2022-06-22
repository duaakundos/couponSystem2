package com.example.couponSystem2.services;

import com.example.couponSystem2.entities.Category;
import com.example.couponSystem2.entities.Coupon;
import com.example.couponSystem2.repositories.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class CouponService {

    @Autowired
    CouponRepository couponRepository;

    public void deleteAll() {
        couponRepository.deleteAll();
    }

    public boolean isCouponExists(Coupon coupon) {
        return couponRepository.existsCouponByCompany_IdAndTitle(coupon.getCompany().getId(), coupon.getTitle());
    }

    public Coupon addCoupon(Coupon coupon) {
        Coupon couponFromDB = couponRepository.save(coupon);
        return couponFromDB;
    }

    public void updateCoupon(Coupon coupon) {
    }

    public void deleteCoupon(int couponID) {
        couponRepository.deleteById(couponID);
    }

    public ArrayList<Coupon> getAllCoupons() {
        return new ArrayList<>(couponRepository.findAll());
    }

    public Coupon getOneCoupon(int couponID) {
        return couponRepository.findById(couponID);
    }

    public void addCouponPurchase(int customerID, int couponID) {
        couponRepository.addCouponPurchase(customerID, couponID);
    }

    public void deleteCouponPurchase(int customerID, int couponID) {
    }

    public ArrayList<Coupon> getPurchasedCouponsByCustomer(int customerID) {
        return couponRepository.getPurchasedCouponsByCustomer(customerID);
    }

    public ArrayList<Coupon> getAllCouponsByCustomerByCategory(int customerID, Category category) {
        System.out.println("here:  " + category);
        System.out.println("here:  " + category.getValue());
        return couponRepository.getAllCouponsByCustomerByCategory( customerID, category.getValue());
    }

    public ArrayList<Coupon> getAllCouponsByCustomerByMaxPrice(int customerID, double maxPrice) {
        return null;
    }

    public boolean isCustomerHasCoupon(int customerID, int couponID) {
        return false;
    }

    public ArrayList<Coupon> getAllCouponsByCompany(int companyID) {
        return couponRepository.findAllByCompany_Id(companyID);
    }

    public ArrayList<Coupon> getAllCouponsByCompanyByCategory(int companyID, Category category) {
        return couponRepository.findAllByCompany_IdAndCategory(companyID, category);
    }

    public ArrayList<Coupon> getAllCouponsByCompanyByMaxPrice(int companyID, double maxPrice) {
        return couponRepository.findAllByCompany_IdAndPriceLessThan(companyID, maxPrice);
    }

    public void deleteExpiredCoupons() {
        int numberOfDeletedRows = couponRepository.deleteAllByEndDateLessThan(Date.valueOf(LocalDate.now()));
        System.out.println("number of deleted rows: " + numberOfDeletedRows);
    }

}
