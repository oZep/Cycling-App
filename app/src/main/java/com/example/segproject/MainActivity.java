package com.example.segproject;

public class MainActivity {

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
