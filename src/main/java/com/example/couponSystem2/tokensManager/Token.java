package com.example.couponSystem2.tokensManager;

import com.example.couponSystem2.loginManager.LoginManager;
import com.example.couponSystem2.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class Token {
    private static int TOKEN_COUNTER = 1;
    private int sessionDurationInMinutes = 1; //30 minutes
    private String token = "token_" + (TOKEN_COUNTER++) + "_" + Math.random() * Math.random();
    private LocalDateTime startSessionTime = LocalDateTime.now();
    private LocalDateTime endSessionTime = startSessionTime.plusMinutes(sessionDurationInMinutes);
    private AuthDetails authDetails;
//    LoginManager loginManager; //= new LoginManager(adminService,ctx);

    public Token(AuthDetails authDetails) throws SQLException, InterruptedException {
        this.authDetails = authDetails;
//        getService();
    }

    public String getToken() {
        return token;
    }

    public LocalDateTime getEndSessionTime() {
        return endSessionTime;
    }

    public AuthDetails getAuthDetails() {
        return authDetails;
    }

//        public ClientService getService() throws SQLException, InterruptedException {
////        LoginManager loginManager1 = new LoginManager()
//        System.out.println("print login manager" + loginManager);
//                    return loginManager.login(authDetails.getEmail(),authDetails.getPassword(),authDetails.getClientType());
//    }
}
