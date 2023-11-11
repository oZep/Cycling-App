package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminHandleAddEvent extends AppCompatActivity {

    Button goBackButton, addEvent, viewEvent;
    AdminEventDBHandler db;
    EditText name, email, age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_handle_add_event);
        db = new AdminEventDBHandler(this);

        goBackButton = findViewById(R.id.goBackButton);
        addEvent = findViewById(R.id.addEvent);
        viewEvent = findViewById(R.id.viewEventsButton);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        age = findViewById(R.id.age);

        addEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String emailTXT = email.getText().toString();
                String ageTXT = age.getText().toString();

                Boolean checkInsertData = db.insertUserData(nameTXT, emailTXT, ageTXT);
                if(checkInsertData){
                    Toast.makeText(AdminHandleAddEvent.this,"yay", Toast.LENGTH_SHORT).show();
                }


            }
        });


        goBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminManageActivities.class);
                startActivity(intent);
                finish();
            }
        });

        viewEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminViewEvents.class);
                startActivity(intent);
                finish();
            }
        });
    }
}