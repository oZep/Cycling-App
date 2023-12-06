package com.example.segproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserRegister extends AppCompatActivity {

    Button goBackBtn, register;
    TextView name, age;

    ClubDBHandler cdb;
    EventTypeDBHandler db;
<<<<<<< HEAD
    String clubowner, eventname, username;
=======
    String clubName, eventName, username;
>>>>>>> 480f03cbc17d8da8fff1e4b097cef31ef777ee85


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        goBackBtn = findViewById(R.id.goBack);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        register = findViewById(R.id.reg);
        Intent intent = getIntent();

        db = new EventTypeDBHandler(this);
        cdb = new ClubDBHandler(this);
<<<<<<< HEAD
        clubowner = intent.getStringExtra("clubowner"); //send from search or main activity
        eventname = intent.getStringExtra("eventname");
=======
        clubName = intent.getStringExtra("clubName"); //send from search or main activity
        eventName = intent.getStringExtra("eventName");
>>>>>>> 480f03cbc17d8da8fff1e4b097cef31ef777ee85
        username = intent.getStringExtra("username");




        goBackBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String sName, sAge;

                sName = String.valueOf(name.getText());
                sAge = String.valueOf(age.getText());
                EventType q = db.getEventType(eventName);
                int minAge = q.getMinAge();


                if(TextUtils.isEmpty(sName)){
                    Toast.makeText(UserRegister.this, "Enter a Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(sAge)){
                    Toast.makeText(UserRegister.this, "Enter your Age", Toast.LENGTH_SHORT).show();
                    return;
                }
                int iAge = 0;
                try {
                    iAge = Integer.parseInt(sAge);
                }
                catch (NumberFormatException e) {
                    Toast.makeText(UserRegister.this, "Enter a valid age", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (iAge < minAge) {
                    Toast.makeText(UserRegister.this, "Sorry you are not old enough", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (iAge < 0) {
                    Toast.makeText(UserRegister.this, "Enter a valid age", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(getApplicationContext(), RateClub.class);
                // TODO: pass username to rate page
                intent.putExtra("username", username);
                intent.putExtra("eventname", username.toLowerCase());
                intent.putExtra("clubname", username.toLowerCase());
                startActivity(intent);
                finish();
            }
        });
    }
}
