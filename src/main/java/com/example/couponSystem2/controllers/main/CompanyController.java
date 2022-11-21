package com.example.couponSystem2.controllers.main;

import com.example.couponSystem2.controllers.ResponseDetails;
import com.example.couponSystem2.entities.Category;
import com.example.couponSystem2.entities.Company;
import com.example.couponSystem2.entities.Coupon;
import com.example.couponSystem2.services.AdminServiceImplementation;
import com.example.couponSystem2.services.CompanyServiceImplementation;
import com.example.couponSystem2.tokensManager.TokensManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class CompanyController {
    @Autowired
    TokensManager tokensManager;


    @PostMapping("/addCoupon")
    @ResponseBody
    public ResponseEntity<?> addCoupon(@RequestBody Coupon coupon, @RequestHeader("token") String token) {
        System.out.println("Got: " + coupon);
        ((CompanyServiceImplementation) tokensManager.getService(token)).addCoupon(coupon);
        ResponseEntity<ResponseDetails> responseEntity = new ResponseEntity<>(new ResponseDetails("Success", "Coupon Successfully Added"), HttpStatus.OK);
        return responseEntity;
    }


    @PostMapping("/updateCoupon")
    @ResponseBody
    public ResponseEntity<?> updateCoupon(@RequestBody Coupon coupon, @RequestHeader("token") String token) {
        System.out.println("Got: " + coupon);
        ((CompanyServiceImplementation) tokensManager.getService(token)).updateCoupon(coupon);
        ResponseEntity<ResponseDetails> responseEntity = new ResponseEntity<>(new ResponseDetails("Success", "Coupon Successfully Updated"), HttpStatus.OK);
        return responseEntity;
    }


    @DeleteMapping("/deleteCoupon")
    @ResponseBody
    public ResponseEntity<?> deleteCoupon(@RequestBody Coupon coupon, @RequestHeader("token") String token) {
        System.out.println("Got: " + coupon);
        ((CompanyServiceImplementation) tokensManager.getService(token)).deleteCoupon(coupon);
        ResponseEntity<ResponseDetails> responseEntity = new ResponseEntity<>(new ResponseDetails("Success", "Coupon Successfully Deleted"), HttpStatus.OK);
        return responseEntity;
    }


    @GetMapping("/getAllCompanyCoupons")
    public ResponseEntity<?> getAllCompanyCoupons(@RequestHeader("token") String token) {
        List<Coupon> getAllCompanyCoupons = ((CompanyServiceImplementation) tokensManager.getService(token)).getCompanyCoupons();
        System.out.println("got: " + getAllCompanyCoupons);
        ResponseEntity<List<Coupon>> responseEntity = new ResponseEntity<>(getAllCompanyCoupons, HttpStatus.OK);
        System.out.println("resopnse: " + responseEntity);
        return responseEntity;
    }


    @GetMapping("/getCompanyCouponsByCategory/{category}")
    @ResponseBody
    public ResponseEntity<?> getCompanyCouponsByCategory(@PathVariable Category category, @RequestHeader("token") String token) {
        List<Coupon> getCompanyCouponsByCategory = ((CompanyServiceImplementation) tokensManager.getService(token)).getCompanyCouponsByCategory(category);
        System.out.println("got: " + getCompanyCouponsByCategory);
        ResponseEntity<List<Coupon>> responseEntity = new ResponseEntity<>(getCompanyCouponsByCategory, HttpStatus.OK);
        return responseEntity;
    }


    @GetMapping("/getAllCompanyCouponsByMaxPrice/{maxPrice}")
    @ResponseBody
    public ResponseEntity<?> getCompanyCouponsMaxPrice(@PathVariable double maxPrice, @RequestHeader("token") String token) {
        List<Coupon> getCompanyCouponsMaxPrice = ((CompanyServiceImplementation) tokensManager.getService(token)).getCompanyCouponsMaxPrice(maxPrice);
        System.out.println("got: " + getCompanyCouponsMaxPrice);
        ResponseEntity<List<Coupon>> responseEntity = new ResponseEntity<>(getCompanyCouponsMaxPrice, HttpStatus.OK);
        return responseEntity;
    }


    @GetMapping("/getCompanyDetails")
    public ResponseEntity<?> getCompanyDetails(@RequestHeader("token") String token) {
        Company company = ((CompanyServiceImplementation) tokensManager.getService(token)).getCompanyDetails();
        System.out.println("got: " + company);
        ResponseEntity<Company> responseEntity = new ResponseEntity<>(company, HttpStatus.OK);
        return responseEntity;
    }
}
