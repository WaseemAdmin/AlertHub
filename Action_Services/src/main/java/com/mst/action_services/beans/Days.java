package com.mst.action_services.beans;

public enum Days{
    ALL(0),
    SUNDAY(1),
    MONDAY(2),
    TUESDAY(3),
    WEDNESDAY(4),
    THURSDAY(5),
    FRIDAY(6),
    SATURDAY(7);

    private final int value;

    Days(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Days fromValue(int value) {
        for (Days day : Days.values()) {
            if (day.value == value) {
                return day;
            }
        }
        throw new IllegalArgumentException("Invalid integer value for Days enum: " + value);
    }
}
