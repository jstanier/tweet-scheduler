package org.jstanier.tweetscheduler.scheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.joda.time.DateTimeConstants;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.google.common.collect.ImmutableList;
import com.jstanier.tweetscheduler.domain.TimeSlot;
import com.jstanier.tweetscheduler.domain.TweetToSchedule;
import com.jstanier.tweetscheduler.ranking.RankedTimeSlots;
import com.jstanier.tweetscheduler.ranking.TweetToScheduleRanker;
import com.jstanier.tweetscheduler.scheduler.Schedule;
import com.jstanier.tweetscheduler.scheduler.TweetScheduler;
import com.jstanier.tweetscheduler.util.TimeAssigner;

@RunWith(MockitoJUnitRunner.class)
public class TweetSchedulerTest {

    @Mock
    private TweetToScheduleRanker tweetToScheduleRanker;

    @Mock
    private RankedTimeSlots rankedTimeSlots;

    @Mock
    private TimeAssigner timeAssigner;

    @InjectMocks
    private TweetScheduler tweetScheduler;

    private final DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm");

    @Before
    @SuppressWarnings("unchecked")
    public void setupTests() {

        Mockito.when(tweetToScheduleRanker.rankTweetsToSchedule(Mockito.anyList())).thenAnswer(
                new Answer<List<TweetToSchedule>>() {
                    public List<TweetToSchedule> answer(InvocationOnMock invocation)
                            throws Throwable {
                        Object[] arguments = invocation.getArguments();
                        return (List<TweetToSchedule>) arguments[0];
                    }
                });
        final TimeSlot timeSlot = new TimeSlot(18, DateTimeConstants.THURSDAY);
        List<TimeSlot> timeSlots = ImmutableList.of(timeSlot);
        Mockito.when(rankedTimeSlots.getRankedTimeSlots()).thenReturn(timeSlots);
        Mockito.when(timeAssigner.assignTime((TimeSlot) Mockito.any())).thenReturn(
                formatter.parseDateTime("1/1/2014 18:00"));
    }

    @Test(expected = NullPointerException.class)
    public void scheduleTweets_whenGivenNullInput_throwsANullPointerException() {
        tweetScheduler.scheduleTweets(null);
    }

    @Test
    public void scheduleTweets_whenGivenNoTweets_returnsAnEmptySchedule() {
        List<TweetToSchedule> tweetsToSchedule = Collections.emptyList();
        Schedule schedule = tweetScheduler.scheduleTweets(tweetsToSchedule);
        Assert.assertTrue("The schedule should be empty.", schedule.getSchedule().isEmpty());
    }

    @Test
    public void scheduleTweets_whenGivenOneTweet_theScheduleContainsThatTweet() {
        List<TweetToSchedule> tweetsToSchedule = createListOfOneTweetToSchedule();
        Schedule schedule = tweetScheduler.scheduleTweets(tweetsToSchedule);
        Assert.assertEquals(1, schedule.getSchedule().size());
        Assert.assertEquals(tweetsToSchedule.get(0).getContent(), schedule
            .getSchedule()
            .iterator()
            .next()
            .getContent());
        Assert.assertEquals(0, tweetScheduler.getNumberOfTweetsNotScheduled());
    }

    @Test
    public void scheduleTweets_whenTooManyTweetsToSchedule_thenTheRemainderAreCounted() {
        List<TweetToSchedule> tweetsToSchedule = createListOfTwoTweetsToSchedule();
        Schedule schedule = tweetScheduler.scheduleTweets(tweetsToSchedule);
        Assert.assertEquals(1, schedule.getSchedule().size());
        Assert.assertEquals(tweetsToSchedule.get(0).getContent(), schedule
            .getSchedule()
            .iterator()
            .next()
            .getContent());
        Assert.assertEquals(1, tweetScheduler.getNumberOfTweetsNotScheduled());
    }

    private List<TweetToSchedule> createListOfTwoTweetsToSchedule() {
        List<TweetToSchedule> tweetsToSchedule = createListOfOneTweetToSchedule();
        TweetToSchedule tweetToSchedule = new TweetToSchedule();
        tweetToSchedule.setContent("@jstanier This won't get in the schedule...");
        tweetToSchedule.setRank(2);
        tweetsToSchedule.add(tweetToSchedule);
        return tweetsToSchedule;
    }

    private List<TweetToSchedule> createListOfOneTweetToSchedule() {
        List<TweetToSchedule> tweetsToSchedule = new ArrayList<TweetToSchedule>();
        TweetToSchedule tweetToSchedule = new TweetToSchedule();
        tweetToSchedule.setContent("@jstanier Hey!");
        tweetToSchedule.setRank(1);
        tweetsToSchedule.add(tweetToSchedule);
        return tweetsToSchedule;
    }
}
