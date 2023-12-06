package com.example.segproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class AccountDBHandler extends SQLiteOpenHelper {
    public AccountDBHandler(Context context) {
        super(context, "UserAccounts.db", null, 1);
        insertUserData(ClubOwner.GCC_ADMIN);
        insertUserData(Participant.CYCLING_ADDICT);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Accounts(email TEXT primary key, password TEXT, isClubOwner INTEGER, socialMedia TEXT, contact TEXT, phoneNum TEXT, reviews TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int ii) {
        db.execSQL("drop Table if exists Accounts");
    }

    public boolean insertUserData(UserAccount u) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", u.getUsername());
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
            ArrayList<ClubReview> rArr = ((Participant) u).getReviews();
            StringBuilder reviews = new StringBuilder("");
            for (int i = 0; i < rArr.size(); i++) {
                ClubReview r = rArr.get(i);
                reviews.append(String.format("%s:%d:%s", r.getRatee(), r.getRating()));
                if (i < rArr.size() - 1) {
                    reviews.append(" ");
                }
            }
            contentValues.put("reviews", reviews.toString());
        }
        long result = db.insert("Accounts", null, contentValues);
        return result == -1;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Accounts", null );
        return cursor;
    }

    public UserAccount getUser(String username, ClubDBHandler cdb, EventTypeDBHandler etdb, EventDBHandler edb) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Accounts WHERE email = \"" + username + "\"", null );
        if (!cursor.moveToFirst()) {
            return null;
        }
        if (cursor.getInt(2) == 1) {
            return new ClubOwner(cursor.getString(0), cursor.getString(1), cursor.getString(3), cursor.getString(4), cursor.getString(5));
        }
        Participant result = new Participant(cursor.getString(0), cursor.getString(1));
        String[] rArr = cursor.getString(6).split(" ");
        for (String i : rArr) {
            String[] sArr = i.split(":");
            Club c = cdb.getByClubName(sArr[0], etdb, edb, this);
            result.addReview(c, new ClubReview(result, c, Integer.parseInt(sArr[1]), ""));
        }
        return result;
    }

    public boolean deleteUserData(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("Accounts", "email=?", new String[]{email});
        return result != -1;
    }
}
