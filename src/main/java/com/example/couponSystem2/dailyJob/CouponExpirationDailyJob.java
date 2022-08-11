package com.example.couponSystem2.dailyJob;

import com.example.couponSystem2.oldServices.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Scope("singleton")
public class CouponExpirationDailyJob implements Runnable {

    //    private CouponDBDao couponDBDao = CouponDBDao.getInstance();
    @Autowired
    CouponService couponService;
    private boolean quit = false;
    private boolean firstRun = true;
    private LocalDateTime currentTime = LocalDateTime.now();
    private LocalDateTime currentTimePlus24Hours = currentTime.plusHours(24);
    private final int sleepTime = 1000 * 30;/// 30 seconds

    public void stop() {
        System.out.println("init stop function");
        this.quit = true;
    }

    @Override
    public void run() {
        while (!quit) {
            currentTime = LocalDateTime.now();
            try {

                Thread.sleep(sleepTime);
                if (firstRun) {
                            couponService.deleteExpiredCoupons();
                    firstRun = false;
                }
                if (currentTimePlus24Hours.isBefore(currentTime)) {
                    currentTimePlus24Hours = currentTimePlus24Hours.plusHours(24);
                    couponService.deleteExpiredCoupons();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread is Done ");
    }
}
