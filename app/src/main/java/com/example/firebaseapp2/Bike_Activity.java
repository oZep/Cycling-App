package com.example.firebaseapp2;

public class Bike_Activity {
    private String _id;
    private String _activityname;
    private double _level;

    public Bike_Activity(String id, String activityname, double level) {
        _id = id;
        _activityname = activityname;
        _level = level;
    }
    public void Activity(String activityname, double level) {
        _activityname = activityname;
        _level = level;
    }

    public void setId(String id) {
        _id = id;
    }
    public String getId() {
        return _id;
    }
    public void setActivityName(String activityname) {
        _activityname = activityname;
    }
    public String getActivityName() {
        return _activityname;
    }
    public void setLevel(double level) {
        _level = level;
    }
    public double getLevel() {
        return _level;
    }
}
