package com.example.segproject;

import java.util.ArrayList;
import java.util.HashMap;

public class Participant extends UserAccount {
    private HashMap<Club, ClubReview> reviews;

    public Participant(String u, String p) {
        username = u;
        password = p;
        reviews = new HashMap<Club, ClubReview>();
    }

    public void addReview(Club c, ClubReview r) {
        reviews.put(c, r);
    }

    public ArrayList<ClubReview> getReviews() {
        return new ArrayList<ClubReview>(reviews.values());
    }

    public ClubReview findReview(Club c) {
        return reviews.get(c);
    }
}
