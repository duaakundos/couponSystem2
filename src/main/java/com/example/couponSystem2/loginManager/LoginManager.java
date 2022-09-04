package com.example.couponSystem2.loginManager;

import com.example.couponSystem2.myException.CouponSystemException;
import com.example.couponSystem2.myException.enums.CouponEnumException;
import com.example.couponSystem2.myException.enums.GeneralEnumException;
import com.example.couponSystem2.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
@Scope("singleton")
public class LoginManager {

    private final AdminService adminService;
    private final ApplicationContext ctx;

    public ClientService login(String email, String password, ClientType clientType) throws SQLException, InterruptedException {
        ClientService clientService = null;
        switch (clientType) {
            case Administrator:
                clientService = (ClientService) adminService;
                break;
            case Company:
                clientService = (ClientService) ctx.getBean(CompanyService.class);
                break;
            case Customer:
                clientService = (ClientService) ctx.getBean(CustomerService.class);
                break;
        }

        if (!clientService.login(email, password)) {
            throw new CouponSystemException(GeneralEnumException.INVALID_PASSWORD_OR_EMAIL);
        }
        return clientService;
    }
}
