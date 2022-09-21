package com.example.couponSystem2.controllers;

import com.example.couponSystem2.entities.Category;
import com.example.couponSystem2.entities.Company;
import com.example.couponSystem2.entities.Coupon;
import com.example.couponSystem2.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostUpdate;
import java.util.List;

@RestController
@RequestMapping("api")
public class CompanyController {


    @Autowired
    CompanyService companyService;


    @PostMapping("/addCoupon")
    @ResponseBody
    public ResponseEntity<?> addCoupon (@RequestBody Coupon coupon){
        System.out.println("Got: " + coupon);
        Coupon addCoupon = companyService.addCoupon(coupon);
        ResponseEntity<Coupon> responseEntity = new ResponseEntity<>(addCoupon, HttpStatus.OK);
        return responseEntity;
    }


    @PostUpdate
    @ResponseBody
    public void updateCoupon (@RequestBody Coupon coupon){
        System.out.println("Got: " + coupon);
        companyService.updateCoupon(coupon);
//        ResponseEntity<Coupon> responseEntity = new ResponseEntity<>(addCoupon, HttpStatus.OK);
//        return responseEntity;
    }


    @DeleteMapping
    @ResponseBody
    public void deleteCoupon (@RequestBody Coupon coupon){
        System.out.println("Got: " + coupon);
        companyService.deleteCoupon(coupon);
//        ResponseEntity<Coupon> responseEntity = new ResponseEntity<>(addCoupon, HttpStatus.OK);
//        return responseEntity;
    }


    @GetMapping("/getAllCompanyCoupons")
    public ResponseEntity<?> getAllCompanyCoupons(){
        List<Coupon> getAllCompanyCoupons = companyService.getCompanyCoupons();
        System.out.println("got: " + getAllCompanyCoupons);
        ResponseEntity<List<Coupon>> responseEntity = new ResponseEntity<>(getAllCompanyCoupons, HttpStatus.OK);
        return responseEntity;
    }


    @GetMapping("/getCompanyCouponsByCategory/{category}")
    public ResponseEntity<?> getCompanyCouponsByCategory(@PathVariable Category category){
        List<Coupon> getCompanyCouponsByCategory = companyService.getCompanyCouponsByCategory(category);
        System.out.println("got: " + getCompanyCouponsByCategory);
        ResponseEntity<List<Coupon>> responseEntity = new ResponseEntity<>(getCompanyCouponsByCategory, HttpStatus.OK);
        return responseEntity;
    }


    @GetMapping("/getAllCompanyCoupons/{maxPrice}")
    public ResponseEntity<?> getCompanyCouponsMaxPrice(@PathVariable double maxPrice){
        List<Coupon> getCompanyCouponsMaxPrice = companyService.getCompanyCouponsMaxPrice(maxPrice);
        System.out.println("got: " + getCompanyCouponsMaxPrice);
        ResponseEntity<List<Coupon>> responseEntity = new ResponseEntity<>(getCompanyCouponsMaxPrice, HttpStatus.OK);
        return responseEntity;
    }




    @GetMapping("/getCompanyDetails")
    public ResponseEntity<?> getCompanyDetails(){
        Company company = companyService.getCompanyDetails();
        System.out.println("got: " + company);
        ResponseEntity<Company> responseEntity = new ResponseEntity<>(company, HttpStatus.OK);
        return responseEntity;
    }


    // todo: how will postman know what company were talking about?
    //todo: postman returns company coupons list empty.. because again it doesnt know which one..
}