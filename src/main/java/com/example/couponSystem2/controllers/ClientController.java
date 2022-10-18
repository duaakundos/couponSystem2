package com.example.couponSystem2.controllers;

import com.example.couponSystem2.tokensManager.AuthDetails;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;

public abstract class  ClientController {

    public abstract ResponseEntity<?> login (AuthDetails authDetails) throws SQLException, InterruptedException;
}
