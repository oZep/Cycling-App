package com.example.segproject;

public class Admin{
    private String username;
    private String password;
    private Admin admin = new Admin();
    private Admin() {
        username = "admin";
        password = "admin";
    }
    public Admin getInstance() {
        return admin;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
