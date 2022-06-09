package com.example.couponSystem2.services;


import com.example.couponSystem2.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class companyService {

    @Autowired
    CompanyRepository companyRepository;
}
