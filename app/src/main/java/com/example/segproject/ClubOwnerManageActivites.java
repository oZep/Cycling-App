package com.example.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ClubOwnerManageActivites extends AppCompatActivity {
    Button goBackButton, goBackToHomepage, goToAddEventPage, viewEvents, deleteEvent, editEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubowner_manage_activites);
        goBackButton = findViewById(R.id.goBackButton);
        goBackToHomepage = findViewById(R.id.goToHomepageAdmin);
        goToAddEventPage = findViewById(R.id.addevent);
        viewEvents = findViewById(R.id.viewEvents_btn);
        deleteEvent = findViewById(R.id.deleteEvent_btn);
        editEvents = findViewById(R.id.editEvent);

        Intent intent = getIntent();
        String clubName = intent.getStringExtra("clubName");




        deleteEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DeleteEventPage.class);
                intent.putExtra("clubName", clubName);
                startActivity(intent);
                finish();
            }
        });
        goBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        goBackToHomepage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        goToAddEventPage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddEventPage.class);
                startActivity(intent);
                finish();
            }
        });

        deleteEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DeleteEventPage.class);
                intent.putExtra("clubName", clubName);
                startActivity(intent);
                finish();
            }
        });

        viewEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EventAdapter.class);
                startActivity(intent);
                finish();
            }
        });

        editEvents.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditEvent.class);
                intent.putExtra("clubName", clubName);
                startActivity(intent);
                finish();
            }
        });
    }
}



