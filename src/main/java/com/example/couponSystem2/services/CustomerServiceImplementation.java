package com.example.couponSystem2.services;

import com.example.couponSystem2.entities.Category;
import com.example.couponSystem2.entities.Coupon;
import com.example.couponSystem2.entities.Customer;
import com.example.couponSystem2.myException.CouponSystemException;
import com.example.couponSystem2.myException.enums.CustomerEnumExceptions;
import com.example.couponSystem2.repositories.CompanyRepository;
import com.example.couponSystem2.repositories.CouponRepository;
import com.example.couponSystem2.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CustomerServiceImplementation extends ClientService implements CustomerService{
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

    }

    @Override
    public List<Coupon> getCustomerCoupons() {
        return null;
    }

    @Override
    public List<Coupon> getCustomerCouponByCategory(Category category) {
        return null;
    }

    @Override
    public List<Coupon> getCustomerCouponByMaxPrice(double maxPrice) {
        return null;
    }

    @Override
    public Customer getCustomerDetails() {
        Customer customer = customerRepository.findById(customerID).get();
        customer.setCoupons(getCustomerCoupons());
        return customer;
    }
}
