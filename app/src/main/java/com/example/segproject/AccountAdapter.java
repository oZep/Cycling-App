package com.example.segproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class AccountAdapter extends ArrayAdapter<UserAccount> {
    private Activity context;
    List<UserAccount> accounts;

    public AccountAdapter(@NonNull Activity context, List<UserAccount> accounts) {
        super(context, R.layout.activity_account_list, accounts);
        this.context = context;
        this.accounts = accounts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_account_list, null, true);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView textAccountUser = (TextView) listViewItem.findViewById(R.id.textAccountUser);

        UserAccount account = accounts.get(position);
        textAccountUser.setText(account.getUsername());
        return listViewItem;
    }
}