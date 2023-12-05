package com.example.segproject;

public class ClubReview {
    private Participant rater;
    private Club ratee;
    private int rating;

    public ClubReview(Participant p, Club c, int stars) {
        rater = p;
        ratee = c;
        rating = stars;
        rater.addReview(c, this);
        ratee.addReview(this);
    }

    public int getRating() {
        return rating;
    }

    public Participant getRater() {
        return rater;
    }

    public Club getRatee() {
        return ratee;
    }
}
