package com.example.couponSystem2.services;

import com.example.couponSystem2.entities.Company;
import com.example.couponSystem2.entities.Customer;
import com.example.couponSystem2.myException.CouponSystemException;
import com.example.couponSystem2.myException.enums.AdminEnumException;
import com.example.couponSystem2.myException.enums.CompanyEnumException;
import com.example.couponSystem2.myException.enums.CustomerEnumExceptions;
import com.example.couponSystem2.repositories.CompanyRepository;
import com.example.couponSystem2.repositories.CouponRepository;
import com.example.couponSystem2.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImplementation extends ClientService implements AdminService {
    private static final String EMAIL = "admin@admin.com";
    private static final String PASSWORD = "admin";


    public AdminServiceImplementation(CompanyRepository companyRepository, CustomerRepository customerRepository, CouponRepository couponRepository) {
        super(companyRepository, customerRepository, couponRepository);
    }

    public void deleteAll() {
        customerRepository.deleteAll();
        companyRepository.deleteAll();
        couponRepository.deleteAll();
    }

    @Override
    public boolean login(String email, String password) throws SQLException, InterruptedException {
        if (!(email.equals(EMAIL) && password.equals(PASSWORD))) {
            throw new CouponSystemException(AdminEnumException.ADMIN_NOT_FOUND);
        }
        return true;
    }

    @Override
    public Company addCompany(Company company) {
        if (companyRepository.existsCompanyByEmailOrName(company.getEmail(), company.getName())) {
            throw new CouponSystemException(CompanyEnumException.COMPANY_NAME_OR_EMAIL_ALREADY_EXISTS);
        }
        Company companyFromDB = companyRepository.save(company);
        return companyFromDB;
    }

    @Override
    public void updateCompany(Company company) {
        if (companyRepository.findById(company.getId()).isEmpty()) {
            throw new CouponSystemException(CompanyEnumException.COMPANY_NOT_FOUND);
        }
        if (companyRepository.existsCompanyByEmailAndIdIsNot(company.getEmail(), company.getId())) {
            throw new CouponSystemException(CompanyEnumException.COMPANY_NAME_OR_EMAIL_ALREADY_EXISTS);
        }
        companyRepository.updateCompany(company.getEmail(), company.getPassword(), company.getId());
    }

    @Override
    public void deleteCompany(Company company) {
        if (!companyRepository.existsCompanyByEmailAndPassword(company.getEmail(), company.getPassword())) {
            throw new CouponSystemException(CompanyEnumException.COMPANY_NOT_FOUND);
        }
        companyRepository.deleteById(company.getId());
    }

    @Override
    public List<Company> getAllCompanies() {
        List<Company> getAllCompaniesList = companyRepository.findAll();
        if (getAllCompaniesList.isEmpty()) {
            System.out.println("Get all companies list is empty");
        }
        return getAllCompaniesList;
    }

    @Override
    public Company getOneCompany(int companyID) {
        return companyRepository.findById(companyID).orElseThrow(() -> new CouponSystemException(CompanyEnumException.COMPANY_NOT_FOUND));
    }

    @Override
    public Customer addCustomer(Customer customer) {
        if (customerRepository.existsCustomerByEmail(customer.getEmail())) {
            throw new CouponSystemException(CustomerEnumExceptions.CUSTOMER_ALREADY_EXISTS);
        }
        Customer customerFromDB = customerRepository.save(customer);
        return customerFromDB;
    }

    @Override
    public void updateCustomer(Customer customer) {
        if (customerRepository.findById(customer.getId()).isEmpty()) {
            throw new CouponSystemException(CustomerEnumExceptions.CUSTOMER_NOT_FOUND);
        }
        if (customerRepository.existsCustomerByEmailAndIdIsNot(customer.getEmail(),customer.getId())) {
            throw new CouponSystemException(CustomerEnumExceptions.CUSTOMER_ALREADY_EXISTS);
        }
        customerRepository.updateCustomer(customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getPassword(), customer.getId());
    }

    @Override
    public void deleteCustomer(int customerID) {
        customerRepository.findById(customerID).orElseThrow(() -> new CouponSystemException(CustomerEnumExceptions.CUSTOMER_NOT_FOUND));
        customerRepository.deleteById(customerID);
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> getAllCustomersList = customerRepository.findAll();
        if (getAllCustomersList.isEmpty()) {
            System.out.println("Get all customers list is empty");
        }
        return getAllCustomersList;
    }

    @Override
    public Customer getOneCustomer(int customerID) {
        return customerRepository.findById(customerID).orElseThrow(() -> new CouponSystemException(CustomerEnumExceptions.CUSTOMER_NOT_FOUND));
    }
}
