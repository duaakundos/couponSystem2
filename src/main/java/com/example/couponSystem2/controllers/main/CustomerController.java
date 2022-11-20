package com.example.couponSystem2.controllers.main;

import com.example.couponSystem2.controllers.ResponseDetails;
import com.example.couponSystem2.entities.Category;
import com.example.couponSystem2.entities.Coupon;
import com.example.couponSystem2.entities.Customer;
import com.example.couponSystem2.services.CompanyServiceImplementation;
import com.example.couponSystem2.services.CustomerServiceImplementation;
import com.example.couponSystem2.tokensManager.TokensManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class CustomerController {
    @Autowired
    TokensManager tokensManager;

    @PostMapping("/purchaseCoupon")
    @ResponseBody
    public ResponseEntity<?> purchaseCoupon(@RequestBody Coupon coupon, @RequestHeader("token") String token) {
        System.out.println("Got: " + coupon);
        ((CustomerServiceImplementation) tokensManager.getService(token)).purchaseCoupon(coupon);
        ResponseEntity<ResponseDetails> responseEntity = new ResponseEntity<>(new ResponseDetails("Success","You successfully purchased the coupon. Enjoy! and don't forget to come back :)"), HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/getAllCustomerCoupons")
    public ResponseEntity<?> getAllCustomerCoupons(@RequestHeader("token") String token) {
        List<Coupon> getAllCustomerCoupons = ((CustomerServiceImplementation) tokensManager.getService(token)).getCustomerCoupons();
        System.out.println("got: " + getAllCustomerCoupons);
        ResponseEntity<List<Coupon>> responseEntity = new ResponseEntity<>(getAllCustomerCoupons, HttpStatus.OK);
        return responseEntity;
    }


    @GetMapping("/getCustomerCouponsByCategory/{category}")
    @ResponseBody
    public ResponseEntity<?> getCustomerCouponsByCategory(@PathVariable Category category, @RequestHeader("token") String token) {
        List<Coupon> getCustomerCouponsByCategory = ((CustomerServiceImplementation) tokensManager.getService(token)).getCustomerCouponByCategory(category);
        System.out.println("got: " + getCustomerCouponsByCategory);
        ResponseEntity<List<Coupon>> responseEntity = new ResponseEntity<>(getCustomerCouponsByCategory, HttpStatus.OK);
        return responseEntity;
    }


    @GetMapping("/getAllCustomerCouponsByMaxPrice/{maxPrice}")
    @ResponseBody
    public ResponseEntity<?> getAllCustomerCouponsByMaxPrice(@PathVariable double maxPrice, @RequestHeader("token") String token) {
        List<Coupon> getAllCustomerCouponsByMaxPrice = ((CustomerServiceImplementation) tokensManager.getService(token)).getCustomerCouponByMaxPrice(maxPrice);
        System.out.println("got: " + getAllCustomerCouponsByMaxPrice);
        ResponseEntity<List<Coupon>> responseEntity = new ResponseEntity<>(getAllCustomerCouponsByMaxPrice, HttpStatus.OK);
        return responseEntity;
    }


    @GetMapping("/getCustomerDetails")
    public ResponseEntity<?> getCustomerDetails(@RequestHeader("token") String token) {
        Customer customer = ((CustomerServiceImplementation) tokensManager.getService(token)).getCustomerDetails();
        System.out.println("got: " + customer);
        ResponseEntity<Customer> responseEntity = new ResponseEntity<>(customer, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/getAllCoupons")
    @ResponseBody
    public ResponseEntity<?> getAllCoupons(@RequestHeader("token") String token) {
        List<Coupon> getAllCoupons = ((CustomerServiceImplementation) tokensManager.getService(token)).getAllCoupons();
        System.out.println("got: " + getAllCoupons);
        ResponseEntity<List<Coupon>> responseEntity = new ResponseEntity<>(getAllCoupons, HttpStatus.OK);
        return responseEntity;
    }



}
