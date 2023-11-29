package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminManageActivities extends AppCompatActivity {
    Button goBackButton, goBackToHomepage, goToAddEventPage, viewEvents, deleteEvent, editEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_activities);
        goBackButton = findViewById(R.id.goBackButton);
        goBackToHomepage = findViewById(R.id.goToHomepageAdmin);
        goToAddEventPage = findViewById(R.id.addevent);
        viewEvents = findViewById(R.id.viewEvents_btn);
        deleteEvent = findViewById(R.id.deleteEvent_btn);
        editEvents = findViewById(R.id.editEvent);



        viewEvents.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminViewEventTypes.class);
                startActivity(intent);
                finish();
            }
        });


        //event adder button
        Button  addevent = findViewById(R.id.addevent);

        addevent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        Button deleteEvent = (Button) findViewById(R.id.deleteEvent_btn);
        deleteEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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
                Intent intent = new Intent(getApplicationContext(), AdminLoginScreen.class);
                startActivity(intent);
                finish();
            }
        });

        goToAddEventPage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminHandleAddEventType.class);
                startActivity(intent);
                finish();
            }
        });

        deleteEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminHandleDeleteEventType.class);
                startActivity(intent);
                finish();
            }
        });

        editEvents.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditEvent.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

