package com.jstanier;

import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TweetScheduler {
    
    @Autowired
    private TweetToScheduleRanker tweetToScheduleRanker;
    
    @Autowired
    private RankedTimeSlots rankedTimeSlots;
    
    @Autowired
    private TimeAssigner timeAssigner;
    
    private int tweetsNotScheduled = 0;
    
    public Schedule scheduleTweets(List<TweetToSchedule> tweetsToSchedule) {
        Preconditions.checkNotNull(tweetsToSchedule);
        List<Tweet> previousTweets = Collections.emptyList();
        return scheduleTweets(tweetsToSchedule, previousTweets);
    }
    
    public Schedule scheduleTweets(List<TweetToSchedule> tweetsToSchedule, List<Tweet> previousTweets) {
        Schedule schedule = new Schedule();
        List<TweetToSchedule> rankedTweetsToSchedule = tweetToScheduleRanker.rankTweetsToSchedule(tweetsToSchedule);
        List<TimeSlot> timeSlots = rankedTimeSlots.getRankedTimeSlots();
        for (int i = 0; i < rankedTweetsToSchedule.size(); i++) {
            TweetToSchedule tweetToSchedule = rankedTweetsToSchedule.get(i);
            if (i < timeSlots.size()) {
                TimeSlot timeSlot = timeSlots.get(i);
                ScheduledTweet scheduledTweet = new ScheduledTweet();
                scheduledTweet.setContent(tweetToSchedule.getContent());
                scheduledTweet.setTimeToPost(timeAssigner.assignTime(timeSlot));
                schedule.addToSchedule(scheduledTweet);
            } else {
                tweetsNotScheduled++;
            }
        }
        return schedule;
    }
    
    public int getNumberOfTweetsNotScheduled() {
        return tweetsNotScheduled;
    }
}
