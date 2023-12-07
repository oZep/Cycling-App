package com.example.segproject;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.segproject.ClubOwner;
import com.example.segproject.Participant;
import com.example.segproject.UserAccount;

public class AccountDBHandler extends SQLiteOpenHelper {
    public AccountDBHandler(Context context) {
        super(context, "UserAccounts.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Accounts(email TEXT primary key, password TEXT, isClubOwner INTEGER, socialMedia TEXT, contact TEXT, phoneNum TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int ii) {
        db.execSQL("drop Table if exists Accounts");
    }

    public boolean insertUserData(UserAccount u) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", u.getUsername());  // Corrected column name to "email"
        contentValues.put("password", u.getPassword());
        if (u instanceof ClubOwner) {
            ClubOwner c = (ClubOwner) u;
            contentValues.put("isClubOwner", 1);
            contentValues.put("socialMedia", c.getSocialMedia());
            contentValues.put("contact", c.getContact());
            contentValues.put("phoneNum", c.getPhoneNum());
        } else {
            contentValues.put("isClubOwner", 0);
        }
        long result = db.insert("Accounts", null, contentValues);
        return result == -1;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Accounts", null);
        return cursor;
    }

    @SuppressLint("Range")
    public UserAccount getUser(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Accounts WHERE email = \"" + email + "\"", null);
        if (!cursor.moveToFirst()) {
            return null;
        }
        UserAccount result;
        result = cursor.getInt(cursor.getColumnIndex("isClubOwner")) == 0 ?
                new Participant(cursor.getString(cursor.getColumnIndex("email")), cursor.getString(cursor.getColumnIndex("password"))) :
                new ClubOwner(
                        cursor.getString(cursor.getColumnIndex("email")),
                        cursor.getString(cursor.getColumnIndex("password")),
                        cursor.getString(cursor.getColumnIndex("socialMedia")),
                        cursor.getString(cursor.getColumnIndex("contact")),
                        cursor.getString(cursor.getColumnIndex("phoneNum"))
                );
        return result;
    }

    public boolean deleteUserData(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("Accounts", "email=?", new String[]{email});
        return result != -1;
    }
}