package com.example.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class UserClubList extends AppCompatActivity {


    Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_club_list);
        goBack = findViewById(R.id.goBackButton3);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");


        goBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("username", username.toLowerCase());
                startActivity(intent);
                finish();
            }
        });
    }
}
