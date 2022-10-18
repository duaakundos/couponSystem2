package com.example.couponSystem2.tokensManager;

import com.example.couponSystem2.loginManager.LoginManager;
import com.example.couponSystem2.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
@EnableScheduling
public class TokensManager {
//    private static Set<Token> activeTokens = new HashSet<>();
    private static Map<Token, ClientService> activeTokenServiceMap = new HashMap<>();
//    private static Set<Token> inActiveTokens = new HashSet<>();
    private static Map<Token, ClientService> inActiveTokenServiceMap = new HashMap<>();
    @Autowired
    LoginManager loginManager;

    public Token getToken(AuthDetails authDetails) throws SQLException, InterruptedException {
        Token token = new Token(authDetails);
        System.out.println("new token: " + token + ", session end time: " + token.getEndSessionTime());
        System.out.println("token user details: email: " + token.getAuthDetails().getEmail() + ", password: " + token.getAuthDetails().getPassword() + ", client type: " + token.getAuthDetails().getClientType());
        System.out.println("implementation: " + getService(authDetails));
//        CompanyService companyService = (CompanyService) getService(authDetails);
//        activeTokens.add(token);
        activeTokenServiceMap.put(token, getService(authDetails));
        return token;
    }


    public ClientService getService(AuthDetails authDetails) throws SQLException, InterruptedException {
        System.out.println("print login manager" + loginManager);
        return loginManager.login(authDetails.getEmail(), authDetails.getPassword(), authDetails.getClientType());
    }

    @Scheduled(fixedDelay = 30000)
    public void scheduleFixedDelayTask() {
//        System.out.println(
//                "Number of Active Sessions: " + activeTokens.size());
        System.out.println(
                "Number of Active map Sessions: " + activeTokenServiceMap.size());
//        for (Token token : activeTokens) {
//            if (LocalDateTime.now().isAfter(token.getEndSessionTime())) {
//                System.out.println("token.getEndSessionTime(): " + token.getEndSessionTime());
//                inActiveTokens.add(token);
//            }
//        }
        for (Token token : activeTokenServiceMap.keySet()) {
            if (LocalDateTime.now().isAfter(token.getEndSessionTime())) {
                System.out.println("token.getEndSessionTime(): " + token.getEndSessionTime());
                inActiveTokenServiceMap.put(token, null);
            }
        }
//        activeTokens.removeAll(inActiveTokens);
        activeTokenServiceMap.keySet().removeAll(inActiveTokenServiceMap.keySet());
//        System.out.println(
//                "Number of Active Sessions after remove: " + activeTokens.size());
//        inActiveTokens.clear();

        System.out.println(
                "Number of Active map Sessions after remove: " + activeTokenServiceMap.size());
        inActiveTokenServiceMap.clear();


        activeTokenServiceMap.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " " + entry.getValue());
        });
    }
}
