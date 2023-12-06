package com.example.segproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;

public class Registration extends AppCompatActivity {

    TextInputEditText editTextEmail, editTextPassword;
    Button buttonReg;
    static AccountDBHandler db;
    ProgressBar progressBar;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        db = new AccountDBHandler(this);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        buttonReg = findViewById(R.id.btn_login);
        progressBar =findViewById(R.id.progressBar);
        textView =findViewById(R.id.loginNow);
        textView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), com.example.segproject.Login.class);
                startActivity(intent);
                finish();

            }


        });


        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;
                
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Registration.this, "Enter an email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (email.indexOf(' ') > -1) {
                    Toast.makeText(Registration.this, "Emails may not contain spaces", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Registration.this, "Enter a password", Toast.LENGTH_SHORT).show();
                    return;
                }
                UserAccount user;
                if (db.getUser(email.toLowerCase()) != null) {
                    Toast.makeText(Registration.this, "This email was already taken", Toast.LENGTH_SHORT).show();
                    return;
                }
                user = new Participant(email.toLowerCase(), password);
                db.insertUserData(user);
                Toast.makeText(Registration.this, "Participant Account created", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }

        });
    }
}
