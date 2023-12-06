package com.example.segproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EventList extends AppCompatActivity {

    static EventDBHandler db;

    Button goBack;
    ListView eventList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new EventDBHandler(this);
        setContentView(R.layout.activity_event_list);
        eventList = findViewById(R.id.eventList);
        goBack = findViewById(R.id.goBack);
        Cursor c = db.getData();
        ArrayList<String> arr = new ArrayList<>();

        if (c.getCount() == 0) {
            Toast.makeText(EventList.this, "No entries", Toast.LENGTH_SHORT).show();
            return;
        } else

        {
            while (c.moveToNext()) {
                arr.add(c.getString(0));
            }
        }

        goBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ClubOwnerManageActivities.class);
                startActivity(intent);
                finish();
            }
        });


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arr);
        eventList.setAdapter(adapter);
        eventList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick (AdapterView < ? > parent,final View view, int position, long id){
                String eventName = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), EventPopup.class);
                intent.putExtra("eventN", eventName);
                startActivity(intent);
                finish();
            }
        });
    }
}

