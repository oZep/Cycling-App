package com.example.segproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminEventDBHandler extends SQLiteOpenHelper {
    public AdminEventDBHandler(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table UserDetails(name TEXT primary key, email TEXT, age TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists UserDetails");

    }

    public boolean insertUserData(String name, String email, String age) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("age", age);
        long result = DB.insert("Userdetails", null, contentValues);
        if (result == -1) {
            return false;
        } else return true;
    }

    //TODO add something for deleting the data
    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails", null );
        return cursor;
    }

    public boolean deleteUserData(String name) {
        SQLiteDatabase DB = this.getWritableDatabase();
        long result = DB.delete("Userdetails", "email=? OR name=? OR age=?", new String[]{name});
        return result != -1;
    }
}


