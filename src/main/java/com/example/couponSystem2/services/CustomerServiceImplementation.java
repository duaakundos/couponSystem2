package com.example.couponSystem2.services;

import com.example.couponSystem2.entities.Category;
import com.example.couponSystem2.entities.Coupon;
import com.example.couponSystem2.entities.Customer;
import com.example.couponSystem2.myException.CouponSystemException;
import com.example.couponSystem2.myException.enums.CouponEnumException;
import com.example.couponSystem2.myException.enums.CustomerEnumExceptions;
import com.example.couponSystem2.repositories.CompanyRepository;
import com.example.couponSystem2.repositories.CouponRepository;
import com.example.couponSystem2.repositories.CustomerRepository;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Scope("prototype")
public class CustomerServiceImplementation extends ClientService implements CustomerService {
    private int customerID;


    public CustomerServiceImplementation(CompanyRepository companyRepository, CustomerRepository customerRepository, CouponRepository couponRepository) {
        super(companyRepository, customerRepository, couponRepository);
    }


    @Override
    public boolean login(String email, String password) throws SQLException, InterruptedException {
        if (!customerRepository.existsCustomerByEmailAndPassword(email, password)) {
            throw new CouponSystemException(CustomerEnumExceptions.CUSTOMER_NOT_FOUND);
        }
        this.customerID = customerRepository.getCustomerID(email, password);
        return true;
    }

    @Override
    public void purchaseCoupon(Coupon coupon) {
        boolean isCustomerHasCoupon = couponRepository.isCustomerHasCoupon(customerID, coupon.getId()) == null ? false : true;
        if (coupon.getAmount() == 0) {
            throw new CouponSystemException(CouponEnumException.COUPON_OUT_OF_STOCK);
        } else if (coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
            throw new CouponSystemException(CouponEnumException.COUPON_IS_EXPIRED);
        } else if (isCustomerHasCoupon) {
            throw new CouponSystemException(CouponEnumException.CANT_PURCHASE_THE_SAME_COUPON_MORE_THAN_ONCE);
        }
        couponRepository.addCouponPurchase(customerID, coupon.getId());
        coupon.setAmount(coupon.getAmount() - 1);
        couponRepository.updateCoupon( coupon.getTitle(),
                coupon.getDescription(),
                coupon.getStartDate(),
                coupon.getEndDate(),
                coupon.getAmount(),
                coupon.getPrice(),
                coupon.getImage(),
                coupon.getId());
    }

    @Override
    public List<Coupon> getCustomerCoupons() {
        List<Coupon> PurchasedCouponsByCustomerList = couponRepository.getPurchasedCouponsByCustomer(customerID);
        if (PurchasedCouponsByCustomerList.isEmpty()) {
            System.out.println("Get all customers coupons list is empty");
        }
        return PurchasedCouponsByCustomerList;
    }

    @Override
    public List<Coupon> getCustomerCouponByCategory(Category category) {
        List<Coupon> getCustomerCouponByCategory = couponRepository.getAllCouponsByCustomerByCategory(customerID, category.getValue());
        if (getCustomerCouponByCategory.isEmpty()) {
            System.out.println("Get all customers coupons list by category is empty");
        }
        return getCustomerCouponByCategory;
    }

    @Override
    public List<Coupon> getCustomerCouponByMaxPrice(double maxPrice) {
        List<Coupon> getCustomerCouponByMaxPrice = couponRepository.getAllCouponsByCustomerByMaxPrice(customerID, maxPrice);
        if (getCustomerCouponByMaxPrice.isEmpty()) {
            System.out.println("Get all customers coupons list by max price is empty");
        }
        return getCustomerCouponByMaxPrice;
    }

    @Override
    public Customer getCustomerDetails() {
        Customer customer = customerRepository.findById(customerID).get();
        customer.setCoupons(getCustomerCoupons());
        return customer;
    }

    @Override
    public List<Coupon> getAllCoupons() {
        List<Coupon> getAllCoupons = couponRepository.findAll();
        return getAllCoupons;
    }
}
