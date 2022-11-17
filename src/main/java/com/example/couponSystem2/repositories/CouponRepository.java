package com.example.couponSystem2.repositories;

import com.example.couponSystem2.entities.Category;
import com.example.couponSystem2.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    boolean existsCouponByCompany_IdAndTitle(int company_id, String title);
//    boolean existsCouponByCompany_idAndTitle(int company_id, String title);

    public Coupon findById(int couponID);

    ArrayList<Coupon> findAllByCompany_Id(int companyID);

    ArrayList<Coupon> findAllByCompany_IdAndCategory(int companyID, Category category);

    ArrayList<Coupon> findAllByCompany_IdAndPriceLessThan(int company_id, double price);

    @Transactional
    @Modifying
    @Query(value = "insert into customer_list_of_coupons (customer_id, coupon_id) values (?1,?2) ", nativeQuery = true)
    void addCouponPurchase(int customerID, int couponID);

    @Transactional
    int deleteAllByEndDateLessThan(Date date);

    @Query(value = "select c.* from coupon c Join customer_list_of_coupons cvc on c.coupon_id = cvc.coupon_id and cvc.customer_id = ?1", nativeQuery = true)
    ArrayList<Coupon> getPurchasedCouponsByCustomer(int customerID);

    @Query(value = "select c.* from coupon c Join customer_list_of_coupons cvc on c.coupon_id = cvc.coupon_id and cvc.customer_id = ?1 and c.coupon_category_id = ?2", nativeQuery = true)
    ArrayList<Coupon> getAllCouponsByCustomerByCategory(int customerID, int category_id);

    @Query(value = "select c.* from coupon c Join customer_list_of_coupons cvc on c.coupon_id = cvc.coupon_id and cvc.customer_id = ?1 and c.coupon_price < ?2", nativeQuery = true)
    ArrayList<Coupon> getAllCouponsByCustomerByMaxPrice(int customerID, double maxPrice);

    @Transactional
    @Modifying
    @Query(value = "update coupon c set " +
            "c.coupon_title = :coupon_title, " +
            "c.coupon_description = :coupon_description, " +
            "c.start_date = :coupon_start_date, " +
            "c.end_date = :coupon_end_date, " +
            "c.coupon_amount = :coupon_amount, " +
            "c.coupon_price = :coupon_price, " +
            "c.coupon_image = :coupon_image " +
            "where c.coupon_id = :coupon_ID", nativeQuery = true)
    Integer updateCoupon(@Param("coupon_title") String coupon_title,
                         @Param("coupon_description") String coupon_description,
                         @Param("coupon_start_date") java.sql.Date start_date,
                         @Param("coupon_end_date") java.sql.Date end_date,
                         @Param("coupon_amount") int coupon_amount,
                         @Param("coupon_price") double coupon_price,
                         @Param("coupon_image") String coupon_image,
                         @Param("coupon_ID") int couponID);


    @Transactional
    @Modifying
    @Query(value = "delete from customer_list_of_coupons where customer_id = :customerID and coupon_id = :couponID", nativeQuery = true)
    void deleteCouponPurchase(@Param("customerID") int customerID, @Param("couponID") int couponID);

    // todo: couldn't figure out a way to return boolean, tried with case ^ but didn't work.
//    @Query(value = "SELECT CASE WHEN COUNT(customer_list_of_coupons) > 0 THEN true ELSE false END FROM customer_list_of_coupons cvc WHERE customer_id = :customerID and coupon_id = :couponID", nativeQuery = true)
//    boolean isCustomerHasCoupon(@Param("customerID") int customerID,@Param("couponID") int couponID);

    @Query(value = "SELECT* FROM customer_list_of_coupons where customer_id = :customerID and coupon_id = :couponID", nativeQuery = true)
    Integer isCustomerHasCoupon(@Param("customerID") int customerID, @Param("couponID") int couponID);


    @Query(value = "SELECT * FROM coupons where coupon_title = :couponTitle", nativeQuery = true)
    Coupon getCouponbyCoupon(@Param("couponTitle") String couponTitle);

}
