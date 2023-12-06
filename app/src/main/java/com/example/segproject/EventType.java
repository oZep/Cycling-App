package com.example.segproject;

public class EventType {
    int minAge;
    int level;
    String name;

    public static final EventType TIME_TRIAL = new EventType("time trial", 1, 2);
    public static final EventType HILL_CLIMB = new EventType("hill climb", 2, 3);
    public static final EventType ROAD_STAGE_RACE = new EventType("road stage race", 3, 4);
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
