package com.example.couponSystem2.controllers;

import java.sql.SQLException;

public abstract class  ClientController {

    public abstract boolean login (String email,String password) throws SQLException, InterruptedException;
}
