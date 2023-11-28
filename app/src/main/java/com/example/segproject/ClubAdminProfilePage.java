package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ClubAdminProfilePage extends AppCompatActivity {
    Button goToLogin, goToMain;
    TextView Username, Contact, Phone, Socials, clubNamed;

    AccountDBHandler dbHandler;
    ClubOwner userAccount;
    String usernamefill, contactfill, phonefill, socialsfill;
    public ClubAdminProfilePage(String username, String contact, String phone, String socials) {
        this.usernamefill = username;
        this.contactfill = contact;
        this.phonefill = phone;
        this.socialsfill = socials;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_admin_profile_page);
        goToLogin = findViewById(R.id.btn_return_to_login);
        goToMain = findViewById(R.id.btn_return_to_main);
        Username = findViewById(R.id.Username);
        Contact = findViewById(R.id.Contact);
        Phone = findViewById(R.id.Phone);
        Socials = findViewById(R.id.Socials);
        clubNamed = findViewById(R.id.clubName);
        Intent intent = getIntent();
        String usernamefill = intent.getStringExtra("Username");
        userAccount = (ClubOwner) dbHandler.getUser(usernamefill);

        Username.setText("Username: " + usernamefill);
        Contact.setText("Contact: " + userAccount.getContact());
        Phone.setText("Phone: " + userAccount.getPhoneNum());
        Socials.setText("Socials: " + userAccount.getSocialMedia());




        goToLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String clubName;
                clubName = String.valueOf(clubNamed.getText());

                if(TextUtils.isEmpty(clubName)){
                    Toast.makeText(ClubAdminProfilePage.this, "Enter a Club Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                // TODO: Added clubName to club object (create it) and add that to the db

                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });


        goToMain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String clubName;
                clubName = String.valueOf(clubNamed.getText());

                if(TextUtils.isEmpty(clubName)){
                    Toast.makeText(ClubAdminProfilePage.this, "Enter a Club Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                // TODO: Added clubName to club object (create it) and add that to the db

                Intent intent = new Intent(getApplicationContext(), ClubAdminLogin.class);
                startActivity(intent);
                finish();
            }
        });
    }

}