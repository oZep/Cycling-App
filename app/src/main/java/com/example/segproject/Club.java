package com.example.segproject;

import java.util.ArrayList;

public class Club {

    String username, clubName;
    ArrayList<EventType> eventTypes;

    public Club(String username, String clubName, ArrayList<EventType> et) {
        this.username = username;
        this.clubName = clubName;
        this.eventTypes = et;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEvents(ArrayList<EventType> et) {
        this.eventTypes = et;
    }
    public String getClubName() {
        return clubName;
    }
    public String getUsername() {
        return username;
    }
    public ArrayList<EventType> getEventTypes() {
        return eventTypes;
    }
}
