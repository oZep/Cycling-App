package com.example.firebaseapp2;

public class BikeActivity {
    private int _id;
    private String _activityname;

    private String _location;
    private int _level;

    public BikeActivity(int id, String activityname, int level, String location) {
        _id = id;
        _activityname = activityname;
        _location = location;
        _level = level;
    }
    public BikeActivity(){}
    public void Activity(String activityname, int level, String location, int sku) {
        _activityname = activityname;
        _level = level;
        _location = location;
    }

    public void setId(int id) {
        _id = id;
    }
    public int getId() {
        return _id;
    }

    public String getLocation() {
        return _location;
    }
    public void setActivityName(String activityname) {
        _activityname = activityname;
    }
    public String getActivityName() {
        return _activityname;
    }
    public void setLevel(int level) {
        _level = level;
    }
    public int getLevel() {return _level;}

}
