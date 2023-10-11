package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class AdminLoginScreen extends AppCompatActivity {

    Button buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtity_login);
        buttonLogin = findViewById(R.id.btn_login);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login_screen);
    }
}