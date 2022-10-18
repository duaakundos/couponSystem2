package com.example.couponSystem2.tokensManager;

import com.example.couponSystem2.loginManager.ClientType;

public class AuthDetails {
    private String email;
    private String password;
    private ClientType clientType;

    public AuthDetails(String email, String password, ClientType clientType) {
        this.email = email;
        this.password = password;
        this.clientType = clientType;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ClientType getClientType() {
        return clientType;
    }
}
