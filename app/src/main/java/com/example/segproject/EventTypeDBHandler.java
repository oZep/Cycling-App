package com.example.segproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EventTypeDBHandler extends SQLiteOpenHelper {
    public EventTypeDBHandler(Context context) {
        super(context, "EventTypes.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table EventTypes(name TEXT primary key, level INTEGER, age INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists EventTypes");

    }

    public boolean insertEventType(EventType et) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", et.getName());
        contentValues.put("level", et.getLevel());
        contentValues.put("age", et.getMinAge());
        long result = DB.insert("EventTypes", null, contentValues);
        return result != -1;
    }

    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from EventTypes", null );
        return cursor;
    }

    public EventType getEventType(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from EventTypes WHERE name = \"" + name + "\"", null );
        if (!cursor.moveToFirst()) {
            return null;
        }
        EventType result = new EventType(cursor.getString(0), cursor.getInt(1), cursor.getInt(2));
        return result;
    }

    public boolean deleteEventType(String name) {
        SQLiteDatabase DB = this.getWritableDatabase();
        long result = DB.delete("EventTypes", "name=?", new String[]{name});
        return result != -1;
    }

}


