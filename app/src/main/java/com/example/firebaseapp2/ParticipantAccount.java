package com.example.firebaseapp2;

public class ParticipantAccount extends UserAccount{

    public ParticipantAccount(String u, String e, String p) {
        super(u, e, p);
    }

    public ParticipantAccount(String e, String p) {
        super(e, p);
    }
}
