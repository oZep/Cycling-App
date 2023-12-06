package com.example.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EventPopup extends AppCompatActivity {
    Button goBackButton, goBackToHomepage, deleteEvent, editEvents;
    String eventN;
    EventDBHandler db;
    EventTypeDBHandler etdb;
    ClubDBHandler cdb;
    AccountDBHandler adb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_popup);
        goBackButton = findViewById(R.id.goBackButton);
        goBackToHomepage = findViewById(R.id.goToHomepageAdmin);
        deleteEvent = findViewById(R.id.deleteEvent_btn);
        editEvents = findViewById(R.id.editEvent);
        db = new EventDBHandler(this);
        etdb = new EventTypeDBHandler(this);
        cdb = new ClubDBHandler(this);
        Intent intent = getIntent();
        eventN = intent.getStringExtra("eventN");


        deleteEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String clubName = db.getEvent(eventN, cdb, etdb, adb).getClub().getClubName();
                db.deleteEvent(eventN);
                Intent intent = new Intent(getApplicationContext(), ClubOwnerManageActivities.class);
                intent.putExtra("clubName", clubName);
                startActivity(intent);
                finish();
            }
        });

        editEvents.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditEvent.class);
                intent.putExtra("eventN", eventN);
                startActivity(intent);
                finish();
            }
        });

        goBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ClubOwnerManageActivities.class);
                startActivity(intent);
                finish();
            }
        });

        goBackToHomepage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
