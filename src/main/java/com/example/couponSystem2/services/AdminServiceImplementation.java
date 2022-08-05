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

    // todo: also in customer
    @Override
    public void updateCompany(int companyID, Company company) {
        Company companyFromDB = companyRepository.findById(companyID).orElseThrow();// add exception

        company.setId(companyID);


        if (!companyFromDB.getName().equals(company.getName())){
            // throw
        }
        companyRepository.save(company);
//        companyRepository.updateCompany(company.getEmail(), company.getPassword(), company.getId());
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
//        Company company = companyRepository.findById(companyID).get();
//        System.out.println("Tomer "+ company);
//        //if company does not exist throw Exception,else return company
//        if (!companyRepository.existsCompanyByEmailAndPassword(company.getEmail(), company.getPassword())) {
//            throw new CouponSystemException(CompanyEnumException.COMPANY_NOT_FOUND);
//        }
////         or add our throw like this
        return companyRepository.findById(companyID).orElseThrow(() -> new CouponSystemException(CompanyEnumException.COMPANY_NOT_FOUND));
    }

    @Override
    public Customer addCustomer(Customer customer) {
        if (customerRepository.existsCustomerByEmailAndPassword(customer.getEmail(), customer.getPassword())) {
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
        customerRepository.updateCustomer(customer.getEmail(), customer.getPassword(), customer.getId());
    }

    @Override
    public void deleteCustomer(int customerID) {
        Customer customer = customerRepository.findById(customerID).get();
        if (!customerRepository.existsCustomerByEmailAndPassword(customer.getEmail(), customer.getPassword())) {
            throw new CouponSystemException(CustomerEnumExceptions.CUSTOMER_NOT_FOUND);
        }
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
//        Customer customer = customerRepository.findById(customerID).get();
//        //if customer does not exist throw Exception,else return customer
//        if (!customerRepository.existsCustomerByEmailAndPassword(customer.getEmail(), customer.getPassword())) {
//            throw new CouponSystemException(CustomerEnumExceptions.CUSTOMER_NOT_FOUND);
//        }
        return customerRepository.findById(customerID).orElseThrow(() -> new CouponSystemException(CustomerEnumExceptions.CUSTOMER_NOT_FOUND));
    }
}
