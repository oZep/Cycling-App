package com.example.segproject;

public class ClubOwner extends UserAccount{
    private String socialMedia;
    private String contact;
    private String phoneNum;

    public ClubOwner(String u, String p, String sm, String c, String pn) {
        username = u;
        password = p;
        socialMedia = sm;
        contact = c;
        phoneNum = pn;
    }

    public String getSocialMedia() {
        return socialMedia;
    }

    public String getContact() {
        return contact;
    }

    public String getPhoneNum() {
        return phoneNum;
    }
}
