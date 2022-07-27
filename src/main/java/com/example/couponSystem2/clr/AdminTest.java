package com.example.couponSystem2.clr;

import com.example.couponSystem2.loginManager.ClientType;
import com.example.couponSystem2.loginManager.LoginManager;
import com.example.couponSystem2.myException.CouponSystemException;
import com.example.couponSystem2.myException.enums.AdminEnumException;
import com.example.couponSystem2.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
@Order(1)
@RequiredArgsConstructor
public class AdminTest implements CommandLineRunner {

    private AdminService adminService;
    private final LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {
        loginTest();
    }

    public void loginTest() {
        // Fail
        try {
            adminService = (AdminService) loginManager.login("admi@admin.com", "admin", ClientType.Administrator);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (CouponSystemException c){
            if (c.getErrorDetails() == AdminEnumException.ADMIN_NOT_FOUND){
                System.out.println("ADMIN_NOT_FOUND");
            }
        }
        // Success
        try {
            adminService = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.Administrator);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

