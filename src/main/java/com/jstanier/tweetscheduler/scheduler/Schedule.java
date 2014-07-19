package com.jstanier.tweetscheduler.scheduler;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.jstanier.tweetscheduler.domain.ScheduledTweet;

import java.util.Set;

public class Schedule {
    
    private final Set<ScheduledTweet> schedule;

    public Schedule() {
        schedule = Sets.newTreeSet();
    }
    
    public void addToSchedule(ScheduledTweet scheduledTweet) {
        Preconditions.checkNotNull(scheduledTweet);
        schedule.add(scheduledTweet);
    }
    
    public Set<ScheduledTweet> getSchedule() {
        return schedule;
    }
}
