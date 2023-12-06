package com.example.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EventTypePopup extends AppCompatActivity {
    Button goBackButton, goBackToHomepage, deleteEventType, editEventType;
    String eventTypeN;
    EventTypeDBHandler db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_type_popup);
        goBackButton = findViewById(R.id.goBackButton);
        goBackToHomepage = findViewById(R.id.goToHomepageAdmin);
        deleteEventType = findViewById(R.id.deleteEvent_btn);
        editEventType = findViewById(R.id.editEvent);
        db = new EventTypeDBHandler(this);
        Intent intent = getIntent();
        eventTypeN = intent.getStringExtra("eventTypeN");


        deleteEventType.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                db.deleteEventType(eventTypeN);
                Intent intent = new Intent(getApplicationContext(), AdminViewEventTypes.class);
                startActivity(intent);
                finish();
            }
        });

        editEventType.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditEventType.class);
                intent.putExtra("eventN", eventTypeN);
                startActivity(intent);
                finish();
            }
        });

        goBackToHomepage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminViewEventTypes.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
