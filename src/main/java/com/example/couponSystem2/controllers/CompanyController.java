package com.example.couponSystem2.controllers;

import com.example.couponSystem2.entities.Category;
import com.example.couponSystem2.entities.Company;
import com.example.couponSystem2.entities.Coupon;
import com.example.couponSystem2.services.CompanyServiceImplementation;
import com.example.couponSystem2.tokensManager.AuthDetails;
import com.example.couponSystem2.tokensManager.TokensManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostUpdate;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class CompanyController {


    @Autowired
    CompanyServiceImplementation companyServiceImplementation;
    @Autowired
    TokensManager tokensManager;

//    @Override
//    public ResponseEntity<?> login(AuthDetails authDetails) throws SQLException, InterruptedException {
//        boolean isAuth = companyServiceImplementation.login(authDetails.getEmail(), authDetails.getPassword());
//        String token = tokensManager.getToken(authDetails).getToken();
//        System.out.println("token name: " + token );
////        return isAuth;
//
//        ResponseEntity<String> responseEntity = new ResponseEntity<>("bad", HttpStatus.OK);
//        return responseEntity;
//    }

    @PostMapping("/addCoupon")
    @ResponseBody
    public ResponseEntity<?> addCoupon(@RequestBody Coupon coupon) {
        System.out.println("Got: " + coupon);
        Coupon addCoupon = companyServiceImplementation.addCoupon(coupon);
        ResponseEntity<Coupon> responseEntity = new ResponseEntity<>(addCoupon, HttpStatus.OK);
        return responseEntity;
    }


    @PostUpdate
    @ResponseBody
    public ResponseEntity<?> updateCoupon(@RequestBody Coupon coupon) {
        System.out.println("Got: " + coupon);
        companyServiceImplementation.updateCoupon(coupon);
        ResponseEntity<String> responseEntity = new ResponseEntity<>("successfully updated coupon", HttpStatus.OK);
        return responseEntity;
    }


    @DeleteMapping
    @ResponseBody
    public ResponseEntity<?> deleteCoupon(@RequestBody Coupon coupon) {
        System.out.println("Got: " + coupon);
        companyServiceImplementation.deleteCoupon(coupon);
        ResponseEntity<String> responseEntity = new ResponseEntity<>("successfully deleted coupon", HttpStatus.OK);
        return responseEntity;
    }


    @GetMapping("/getAllCompanyCoupons")
    public ResponseEntity<?> getAllCompanyCoupons() {
        List<Coupon> getAllCompanyCoupons = companyServiceImplementation.getCompanyCoupons();
        System.out.println("got: " + getAllCompanyCoupons);
        ResponseEntity<List<Coupon>> responseEntity = new ResponseEntity<>(getAllCompanyCoupons, HttpStatus.OK);
        return responseEntity;
    }


    @GetMapping("/getCompanyCouponsByCategory/{category}")
    @ResponseBody
    public ResponseEntity<?> getCompanyCouponsByCategory(@PathVariable Category category) {
        List<Coupon> getCompanyCouponsByCategory = companyServiceImplementation.getCompanyCouponsByCategory(category);
        System.out.println("got: " + getCompanyCouponsByCategory);
        ResponseEntity<List<Coupon>> responseEntity = new ResponseEntity<>(getCompanyCouponsByCategory, HttpStatus.OK);
        return responseEntity;
    }


    @GetMapping("/getAllCompanyCouponsByMaxPrice/{maxPrice}")
    @ResponseBody
    public ResponseEntity<?> getCompanyCouponsMaxPrice(@PathVariable double maxPrice) {
        List<Coupon> getCompanyCouponsMaxPrice = companyServiceImplementation.getCompanyCouponsMaxPrice(maxPrice);
        System.out.println("got: " + getCompanyCouponsMaxPrice);
        ResponseEntity<List<Coupon>> responseEntity = new ResponseEntity<>(getCompanyCouponsMaxPrice, HttpStatus.OK);
        return responseEntity;
    }


    @GetMapping("/getCompanyDetails")
    public ResponseEntity<?> getCompanyDetails() {
        Company company = companyServiceImplementation.getCompanyDetails();
        System.out.println("got: " + company);
        ResponseEntity<Company> responseEntity = new ResponseEntity<>(company, HttpStatus.OK);
        return responseEntity;
    }


    // todo: how will postman know what company were talking about?
    //todo: postman returns company coupons list empty.. because again it doesnt know which one..
}
