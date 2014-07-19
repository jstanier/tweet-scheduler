package com.jstanier.tweetscheduler.ranking;

import com.google.common.collect.Lists;
import com.jstanier.tweetscheduler.domain.TimeSlot;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RankedTimeSlots {
    
    @Autowired
    private RankedDaysOfWeek rankedDaysOfWeek;
    
    @Autowired
    private RankedTimesOfWeek rankedTimesOfWeek;
    
    private List<TimeSlot> rankedTimeSlots;

    @PostConstruct
    private void generateRankedTimeSlots() {
        rankedTimeSlots = Lists.newArrayList();
        List<Integer> timesOfWeek = rankedTimesOfWeek.getRankedTimesOfWeek();
        List<Integer> daysOfWeek = rankedDaysOfWeek.getRankedDaysOfWeek();
        for (Integer timeOfWeek : timesOfWeek) {
            for(Integer dayOfWeek : daysOfWeek) {
                TimeSlot timeslot = new TimeSlot(timeOfWeek, dayOfWeek);
                rankedTimeSlots.add(timeslot);
            }
        }
    }
    
    public List<TimeSlot> getRankedTimeSlots() {
        return rankedTimeSlots;
    }
}
