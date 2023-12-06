package com.example.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    Button goBackBtn, searchType, searchName, searchClub;

    TextView searchBar;

    RecyclerView finder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goBackBtn = findViewById(R.id.goBack2);
        searchType = findViewById(R.id.eventType);
        searchName = findViewById(R.id.eventname);
        searchClub = findViewById(R.id.clubname);
        searchBar = findViewById(R.id.search);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        // changing to search bar

        goBackBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        // search by event type
        searchType.setOnClickListener(new View.OnClickListener() {
            String searched = String.valueOf(searchBar.getText());
            public void onClick(View view) {
                if(TextUtils.isEmpty(searched)){
                    Toast.makeText(MainActivity.this, "Enter Something to Search By", Toast.LENGTH_SHORT).show();
                    return;
                }


                // change the recycler
                // TODO: Send clubname + eventname to Userregister  + username as an intent
                Intent intent = new Intent(getApplicationContext(), UserRegister.class);
                intent.putExtra("username", username.toLowerCase());
                intent.putExtra("eventname", eventname.toLowerCase());
                intent.putExtra("clubowner", clubowner.toLowerCase());
                startActivity(intent);
                finish();
                // on click of element

            }
        });

        // search by event name
        searchName.setOnClickListener(new View.OnClickListener() {
            String searched = String.valueOf(searchBar.getText());
            public void onClick(View view) {
                if(TextUtils.isEmpty(searched)){
                    Toast.makeText(MainActivity.this, "Enter Something to Search By", Toast.LENGTH_SHORT).show();
                    return;
                }

                // TODO: Send clubname + eventname to Userregister  + username as an intent
                Intent intent = new Intent(getApplicationContext(), UserRegister.class);
                intent.putExtra("username", username.toLowerCase());
                intent.putExtra("eventname", eventname.toLowerCase());
                intent.putExtra("clubowner", clubowner.toLowerCase());
                startActivity(intent);
                finish();

            }
        });

        // search by club name
        searchClub.setOnClickListener(new View.OnClickListener() {
            String searched = String.valueOf(searchBar.getText());
            public void onClick(View view) {
                if(TextUtils.isEmpty(searched)){
                    Toast.makeText(MainActivity.this, "Enter Something to Search By", Toast.LENGTH_SHORT).show();
                    return;
                }
                // TODO: Send clubname + eventname to Userregister + username  as an intent
                Intent intent = new Intent(getApplicationContext(), UserRegister.class);
                intent.putExtra("username", username.toLowerCase());
                intent.putExtra("eventname", eventname.toLowerCase());
                intent.putExtra("clubowner", clubowner.toLowerCase());
                startActivity(intent);
                finish();

            }
        });




    }

}