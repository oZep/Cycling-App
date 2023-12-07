package com.example.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UserEventList extends AppCompatActivity {


    Button goBack;
    ListView clubList;
    ClubDBHandler db;
    EventTypeDBHandler etdb;
    EventDBHandler edb;
    AccountDBHandler adb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_event_list);
        goBack = findViewById(R.id.goBackButton3);
        clubList = findViewById(R.id.activity_club_list);
        db = new ClubDBHandler(this);
        etdb = new EventTypeDBHandler(this);
        edb = new EventDBHandler(this);
        adb = new AccountDBHandler(this);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String type = intent.getStringExtra("eventType");
        String clubOwner = intent.getStringExtra("clubOwner");




        goBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("username", username.toLowerCase());
                startActivity(intent);
                finish();
            }
        });

        Club club = db.getClub(clubOwner, etdb, edb, adb);

        ArrayList<Event> clubs = edb.getEvents(club, db, etdb, adb);
        ArrayList<String> arr = new ArrayList<String>();

            if (clubs.size() == 0) {
            Toast.makeText(UserEventList.this, "No entries", Toast.LENGTH_SHORT).show();
            return;
        }
            else {
            for (Event i : clubs) {
                arr.add(i.getName());
            }
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arr);
        clubList.setAdapter(adapter);
        clubList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                String eventName = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), UserRegister.class);
                intent.putExtra("username", username);
                intent.putExtra("clubOwner", clubOwner);
                intent.putExtra("eventName", eventName);
                startActivity(intent);
                finish();
            }
        });
    }
}

