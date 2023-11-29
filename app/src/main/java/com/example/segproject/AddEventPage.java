package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddEventPage  extends AppCompatActivity {

    String clubName;
    Button goBackButton, finishEvent, viewEvent;
    EventTypeDBHandler db;
    EventDBHandler edb;
    ClubDBHandler cdb;
    EditText eventName, eventType, eventLocation, eventParticipants, day, month, year;
    public static boolean validDate(int y, int m, int d) {
        byte[] moreDays = {0, 2, 4, 6, 7, 9, 11};
        if (m < 0 || m > 11 || y < 2023 || d < 1) {
            return false;
        }
        for (byte i : moreDays) {
            if (m == i) {
                return d < 32;
            }
        }
        if (m == 1) {
            return d < 29 || (y % 4 == 0 && (y % 100 != 0 || y % 400 == 0) && d == 29);
        }
        return d < 31;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event_page);
        Intent intent = getIntent();
        clubName = intent.getStringExtra("clubName");


        db = new EventTypeDBHandler(this);
        cdb = new ClubDBHandler(this);
        edb = new EventDBHandler(this);

        goBackButton = findViewById(R.id.goBackButton);
        finishEvent = findViewById(R.id.addEvent);
        viewEvent = findViewById(R.id.viewEventsButton);
        eventName = findViewById(R.id.eventName);
        eventType = findViewById(R.id.eventTypes);
        eventLocation = findViewById(R.id.location);
        eventParticipants = findViewById(R.id.numParticipants);
        day = findViewById(R.id.day);
        month = findViewById(R.id.month);
        year = findViewById(R.id.year);

        finishEvent.setOnClickListener(new View.OnClickListener() {
            String eventNamed = eventName.getText().toString();
            String eventTyped = eventType.getText().toString();
            String location = eventLocation.getText().toString();
            String day2 = day.getText().toString();
            String month2 = month.getText().toString();
            String year2 = year.getText().toString();
            String part = eventParticipants.getText().toString();
            public void onClick(View view) {
                if(TextUtils.isEmpty(eventTyped)){
                    Toast.makeText(AddEventPage.this, "Select a Event Type", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(eventNamed)){
                    Toast.makeText(AddEventPage.this, "Enter a Event Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(location)){
                    Toast.makeText(AddEventPage.this, "Enter a Location", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(part) && (Integer.parseInt(eventParticipants.getText().toString()) > 0)){
                    Toast.makeText(AddEventPage.this, "Enter Number of Participants", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(day2) && TextUtils.isEmpty(month2) && TextUtils.isEmpty(year2)) {
                    Toast.makeText(AddEventPage.this, "Enter a Proper Date", Toast.LENGTH_SHORT).show();
                    return;
                }
                int y = 0, m = 0, d = 0;
                try {
                    y = Integer.parseInt(year2);
                    m = Integer.parseInt(month2);
                    d = Integer.parseInt(day2);
                }
                catch (NumberFormatException e) {
                    Toast.makeText(AddEventPage.this, "Enter a Proper Date", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!validDate(y, m, d)) {
                    Toast.makeText(AddEventPage.this, "Enter a Proper Date", Toast.LENGTH_SHORT).show();
                    return;
                }
                Calendar c = Calendar.getInstance();
                c.set(y, m, d);
                Event e = new Event(eventNamed, db.getEventType(eventTyped), cdb.getClub(clubName, db), c.getTime(), location, Integer.parseInt(eventParticipants.getText().toString()));
                edb.insertEvent(e);
                Toast.makeText(AddEventPage.this,"Event Edited successfully", Toast.LENGTH_SHORT).show();
            }
        });


        goBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminManageActivities.class);
                startActivity(intent);
                finish();
            }
        });

        viewEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminViewEventTypes.class);
                startActivity(intent);
                finish();
            }
        });
    }
}