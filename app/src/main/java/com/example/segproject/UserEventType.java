package com.example.segproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UserEventType extends AppCompatActivity {


        Button goBack;
        ListView typeList;
        EventTypeDBHandler db;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_user_event_type_list);
            goBack = findViewById(R.id.backbutton);
            typeList = findViewById(R.id.activity_club_list);
            Intent intent = getIntent();
            String username = intent.getStringExtra("username");
            db = new EventTypeDBHandler(this);

            goBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                    finish();
                }
            });

            Cursor c = db.getData();
            ArrayList<String> arr = new ArrayList<String>();

            if (c.getCount() == 0) {
                Toast.makeText(UserEventType.this, "No entries", Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                while (c.moveToNext()) {
                    arr.add(c.getString(0));
                }
            }


            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arr);
            typeList.setAdapter(adapter);
            typeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                    String type = (String) parent.getItemAtPosition(position);
                    Intent intent = new Intent(getApplicationContext(), UserClubList.class);
                    intent.putExtra("username", username);
                    intent.putExtra("eventType", type);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }