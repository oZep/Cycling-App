package com.example.segproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class ClubDBHandler extends SQLiteOpenHelper {

    public ClubDBHandler(Context context) {
        super(context, "ClubAccounts.db", null, 1);
        insertUserData(Club.GCC_CLUB);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table ClubAccounts(username TEXT primary key, clubName TEXT, clubEventType TEXT, clubEvents TEXT, participants TEXT, raters TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int ii) {
        db.execSQL("drop Table if exists ClubAccounts");
    }

    public boolean insertUserData(Club u) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", u.getUsername());
        contentValues.put("clubName", u.getClubName());
        contentValues.put("clubEventType", u.getEventType().toString());
        StringBuilder events = new StringBuilder("");
        ArrayList<Event> eArr = u.getEvents();

        for (int i = 0; i < eArr.size(); i++) {
            events.append(eArr.get(i).getName());
            if (i < eArr.size() - 1) {
                events.append(" ");
            }
        }
        contentValues.put("clubEvents", events.toString());
        StringBuilder participants = new StringBuilder("");
        ArrayList<Participant> pArr = u.getParticipants();
        for (int i = 0; i < pArr.size(); i++) {
            participants.append(pArr.get(i).getUsername());
            if (i < pArr.size() - 1) {
                participants.append(" ");
            }
        }
        contentValues.put("participants", participants.toString());
        StringBuilder raters = new StringBuilder("");
        ArrayList<ClubReview> rArr = u.getReviews();
        for (int i = 0; i < rArr.size(); i++) {
            raters.append(rArr.get(i).getRater().getUsername());
            if (i < rArr.size() - 1) {
                raters.append(" ");
            }
        }
        contentValues.put("raters", raters.toString());
        long result = db.insert("ClubAccounts", null, contentValues);
        return result != -1;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from ClubAccounts", null );
        return cursor;
    }

    public Club getClub(String username, EventTypeDBHandler etdb, EventDBHandler edb, AccountDBHandler adb) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from ClubAccounts WHERE username = \"" + username + "\"", null );
        if (!cursor.moveToFirst()) {
            return null;
        }
        EventType type = etdb.getEventType(cursor.getString(2));
        String[] evnt = cursor.getString(3).split(" ");
        ArrayList<Event> events = new ArrayList<Event>();
        for (String i : evnt) {
            events.add(edb.getEvent(i, this, etdb, adb));
        }
        Club result = new Club(cursor.getString(0), cursor.getString(1), type);
        String[] ppl = cursor.getString(4).split(" ");
        for (String i : ppl) {
            result.addParticipant((Participant) adb.getUser(i, this, etdb, edb));
        }
        String[] r = cursor.getString(5).split(" ");
        for (String i : r) {
            result.addReview(((Participant) adb.getUser(i, this, etdb, edb)).findReview(result));
        }
        return result;
    }

    public ArrayList<Club> getClubsByEventType(String etName, EventTypeDBHandler etdb, EventDBHandler edb, AccountDBHandler adb) {
        ArrayList<Club> result = new ArrayList<Club>();
        Cursor cursor = getData();
        while (cursor.moveToNext()) {
            if (cursor.getString(2).equals(etName)) {
                EventType type = etdb.getEventType(cursor.getString(2));
                String[] evnt = cursor.getString(3).split(" ");
                ArrayList<Event> events = new ArrayList<Event>();
                for (String i : evnt) {
                    events.add(edb.getEvent(i, this, etdb, adb));
                }
                Club c = new Club(cursor.getString(0), cursor.getString(1), type);
                String[] ppl = cursor.getString(4).split(" ");
                for (String i : ppl) {
                    c.addParticipant((Participant) adb.getUser(i, this, etdb, edb));
                }
                String[] r = cursor.getString(5).split(" ");
                for (String i : r) {
                    c.addReview(((Participant) adb.getUser(i, this, etdb, edb)).findReview(c));
                }
                result.add(c);
            }
        }
        return result;
    }

    public Club getByEventName(String eName, EventTypeDBHandler etdb, EventDBHandler edb, AccountDBHandler adb) {
        Cursor cursor = getData();
        while (cursor.moveToNext()) {
            String[] eventNames = cursor.getString(3).split(" ");
            if (findString(eventNames, eName)) {
                EventType type = etdb.getEventType(cursor.getString(2));
                ArrayList<Event> events = new ArrayList<Event>();
                for (String i : eventNames) {
                    events.add(edb.getEvent(i, this, etdb, adb));
                }
                Club result = new Club(cursor.getString(0), cursor.getString(1), type);
                String[] ppl = cursor.getString(4).split(" ");
                for (String i : ppl) {
                    result.addParticipant((Participant) adb.getUser(i, this, etdb, edb));
                }
                String[] r = cursor.getString(5).split(" ");
                for (String i : r) {
                    result.addReview(((Participant) adb.getUser(i, this, etdb, edb)).findReview(result));
                }
                return result;
            }
        }
        return null;
    }

    public Club getByClubName(String cName, EventTypeDBHandler etdb, EventDBHandler edb, AccountDBHandler adb) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from ClubAccounts WHERE clubName = \"" + cName + "\"", null );
        if (!cursor.moveToFirst()) {
            return null;
        }
        EventType type = etdb.getEventType(cursor.getString(2));
        String[] evnt = cursor.getString(3).split(" ");
        ArrayList<Event> events = new ArrayList<Event>();
        for (String i : evnt) {
            events.add(edb.getEvent(i, this, etdb, adb));
        }
        Club result = new Club(cursor.getString(0), cursor.getString(1), type);
        String[] ppl = cursor.getString(4).split(" ");
        for (String i : ppl) {
            result.addParticipant((Participant) adb.getUser(i, this, etdb, edb));
        }
        String[] r = cursor.getString(5).split(" ");
        for (String i : r) {
            result.addReview(((Participant) adb.getUser(i, this, etdb, edb)).findReview(result));
        }
        return result;
    }

    public boolean deleteClubData(String username) {
        SQLiteDatabase DB = this.getWritableDatabase();
        long result = DB.delete("ClubAccounts", "username=?", new String[]{username});
        return result != -1;
    }

    private boolean findString(String[] arr, String s) {
        for (String i : arr) {
            if (i.equals(s)) {
                return true;
            }
        }
        return false;
    }
}
