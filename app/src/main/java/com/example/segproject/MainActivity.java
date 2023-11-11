package com.example.segproject;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
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

}