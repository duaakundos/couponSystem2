package com.example.couponSystem2.controllers.main;

import com.example.couponSystem2.entities.Category;
import com.example.couponSystem2.entities.Coupon;
import com.example.couponSystem2.entities.Customer;
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
    CustomerServiceImplementation customerServiceImplementation;

    @Autowired
    TokensManager tokensManager;

    @PostMapping("/purchaseCoupon")
    @ResponseBody
    public ResponseEntity<?> purchaseCoupon(@RequestBody Coupon coupon, @RequestHeader("token") String token) {
        System.out.println("Got: " + coupon);
        customerServiceImplementation.purchaseCoupon(coupon);
        ResponseEntity<String> responseEntity = new ResponseEntity<>("successfully purchased coupon", HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/getAllCustomerCoupons")
    public ResponseEntity<?> getAllCustomerCoupons(@RequestHeader("token") String token) {
        List<Coupon> getAllCustomerCoupons = customerServiceImplementation.getCustomerCoupons();
        System.out.println("got: " + getAllCustomerCoupons);
        ResponseEntity<List<Coupon>> responseEntity = new ResponseEntity<>(getAllCustomerCoupons, HttpStatus.OK);
        return responseEntity;
    }


    @GetMapping("/getCustomerCouponsByCategory/{category}")
    @ResponseBody
    public ResponseEntity<?> getCustomerCouponsByCategory(@PathVariable Category category, @RequestHeader("token") String token) {
        List<Coupon> getCustomerCouponsByCategory = customerServiceImplementation.getCustomerCouponByCategory(category);
        System.out.println("got: " + getCustomerCouponsByCategory);
        ResponseEntity<List<Coupon>> responseEntity = new ResponseEntity<>(getCustomerCouponsByCategory, HttpStatus.OK);
        return responseEntity;
    }


    @GetMapping("/getAllCustomerCouponsByMaxPrice/{maxPrice}")
    @ResponseBody
    public ResponseEntity<?> getAllCustomerCouponsByMaxPrice(@PathVariable double maxPrice, @RequestHeader("token") String token) {
        List<Coupon> getAllCustomerCouponsByMaxPrice = customerServiceImplementation.getCustomerCouponByMaxPrice(maxPrice);
        System.out.println("got: " + getAllCustomerCouponsByMaxPrice);
        ResponseEntity<List<Coupon>> responseEntity = new ResponseEntity<>(getAllCustomerCouponsByMaxPrice, HttpStatus.OK);
        return responseEntity;
    }


    @GetMapping("/getCustomerDetails")
    public ResponseEntity<?> getCustomerDetails(@RequestHeader("token") String token) {
        Customer customer = customerServiceImplementation.getCustomerDetails();
        System.out.println("got: " + customer);
        ResponseEntity<Customer> responseEntity = new ResponseEntity<>(customer, HttpStatus.OK);
        return responseEntity;
    }

}
