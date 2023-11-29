package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdminHandleDeleteEvent extends AppCompatActivity {
    AdminEventDBHandler db = new AdminEventDBHandler(this);
    EditText nameToDelete;
    Button deleteEvent, goBackButton, viewEvents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_handle_delete_event);
        nameToDelete = findViewById(R.id.nameToDelete);
        goBackButton = findViewById(R.id.goBackButton);
        deleteEvent = findViewById(R.id.deleteEvent_btn);
        viewEvents = findViewById(R.id.viewEventsButton);

        goBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminManageActivities.class);
                startActivity(intent);
                finish();
            }
        });

        viewEvents.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminViewEvents.class);
                startActivity(intent);
                finish();
            }
        });

        deleteEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                removeProduct();
            }
        });

    }

    public void removeProduct() {
        String name = nameToDelete.getText().toString(); // Get the name from the EditText

        boolean result = db.deleteEventType(name);

        if (result) {
            nameToDelete.setText(" :Deleted");
        }
    }




}