package com.example.segproject;

import java.util.ArrayList;

public class Club {

    String username, clubName;
    EventType eventType;
    ArrayList<Event> events;
    ArrayList<Participant> participants;
    ArrayList<ClubReview> reviews;
    double rating = 0.0;

    public Club(String username, String clubName, EventType et) {
        this.username = username;
        this.clubName = clubName;
        this.eventType = et;
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

    public void setEventTypes(EventType et) {
        this.eventType = et;
    }

    public void addParticipant(Participant p) {
        participants.add(p);
    }

    public void addReview(ClubReview r) {
        rating = (rating * reviews.size() + r.getRating()) / (reviews.size() + 1.0);
        reviews.add(r);
    }

    public String getClubName() {
        return clubName;
    }
    public String getUsername() {
        return username;
    }
    public double getRating() {
        return rating;
    }
    public EventType getEventType() {
        return eventType;
    }
    public ArrayList<Event> getEvents() {
        return events;
    }
    public ArrayList<Participant> getParticipants() {
        return participants;
    }
    public ArrayList<ClubReview> getReviews() {
        return reviews;
    }

    public int hashCode() {
        return clubName.hashCode();
    }

    public boolean equals(Object o) {
        if (!(o instanceof Club)) {
            return false;
        }
        Club c = (Club) o;
        return c.clubName.equals(clubName);
    }
}
