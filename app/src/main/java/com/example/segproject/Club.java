package com.example.segproject;

import java.util.ArrayList;

public class Club {

    String username, clubName;
    ArrayList<Events> events;

    public Club(String username, String clubName, ArrayList<Events> events) {
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

    public void setEvents(ArrayList<Events> events) {
        this.events = events;
    }
    public String getClubName() {
        return clubName;
    }
    public String getUsername() {
        return username;
    }
    public ArrayList<Events> getEvents() {
        return events;
    }
}
