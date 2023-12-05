package com.example.segproject;

import java.util.ArrayList;
import java.util.Date;

public class Event {
    private EventType type;
    private String location;
    private Date date;
    private int maxParticipants;
    private String name;
    private Club club;
    private ArrayList<Participant> participants;

    public Event(String n, EventType et, Club c, Date d, String l, int max) {
        type = et;
        name = n;
        location = l;
        date = d;
        maxParticipants = max;
        club = c;
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
    public void addParticipant(Participant p) {
        participants.add(p);
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
    public Club getClub() {
        return club;
    }
    public ArrayList<Participant> getParticipants() {
        return participants;
    }

}
