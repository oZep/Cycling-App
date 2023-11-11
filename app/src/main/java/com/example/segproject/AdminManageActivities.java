package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminManageActivities extends AppCompatActivity {
    Button goBackButton, goBackToHomepage, goToAddEventPage, viewEvents, deleteEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_activities);
        goBackButton = findViewById(R.id.goBackButton);
        goBackToHomepage = findViewById(R.id.goToHomepageAdmin);
        goToAddEventPage = findViewById(R.id.addEvent_btn);
        viewEvents = findViewById(R.id.viewEvents_btn);
        deleteEvent = findViewById(R.id.deleteEvent_btn);



        viewEvents.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminViewEvents.class);
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
                Intent intent = new Intent(getApplicationContext(), AdminHandleAddEvent.class);
                startActivity(intent);
                finish();
            }
        });

        deleteEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminHandleDeleteEvent.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

