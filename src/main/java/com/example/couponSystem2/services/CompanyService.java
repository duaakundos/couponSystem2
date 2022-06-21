package com.example.couponSystem2.services;


import com.example.couponSystem2.entities.Company;
import com.example.couponSystem2.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    public void deleteAll() {
        companyRepository.deleteAll();
    }

    public Company addCompany(Company company) {
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
        return companyRepository.findById(companyID);
    }

    public int getCompanyID(String email, String password) {
        return companyRepository.getCompanyID(email, password);
    }

    public void deleteCompany(int companyID) {
        companyRepository.deleteById(companyID);
    }
}
