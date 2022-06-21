package com.example.couponSystem2.repositories;

import com.example.couponSystem2.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {
}
