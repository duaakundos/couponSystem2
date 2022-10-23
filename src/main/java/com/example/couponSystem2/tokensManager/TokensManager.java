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
import java.util.Map;

@Component
@EnableScheduling
public class TokensManager {
    //    private static Set<Token> activeTokens = new HashSet<>();
    private static Map<Token, ClientService> activeTokenServiceMap = new HashMap<>();
    //    private static Set<Token> inActiveTokens = new HashSet<>();
    private static Map<Token, ClientService> inActiveTokenServiceMap = new HashMap<>();
    @Autowired
    LoginManager loginManager;

    public Token createToken(AuthDetails authDetails) throws SQLException, InterruptedException {
        Token token = new Token(authDetails);
        System.out.println("new token: " + token + ", session end time: " + token.getEndSessionTime());
        System.out.println("token user details: email: " + token.getAuthDetails().getEmail() + ", password: " + token.getAuthDetails().getPassword() + ", client type: " + token.getAuthDetails().getClientType());
        System.out.println("implementation: " + createService(authDetails));
        activeTokenServiceMap.put(token, createService(authDetails));
        return token;
    }


    public ClientService createService(AuthDetails authDetails) throws SQLException, InterruptedException {
        System.out.println("print login manager" + loginManager);
        return loginManager.login(authDetails.getEmail(), authDetails.getPassword(), authDetails.getClientType());
    }

    public boolean isSessionTokenExists(String token) {
        Token tmpToken = new Token();
        tmpToken.setToken(token);
        System.out.println(" key: " + activeTokenServiceMap.containsKey(tmpToken));
        return activeTokenServiceMap.containsKey(tmpToken);
    }


    public ClientService getService(String token) {
        Token tmpToken = new Token();
        tmpToken.setToken(token);
        return activeTokenServiceMap.get(tmpToken);
    }

    @Scheduled(fixedDelay = 30000)
    public void scheduleFixedDelayTask() {
        System.out.println(
                "Number of Active map Sessions: " + activeTokenServiceMap.size());
        for (Token token : activeTokenServiceMap.keySet()) {
            if (LocalDateTime.now().isAfter(token.getEndSessionTime())) {
                System.out.println("token.getEndSessionTime(): " + token.getEndSessionTime());
                inActiveTokenServiceMap.put(token, null);
            }
        }
        activeTokenServiceMap.keySet().removeAll(inActiveTokenServiceMap.keySet());
        System.out.println(
                "Number of Active map Sessions after remove: " + activeTokenServiceMap.size());
        inActiveTokenServiceMap.clear();


        activeTokenServiceMap.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " " + entry.getValue());
        });
    }
}
