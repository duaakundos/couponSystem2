package com.example.couponSystem2.clr;

import com.example.couponSystem2.dailyJob.CouponExpirationDailyJob;
import com.example.couponSystem2.entities.Company;
import com.example.couponSystem2.entities.Customer;
import com.example.couponSystem2.loginManager.ClientType;
import com.example.couponSystem2.loginManager.LoginManager;
import com.example.couponSystem2.myException.CouponSystemException;
import com.example.couponSystem2.myException.enums.AdminEnumException;
import com.example.couponSystem2.myException.enums.CompanyEnumException;
import com.example.couponSystem2.myException.enums.CustomerEnumExceptions;
import com.example.couponSystem2.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;

@Component
@Order(1)
@RequiredArgsConstructor
public class AdminTest implements CommandLineRunner {

    private AdminService adminService;
    private final LoginManager loginManager;
    @Autowired
    CouponExpirationDailyJob couponExpirationDailyJob;
    @Override
    public void run(String... args) throws Exception {
        loginTest();
    }

    public void loginTest() {
        // Fail
        try {
            adminService = (AdminService) loginManager.login("admi@admin.com", "admin", ClientType.Administrator);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (CouponSystemException c) {
            if (c.getErrorDetails() == AdminEnumException.ADMIN_NOT_FOUND) {
                System.out.println("ADMIN_NOT_FOUND");
            }
        }
        // Success
        try {
            Thread thread = new Thread(couponExpirationDailyJob);
            thread.start();

            System.out.println("****TESTING ADMIN FACADE*****");
            adminService = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.Administrator);
            ///////////////////////////////////////////////////////////////////////////////////////////
            Company company1 = Company.builder().name("Company_1").password("password_1").email("Company@_1").coupons(new ArrayList<>()).build();
            Company company2 = Company.builder().name("Company_2").password("password_2").email("Company@_2").coupons(new ArrayList<>()).build();
            Company company3 = Company.builder().name("Company_3").password("password_3").email("Company@_3").coupons(new ArrayList<>()).build();
            ///////////////////////////////////////////////////////////////////////////////////////////
            Customer customer1 = Customer.builder().firstName("customer_1").lastName("dov_1").email("asd_1").password("aaa1_1").coupons(new ArrayList<>()).build();
            Customer customer2 = Customer.builder().firstName("customer_2").lastName("dov_2").email("asd_2").password("aaa1_2").coupons(new ArrayList<>()).build();
            Customer customer3 = Customer.builder().firstName("customer_3").lastName("dov_3").email("asd_3").password("aaa1_3").coupons(new ArrayList<>()).build();
            ////////////////////////////////////////////////////////////////////////////////////////////////
            try {
                System.out.println("****TESTING ADD COMPANY****");
                adminService.addCompany(company1);
                adminService.addCompany(company2);
                adminService.addCompany(company3);
                //adding the same company
                adminService.addCompany(company3);
            } catch (CouponSystemException s) {
                if (s.getErrorDetails() == CompanyEnumException.COMPANY_NAME_OR_EMAIL_ALREADY_EXISTS) {
                    System.out.println("COMPANY_NAME_OR_EMAIL_ALREADY_EXISTS");
                }
            }

            System.out.println("****TESTING UPDATE COMPANY****");
            Company firstCompany = adminService.getAllCompanies().get(0);
            try {
                firstCompany.setEmail("updatingEmailCompany@_");
                firstCompany.setPassword("updatingPassword");
                adminService.updateCompany(firstCompany);
                // trying to update a non-existing company
                adminService.updateCompany(adminService.getOneCompany(999));
            } catch (CouponSystemException s) {
                if (s.getErrorDetails() == CompanyEnumException.COMPANY_NOT_FOUND) {
                    System.out.println("Cannot update: company not found");
                }
            }

            try {
                System.out.println("****TESTING DELETE COMPANY****");
                Company secondCompany = adminService.getAllCompanies().get(1);
                adminService.deleteCompany(secondCompany);
                // trying to delete a non-existing company
                adminService.deleteCompany(adminService.getOneCompany(999));
            } catch (CouponSystemException s) {
                if (s.getErrorDetails() == CompanyEnumException.COMPANY_NOT_FOUND) {
                    System.out.println("Cannot delete: company not found");
                }
            }

            System.out.println("****TESTING GET ALL COMPANIES****");
            System.out.println(adminService.getAllCompanies());
            System.out.println("****TESTING GET ONE COMPANY****");
            try {
                System.out.println(adminService.getOneCompany(firstCompany.getId()));
                // trying to get a non-existing company
                System.out.println(adminService.getOneCompany(999));
            } catch (CouponSystemException s) {
                if (s.getErrorDetails() == CompanyEnumException.COMPANY_NOT_FOUND) {
                    System.out.println("company not found");
                }
            }

            System.out.println("****TESTING ADD CUSTOMER****");
            try {
                adminService.addCustomer(customer1);
                adminService.addCustomer(customer2);
                adminService.addCustomer(customer3);
                // trying to add the same customer
                adminService.addCustomer(customer3);
            } catch (CouponSystemException s) {
                if (s.getErrorDetails() == CustomerEnumExceptions.CUSTOMER_ALREADY_EXISTS) {
                    System.out.println("customer already exists");
                }
            }


            System.out.println("****TESTING UPDATING CUSTOMER****");
            Customer firstCustomer = adminService.getAllCustomers().get(0);
            firstCustomer.setFirstName("updatingCustomerFirstName");
            firstCustomer.setLastName("updatingCustomerLastName");
            firstCustomer.setEmail("customerUpdate@");
            firstCustomer.setPassword("updatePassword");
            try {
                adminService.updateCustomer(firstCustomer);
                // trying to update a non-existing customer
                adminService.updateCustomer(adminService.getOneCustomer(999));
            } catch (CouponSystemException s) {
                if (s.getErrorDetails() == CustomerEnumExceptions.CUSTOMER_NOT_FOUND) {
                    System.out.println("customer not found");
                }
            }

            System.out.println("****TESTING DELETE CUSTOMER****");
            int secondCustomerID = adminService.getAllCustomers().get(1).getId();
            try {
                adminService.deleteCustomer(secondCustomerID);
                // trying to delete a non-existing customer
                adminService.deleteCustomer(adminService.getOneCustomer(-9999).getId());
            } catch (CouponSystemException s) {
                if (s.getErrorDetails() == CustomerEnumExceptions.CUSTOMER_NOT_FOUND) {
                    System.out.println("Cannot Delete Customer: customer not found");
                }
            }

            System.out.println("****TESTING GET ALL CUSTOMERS****");
            System.out.println(adminService.getAllCustomers());

            System.out.println("****TESTING GET ONE CUSTOMER****");
            try {
                System.out.println(adminService.getOneCustomer(firstCustomer.getId()));
                // trying to get a non-existing company
                System.out.println(adminService.getOneCustomer(999));
            } catch (CouponSystemException s) {
                if (s.getErrorDetails() == CustomerEnumExceptions.CUSTOMER_NOT_FOUND) {
                    System.out.println("Cannot get one customer: customer not found");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

