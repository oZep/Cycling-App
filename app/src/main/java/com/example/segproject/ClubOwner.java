package com.example.segproject;

public class ClubOwner extends UserAccount{
    public ClubOwner(String u, String e, String p) {
        username = u;
        email = e;
        password = p;
    }

    public ClubOwner(String e, String p) {
        email = e;
        password = p;
    }
}
