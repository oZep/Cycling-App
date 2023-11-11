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
        db.execSQL("create Table Accounts(email TEXT primary key, username TEXT, password TEXT, isClubOwner INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int ii) {
        db.execSQL("drop Table if exists Accounts");
    }

    public boolean insertUserData(UserAccount u) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", u.getEmail());
        contentValues.put("username", u.getUsername());
        contentValues.put("password", u.getPassword());
        if (u instanceof ClubOwner) {
            contentValues.put("isClubOwner", 1);
        }
        else {
            contentValues.put("isClubOwner", 0);
        }
        long result = db.insert("Accounts", null, contentValues);
        if (result == -1) {
            return false;
        } else return true;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Accounts", null );
        return cursor;
    }

    public UserAccount getUser(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Accounts WHERE email = \"" + email + "\"", null );
        if (!cursor.moveToFirst()) {
            return null;
        }
        UserAccount result;
        result = cursor.getInt(3) == 0 ? new Participant(cursor.getString(1), cursor.getString(0), cursor.getString(2)) :
                new ClubOwner(cursor.getString(1), cursor.getString(0), cursor.getString(2));
        return result;
    }

    public boolean deleteUserData(String email) {
        SQLiteDatabase DB = this.getWritableDatabase();
        long result = DB.delete("Accounts", "email=?", new String[]{email});
        return result != -1;
    }
}
