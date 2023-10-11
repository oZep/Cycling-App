package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
public class MainActivity extends AppCompatActivity {
    public static boolean login(String name, String pass, boolean Admin) {
        if (name.equals("username") && pass.equals("password") && !Admin) {
            return true;
        }
        else if (name.equals("username") && pass.equals("password")) {
            return true;
        }
        return false;
    }

}
