package com.example.couponSystem2.controllers;

import com.example.couponSystem2.services.AdminServiceImplementation;
import com.example.couponSystem2.services.CompanyServiceImplementation;
import com.example.couponSystem2.services.CustomerServiceImplementation;
import com.example.couponSystem2.tokensManager.AuthDetails;
import com.example.couponSystem2.tokensManager.TokensManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController extends ClientController {
    @Autowired
    AdminServiceImplementation adminServiceImplementation;
    @Autowired
    CompanyServiceImplementation companyServiceImplementation;
    @Autowired
    CustomerServiceImplementation customerServiceImplementation;
    @Autowired
    TokensManager tokensManager;

    @Override
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDetails authDetails) throws SQLException, InterruptedException {
        System.out.println("here from web");
        System.out.println(authDetails.getEmail());
        System.out.println(authDetails.getPassword());
        System.out.println(authDetails.getClientType());
        boolean isAuth;
        if (authDetails.getClientType().toString().equals("Administrator")) {
            isAuth = adminServiceImplementation.login(authDetails.getEmail(), authDetails.getPassword());
        } else if (authDetails.getClientType().toString().equals("Company")) {
            isAuth = companyServiceImplementation.login(authDetails.getEmail(), authDetails.getPassword());
        } else {
            isAuth = customerServiceImplementation.login(authDetails.getEmail(), authDetails.getPassword());
        }
        if (isAuth) {
            String token = tokensManager.createToken(authDetails).getToken();
            System.out.println("token name: " + token);
            ResponseEntity<String> responseEntity = new ResponseEntity<>(token, HttpStatus.OK);
            return responseEntity;
        }
        return null;
    }
}
