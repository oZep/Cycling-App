package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminViewEventTypes extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<String> name;
    ArrayList<Integer> level, age;
    EventTypeDBHandler db;
    MyAdapter adapter;
    Button goBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_event_types);
        db = new EventTypeDBHandler(this);
        name = new ArrayList<String>();
        level = new ArrayList<Integer>();
        age = new ArrayList<Integer>();
        rv = findViewById(R.id.recyclerView);
        adapter = new MyAdapter(this, name, level, age);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        displayData();
        goBackButton = findViewById(R.id.makeChanges_btn);

        goBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminManageActivities.class);
                startActivity(intent);
                finish();
            }
        });

    }


    private void displayData() {
        Cursor c = db.getData();
        if(c.getCount() ==0){
            Toast.makeText(AdminViewEventTypes.this, "No entries", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            while(c.moveToNext()){
                name.add(c.getString(0));
                level.add(Integer.parseInt(c.getString(1)));
                age.add(Integer.parseInt(c.getString(2)));
            }
        }
    }
}