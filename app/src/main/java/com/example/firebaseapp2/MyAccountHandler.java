package com.example.segproject;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;



public class MyAccountHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "accountDB.db";
    public static final String TABLE_PRODUCTS = "account";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USERNAME = "accountname";
    public static final String COLUMN_PASSWORD = "passwordname";
    public static final Bool COLUMN_TYPE = null;
    public static final String COLUMN_SKU = "SKU";

    public MyAccountHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_PRODUCTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_PRODUCTNAME
                + " TEXT," + COLUMN_SKU + " INTEGER" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);

    }


    public void addUserAccount(String Username, String Passowrd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, Username. getActivityName());
        values.put(COLUMN_PASSWORD, Password. getActivityName());
        values.put(COLUMN_TYPE, True. getActivityName());
        values.put(COLUMN_SKU, Username.getSku());

        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    public void addAdminAccount(String Username, String Passowrd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, Username. getActivityName());
        values.put(COLUMN_PASSWORD, Password. getActivityName());
        values.put(COLUMN_TYPE, False. getActivityName());
        values.put(COLUMN_SKU, Username.getSku());

        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    public Product findProduct(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_USERNAME
                + " = \"" + accountname + "\"";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            username.setID(Integer.parseInt(cursor.getString(0)));
            username.setActivityName(cursor.getString(1));
            username.setSku(Integer.parseInt(cursor.getString(2)));
            cursor.close();

        } else {
            username = null;
        }
        db.close();
        return username;
    }

    public boolean deleteProduct(String username) {
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_USERNAME +
                " = \"" + accountname + "\"";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            String idStr = cursor.getString(0);
            db.delete(TABLE_PRODUCTS, COLUMN_ID + " = " + idStr, null);
            cursor.close();
            result = true;
        }

        db.close();
        return result;
    }


}