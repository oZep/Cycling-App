package com.example.segproject;

import java.util.ArrayList;

public class Club {

    String username, clubName;
    ArrayList<EventType> eventTypes;
    ArrayList<Event> events;

    public Club(String username, String clubName, ArrayList<EventType> et) {
        this.username = username;
        this.clubName = clubName;
        this.eventTypes = et;
    }

    public void addEvent(Event e) {
        events.add(e);
    }
    public void removeEvent(Event e) {
        events.remove(e);
    }
    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEventTypes(ArrayList<EventType> et) {
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
    public ArrayList<Event> getEvents() {
        return events;
    }
}
