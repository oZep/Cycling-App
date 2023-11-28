package com.example.segproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

public class EventDBHandler extends SQLiteOpenHelper {

    public EventDBHandler(Context context) {
        super(context, "Events.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Events(name TEXT primary key, eventType TEXT, date INTEGER, location TEXT, maxParticipants INTEGER)");
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
        contentValues.put("date", e.getDate().getTime());
        contentValues.put("location", e.getLocation());
        contentValues.put("maxParticipants", e.getMaxParticipants());
        long result = db.insert("Events", null, contentValues);
        return result == -1;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Events", null );
        return cursor;
    }

    public Event getEvent(String name, AdminEventDBHandler ETBDHandler) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Events WHERE name = \"" + name + "\"", null );
        if (!cursor.moveToFirst()) {
            return null;
        }
        Event result = new Event(cursor.getString(0), ETBDHandler.getEventType(cursor.getString(1)), new Date(cursor.getLong(2)), cursor.getString(3), cursor.getInt(4));
        return result;
    }
}
