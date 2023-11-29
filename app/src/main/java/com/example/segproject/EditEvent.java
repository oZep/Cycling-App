package com.example.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.;

public class AdminHandleEditEvent extends AppCompatActivity {


    Button goBackButton, finishEvent, viewEvent;
    AdminEventDBHandler db;
    EditText eventName, eventType, eventLocation, eventParticipants, day, month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_handle_edit_event);
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
                    Toast.makeText(AdminHandleEditEvent.this, "Select a Event Type", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(eventNamed)){
                    Toast.makeText(AdminHandleEditEvent.this, "Enter a Event Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(location)){
                    Toast.makeText(AdminHandleEditEvent.this, "Enter a Location", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(part) && (Integer.parseInt(eventParticipants.getText().toString()) > 0)){
                    Toast.makeText(AdminHandleEditEvent.this, "Enter Number of Participants", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(day2) && TextUtils.isEmpty(month2) && TextUtils.isEmpty(year2)) {
                    Toast.makeText(AdminHandleEditEvent.this, "Enter a Proper Date", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    // TODO: add the new event and delete the old
                    Event et = new Event(eventNamed, eventType, Calender.set(year, month, day).getTime(), location, Integer.parseInt(eventParticipants.getText().toString()))

                    Toast.makeText(AdminHandleEditEvent.this,"Event Edited successfully", Toast.LENGTH_SHORT).show();

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
