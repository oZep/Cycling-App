package com.example.segproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class EventDBHandler extends SQLiteOpenHelper {

    public EventDBHandler(Context context) {
        super(context, "Events.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Events(name TEXT primary key, eventType TEXT, club TEXT, date INTEGER, location TEXT, maxParticipants INTEGER, participants TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists Events");
    }

    public boolean insertEvent(Event e) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", e.getName());
        contentValues.put("eventType", e.getEventType().getName());
        contentValues.put("club", e.getClub().getClubName());
        contentValues.put("date", e.getDate().getTime());
        contentValues.put("location", e.getLocation());
        contentValues.put("maxParticipants", e.getMaxParticipants());
        StringBuilder participants = new StringBuilder("");
        ArrayList<Participant> pArr = e.getParticipants();
        for (int i = 0; i < pArr.size(); i++) {
            participants.append(pArr.get(i).getUsername());
            if (i < pArr.size() - 1) {
                participants.append(" ");
            }
        }
        contentValues.put("participants", participants.toString());
        long result = db.insert("Events", null, contentValues);
        return result == -1;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Events", null );
        return cursor;
    }

    public Event getEvent(String name, ClubDBHandler cdb, EventTypeDBHandler etdb, AccountDBHandler adb) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Events WHERE name = \"" + name + "\"", null );
        if (!cursor.moveToFirst()) {
            return null;
        }
        Event result = new Event(cursor.getString(0), etdb.getEventType(cursor.getString(1)), cdb.getClub(cursor.getString(2), etdb, this, adb), new Date(cursor.getLong(3)), cursor.getString(4), cursor.getInt(5));
        String[] ppl = cursor.getString(6).split(" ");
        for (String i : ppl) {
            adb.getUser(i, cdb, etdb, this);
        }
        return result;
    }

    public ArrayList<Event> getEvents(Club club, ClubDBHandler cdb, EventTypeDBHandler etdb, AccountDBHandler adb) {
        ArrayList<Event> result = new ArrayList<Event>();
        Cursor cursor = getData();
        while(cursor.moveToNext()){
            if (cursor.getString(2).equals(club.getClubName())) {
                result.add(new Event(cursor.getString(0), etdb.getEventType(cursor.getString(1)), club, new Date(cursor.getLong(3)), cursor.getString(4), cursor.getInt(5)));
                String[] ppl = cursor.getString(6).split(" ");
                for (String i : ppl) {
                    adb.getUser(i, cdb, etdb, this);
                }
                return result;
            }
        }
        return result;
    }

    public boolean deleteEvent(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("Events", "name=?", new String[]{name});
        return result != -1;
    }
}
