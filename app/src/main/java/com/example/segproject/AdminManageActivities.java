package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminManageActivities extends AppCompatActivity {

    Button goBackButton;
    Button goBackToHomepage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_activities);
        goBackButton = findViewById(R.id.goBackButton);
        goBackToHomepage = findViewById(R.id.goToHomepageAdmin);

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

