package com.example.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RateClub extends AppCompatActivity {

    String username, clubowner;

    ClubDBHandler cdb;

    EventDBHandler edb;
    EventTypeDBHandler etdb;

    AccountDBHandler adb;
    Button goBack, submit;

    TextView comment;

    RatingBar score;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_club);
        goBack = findViewById(R.id.goBack3);
        submit = findViewById(R.id.submit);
        score = findViewById(R.id.ratingBar);
        comment = findViewById(R.id.comment);
        cdb = new ClubDBHandler(this);
        etdb = new EventTypeDBHandler(this);
        edb = new EventDBHandler(this);
        adb = new AccountDBHandler(this);


        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        clubowner = intent.getStringExtra("clubowner");
        Participant user = (Participant) adb.getUser(username);


        goBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String comments = String.valueOf(comment.getText());
                float rating = score.getRating();
                int rate = (int) rating;
                Club club = cdb.getClub(clubowner, etdb, edb, adb);
                ClubReview rev = new ClubReview(user, club, rate, comments);
                adb.deleteUserData(username);
                cdb.deleteClubData(clubowner);
                cdb.insertUserData(club);
                adb.insertUserData(user);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
