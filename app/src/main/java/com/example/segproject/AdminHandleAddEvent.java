package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminHandleAddEvent extends AppCompatActivity {

    Button goBackButton, addEvent, viewEvent;
    AdminEventDBHandler db;
    EditText name, level, minAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_handle_add_event);
        db = new AdminEventDBHandler(this);

        goBackButton = findViewById(R.id.goBackButton);
        addEvent = findViewById(R.id.addEvent);
        viewEvent = findViewById(R.id.viewEventsButton);
        name = findViewById(R.id.eventName);
        level = findViewById(R.id.eventLevel);
        minAge = findViewById(R.id.age);


        addEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                int levelTXT = Integer.parseInt(level.getText().toString());
                int minAgeTXT = Integer.parseInt(minAge.getText().toString());

                if(TextUtils.isEmpty(nameTXT)){
                    Toast.makeText(AdminHandleAddEvent.this, "Enter a Event Type Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(level.getText().toString())){
                    Toast.makeText(AdminHandleAddEvent.this, "Enter a Event Type Level", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(minAge.getText().toString())){
                    Toast.makeText(AdminHandleAddEvent.this, "Enter a Minimum Age", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    EventType et = new EventType(levelTXT, minAgeTXT, nameTXT);

                    boolean checkInsertData = db.insertEventType(et);
                    if(checkInsertData){
                        Toast.makeText(AdminHandleAddEvent.this,"Event created successfully", Toast.LENGTH_SHORT).show();
                    }
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