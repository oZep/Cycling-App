package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class AdminLoginScreen extends AppCompatActivity {

    Button buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        buttonLogin = findViewById(R.id.btn_login);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login_screen);
    }
}