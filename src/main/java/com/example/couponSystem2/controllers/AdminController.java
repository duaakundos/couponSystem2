package com.example.couponSystem2.controllers;


import com.example.couponSystem2.entities.Company;
import com.example.couponSystem2.entities.Coupon;
import com.example.couponSystem2.entities.Customer;
import com.example.couponSystem2.services.AdminServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostUpdate;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("api")
public class AdminController extends ClientController{

    @Autowired
    AdminServiceImplementation adminServiceImplementation;


    @PostMapping("/addCompany")
    @ResponseBody
    public ResponseEntity<?> addCompany (@RequestBody Company company){
        System.out.println("Got: " + company);
        Company companyID = adminServiceImplementation.addCompany(company);
        ResponseEntity<Company> responseEntity = new ResponseEntity<>(companyID, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/updateCompany")
//    @PostUpdate
    @ResponseBody
    public ResponseEntity<?> updateCompany (@RequestBody Company company){
        System.out.println("Got: " + company);
        adminServiceImplementation.updateCompany(company);
        ResponseEntity<String> responseEntity = new ResponseEntity<>("successfully updated company", HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/deleteCompany") //
    @ResponseBody
    public ResponseEntity<?> deleteCompany(@RequestBody Company company){
        adminServiceImplementation.deleteCompany(company);
        System.out.println("delete company: " + company);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping("/getAllCompanies")
    public ResponseEntity<?> getAllCompanies(){
        List<Company> companyListFromDB = adminServiceImplementation.getAllCompanies();
        System.out.println("got: " + companyListFromDB);
        ResponseEntity<List<Company>> responseEntity = new ResponseEntity<>(companyListFromDB, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/getOneCompanyByID/{id}")
    public ResponseEntity<?> getOneCompany(@PathVariable int id){
        Company companyFromDB = adminServiceImplementation.getOneCompany(id);
        System.out.println("got: " + companyFromDB);
        ResponseEntity<Company> responseEntity = new ResponseEntity<>(companyFromDB, HttpStatus.OK);
        return responseEntity;
    }


    @PostMapping("/addCustomer")
    @ResponseBody
    public ResponseEntity<?> addCustomer (@RequestBody Customer customer){
        System.out.println("Got: " + customer);
        Customer customerID = adminServiceImplementation.addCustomer(customer);
        ResponseEntity<Customer> responseEntity = new ResponseEntity<>(customerID, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/updateCustomer")
//    @PostUpdate
    @ResponseBody
    public ResponseEntity<?> updateCustomer (@RequestBody Customer customer){
        System.out.println("Got: " + customer);
        adminServiceImplementation.updateCustomer(customer);
        ResponseEntity<String> responseEntity = new ResponseEntity<>("successfully updated customer", HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/deleteCustomer")
    @ResponseBody
    public ResponseEntity<?> deleteCustomer(@RequestBody Customer customer){
        adminServiceImplementation.deleteCustomer(customer.getId());
        System.out.println("delete customer: " + customer);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<?> getAllCustomers(){
        List<Customer> customerListFromDB = adminServiceImplementation.getAllCustomers();
        System.out.println("got: " + customerListFromDB);
        ResponseEntity<List<Customer>> responseEntity = new ResponseEntity<>(customerListFromDB, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/getOneCustomerByID/{id}")
    public ResponseEntity<?> getOneCustomer(@PathVariable int id){
        Customer customerFromDB = adminServiceImplementation.getOneCustomer(id);
        System.out.println("got: " + customerFromDB);
        ResponseEntity<Customer> responseEntity = new ResponseEntity<>(customerFromDB, HttpStatus.OK);
        return responseEntity;
    }

    @Override
    public boolean login(String email, String password) throws SQLException, InterruptedException {
        boolean isAuth = adminServiceImplementation.login(email,password);
        return isAuth;
    }
}