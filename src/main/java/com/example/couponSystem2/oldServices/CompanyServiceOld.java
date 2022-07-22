package com.example.couponSystem2.oldServices;


import com.example.couponSystem2.myException.CouponSystemException;
import com.example.couponSystem2.myException.enums.CompanyEnumException;
import com.example.couponSystem2.entities.Company;
import com.example.couponSystem2.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class CompanyServiceOld {

    @Autowired
    CompanyRepository companyRepository;


    public void deleteAll() {
        companyRepository.deleteAll();
    }

    public Company addCompany(Company company) {
        if (companyRepository.existsCompanyByEmailOrName(company.getEmail(), company.getName())){
            throw new CouponSystemException(CompanyEnumException.COMPANY_NAME_OR_EMAIL_ALREADY_EXISTS);
        }
        Company companyFromDB = companyRepository.save(company);
        return companyFromDB;
    }

    public void updateCompany(Company company) {
        companyRepository.updateCompany(company.getEmail(), company.getPassword(), company.getId());
    }

    public boolean isCompanyExists(String email, String password) {
        return companyRepository.existsCompanyByEmailAndPassword(email, password);
    }

    public boolean isCompanyExistsByNameOrEmail(Company company) {
        return companyRepository.existsCompanyByEmailOrName(company.getPassword(), company.getName());
    }

    public ArrayList<Company> getAllCompanies() {
        return new ArrayList<>(companyRepository.findAll());
    }

    public Company getOneCompany(int companyID) {
        // add our throw
        return companyRepository.findById(companyID).orElseThrow();
    }

    public int getCompanyID(String email, String password) {
        return companyRepository.getCompanyID(email, password);
    }

    public void deleteCompany(int companyID) {
        companyRepository.deleteById(companyID);
    }


}