package com.example.firebaseapp2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

public class Registration extends AppCompatActivity {
    EditText emailBox;
    EditText passwordBox;
    Button userRegister;
    Button adminRegister;
    DatabaseReference databaseReference;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        emailBox = (EditText) findViewById(R.id.emailSignUp);
        passwordBox = (EditText) findViewById(R.id.passwordSignUp);
        adminRegister = (Button) findViewById(R.id.adminSignUp);
        userRegister = (Button) findViewById(R.id.userSignUp);

        adminRegister.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        adminRegister(emailBox.getText().toString(), passwordBox.getText().toString());
                    }
                }
        );
        userRegister.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        adminRegister(emailBox.getText().toString(), passwordBox.getText().toString());
                    }
                }
        );
    }

    private void adminRegister(String email, String password) {

    }

    private void userRegister(String email, String password) {

    }

}
