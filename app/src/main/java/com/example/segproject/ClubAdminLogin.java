package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ClubAdminLogin extends AppCompatActivity {
    Button goBackButton, goToProfilePage, goToViewEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_club_admin_login);
        goBackButton = findViewById(R.id.goBackButton);
        goToProfilePage = findViewById(R.id.btn_profile_editor);
        goToViewEvents = findViewById(R.id.btn_view_event_types);
        Intent intent = getIntent();
        String Username = intent.getStringExtra("Username");
        super.onCreate(savedInstanceState);

        goBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        goToViewEvents.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ClubAdminEstablishEventType.class);
                startActivity(intent);
                finish();
            }
        });

        goToProfilePage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ClubAdminProfilePage.class);
                intent.putExtra("Username", Username);
                startActivity(intent);
                finish();
            }
        });
    }
}