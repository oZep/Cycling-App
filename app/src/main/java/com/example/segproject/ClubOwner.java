package com.example.segproject;

public class ClubOwner extends UserAccount{
    private String socialMedia;
    private String contact;
    private String phoneNum;

    public static final ClubOwner GCC_ADMIN = new ClubOwner("gccadmin", "GCCRocks!", "google.com", "", "1234");

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
