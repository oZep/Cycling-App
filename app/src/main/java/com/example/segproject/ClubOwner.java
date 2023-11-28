package com.example.segproject;

public class ClubOwner extends UserAccount{
    private String socialMedia;
    private String contact;
    private String phoneNum;

    public ClubOwner(String e, String p, String sm, String c, String pn) {
        email = e;
        password = p;
        socialMedia = sm;
        contact = c;
        phoneNum = pn;
    }

    public ClubOwner(String e, String p, String sm, String pn) {
        email = e;
        password = p;
        socialMedia = sm;
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
