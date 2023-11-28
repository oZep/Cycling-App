package com.example.segproject;

public class EventType {
    int minAge;
    int level;
    String name;
    public EventType(String n, int l, int a) {
        minAge = a;
        level = l;
        name = n;
    }

    public int getMinAge() {
        return minAge;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }
}
