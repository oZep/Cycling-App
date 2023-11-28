package com.example.segproject;

public class EventType {
    int[] ageRange;
    int level;
    String name;
    public EventType(int[] ar, int l, String n) {
        ageRange = ar;
        level = l;
        name = n;
    }

    public int[] getAgeRange() {
        return ageRange;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }
}
