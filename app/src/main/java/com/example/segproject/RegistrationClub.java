package com.example.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class RegistrationClub extends AppCompatActivity {

    TextInputEditText editTextEmail, editTextPassword, editTextPhone, editTextSocialLink, editTextContact;
    Button buttonReg_A;
    static AccountDBHandler db;
    ProgressBar progressBar;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_club);
        db = new AccountDBHandler(this);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        editTextPhone = findViewById(R.id.phoneNumber);
        editTextSocialLink = findViewById(R.id.socialLink);
        editTextContact = findViewById(R.id.contact);
        buttonReg_A = findViewById(R.id.btn_reg_a);
        progressBar =findViewById(R.id.progressBar);
        textView =findViewById(R.id.loginNow);

        textView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), com.example.segproject.Login.class);
                startActivity(intent);
                finish();

            }


        });


        buttonReg_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password, phone, social, contact;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());

                phone = String.valueOf(editTextPhone.getText());
                social = String.valueOf(editTextSocialLink.getText());
                contact = String.valueOf(editTextContact.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(RegistrationClub.this, "Enter an email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(RegistrationClub.this, "Enter a password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(phone)){
                    Toast.makeText(RegistrationClub.this, "Enter a phone number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if((phone.length() <= 10 && Pattern.compile("^[1-9]\\d*$").matcher(phone).find() )){
                    Toast.makeText(RegistrationClub.this, "Enter a phone number", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(social)){
                    Toast.makeText(RegistrationClub.this, "Enter a social link", Toast.LENGTH_SHORT).show();
                    return;
                }
                UserAccount user;
                if (db.getUser(email) != null) {
                    Toast.makeText(RegistrationClub.this, "This email was already taken", Toast.LENGTH_SHORT).show();
                    return;
                }
                user = new ClubOwner(email, password, social, contact, phone);
                db.insertUserData(user);
                Toast.makeText(RegistrationClub.this, "Club Owner Account created", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ClubOwnerEstablishEventType.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
