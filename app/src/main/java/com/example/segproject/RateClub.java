package com.example.segproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class RateClub extends AppCompatActivity {

    String username;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_club);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
    }
}
