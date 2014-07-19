package com.jstanier.tweetscheduler.util;

import com.google.common.base.Preconditions;
import com.jstanier.tweetscheduler.domain.TimeSlot;
import com.jstanier.tweetscheduler.ranking.RankedDaysOfWeek;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimeAssigner {
    
    @Autowired
    private RankedDaysOfWeek rankedDaysOfWeek;
    
    @Autowired
    private DateTimeSupplier dateTimeSupplier;
    
    public DateTime assignTime(TimeSlot timeSlot) {
        checkArgument(timeSlot);
        int dayOfWeek = timeSlot.getDayOfWeek();
        DateTime currentTime = dateTimeSupplier.get().dayOfWeek().roundFloorCopy();
        DateTime assignedTime = calculateDayOfWeek(currentTime, dayOfWeek);
        return assignedTime.plusHours(timeSlot.getHourOfDay());   
    }

    private DateTime calculateDayOfWeek(DateTime currentTime, int day) {
        DateTime assignedTime;
        int currentDayOfWeek = currentTime.getDayOfWeek();
        if (currentDayOfWeek == day) {
            assignedTime = currentTime.plusDays(7);
        } else if (currentDayOfWeek < day) {
            assignedTime = currentTime.plusDays(day - currentDayOfWeek);
        } else {
            assignedTime = currentTime.plusDays(7 - (currentDayOfWeek - day));
        }
        return assignedTime;
    }

    private void checkArgument(TimeSlot timeSlot) {
        Preconditions.checkNotNull(timeSlot);
        int dayOfWeek = timeSlot.getDayOfWeek();
        final List<Integer> validDays = rankedDaysOfWeek.getRankedDaysOfWeek();
        Preconditions.checkArgument(validDays.contains(dayOfWeek));
    }
}
