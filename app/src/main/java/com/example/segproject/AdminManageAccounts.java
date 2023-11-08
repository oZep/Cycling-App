package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class AdminManageAccounts extends AppCompatActivity {

    Button goBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_accounts);
        goBackButton = findViewById(R.id.goBackButton);
    }
}