package com.example.segproject;

public class Admin extends Account{
    public Admin(String u, String e, String p) {
        username = u;
        email = e;
        password = p;
    }

    public Admin(String e, String p) {
        email = e;
        password = p;
    }
}
