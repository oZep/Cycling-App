package com.example.firebaseapp2;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;



public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "activityDB.db";
    public static final String TABLE_ACTIVITIES = "activity";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCTNAME = "activityname";
    public static final String COLUMN_SKU = "SKU";

    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_ACTIVITIES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_PRODUCTNAME
                + " TEXT," + COLUMN_SKU + " INTEGER" + ")";
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
        values.put(COLUMN_PRODUCTNAME, product. getActivityName());
        values.put(COLUMN_SKU, product.getSku());

        db.insert(TABLE_ACTIVITIES, null, values);
        db.close();
    }

    public Product findProduct(String productname) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * FROM " + TABLE_ACTIVITIES + " WHERE " + COLUMN_PRODUCTNAME
                + " = \"" + productname + "\"";

        Cursor cursor = db.rawQuery(query, null);
        BikeActivity product = new BikeActivity();
        if (cursor.moveToFirst()) {
            product.setID(Integer.parseInt(cursor.getString(0)));
            product.setActivityName(cursor.getString(1));
            product.setSku(Integer.parseInt(cursor.getString(2)));
            cursor.close();

        } else {
            product = null;
        }
        db.close();
        return product;
    }

    public boolean deleteProduct(String productname) {
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * FROM " + TABLE_ACTIVITIES + " WHERE " + COLUMN_PRODUCTNAME +
                " = \"" + productname + "\"";
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


}