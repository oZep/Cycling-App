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

    Button goBackBtn, Register;
    TextView Name, Age;

    ClubDBHandler cdb;
    EventTypeDBHandler db;
    String clubname, eventname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        goBackBtn = findViewById(R.id.goBack);
        Name = findViewById(R.id.name);
        Age = findViewById(R.id.age);
        Register = findViewById(R.id.reg);
        Intent intent = getIntent();

        db = new EventTypeDBHandler(this);
        cdb = new ClubDBHandler(this);
        clubname = intent.getStringExtra("clubname"); //send from search or main activity
        eventname = intent.getStringExtra("eventname");


        goBackBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String name, age;

                name = String.valueOf(Name.getText());
                age = String.valueOf(Age.getText());
                EventType q = db.getEventType(eventname);
                int minAge = q.getMinAge();


                if(TextUtils.isEmpty(name)){
                    Toast.makeText(UserRegister.this, "Enter A Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(age)){
                    Toast.makeText(UserRegister.this, "Enter your Age", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (Integer.parseInt(age) >= minAge) {
                    Toast.makeText(UserRegister.this, "Sorry you are not old enough", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
