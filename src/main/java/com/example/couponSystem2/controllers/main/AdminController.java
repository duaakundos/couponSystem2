package com.example.couponSystem2.controllers.main;


import com.example.couponSystem2.entities.Company;
import com.example.couponSystem2.entities.Customer;
import com.example.couponSystem2.services.AdminServiceImplementation;
import com.example.couponSystem2.tokensManager.TokensManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {
    @Autowired
    TokensManager tokensManager;

    @PostMapping("/addCompany")
    @ResponseBody
    public ResponseEntity<?> addCompany(@RequestBody Company company, @RequestHeader("token") String token) {
        System.out.println("Got: " + company);
        Company companyID = ((AdminServiceImplementation) tokensManager.getService(token)).addCompany(company);
        ResponseEntity<Company> responseEntity = new ResponseEntity<>(companyID, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/updateCompany")
//    @PostUpdate
    @ResponseBody
    public ResponseEntity<?> updateCompany(@RequestBody Company company, @RequestHeader("token") String token) {
        System.out.println("Got: " + company);
        ((AdminServiceImplementation) tokensManager.getService(token)).updateCompany(company);
        ResponseEntity<String> responseEntity = new ResponseEntity<>("successfully updated company", HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/deleteCompany") //
    @ResponseBody
    public ResponseEntity<?> deleteCompany(@RequestBody Company company, @RequestHeader("token") String token) {
        ((AdminServiceImplementation) tokensManager.getService(token)).deleteCompany(company);
        System.out.println("delete company: " + company);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping("/getAllCompanies")
    public ResponseEntity<?> getAllCompanies(@RequestHeader("token") String token) {
        System.out.println("myToken: " + token);
        List<Company> companyListFromDB = ((AdminServiceImplementation) tokensManager.getService(token)).getAllCompanies();
        System.out.println("got: " + companyListFromDB);
        ResponseEntity<List<Company>> responseEntity = new ResponseEntity<>(companyListFromDB, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/getOneCompanyByID/{id}")
    public ResponseEntity<?> getOneCompany(@PathVariable int id, @RequestHeader("token") String token) {
        Company companyFromDB = ((AdminServiceImplementation) tokensManager.getService(token)).getOneCompany(id);
        System.out.println("got: " + companyFromDB);
        ResponseEntity<Company> responseEntity = new ResponseEntity<>(companyFromDB, HttpStatus.OK);
        return responseEntity;
    }


    @PostMapping("/addCustomer")
    @ResponseBody
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer, @RequestHeader("token") String token) {
        System.out.println("Got: " + customer);
        Customer customerID = ((AdminServiceImplementation) tokensManager.getService(token)).addCustomer(customer);
        ResponseEntity<Customer> responseEntity = new ResponseEntity<>(customerID, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/updateCustomer")
    @ResponseBody
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @RequestHeader("token") String token) {
        System.out.println("Got: " + customer);
        ((AdminServiceImplementation) tokensManager.getService(token)).updateCustomer(customer);
        ResponseEntity<String> responseEntity = new ResponseEntity<>("successfully updated customer", HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/deleteCustomer")
    @ResponseBody
    public ResponseEntity<?> deleteCustomer(@RequestBody Customer customer, @RequestHeader("token") String token) {
        ((AdminServiceImplementation) tokensManager.getService(token)).deleteCustomer(customer.getId());
        System.out.println("delete customer: " + customer);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<?> getAllCustomers(@RequestHeader("token") String token) {
        List<Customer> customerListFromDB = ((AdminServiceImplementation) tokensManager.getService(token)).getAllCustomers();
        System.out.println("got: " + customerListFromDB);
        ResponseEntity<List<Customer>> responseEntity = new ResponseEntity<>(customerListFromDB, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/getOneCustomerByID/{id}")
    public ResponseEntity<?> getOneCustomer(@PathVariable int id, @RequestHeader("token") String token) {
        Customer customerFromDB = ((AdminServiceImplementation) tokensManager.getService(token)).getOneCustomer(id);
        System.out.println("got: " + customerFromDB);
        ResponseEntity<Customer> responseEntity = new ResponseEntity<>(customerFromDB, HttpStatus.OK);
        return responseEntity;
    }

}
