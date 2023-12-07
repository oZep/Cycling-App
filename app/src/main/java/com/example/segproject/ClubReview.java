package com.example.segproject;

public class ClubReview {
    private Participant rater;
    private Club ratee;
    private int rating;
    private String comment;

    public ClubReview(Participant p, Club c, int stars, String words) {
        rater = p;
        ratee = c;
        rating = stars;
        comment = words;
        rater.addReview(c, this);
        ratee.addReview(this);
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public Participant getRater() {
        return rater;
    }

    public Club getRatee() {
        return ratee;
    }
}
