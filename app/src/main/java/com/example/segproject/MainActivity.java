package com.example.segproject;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button goBackBtn, searchType, searchName, searchClub;

    TextView searchBar;

    ClubDBHandler cdb;

    EventDBHandler edb;
    EventTypeDBHandler etdb;

    AccountDBHandler adb;


    ListView finder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goBackBtn = findViewById(R.id.goBack2);
        searchType = findViewById(R.id.eventType);
        searchName = findViewById(R.id.eventname);
        searchClub = findViewById(R.id.clubname);
        searchBar = findViewById(R.id.search);
        finder = findViewById(R.id.finder);
        cdb = new ClubDBHandler(this);
        etdb = new EventTypeDBHandler(this);
        edb = new EventDBHandler(this);
        adb = new AccountDBHandler(this);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        // changing to search bar

        goBackBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        // search by event type
        searchType.setOnClickListener(new View.OnClickListener() {
            String searched = String.valueOf(searchBar.getText()).toLowerCase();
            public void onClick(View view) {
                if(TextUtils.isEmpty(searched)){
                    Toast.makeText(MainActivity.this, "Enter Something to Search By", Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<String> arr = new ArrayList<String>();
                for (Club i : cdb.getClubsByEventType(searched, etdb, edb, adb)) {
                    arr.add(i.getClubName());
                }

                ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, (List<String>) arr);
                finder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                        String club = (String) parent.getItemAtPosition(position);
                        Intent intent = new Intent(getApplicationContext(), UserRegister.class);
                        intent.putExtra("username", username.toLowerCase());
                        intent.putExtra("clubowner", cdb.getByClubName(club, etdb, edb, adb).getUsername());
                        startActivity(intent);
                        finish();
                    }
                });

            }
        });

        // search by event name
        searchName.setOnClickListener(new View.OnClickListener() {
            String searched = String.valueOf(searchBar.getText()).toLowerCase();
            public void onClick(View view) {
                if(TextUtils.isEmpty(searched)){
                    Toast.makeText(MainActivity.this, "Enter Something to Search By", Toast.LENGTH_SHORT).show();
                    return;
                }


                ArrayList<Club> arr = cdb.getClubsByEventName(searched, etdb, edb, adb);

                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arr);
                finder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                        String clubowner = (String) parent.getItemAtPosition(position);


                        Intent intent = new Intent(getApplicationContext(), UserRegister.class);
                        intent.putExtra("username", username.toLowerCase());
                        intent.putExtra("clubowner", clubowner.toLowerCase());
                        startActivity(intent);
                        finish();
                    }


            });
                Club c = cdb.getByEventName(searched, etdb, edb, adb);
                Intent intent = new Intent(getApplicationContext(), UserRegister.class);
                intent.putExtra("username", username.toLowerCase());
                intent.putExtra("clubOwner", c.getUsername());
                startActivity(intent);
                finish();
            }

        });

        // search by club name
        searchClub.setOnClickListener(new View.OnClickListener() {
            String searched = String.valueOf(searchBar.getText()).toLowerCase());
            public void onClick(View view) {
                if (TextUtils.isEmpty(searched)) {
                    Toast.makeText(MainActivity.this, "Enter Something to Search By", Toast.LENGTH_SHORT).show();
                    return;
                }
                Club c = cdb.getByClubName(searched, etdb, edb, adb);


            });

                Intent intent = new Intent(getApplicationContext(), UserRegister.class);
                intent.putExtra("username", username.toLowerCase());
                intent.putExtra("clubOwner", c.getUsername());
                startActivity(intent);
                finish();
            }

        });

    }

}
