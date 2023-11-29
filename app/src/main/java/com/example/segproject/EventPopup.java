package com.example.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EventPopup extends AppCompatActivity {
    Button goBackButton, goBackToHomepage, deleteEvent, editEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubowner_manage_activites);
        goBackButton = findViewById(R.id.goBackButton);
        goBackToHomepage = findViewById(R.id.goToHomepageAdmin);
        deleteEvent = findViewById(R.id.deleteEvent_btn);
        editEvents = findViewById(R.id.editEvent);
        Intent intent = getIntent();
        String eventN = intent.getStringExtra("eventN");



        deleteEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DeleteEventPage.class);
                intent.putExtra("clubName", clubName);
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
