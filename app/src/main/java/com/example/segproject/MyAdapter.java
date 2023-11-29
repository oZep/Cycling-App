package com.example.segproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> names;
    private ArrayList<Integer> ages, levels;

    public MyAdapter(Context context, ArrayList<String> n, ArrayList<Integer> a, ArrayList<Integer> l) {
        this.context = context;
        names = n;
        ages = a;
        levels = l;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.event_type, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name_id.setText(String.valueOf(names.get(position)));
        holder.level_id.setText(String.valueOf(levels.get(position)));
        holder.age_id.setText(String.valueOf(ages.get(position)));

    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name_id, level_id, age_id;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name_id = itemView.findViewById(R.id.textname);
            level_id = itemView.findViewById(R.id.textlevel);
            age_id = itemView.findViewById(R.id.textage);
        }
    }
}
