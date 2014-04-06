package com.jstanier;

public class TimeSlot {
    
    private final int hourOfDay;
    private final int dayOfWeek;
    
    public TimeSlot(int hourOfDay, int dayOfWeek) {
        this.hourOfDay = hourOfDay;
        this.dayOfWeek = dayOfWeek;
    }

    public int getHourOfDay() {
        return hourOfDay;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }
}
