package com.example.couponSystem2.dailyJob;

import com.example.couponSystem2.repositories.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Scope("singleton")
@Component
public class CouponExpirationDailyJob implements Runnable {

    @Autowired
    private CouponRepository couponRepository ;
    private boolean quit = false;
    private boolean firstRun = true;
    private LocalDateTime currentTime = LocalDateTime.now();
    private LocalDateTime currentTimePlus24Hours = currentTime.plusHours(24);
    private final int sleepTime = 1000 * 30;/// 30 seconds
    public void stop() {
        System.out.println("init stop function");
        this.quit = true;
    }

    private void deleteExpiredCoupons() {
        int numberOfDeletedRows = couponRepository.deleteAllByEndDateLessThan(Date.valueOf(LocalDate.now()));
        System.out.println("number of deleted rows: " + numberOfDeletedRows);
    }

    @Override
    public void run() {
        System.out.println("Thread starting");
        while (!quit) {
            currentTime = LocalDateTime.now();
            try {
                Thread.sleep(sleepTime);
                if (firstRun) {
                    deleteExpiredCoupons();
                    firstRun = false;
                }
                if (currentTimePlus24Hours.isBefore(currentTime)) {
                    currentTimePlus24Hours = currentTimePlus24Hours.plusHours(24);
                    deleteExpiredCoupons();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread is Done ");
    }
}
