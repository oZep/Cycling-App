package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ClubOwnerProfilePage extends AppCompatActivity {
    Button goToLogin, goToMain;
    TextView Username, Contact, Phone, Socials, clubNamed;

    AccountDBHandler dbHandler;
    EventTypeDBHandler etdb;
    ClubDBHandler cdb;
    ClubOwner userAccount;
    String clubOwner;
    ListView rc;
    ArrayList<EventType> eventTypes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_owner_profile_page);
        goToLogin = findViewById(R.id.btn_return_to_login);
        goToMain = findViewById(R.id.btn_return_to_main);
        Username = findViewById(R.id.Username);
        Contact = findViewById(R.id.Contact);
        Phone = findViewById(R.id.Phone);
        Socials = findViewById(R.id.Socials);
        clubNamed = findViewById(R.id.clubName);
        Intent intent = getIntent();
        clubOwner = intent.getStringExtra("clubOwner");
        dbHandler = new AccountDBHandler(this);
        etdb = new EventTypeDBHandler(this);
        cdb = new ClubDBHandler(this);
        userAccount = (ClubOwner) dbHandler.getUser(clubOwner);
        rc = findViewById(R.id.rc);

        Username.setText("Username: " + clubOwner);
        Contact.setText("Contact: " + userAccount.getContact());
        Phone.setText("Phone: " + userAccount.getPhoneNum());
        Socials.setText("Socials: " + userAccount.getSocialMedia());





        Cursor c = etdb.getData();
        List<String> arr = new ArrayList<>();

        if (c.getCount() == 0) {
            Toast.makeText(ClubOwnerProfilePage.this, "No entries", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (c.moveToNext()) {
                arr.add(c.getString(0));
            }
        }
        boolean[] checks = new boolean[arr.size()];


        EventTypeAdapter adapter = new EventTypeAdapter(this, arr, checks);
        rc.setAdapter(adapter);

        goToLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String clubName;
                clubName = String.valueOf(clubNamed.getText());

                ArrayList<String> eventTypeNames = adapter.getChecked();
                for (String i : eventTypeNames) {
                    eventTypes.add(etdb.getEventType(i));
                }

                if(TextUtils.isEmpty(clubName)){
                    Toast.makeText(ClubOwnerProfilePage.this, "Enter a Club Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                Club club = new Club(clubOwner, clubName, eventTypes);
                cdb.insertUserData(club);

                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        goToMain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String clubName;
                clubName = String.valueOf(clubNamed.getText());

                ArrayList<String> eventTypeNames = adapter.getChecked();
                for (String i : eventTypeNames) {
                    eventTypes.add(etdb.getEventType(i));
                }

                if(TextUtils.isEmpty(clubName)){
                    Toast.makeText(ClubOwnerProfilePage.this, "Enter a Club Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                Club club = new Club(clubOwner, clubName, eventTypes);
                cdb.insertUserData(club);

                Intent intent = new Intent(getApplicationContext(), ClubOwnerManageActivities.class);
                intent.putExtra("clubName", clubName);

                startActivity(intent);
                finish();
            }
        });
    }

}