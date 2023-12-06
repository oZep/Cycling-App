package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        Cursor c = db.getData();
        ArrayList<String> arr = new ArrayList<String>();
        eventTypeList = findViewById(R.id.activity_event_type_list);
        goBackButton = findViewById(R.id.goBackButton3);

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
            public void onItemClick (AdapterView < ? > parent,final View view, int position, long id){
                String eventTypeName = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), EventPopup.class);
                intent.putExtra("eventTypeN", eventTypeName);
                startActivity(intent);
                finish();
            }
        });
    }
}