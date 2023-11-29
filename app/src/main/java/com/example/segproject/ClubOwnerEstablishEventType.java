package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

public class ClubOwnerEstablishEventType extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<String> name;
    ArrayList<Integer> level, age;
    EventTypeDBHandler db;
    MyAdapter adapter;
    Button goBackButton;
    CheckBox e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_owner_establish_event_type);
        db = new EventTypeDBHandler(this);
        name = new ArrayList<String>();
        level = new ArrayList<Integer>();
        age = new ArrayList<Integer>();
        e = findViewById(R.id.SomeEventType);
        rv = findViewById(R.id.recyclerView);
        adapter = new MyAdapter(this, name, level, age);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        displayData();


    }


    private void displayData() {
        Cursor c = db.getData();
        if (c.getCount() == 0) {
            Toast.makeText(ClubOwnerEstablishEventType.this, "No entries", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            while(c.moveToNext()){
                name.add(c.getString(0));
                level.add(Integer.parseInt(c.getString(1)));
                age.add(Integer.parseInt(c.getString(2)));
            }
            if(c.moveToFirst()){
                e.setText(c.getString(0));
            }
        }
    }
}
