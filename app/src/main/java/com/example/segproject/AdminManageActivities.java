package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminManageActivities extends AppCompatActivity {
    Button goBackButton, goBackToHomepage, viewEventTypes, addEventType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_activities);
        goBackButton = findViewById(R.id.goBackButton);
        goBackToHomepage = findViewById(R.id.goToHomepageAdmin);
        addEventType = findViewById(R.id.addEventType);
        viewEventTypes = findViewById(R.id.viewEvents_btn);


        viewEventTypes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminViewEventTypes.class);
                startActivity(intent);
                finish();
            }
        });

        addEventType.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminHandleAddEventType.class);
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
    }
}

