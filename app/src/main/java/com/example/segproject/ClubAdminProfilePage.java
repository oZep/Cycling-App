package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ClubAdminProfilePage extends AppCompatActivity {
    Button goToLogin, goToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_admin_profile_page);
        goToLogin = findViewById(R.id.btn_return_to_login);
        goToMain = findViewById(R.id.btn_return_to_main);

        goToLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        goToMain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ClubAdminLogin.class);
                startActivity(intent);
                finish();
            }
        });
    }

}