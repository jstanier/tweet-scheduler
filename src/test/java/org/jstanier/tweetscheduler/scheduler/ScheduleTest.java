package org.jstanier.tweetscheduler.scheduler;

import com.jstanier.tweetscheduler.domain.ScheduledTweet;
import com.jstanier.tweetscheduler.scheduler.Schedule;

import java.util.Set;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleTest {
    
    private final DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm");
   
    @InjectMocks
    private Schedule schedule;
    
    @Test(expected = NullPointerException.class)
    public void addToSchedule_whenANullScheduledTweetIsAdded_thenANullPointerExceptionIsThrown() {
        schedule.addToSchedule(null);
    }
    
    @Test
    public void scheduleShouldBeEmptyOnInitialisation() {
        Assert.assertTrue(schedule.getSchedule().isEmpty());
    }
    
    @Test
    public void addToSchedule_whenOneTweetIsAdded_thenThereIsOneTweetInTheSchedule() {
        ScheduledTweet scheduledTweet = createScheduledTweet("@jstanier Cool!", "1/1/2014 00:00");
        schedule.addToSchedule(scheduledTweet);
        Set<ScheduledTweet> scheduledTweets = schedule.getSchedule();
        Assert.assertEquals("There should be one tweet in the schedule.", 1, scheduledTweets.size());
        Assert.assertTrue("The scheduled tweet should be in the schedule.", scheduledTweets.contains(scheduledTweet));
    }
    
    @Test
    public void addToSchedule_whenTwoTweetsAreAdded_thenTheEarliestOneIsFirstInTheSchedule() {
        ScheduledTweet firstTweetToSchedule = createScheduledTweet("@jstanier Cool!", "1/1/2014 00:00");
        schedule.addToSchedule(firstTweetToSchedule);
        ScheduledTweet secondTweetToSchedule = createScheduledTweet("@jstanier Even cooler!", "1/1/2014 01:00");
        schedule.addToSchedule(secondTweetToSchedule);
        Set<ScheduledTweet> scheduledTweets = schedule.getSchedule();
        Assert.assertEquals("There should be two tweets in the schedule.", 2, scheduledTweets.size());
        Assert.assertTrue("The first scheduled tweet should be in the schedule.", scheduledTweets.contains(firstTweetToSchedule));
        Assert.assertTrue("The second scheduled tweet should be in the schedule.", scheduledTweets.contains(secondTweetToSchedule));
        Assert.assertEquals("The earlier tweet should be first in the schedule.", firstTweetToSchedule, scheduledTweets.iterator().next());
    }
    
    private ScheduledTweet createScheduledTweet(String content, String timeToPost) {
        ScheduledTweet scheduledTweet = new ScheduledTweet();
        scheduledTweet.setContent(content);
        scheduledTweet.setTimeToPost(formatter.parseDateTime(timeToPost));
        return scheduledTweet;
    }
}
