package com.example.couponSystem2.services;

import com.example.couponSystem2.repositories.CompanyRepository;
import com.example.couponSystem2.repositories.CouponRepository;
import com.example.couponSystem2.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

public abstract class ClientService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CouponRepository couponRepository;
    ;
    public abstract boolean login(String email, String password) throws SQLException, InterruptedException;

}
