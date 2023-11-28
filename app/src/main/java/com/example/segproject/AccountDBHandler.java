package com.example.segproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        contentValues.put("username", u.getUsername());
        contentValues.put("password", u.getPassword());
        if (u instanceof ClubOwner) {
            ClubOwner c = (ClubOwner) u;
            contentValues.put("isClubOwner", 1);
            contentValues.put("socialMedia", c.getSocialMedia());
            contentValues.put("contact", c.getContact());
            contentValues.put("phoneNum", c.getPhoneNum());
        }
        else {
            contentValues.put("isClubOwner", 0);
        }
        long result = db.insert("Accounts", null, contentValues);
        return result == -1;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Accounts", null );
        return cursor;
    }

    public UserAccount getUser(String username) {
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

    public boolean deleteUserData(String email) {
        SQLiteDatabase DB = this.getWritableDatabase();
        long result = DB.delete("Accounts", "email=?", new String[]{email});
        return result != -1;
    }
}
