package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    TextInputEditText editTextEmail, editTextPassword;
    Button buttonLogin;
    Button buttonLoginAdmin;
    Button buttonLoginOwner;
    static AccountDBHandler db;
    ProgressBar progressBar;
    TextView textView;
    TextView textView_admin;
    Admin admin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new AccountDBHandler(this);
        setContentView(R.layout.activtity_login);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.btn_login);
        progressBar =findViewById(R.id.progressBar);
        textView =findViewById(R.id.registerNow);
        textView_admin =findViewById(R.id.registerNow2);
        buttonLoginOwner = findViewById(R.id.btn_login_club);
        buttonLoginAdmin = findViewById(R.id.btn_login_a);
        admin = Admin.getInstance();
        textView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), Registration.class);
                startActivity(intent);
                finish();

            }
        });
        textView_admin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), RegistrationClub.class);
                startActivity(intent);
                finish();

            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String email, password;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Login.this, "Enter an email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this, "Enter a password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (login(email, password, false)) {
                    Toast.makeText(getApplicationContext(), "Authentication successful.", Toast.LENGTH_SHORT).show();
                    //Intent to open the main activity
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonLoginOwner.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String email, password;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Login.this, "Enter an email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this, "Enter a password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (login(email, password, true)) {
                    Toast.makeText(getApplicationContext(), "Authentication successful.", Toast.LENGTH_SHORT).show();
                    //Intent to open the main activity
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonLoginAdmin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String email, password;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Login.this, "Enter an email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this, "Enter a password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (email.equals(admin.getUsername()) && password.equals(admin.getPassword())) {
                    Toast.makeText(getApplicationContext(), "Authentication successful.", Toast.LENGTH_SHORT).show();
                    //Intent to open the main activity
                    Intent intent = new Intent(getApplicationContext(), AdminLoginScreen.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                }
            }

        });
    }

    public static boolean login(String email, String password, boolean isClubOwner) {
        UserAccount user = db.getUser(email);
        return user != null && user.getPassword().equals(password) && user instanceof ClubOwner == isClubOwner;
    }
}
