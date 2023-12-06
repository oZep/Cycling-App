package com.example.segproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class ClubAdapter {
    private Activity context;
    private List<Club> clubs;

    public ClubAdapter(@NonNull Activity context, List<Club> clubs) {
        super(context, R.layout.activity_club_list, clubs);
        this.context = context;
        this.clubs = clubs;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_event_list, null, true);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView textClub = (TextView) listViewItem.findViewById(R.id.textClub);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView textClubOwner = (TextView) listViewItem.findViewById(R.id.textClubOwner);

        Club c = clubs.get(position);
        textClub.setText(c.getClubName());
        textClubOwner.setText(c.getUsername());
        return listViewItem;
    }
}
