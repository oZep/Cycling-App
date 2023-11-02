package com.example.firebaseapp2;

public class AdminAccount extends UserAccount{
    public AdminAccount(String u, String e, String p) {
        super(u, e, p);
    }

    public AdminAccount(String e, String p) {
        super(e, p);
    }
}
