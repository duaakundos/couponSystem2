package com.example.couponSystem2.clr;

import com.example.couponSystem2.dailyJob.CouponExpirationDailyJob;
import com.example.couponSystem2.entities.Category;
import com.example.couponSystem2.entities.Coupon;
import com.example.couponSystem2.myException.enums.CouponEnumException;
import com.example.couponSystem2.services.CompanyService;
import com.example.couponSystem2.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.example.couponSystem2.entities.Company;
import com.example.couponSystem2.entities.Customer;
import com.example.couponSystem2.loginManager.ClientType;
import com.example.couponSystem2.loginManager.LoginManager;
import com.example.couponSystem2.myException.CouponSystemException;
import com.example.couponSystem2.myException.enums.AdminEnumException;
import com.example.couponSystem2.myException.enums.CompanyEnumException;
import com.example.couponSystem2.myException.enums.CustomerEnumExceptions;
import com.example.couponSystem2.services.AdminService;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

@Component
@Order(3)
@RequiredArgsConstructor
public class CustomerTest implements CommandLineRunner {
    private AdminService adminService;
    private CompanyService companyService;
    private CustomerService customerService;
    private final LoginManager loginManager;
    @Autowired
    CouponExpirationDailyJob couponExpirationDailyJob;
    @Override
    public void run(String... args) throws Exception {
        loginTest();
    }

    private void loginTest() {

        // Fail
        try {
//            adminService = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.Administrator);
            Customer customer = Customer.builder().build();
            customerService = (CustomerService) loginManager.login(customer.getEmail(), customer.getPassword(), ClientType.Customer);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (CouponSystemException c) {
            if (c.getErrorDetails() == CustomerEnumExceptions.CUSTOMER_NOT_FOUND) {
                System.out.println("Failed to login: CUSTOMER_NOT_FOUND");
            }
        }

        // Success
        try {
            AdminService adminService = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.Administrator);
            CompanyService companyService = (CompanyService)
                    loginManager.login(adminService.getAllCompanies().get(0).getEmail(),
                            adminService.getAllCompanies().get(0).getPassword(), ClientType.Company);
            System.out.println("****TESTING CUSTOMER FACADE****");
            CustomerService customerService = (CustomerService)
                    loginManager.login(adminService.getAllCustomers().get(0).getEmail(),
                            adminService.getAllCustomers().get(0).getPassword(), ClientType.Customer);

            System.out.println("****TESTING PURCHASING COUPON****");
            try {
                ArrayList<Coupon> coupons = new ArrayList<>(companyService.getCompanyCoupons());
                customerService.purchaseCoupon(coupons.get(0));
                customerService.purchaseCoupon(coupons.get(1));
                customerService.purchaseCoupon(coupons.get(2));
                // trying to purchase the same coupon
                customerService.purchaseCoupon(coupons.get(2));
                // trying to purchase an out-of-stock coupon
//                    Coupon outOfStockCoupon = coupons.get(0);
//                    outOfStockCoupon.setAmount(0);
//                customerService.purchaseCoupon(outOfStockCoupon);
                // trying to purchase an expired coupon
//                    Coupon expiredCoupon = coupons.get(1);
//                    expiredCoupon.setEndDate(Date.valueOf(LocalDate.now().minusDays(2)));
//                    customerService.purchaseCoupon(expiredCoupon);
            } catch (CouponSystemException c) {
                if (c.getErrorDetails() == CouponEnumException.CANT_PURCHASE_THE_SAME_COUPON_MORE_THAN_ONCE) {
                    System.out.println("cant purchase the same coupon more than once");
                }
                if (c.getErrorDetails() == CouponEnumException.COUPON_OUT_OF_STOCK) {
                    System.out.println("coupon is out of stock");
                }
                if (c.getErrorDetails() == CouponEnumException.COUPON_IS_EXPIRED) {
                    System.out.println("coupon is expired");
                }
            }

            System.out.println("****TESTING GET ALL CUSTOMERS COUPONS****");
            System.out.println(customerService.getCustomerCoupons());

            System.out.println("****TESTING GET ALL CUSTOMERS COUPON BY CATEGORY****");
            System.out.println(customerService.getCustomerCouponByCategory(Category.valueOf(2)));

            System.out.println("****TESTING GET ALL CUSTOMERS COUPON BY MAX PRICE****");
            System.out.println(customerService.getCustomerCouponByMaxPrice(500));

            System.out.println("****TESTING GET CUSTOMER DETAILS");
            System.out.println(customerService.getCustomerDetails());

            System.out.println("****TESTING CASCADE WHEN DELETING CUSTOMER****");
            ArrayList<Coupon> coupons = (ArrayList<Coupon>) companyService.getCompanyCoupons();
            Thread.sleep(1000 * 15);
            companyService.deleteCoupon(coupons.get(1));

            System.out.println("here customer");


            //For testing the coupon delete job
            Thread.sleep(1000 * 40);
            couponExpirationDailyJob.stop();
            System.out.println("BYE");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
