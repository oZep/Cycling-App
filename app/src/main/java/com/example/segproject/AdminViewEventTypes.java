package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminViewEventTypes extends AppCompatActivity {
    static EventTypeDBHandler db;

    ListView eventTypeList;
    Button goBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new EventTypeDBHandler(this);
        setContentView(R.layout.activity_type_list);
        eventTypeList = findViewById(R.id.activity_club_list);
        goBackButton = findViewById(R.id.goBackButton3);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminLoginScreen.class);
                startActivity(intent);
                finish();
            }
        });

        Cursor c = db.getData();
        ArrayList<String> arr = new ArrayList<String>();

        if (c.getCount() == 0) {
            Toast.makeText(AdminViewEventTypes.this, "No entries", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            while (c.moveToNext()) {
                arr.add(c.getString(0));
            }
        }


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arr);
        eventTypeList.setAdapter(adapter);
        eventTypeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick (AdapterView < ? > parent, final View view, int position, long id){
                String eventTypeName = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), EventTypePopup.class);
                intent.putExtra("eventTypeN", eventTypeName);
                startActivity(intent);
                finish();
            }
        });
    }
}