package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the welcome text from the Intent
        String welcomeText = getIntent().getStringExtra("WELCOME_TEXT");

        // Update the UI element with the welcome text
        TextView welcomeTextAny = findViewById(R.id.welcome_text);
        welcomeTextAny.setText(welcomeText);
    }

    public static boolean login(String name, String pass, boolean Admin) {
        if (name.equals("username") && pass.equals("password") && !Admin) {
            return true;
        }
        else if (name.equals("username") && pass.equals("password")) {
            return true;
        }
        return false;
    }

}
