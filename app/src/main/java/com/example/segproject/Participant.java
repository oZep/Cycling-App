package com.example.segproject;

public class Participant extends Account{
    public Participant(String u, String e, String p) {
        username = u;
        email = e;
        password = p;
    }

    public Participant(String e, String p) {
        email = e;
        password = p;
    }
}
