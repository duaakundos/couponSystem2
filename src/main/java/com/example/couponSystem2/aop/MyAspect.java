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

    @Before(value = "execution(* com.example.couponSystem2.controllers.main.*.*(..)) && args(token)")
    public void handleIsSessionTokenExists(JoinPoint joinPoint, String token) {
        System.out.println("Aspect isSessionTokenExists: " + tokensManager.isSessionTokenExists(token));
        if (!tokensManager.isSessionTokenExists(token)) {
            System.out.println("Throwing Exception");
            throw new AuthorizationException(GeneralEnumException.SESSION_EXPIRED.toString());
        }
    }

}
