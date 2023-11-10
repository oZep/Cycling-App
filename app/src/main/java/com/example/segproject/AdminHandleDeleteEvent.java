package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdminHandleDeleteEvent extends AppCompatActivity {
    AdminEventDBHandler db = new AdminEventDBHandler(this);
    EditText nameToDelete;
    Button deleteEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_handle_delete_event);
        nameToDelete = findViewById(R.id.nameToDelete);
        deleteEvent = findViewById(R.id.deleteEvent_btn);

        deleteEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                removeProduct();
            }
        });

    }

    public void removeProduct(){
        boolean result = db.deleteUserData(nameToDelete.toString());

        if(result){
            nameToDelete.setText("Deleted");
        }
    }



}