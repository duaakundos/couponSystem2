package com.example.couponSystem2.services;

import com.example.couponSystem2.repositories.CompanyRepository;
import com.example.couponSystem2.repositories.CouponRepository;
import com.example.couponSystem2.repositories.CustomerRepository;
import com.sun.istack.NotNull;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
//@RequiredArgsConstructor
public abstract class ClientService {
    @Autowired
//    @NonNull
    CompanyRepository companyRepository;
    @Autowired
//    @NonNull
    CustomerRepository customerRepository;
    @Autowired
//    @NonNull
    CouponRepository couponRepository;

    public abstract boolean login(String email, String password) throws SQLException, InterruptedException;

}
