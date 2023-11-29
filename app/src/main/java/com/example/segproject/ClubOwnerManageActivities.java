package com.example.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ClubOwnerManageActivities extends AppCompatActivity {
    Button goBackButton, goToAddEventPage, viewEvents;
    String clubName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_owner_manage_activites);
        goBackButton = findViewById(R.id.goBackButton);
        goToAddEventPage = findViewById(R.id.addevent);
        viewEvents = findViewById(R.id.viewEvents_btn);

        Intent intent = getIntent();
        clubName = intent.getStringExtra("clubName");

        goBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });


        goToAddEventPage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddEventPage.class);
                startActivity(intent);
                intent.putExtra("clubName", clubName);
                finish();
            }
        });


        viewEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EventAdapter.class);
                startActivity(intent);
                intent.putExtra("clubName", clubName);
                finish();
            }
        });

    }
}



