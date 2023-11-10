package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminViewEvents extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<String> name,email,age;
    AdminEventDBHandler db;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_events);
        db = new AdminEventDBHandler(this);
        name = new ArrayList<>();
        email = new ArrayList<>();
        age = new ArrayList<>();
        rv = findViewById(R.id.recyclerView);
        adapter = new MyAdapter(this, name, email, age);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        displayData();
    }

    private void displayData() {
        Cursor c = db.getData();
        if(c.getCount() ==0){
            Toast.makeText(AdminViewEvents.this, "No entries", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            while(c.moveToNext()){
                name.add(c.getString(0));
                email.add(c.getString(1));
                age.add(c.getString(2));

            }
        }
    }
}