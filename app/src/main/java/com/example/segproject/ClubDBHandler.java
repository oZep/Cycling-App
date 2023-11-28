package com.example.segproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ClubDBHandler extends SQLiteOpenHelper {

    public ClubDBHandler(Context context) {
        super(context, "ClubAccounts.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table ClubAccounts(username TEXT primary key, clubName TEXT, clubEvents TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int ii) {
        db.execSQL("drop Table if exists Accounts");
    }

    public boolean insertUserData(Club u) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", u.getUsername());
        contentValues.put("clubName", u.getClubName());
        StringBuilder eventTypes = new StringBuilder();
        ArrayList<EventType> eArr = u.getEventTypes();
        for (int i = 0; i < eArr.size(); i++) {
            eventTypes.append(eArr.get(i).getName());
            if (i < eArr.size() - 1) {
                eventTypes.append(" ");
            }
        }
        contentValues.put("clubEvents", eventTypes.toString());
        long result = db.insert("Accounts", null, contentValues);
        return result != -1;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from ClubAccounts", null );
        return cursor;
    }

    public Club getClub(String username, AdminEventDBHandler ETDBHandler) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from ClubAccounts WHERE username = \"" + username + "\"", null );
        if (!cursor.moveToFirst()) {
            return null;
        }
        String[] ets = cursor.getString(2).split(" ");
        ArrayList<EventType> types = new ArrayList<EventType>();
        for (String i : ets) {
            types.add(ETDBHandler.getEventType(i));
        }
        Club result = new Club(cursor.getString(0), cursor.getString(1), types);
    }

    public boolean deleteClubData(String username) {
        SQLiteDatabase DB = this.getWritableDatabase();
        long result = DB.delete("ClubAccounts", "username=?", new String[]{username});
        return result != -1;
    }
}
