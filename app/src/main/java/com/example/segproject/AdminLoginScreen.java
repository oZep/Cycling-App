package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminLoginScreen extends AppCompatActivity {

    Button goBackButton;
    Button goToEventEditor;
    Button goToAccountEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login_screen);
        goToAccountEditor = findViewById(R.id.goToAccountEditor);
        goToEventEditor = findViewById(R.id.goToEventEditor);
        goBackButton = findViewById(R.id.goBackButton);

        //Navigate back to the login screen
        goBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
        //Navigate to the Event Editor page for admin
        goToEventEditor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminManageActivities.class);
                startActivity(intent);
                finish();
            }
        });

        //Navigate to the Account Editor page for admin
        goToAccountEditor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminManageAccounts.class);
                startActivity(intent);
                finish();
            }
        });
    }
}