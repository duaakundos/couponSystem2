package com.example.couponSystem2.aop;

import com.example.couponSystem2.entities.Company;
import com.example.couponSystem2.myException.AuthorizationException;
import com.example.couponSystem2.myException.CouponSystemException;
import com.example.couponSystem2.myException.enums.AdminEnumException;
import com.example.couponSystem2.myException.enums.GeneralEnumException;
import com.example.couponSystem2.tokensManager.TokensManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;


@Aspect
@Component
@RestController
public class MyAspect {

    @Autowired
    TokensManager tokensManager;

    // && args(token) , String token
    @Before(value = "execution(* com.example.couponSystem2.controllers.main.*.*(..))")
    public void handleIsSessionTokenExists(JoinPoint joinPoint) {
        String token = "";
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            if (joinPoint.getArgs()[i] instanceof String && ((String) joinPoint.getArgs()[i]).contains("token")) {
                System.out.println("my Token " + joinPoint.getArgs()[i]);
                token = joinPoint.getArgs()[i].toString();
            }
        }
        System.out.println("Aspect isSessionTokenExists: " + tokensManager.isSessionTokenExists(token));
        if (!tokensManager.isSessionTokenExists(token)) {
            System.out.println("Throwing Exception");
            throw new AuthorizationException(GeneralEnumException.SESSION_EXPIRED.toString());
        }
    }
}
