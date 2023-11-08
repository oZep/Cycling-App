package com.example.firebaseapp2;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "activityDB.db";
    public static final String TABLE_ACTIVITIES = "activity";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ACTIVITYNAME = "activityname";

    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_ACTIVITIES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_ACTIVITYNAME
                + " TEXT" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITIES);
        onCreate(db);
    }

    public void addProduct(BikeActivity product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ACTIVITYNAME, product. getActivityName());

        db.insert(TABLE_ACTIVITIES, null, values);
        db.close();
    }

    public BikeActivity findProduct(String ActivityName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * FROM " + TABLE_ACTIVITIES + " WHERE " + COLUMN_ACTIVITYNAME
                + " = \"" + ActivityName + "\"";

        Cursor cursor = db.rawQuery(query, null);
        BikeActivity activity = new BikeActivity();
        if (cursor.moveToFirst()) {
            activity.setId(Integer.parseInt(cursor.getString(0)));
            activity.setActivityName(cursor.getString(1));
            cursor.close();

        } else {
            activity = null;
        }
        db.close();
        return activity;
    }

    public boolean deleteProduct(String activityname) {
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * FROM " + TABLE_ACTIVITIES + " WHERE " + COLUMN_ACTIVITYNAME +
                " = \"" + activityname + "\"";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            String idStr = cursor.getString(0);
            db.delete(TABLE_ACTIVITIES, COLUMN_ID + " = " + idStr, null);
            cursor.close();
            result = true;
        }

        db.close();
        return result;
    }

    public int getSize(SQLiteDatabase db) throws SQLException {
        String query = "SELECT COUNT(*) AS total FROM " + TABLE_ACTIVITIES+";";
        Cursor cursor = db.rawQuery(query, null);
        return cursor.getInt(Math.abs(cursor.getColumnIndex("total")));
    }

    public SQLiteDatabase getDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db;
    }


}