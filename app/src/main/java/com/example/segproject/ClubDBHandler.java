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
        db.execSQL("create Table ClubAccounts(email TEXT primary key, clubName TEXT, clubEvents TEXT)");
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
            eventTypes.append(eArr.get(i).getName);
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

    public UserAccount getClub(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Accounts WHERE username = \"" + username + "\"", null );
        if (!cursor.moveToFirst()) {
            return null;
        }
        UserAccount result;
        result = cursor.getInt(3) == 0 ? new Participant(cursor.getString(0), cursor.getString(1)) :
                new ClubOwner(cursor.getString(0), cursor.getString(1), cursor.getString(3), cursor.getString(4), cursor.getString(5));
        return result;
    }

    public boolean deleteClubData(String email) {
        SQLiteDatabase DB = this.getWritableDatabase();
        long result = DB.delete("Accounts", "email=?", new String[]{email});
        return result != -1;
    }
}
