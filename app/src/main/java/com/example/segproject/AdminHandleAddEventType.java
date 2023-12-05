package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminHandleAddEventType extends AppCompatActivity {

    Button goBackButton, addEvent, viewEvent;
    EventTypeDBHandler db;
    EditText name, level, minAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_handle_add_event_type);
        db = new EventTypeDBHandler(this);

        goBackButton = findViewById(R.id.goBackButton);
        addEvent = findViewById(R.id.addEvent);
        viewEvent = findViewById(R.id.viewEventsButton);
        name = findViewById(R.id.eventName);
        level = findViewById(R.id.eventLevel);
        minAge = findViewById(R.id.age);


        addEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String nameTXT = name.getText().toString();

                if(TextUtils.isEmpty(nameTXT)){
                    Toast.makeText(AdminHandleAddEventType.this, "Enter a Event Type Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(level.getText().toString())){
                    Toast.makeText(AdminHandleAddEventType.this, "Enter a Event Type Level", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(minAge.getText().toString())) {
                    Toast.makeText(AdminHandleAddEventType.this, "Enter a Minimum Age", Toast.LENGTH_SHORT).show();
                    return;
                }
                int l = 0;
                int a = 0;
                try {
                    l = Integer.parseInt(level.getText().toString());
                    a = Integer.parseInt(minAge.getText().toString());
                }
                catch (NumberFormatException e) {
                    Toast.makeText(AdminHandleAddEventType.this, "Enter Integers for level and minimum age", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (a < 0) {
                    Toast.makeText(AdminHandleAddEventType.this, "Minimum age must be positive", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (l < 0) {
                    Toast.makeText(AdminHandleAddEventType.this, "Level must be positive", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (db.getEventType(nameTXT) != null) {
                    Toast.makeText(AdminHandleAddEventType.this, "An Event Type already has this name", Toast.LENGTH_SHORT).show();
                    return;
                }
                EventType et = new EventType(nameTXT.toLowerCase(), l, a);
                boolean checkInsertData = db.insertEventType(et);
                if(checkInsertData){
                    Toast.makeText(AdminHandleAddEventType.this,"Event created successfully", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(getApplicationContext(), AdminViewEventTypes.class);
                startActivity(intent);
                finish();
            }
        });
    }
}