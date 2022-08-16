package com.example.couponSystem2.services;

import com.example.couponSystem2.entities.Company;
import com.example.couponSystem2.entities.Customer;

import java.util.List;

public interface AdminService {
    Company addCompany(Company company);
    void updateCompany(Company company);
    void deleteCompany(Company company);
    List<Company> getAllCompanies();
    Company getOneCompany(int companyID);
    Customer addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(int customerID);
    List<Customer> getAllCustomers();
    Customer getOneCustomer(int customerID);
}
