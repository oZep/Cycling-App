package com.example.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditEventType extends AppCompatActivity {
    Button goBackButton, finishEventType;
    EventTypeDBHandler db;
    String eventTypeN;
    EditText eventTypeName, minAge, level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_handle_edit_event_type);
        db = new EventTypeDBHandler(this);

        goBackButton = findViewById(R.id.goBackButton);
        finishEventType = findViewById(R.id.addEvent);
        eventTypeName = findViewById(R.id.eventName);
        minAge = findViewById(R.id.minAge);
        level = findViewById(R.id.level);

        Intent intent = getIntent();
        eventTypeN = intent.getStringExtra("eventTypeN");
        // TODO: Fillin the info using what i did in another class, find the clubName in the database & fill out
        EventType eventType = db.getEventType(eventTypeN);
        eventTypeName.setText(eventType.getName());
        minAge.setText(Integer.toString(eventType.getMinAge()));
        level.setText(Integer.toString(eventType.getLevel()));

        finishEventType.setOnClickListener(new View.OnClickListener() {
            String eventTypeNamed = eventTypeName.getText().toString().toLowerCase();
            String sAge = minAge.getText().toString();
            String sLevel = level.getText().toString();
            public void onClick(View view) {
                if(TextUtils.isEmpty(sAge)){
                    Toast.makeText(EditEventType.this, "Enter a Location", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(sLevel)) {
                    Toast.makeText(EditEventType.this, "Enter a Proper Date", Toast.LENGTH_SHORT).show();
                    return;
                }
                int age = 0, lvl = 0;
                try {
                    age = Integer.parseInt(sAge);
                }
                catch (NumberFormatException e) {
                    Toast.makeText(EditEventType.this, "Enter a Proper Age", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    lvl = Integer.parseInt(sLevel);
                }
                catch (NumberFormatException e) {
                    Toast.makeText(EditEventType.this, "Enter a Proper Level", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (lvl < 0) {
                    Toast.makeText(EditEventType.this, "Enter a Proper Level", Toast.LENGTH_SHORT).show();
                    return;
                }
                EventType e = new EventType(eventTypeNamed, age, lvl);
                db.deleteEventType(eventTypeNamed);
                db.insertEventType(e);
                Toast.makeText(EditEventType.this,"Event Edited successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), AdminViewEventTypes.class);
                startActivity(intent);
                finish();
            }
        });


        goBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminViewEventTypes.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
