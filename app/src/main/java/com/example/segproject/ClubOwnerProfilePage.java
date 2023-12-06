package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ClubOwnerProfilePage extends AppCompatActivity {
    Button goToLogin, goToMain;
    TextView username, contact, phone, socials;
    EditText clubNamed;
    AccountDBHandler dbHandler;
    EventTypeDBHandler etdb;
    EventDBHandler edb;
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
        username = findViewById(R.id.username);
        contact = findViewById(R.id.contact);
        phone = findViewById(R.id.phone);
        socials = findViewById(R.id.Socials);
        clubNamed = findViewById(R.id.clubName);
        Intent intent = getIntent();
        clubOwner = intent.getStringExtra("clubOwner");
        dbHandler = new AccountDBHandler(this);
        etdb = new EventTypeDBHandler(this);
        cdb = new ClubDBHandler(this);
        userAccount = (ClubOwner) dbHandler.getUser(clubOwner, cdb, etdb, edb);
        rc = findViewById(R.id.rc);

        username.setText("Username: " + clubOwner);
        contact.setText("Contact: " + userAccount.getContact());
        phone.setText("Phone: " + userAccount.getPhoneNum());
        socials.setText("Socials: " + userAccount.getSocialMedia());

        Cursor c = etdb.getData();
        if (c == null) {
            Toast.makeText(ClubOwnerProfilePage.this, "C is Null", Toast.LENGTH_SHORT).show();
            return;
        }
        ArrayList<String> arr = new ArrayList<String>();

        if (c.getCount() == 0) {
            Toast.makeText(ClubOwnerProfilePage.this, "No entries", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            while (c.moveToNext()) {
                arr.add(c.getString(0));
            }
        }
        boolean[] checks = new boolean[arr.size()];


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arr);
        rc.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        goToLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String clubName;
                clubName = String.valueOf(clubNamed.getText());

                // Just making it so that all the event types are added to the club
                //ArrayList<String> eventTypeNames = adapter.getChecked();
                //eventTypes = new ArrayList<EventType>();
                //for (String i : eventTypeNames) {
                //    eventTypes.add(etdb.getEventType(i));
                //}

                if(TextUtils.isEmpty(clubName)){
                    Toast.makeText(ClubOwnerProfilePage.this, "Enter a Club Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (cdb.getByClubName(clubName, etdb, edb, dbHandler) != null) {
                    Toast.makeText(ClubOwnerProfilePage.this, "This Club name is already taken", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (eventTypes.size() == 0) {
                    Toast.makeText(ClubOwnerProfilePage.this, "Choose at least one event type", Toast.LENGTH_SHORT).show();
                    return;
                }
                Club club = new Club(clubOwner, clubName, eventTypes);
                cdb.insertUserData(club);

                Intent intent = new Intent(getApplicationContext(), Login.class);
                intent.putExtra("clubname", clubName);
                startActivity(intent);
                finish();
            }
        });

        goToMain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String clubName;
                clubName = String.valueOf(clubNamed.getText());

                /*ArrayList<String> eventTypeNames = adapter.getChecked();
                eventTypes = new ArrayList<EventType>();
                for (String i : eventTypeNames) {
                    eventTypes.add(etdb.getEventType(i));
                }*/

                if(TextUtils.isEmpty(clubName)){
                    Toast.makeText(ClubOwnerProfilePage.this, "Enter a Club Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (cdb.getByClubName(clubName, etdb, edb, dbHandler) != null) {
                    Toast.makeText(ClubOwnerProfilePage.this, "This Club name is already taken", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (eventTypes.size() == 0) {
                    Toast.makeText(ClubOwnerProfilePage.this, "Choose at least one event type", Toast.LENGTH_SHORT).show();
                    return;
                }

                Club club = new Club(clubOwner.toLowerCase(), clubName.toLowerCase(), eventTypes);
                cdb.insertUserData(club);

                Intent intent = new Intent(getApplicationContext(), ClubOwnerManageActivities.class);
                intent.putExtra("clubName", clubName);

                startActivity(intent);
                finish();
            }
        });
    }
}