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
    EventDBHandler edb;
    EventTypeDBHandler etdb;

    AccountDBHandler adb;
    String clubOwner, eventName, username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        goBackBtn = findViewById(R.id.goBack);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        register = findViewById(R.id.reg);
        Intent intent = getIntent();
        cdb = new ClubDBHandler(this);
        etdb = new EventTypeDBHandler(this);
        edb = new EventDBHandler(this);
        adb = new AccountDBHandler(this);
        clubOwner = intent.getStringExtra("clubOwner"); //send from search or main activity
        eventName = intent.getStringExtra("eventName");
        username = intent.getStringExtra("username");

        Club club = cdb.getClub(clubOwner, etdb, edb, adb);

        Participant user = (Participant) adb.getUser(username, cdb, etdb, edb);

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
                Event evt = edb.getEvent(eventName, cdb, etdb, adb);
                EventType q = evt.getEventType();
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
                if (evt.getMaxParticipants() == evt.getParticipants().size()) {
                    Toast.makeText(UserRegister.this, "Max participants reached", Toast.LENGTH_SHORT).show();
                    return;
                }

                // register user in club
                club.addParticipant(user);
                adb.deleteUserData(username);
                cdb.deleteClubData(clubOwner);
                cdb.insertUserData(club);
                adb.insertUserData(user);

                Intent intent = new Intent(getApplicationContext(), RateClub.class);
                intent.putExtra("username", username);
                intent.putExtra("clubOwner", clubOwner);
                startActivity(intent);
                finish();
            }
        });
    }
}
