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

public class EventAdapter extends ArrayAdapter<Event> {
    private Activity context;
    List<Event> events;

    public EventAdapter(@NonNull Activity context, List<Event> events) {
        super(context, R.layout.activity_event_list, events);
        this.context = context;
        this.events = events;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_event_list, null, true);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView textEvent = (TextView) listViewItem.findViewById(R.id.textEvent);

        Event event = events.get(position);
        textEvent.setText(event.getName());
        return listViewItem;
    }
}
