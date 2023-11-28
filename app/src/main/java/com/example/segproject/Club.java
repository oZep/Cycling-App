package com.example.segproject;

import java.util.ArrayList;

public class Club {

    String username, clubName;
    ArrayList<String> events;

    public Club(String username, String clubName, ArrayList<String> events) {
        this.username = username;
        this.clubName = clubName;
        this.events = events;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEvents(ArrayList<String> events) {
        this.events = events;
    }
    public String getClubName() {
        return clubName;
    }
    public String getUsername() {
        return username;
    }
    public ArrayList<String> getEvents() {
        return events;
    }
}
