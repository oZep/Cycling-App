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
    AdminEventDBHandler db;
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
        setContentView(R.layout.activity_clubowner_handle_edit_event);
        db = new AdminEventDBHandler(this);

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

        Intent intent = getIntent();
        String eventNames = intent.getStringExtra("eventName");
        super.onCreate(savedInstanceState);



        // TODO: Fillin the info using what i did in another class
        /*
        eventName.setText(usernamefill);
        eventType.setText(userAccount.getContact());
        eventLocation.setText(userAccount.getPhoneNum());
        eventParticipants.setText(userAccount.getSocialMedia());

        */

        finishEvent.setOnClickListener(new View.OnClickListener() {
            String eventNamed = eventName.getText().toString();
            EventType eventTyped = eventType.getText().toString();
            String location = eventLocation.getText().toString();
            String day2 = day.getText().toString();
            String month2 = month.getText().toString();
            String year2 = year.getText().toString();
            String part = eventParticipants.getText().toString();
            public void onClick(View view) {
                if(TextUtils.isEmpty(eventTyped)){
                    Toast.makeText(EditEvent.this, "Select a Event Type", Toast.LENGTH_SHORT).show();
                    return;
                }
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
                } else {
                    int y = 0, m = 0, d = 0;
                    try {
                        y = Integer.parseInt(year2);
                        m = Integer.parseInt(month2);
                        d = Integer.parseInt(day2);
                        if (validDate(y,m,d)) {
                            Toast.makeText(EditEvent.this, "Enter a Proper Date", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    catch (NumberFormatException e) {
                        Toast.makeText(EditEvent.this, "Enter a Proper Date", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    // TODO: add the new event and delete the old
                    Calendar c = Calendar.getInstance();
                    c.set(y,m,d);
                    Event et = new Event(eventNamed, eventType, c.getTime(), location, Integer.parseInt(eventParticipants.getText().toString()));

                    Toast.makeText(EditEvent.this,"Event Edited successfully", Toast.LENGTH_SHORT).show();

                }

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
                Intent intent = new Intent(getApplicationContext(), AdminViewEvents.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
}
