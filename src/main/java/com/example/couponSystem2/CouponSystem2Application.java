package com.example.couponSystem2;

import com.example.couponSystem2.tokensManager.TokensManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Timer;
import java.util.concurrent.Delayed;

@SpringBootApplication
@EnableAspectJAutoProxy
public class CouponSystem2Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx
                = SpringApplication.run(CouponSystem2Application.class, args);

//
//        TokensManager tokensManager = new TokensManager();
//        tokensManager.getToken();
//        try {
//            Thread.sleep(30000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        tokensManager.getToken();
//        tokensManager.getToken();
//        try {
//            Thread.sleep(30000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        tokensManager.getToken();
//        tokensManager.getToken();
//        try {
//            Thread.sleep(65000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        tokensManager.getToken();
//        System.out.println("tomer bitan end");
    }

}
