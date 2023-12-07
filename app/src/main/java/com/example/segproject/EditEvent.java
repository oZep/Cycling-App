package com.example.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class EditEvent extends AppCompatActivity {


    Button goBackButton, finishEvent, viewEvent;
    EventTypeDBHandler db;
    EventDBHandler edb;

    ClubDBHandler cdb;
    AccountDBHandler adb;

    String eventN;
    EditText eventName, eventLocation, eventParticipants, day, month, year;
    public static boolean validDate(int y, int m, int d) {
        byte[] moreDays = {0, 2, 4, 6, 7, 9, 11};
        if (m < 1 || m > 12 || y < 2023 || d < 1) {
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
        setContentView(R.layout.activity_club_owner_handle_edit_event);
        db = new EventTypeDBHandler(this);
        cdb = new ClubDBHandler(this);
        edb = new EventDBHandler(this);
        adb = new AccountDBHandler(this);


        goBackButton = findViewById(R.id.goBackButton);
        finishEvent = findViewById(R.id.addEvent);
        viewEvent = findViewById(R.id.viewEventsButton);
        eventName = findViewById(R.id.eventName);
        eventLocation = findViewById(R.id.locale);
        eventParticipants = findViewById(R.id.cap);
        day = findViewById(R.id.day);
        month = findViewById(R.id.month);
        year = findViewById(R.id.year);

        Intent intent = getIntent();
        eventN = intent.getStringExtra("eventN");



        // TODO: Fillin the info using what i did in another class, find the clubName in the database & fill out
        Event event = edb.getEvent(eventN, cdb, db, adb);
        eventName.setText(event.getName());
        eventLocation.setText(event.getLocation());
        eventParticipants.setText(((Integer) event.getMaxParticipants()).toString());
        Calendar c = Calendar.getInstance();
        c.setTime(event.getDate());
        day.setText(((Integer) c.get(Calendar.DAY_OF_MONTH)).toString());
        month.setText(((Integer) (c.get(Calendar.MONTH) + 1)).toString());
        year.setText(((Integer) c.get(Calendar.YEAR)).toString());

        finishEvent.setOnClickListener(new View.OnClickListener() {
            String eventNamed = eventName.getText().toString();
            String location = eventLocation.getText().toString();
            String day2 = day.getText().toString();
            String month2 = month.getText().toString();
            String year2 = year.getText().toString();
            String part = eventParticipants.getText().toString();
            Club club = event.getClub();
            public void onClick(View view) {
                if(TextUtils.isEmpty(eventNamed)){
                    Toast.makeText(EditEvent.this, "Enter a Event Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(location)){
                    Toast.makeText(EditEvent.this, "Enter a Location", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(part) && (Integer.parseInt(eventParticipants.getText().toString()) > 0)){
                    Toast.makeText(EditEvent.this, "Enter Number of Participants", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(day2) && TextUtils.isEmpty(month2) && TextUtils.isEmpty(year2)) {
                    Toast.makeText(EditEvent.this, "Enter a Proper Date", Toast.LENGTH_SHORT).show();
                    return;
                }
                int y = 0, m = 0, d = 0;
                try {
                    y = Integer.parseInt(year2);
                    m = Integer.parseInt(month2);
                    d = Integer.parseInt(day2);
                }
                catch (NumberFormatException e) {
                    Toast.makeText(EditEvent.this, "Enter a Proper Date", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!validDate(y, m, d)) {
                    Toast.makeText(EditEvent.this, "Enter a Proper Date", Toast.LENGTH_SHORT).show();
                    return;
                }
                // TODO: add the new event and delete the old
                Calendar c = Calendar.getInstance();
                c.set(y, m - 1, d);
                Event e = new Event(eventNamed.toLowerCase(), club.getEventType(), club, c.getTime(), location.toLowerCase(), Integer.parseInt(eventParticipants.getText().toString()));
                edb.deleteEvent(eventN);
                edb.insertEvent(e);
                Toast.makeText(EditEvent.this,"Event Edited successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), EventList.class);
                intent.putExtra("clubName", club.getUsername());
                startActivity(intent);
                finish();
            }
        });


        goBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String clubName = event.getClub().getClubName();
                Intent intent = new Intent(getApplicationContext(), ClubOwnerManageActivities.class);
                intent.putExtra("clubName", clubName);
                startActivity(intent);
                finish();
            }
        });

        viewEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String clubName = event.getClub().getClubName();
                Intent intent = new Intent(getApplicationContext(), EventList.class);
                intent.putExtra("clubName", clubName);
                startActivity(intent);
                finish();
            }
        });
    }
}