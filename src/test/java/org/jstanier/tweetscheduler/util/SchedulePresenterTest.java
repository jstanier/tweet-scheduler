package org.jstanier.tweetscheduler.util;

import static com.google.common.collect.Sets.newHashSet;
import static org.joda.time.format.DateTimeFormat.forPattern;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.HashSet;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.jstanier.tweetscheduler.domain.ScheduledTweet;
import com.jstanier.tweetscheduler.scheduler.Schedule;
import com.jstanier.tweetscheduler.util.SchedulePresenter;

@RunWith(MockitoJUnitRunner.class)
public class SchedulePresenterTest {

    private static final String TWEET_TIME_TO_POST = "Wed, 16 April 2014 08:00";
    private static final String TWEET_CONTENT = "@jstanier woah!";

    private static DateTimeFormatter DATE_TIME_FORMATTER = forPattern("E, dd MMMM yyyy HH:mm");

    @Mock
    private Schedule schedule;

    @Mock
    private ScheduledTweet scheduledTweetMock;

    @InjectMocks
    private SchedulePresenter schedulePresenter;

    @Before
    public void setupTests() {
        when(scheduledTweetMock.getContent()).thenReturn(TWEET_CONTENT);
        when(scheduledTweetMock.getTimeToPost()).thenReturn(
                DATE_TIME_FORMATTER.parseDateTime(TWEET_TIME_TO_POST));
        HashSet<ScheduledTweet> scheduledTweets = newHashSet(scheduledTweetMock);
        when(schedule.getSchedule()).thenReturn(scheduledTweets);
    }

    @Test(expected = NullPointerException.class)
    public void present_whenInputIsNull_thenANullPointerExceptionIsThrown() {
        schedulePresenter.present(null);
    }

    @Test
    public void present_whenInputScheduleIsEmpty_thenEmptyStringIsReturned() {
        when(schedule.getSchedule()).thenReturn(Collections.<ScheduledTweet> emptySet());
        String presentedSchedule = schedulePresenter.present(schedule);
        assertEquals("", presentedSchedule);
    }

    @Test
    public void present_whenInputScheduleHasOneItem_thenOneItemIsPresented() {
        String presentedSchedule = schedulePresenter.present(schedule);
        DateTime expectedTime = DATE_TIME_FORMATTER.parseDateTime(TWEET_TIME_TO_POST);
        String expectedPresentedTime = DATE_TIME_FORMATTER.print(expectedTime);
        String expectedPresentedSchedule = expectedPresentedTime + " - " + TWEET_CONTENT + "\n";
        assertEquals(expectedPresentedSchedule, presentedSchedule);
    }
}
