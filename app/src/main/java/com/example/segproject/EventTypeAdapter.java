package com.example.segproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class EventTypeAdapter extends ArrayAdapter<EventType> {

    private Activity context;
    private List<String> eventTypes;
    private boolean[] checked;
    public EventTypeAdapter(@NonNull Activity context, List<String> l, boolean[] b) {
        super(context, R.layout.activity_club_owner_establish_event_type);
        this.context = context;
        eventTypes = l;
        checked = b;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_club_owner_establish_event_type, null, true);
        CompoundButton cb = (CompoundButton) convertView.findViewById(R.id.checkBox);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView textEventType = (TextView) listViewItem.findViewById(R.id.textEventType);

        String eventType = eventTypes.get(position);
        textEventType.setText(eventType);
        cb.setChecked(checked[position]);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checked[position] = cb.isChecked();
            }
        });

        return listViewItem;
    }

    public ArrayList<String> getChecked() {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < eventTypes.size(); i++) {
            if (checked[i]) {
               result.add(eventTypes.get(i));
            }
        }
        return result;
    }
}
