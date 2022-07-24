package com.example.couponSystem2.loginManager;

import com.example.couponSystem2.oldServices.CompanyServiceOld;
import com.example.couponSystem2.oldServices.CustomerServiceOld;
import com.example.couponSystem2.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
@Scope("singleton")
public class LoginManager {

    @Autowired
    CompanyServiceImplementation companyServiceImplementation;
    @Autowired
    CustomerServiceImplementation customerServiceImplementation;
    @Autowired
    AdminServiceImplementation adminServiceImplementation;

    public ClientService login(String email, String password, ClientType clientType) throws SQLException, InterruptedException {
        if (clientType.equals(ClientType.Administrator) && adminServiceImplementation.login(email, password)) {
            return adminServiceImplementation;
        } else if (clientType.equals(ClientType.Company) && companyServiceImplementation.login(email, password)) {
            return companyServiceImplementation;
        } else if (clientType.equals(ClientType.Customer) && customerServiceImplementation.login(email, password)) {
            return customerServiceImplementation;
        } else return null;
    }
}
