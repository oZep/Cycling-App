package com.example.segproject;

import java.util.Date;

public class Event {
    private EventType type;
    private String location;
    private Date date;
    private int maxParticipants;
    private String name;

    public Event(String n, EventType et, String l, Date d, int max) {
        type = et;
        name = n;
        location = l;
        date = d;
        maxParticipants = max;
    }

    public void setName(String n) {
        name = n;
    }
    public void setEventType(EventType et) {
        type = et;
    }
    public void setDate(Date d) {
        date = d;
    }
    public void setLocation(String l) {
        location = l;
    }
    public void setMaxParticipants(int max) {
        maxParticipants = max;
    }
    public String getName() {
        return name;
    }
    public EventType getEventType() {
        return type;
    }
    public String getLocation() {
        return location;
    }
    public Date getDate() {
        return date;
    }
    public int getMaxParticipants() {
        return maxParticipants;
    }


}
