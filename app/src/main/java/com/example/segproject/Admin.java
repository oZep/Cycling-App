package com.example.segproject;

public class Admin{
    private String username;
    private String password;
    private static Admin admin = new Admin();
    private Admin() {
        username = "admin";
        password = "admin";
    }
    public static Admin getInstance() {
        return admin;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
