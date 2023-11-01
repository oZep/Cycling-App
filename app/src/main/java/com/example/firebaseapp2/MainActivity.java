package com.example.firebaseapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.app.AlertDialog;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText emailBox;
    EditText passwordBox;
    Button adminLogin;
    Button userLogin;
    Button register;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailBox = (EditText) findViewById(R.id.email);
        passwordBox = (EditText) findViewById(R.id.password);
        adminLogin = (Button) findViewById(R.id.adminLogin);
        userLogin = (Button) findViewById(R.id.userLogin);
        register = (Button) findViewById(R.id.register);

        adminLogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adminLogin(emailBox.getText().toString(), passwordBox.getText().toString());
                    }
                }
        );
        userLogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        userLogin(emailBox.getText().toString(), passwordBox.getText().toString());
                    }
                }
        );
        register.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), Registration.class));
                        finish();
                    }
                }
        );
  }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void adminLogin(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Enter your email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (email.equals("email") && password.equals("password")) {
            startActivity(new Intent(getApplicationContext(), AdminPage.class));
            finish();
        }
        else {
            Toast.makeText(this, "Incorrect login credentials", Toast.LENGTH_SHORT).show();
        }
    }

    public void userLogin(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Enter your email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (email.equals("email") && password.equals("password")) {
            startActivity(new Intent(getApplicationContext(), AdminPage.class));
            finish();
        }
        else {
            Toast.makeText(this, "Incorrect login credentials", Toast.LENGTH_SHORT).show();
        }
    }
}
