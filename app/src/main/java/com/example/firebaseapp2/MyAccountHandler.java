package com.example.firebaseapp2;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;



public class MyAccountHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "accountDB.db";
    public static final String TABLE_ACCOUNTS = "account";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ACCOUNT = "account";

    public static final String COLUMN_USERNAME = "user";
    public static final String COLUMN_PASSWORD = "pass";

    public static final String COLUMN_TYPE = "User";

    public MyAccountHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_ACCOUNTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_ACCOUNT
                + " TEXT" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
        onCreate(db);

    }


    public void addUserAccount(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TYPE, "User");
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);

        db.insert(TABLE_ACCOUNTS, null, values);
        db.close();
    }

    public void addAdminAccount(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TYPE, "Admin");
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);

        db.insert(TABLE_ACCOUNTS, null, values);
        db.close();
    }

    public Boolean findProduct(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * FROM " + TABLE_ACCOUNTS + " WHERE " + COLUMN_ACCOUNT
                + " = \"" + username + "\"";

        Cursor cursor = db.rawQuery(query, null);
        Boolean found = false;

        if (cursor.moveToFirst()) {
            found = true;

        } else {
            found = false;
        }
        db.close();
        return found;
    }

    public boolean deleteProduct(String username) {
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * FROM " + TABLE_ACCOUNTS + " WHERE " + COLUMN_ACCOUNT +
                " = \"" + username + "\"";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            String idStr = cursor.getString(0);
            db.delete(TABLE_ACCOUNTS, COLUMN_ID + " = " + idStr, null);
            cursor.close();
            result = true;
        }

        db.close();
        return result;
    }


}