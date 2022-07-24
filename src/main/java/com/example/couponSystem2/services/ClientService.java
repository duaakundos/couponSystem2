package com.example.couponSystem2.services;

import com.example.couponSystem2.repositories.CompanyRepository;
import com.example.couponSystem2.repositories.CouponRepository;
import com.example.couponSystem2.repositories.CustomerRepository;
import com.sun.istack.NotNull;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public abstract class ClientService {
    //    @Autowired
    protected final CompanyRepository companyRepository;
    //    @Autowired
    protected final CustomerRepository customerRepository;
    //    @Autowired
    protected final CouponRepository couponRepository;
    ;

    public abstract boolean login(String email, String password) throws SQLException, InterruptedException;

}
