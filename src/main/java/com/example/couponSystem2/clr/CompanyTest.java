package com.example.couponSystem2.clr;

import com.example.couponSystem2.entities.Category;
import com.example.couponSystem2.entities.Company;
import com.example.couponSystem2.entities.Coupon;
import com.example.couponSystem2.loginManager.ClientType;
import com.example.couponSystem2.loginManager.LoginManager;
import com.example.couponSystem2.myException.CouponSystemException;
import com.example.couponSystem2.myException.enums.AdminEnumException;
import com.example.couponSystem2.myException.enums.CouponEnumException;
import com.example.couponSystem2.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.example.couponSystem2.services.CompanyService;
import com.example.couponSystem2.services.AdminService;
import com.example.couponSystem2.myException.enums.CompanyEnumException;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Component
@Order(2)
@RequiredArgsConstructor
public class CompanyTest implements CommandLineRunner {
    private AdminService adminService;
    private CompanyService companyService;
    private final LoginManager loginManager;


    @Override
    public void run(String... args) throws Exception {
        loginTest();
    }

    private void loginTest() {
        // Fail
        try {
//            AdminService adminService = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.Administrator);
            System.out.println("****TESTING COMPANY SERVICE****");
            Company company = Company.builder().build();
            System.out.println("company: " + company);
            companyService = (CompanyService)
                    loginManager.login(company.getEmail(),
                            company.getPassword(), ClientType.Company);
//             companyService = (CompanyService)
//                    loginManager.login(adminService.getAllCompanies().get(0).getEmail(),
//                            adminService.getAllCompanies().get(0).getPassword(), ClientType.Company);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (CouponSystemException c) {
            if (c.getErrorDetails() == CompanyEnumException.COMPANY_NOT_FOUND) {
                System.out.println("Failed to login: COMPANY_NOT_FOUND");
            }
        }

        // Success
        try {
            adminService = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.Administrator);
            Date startDate = Date.valueOf(LocalDate.now());
            Date endDate = Date.valueOf(LocalDate.now().plusDays(2));
            Date startDateMinusDays = Date.valueOf(LocalDate.now().minusDays(2));
            Date endDateMinusDays = Date.valueOf(LocalDate.now().minusDays(1));
            Company company = adminService.getAllCompanies().get(0);
            Coupon coupon1 = Coupon.builder().company(company).category(Category.FOOD).title("Title_1").description("Description_1").startDate(startDate).endDate(endDate).amount(31).price(10).image("https://cdn.pixabay.com/photo/2013/07/13/14/00/rocker-161936__340.png").build();
            Coupon coupon2 = Coupon.builder().company(company).category(Category.ELECTRICITY).title("Title_2").description("Description_2").startDate(startDate).endDate(endDate).amount(302).price(60).image("https://cdn.pixabay.com/photo/2016/04/19/15/23/teddy-1338895__340.jpg").build();
            Coupon coupon3 = Coupon.builder().company(company).category(Category.RESTAURANT).title("Title_3").description("Description_3").startDate(startDate).endDate(endDate).amount(50).price(200).image("https://cdn.pixabay.com/photo/2016/04/19/15/23/teddy-1338895__340.jpg").build();
            Coupon coupon4 = Coupon.builder().company(company).category(Category.VACATION).title("Title_4").description("Description_4").startDate(startDate).endDate(endDate).amount(90).price(50).image("https://cdn.pixabay.com/photo/2016/04/19/15/23/teddy-1338895__340.jpg").build();
            Coupon coupon5 = Coupon.builder().company(company).category(Category.RESTAURANT).title("Title_5").description("Description_5").startDate(startDateMinusDays).endDate(endDateMinusDays).amount(50).price(200).image("https://cdn.pixabay.com/photo/2016/04/19/15/23/teddy-1338895__340.jpg").build();
            Coupon coupon6 = Coupon.builder().company(company).category(Category.VACATION).title("Title_6").description("Description_6").startDate(startDateMinusDays).endDate(endDateMinusDays).amount(90).price(50).image("https://cdn.pixabay.com/photo/2016/04/19/15/23/teddy-1338895__340.jpg").build();
            List<Coupon> couponList = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                couponList.add(Coupon.builder().company(company).category(Category.valueOf((int)(Math.random()*3)+1)).title("Title_"+(i+7)).description("Description_"+(i+7)).startDate(startDate).endDate(endDate).amount((int) ((Math.random()*3000)+1)).price((int) (Math.random()*3000)+1).image("https://cdn.pixabay.com/photo/2016/04/19/15/23/teddy-1338895__340.jpg").build());
             }
            System.out.println("couponList.get(7): " + couponList.get(7));
            /////////////////////////////////////////////////////////////////////////////////////////
            companyService = (CompanyService)
                    loginManager.login(company.getEmail(),
                            company.getPassword(), ClientType.Company);
            System.out.println("company: " + companyService);
            try {
                companyService.addCoupon(coupon1);
                companyService.addCoupon(coupon2);
                companyService.addCoupon(coupon3);
                companyService.addCoupon(coupon4);
                companyService.addCoupon(coupon5);
                companyService.addCoupon(coupon6);
                companyService.addCoupon(couponList.get(7));
                for (int i = 0; i < 30; i++) {
                    companyService.addCoupon(couponList.get(i)) ;
                }
                // trying to add a coupon with similar title
                companyService.addCoupon(coupon6);
            } catch (CouponSystemException s) {
                if (s.getErrorDetails() == CouponEnumException.COUPON_TITLE_ALREADY_EXIST) {
                    System.out.println("Failed to add coupon: coupon title already exists");
                }
            }

            System.out.println("****TESTING UPDATE COUPON****");
            Coupon firstCompanyCoupon = companyService.getCompanyCoupons().get(0);
            firstCompanyCoupon.setStartDate(Date.valueOf(LocalDate.of(2022, Month.AUGUST, 3)));
            firstCompanyCoupon.setEndDate(Date.valueOf(LocalDate.of(2023, Month.NOVEMBER, 3)));
            firstCompanyCoupon.setCategory(Category.ELECTRICITY);
            firstCompanyCoupon.setDescription("update description");
            firstCompanyCoupon.setTitle("update Title");
            firstCompanyCoupon.setPrice(1000);
            try {
                companyService.updateCoupon(firstCompanyCoupon);
                firstCompanyCoupon.setCompany(Company.builder().build());
                companyService.updateCoupon(firstCompanyCoupon);
            } catch (CouponSystemException s) {
                if (s.getErrorDetails() == CompanyEnumException.CANT_UPDATE_COMPANY_ID) {
                    System.out.println("cant update company ID");
                }
            }


            System.out.println("****TESTING DELETE COUPON****");
            Coupon secondCompanyCoupon = companyService.getCompanyCoupons().get(1);
            System.out.println("secondCompanyCoupon: " + secondCompanyCoupon);
            try {
                companyService.deleteCoupon(secondCompanyCoupon);
                // trying to delete a non-existing coupon
                secondCompanyCoupon.setId(0);
                companyService.deleteCoupon(secondCompanyCoupon);
            } catch (CouponSystemException s) {
                if (s.getErrorDetails() == CouponEnumException.COUPON_NOT_FOUND) {
                    System.out.println("Failed to delete coupon: COUPON_NOT_FOUND");
                }
            }

            System.out.println("****TESTING GET ALL COMPANY'S COUPONS****");
            System.out.println(companyService.getCompanyCoupons());


            System.out.println("****TESTING GET ALL COMPANY'S COUPONS BY CATEGORY****");
            System.out.println(companyService.getCompanyCouponsByCategory(Category.FOOD));


            System.out.println("****TESTING GET ALL COMPANY'S COUPONS BY MAX PRICE****");
            System.out.println(companyService.getCompanyCouponsMaxPrice(51));


            System.out.println("****TESTING GET COMPANY'S DETAILS****");
            System.out.println(companyService.getCompanyDetails());

            System.out.println("here");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
