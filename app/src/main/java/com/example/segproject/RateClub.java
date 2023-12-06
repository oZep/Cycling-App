package com.example.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

public class RateClub extends AppCompatActivity {

    String username, clubowner;

    ClubDBHandler cdb;

    EventDBHandler edb;
    EventTypeDBHandler etdb;

    AccountDBHandler adb;
    Button goBack, Submit;

    RatingBar score;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_club);
        goBack = findViewById(R.id.goBack3);
        Submit = findViewById(R.id.submit);
        score = findViewById(R.id.ratingBar);
        cdb = new ClubDBHandler(this);
        etdb = new EventTypeDBHandler(this);
        edb = new EventDBHandler(this);
        adb = new AccountDBHandler(this);


        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        clubowner = intent.getStringExtra("clubowner");


        goBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
               float rating = score.getRating();
               int rate = (int) rating;
               Club club = cdb.getClub(clubowner, etdb, edb, adb);

               // TODO: add rating to club







                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
