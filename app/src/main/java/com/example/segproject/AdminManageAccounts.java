package com.example.segproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import java.util.Iterator;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.ExportedUserRecord;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.ListUsersPage;
import com.google.firebase.auth.UserRecord;

import java.util.ArrayList;


public class AdminManageAccounts extends AppCompatActivity {

    Button goBackButton;
    FirebaseAuth mAuth;

    ListView accountList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_accounts);
        mAuth = FirebaseAuth.getInstance();
        goBackButton = findViewById(R.id.goBackButton);
        accountList = findViewById(R.id.accountList);

        goBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        ListUsersPage users = null;
        try {
            users = mAuth.listUsers(null);
        } catch (FirebaseAuthException e) {
            throw new RuntimeException(e);
        }

        ArrayList<ExportedUserRecord> arr= new ArrayList<>();
        while (users != null) {
            Iterator<ExportedUserRecord> iter = users.getValues().iterator();
            while (iter.hasNext()) {
                arr.add(iter.next());
            }
            users = users.getNextPage();
        }

    ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arr); accountList.setAdapter(adapter);
        accountList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                    UserRecord account = (UserRecord) parent.getItemAtPosition(position);
                    String uid = account.getUid();
                    try {
                        mAuth.deleteUser(uid);
                        Toast.makeText(AdminManageAccounts.this, "User Deleted", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(AdminManageAccounts.this, "Error Deleting User", Toast.LENGTH_SHORT).show();
                    }
            }
    });
}
}