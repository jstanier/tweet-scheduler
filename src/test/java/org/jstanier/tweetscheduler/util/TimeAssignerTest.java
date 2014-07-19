package org.jstanier.tweetscheduler.util;

import com.google.common.collect.ImmutableList;
import com.jstanier.tweetscheduler.domain.TimeSlot;
import com.jstanier.tweetscheduler.ranking.RankedDaysOfWeek;
import com.jstanier.tweetscheduler.util.DateTimeSupplier;
import com.jstanier.tweetscheduler.util.TimeAssigner;

import org.joda.time.DateTime;
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
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TimeAssignerTest {
    
    @Mock
    private RankedDaysOfWeek rankedDaysOfWeek;
    
    @Mock
    private DateTimeSupplier dateTimeSupplier;
    
    @InjectMocks
    private TimeAssigner timeAssigner;
    
    private final DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm");
    
    @Before
    public void setupTests() {
        DateTime firstOfJanuary2014 = formatter.parseDateTime("1/1/2014 15:00");
        Mockito.when(dateTimeSupplier.get()).thenReturn(firstOfJanuary2014);
        Mockito.when(rankedDaysOfWeek.getRankedDaysOfWeek()).thenReturn(ImmutableList.of(DateTimeConstants.WEDNESDAY, DateTimeConstants.SATURDAY, DateTimeConstants.MONDAY));
    }
    
    @Test(expected = NullPointerException.class) 
    public void assignTime_whenGivenANullArgument_throwsANullPointerException() {
        timeAssigner.assignTime(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void assignTime_whenGivenAnInvalidInput_throwsIllegalArgumentException() {
        TimeSlot timeSlot = new TimeSlot(0, DateTimeConstants.DECEMBER);
        timeAssigner.assignTime(timeSlot);
    }
    
    @Test
    public void assignTime_whenGivenTheSameDayOfWeekToTheCurrentDay_returnsOneWeekInTheFuture() {
        TimeSlot timeSlot = new TimeSlot(8, DateTimeConstants.WEDNESDAY);
        DateTime assignedTime = timeAssigner.assignTime(timeSlot);
        DateTime expectedTime = formatter.parseDateTime("8/1/2014 08:00");
        Assert.assertEquals("The assigned time should be one week in the future, at 8AM.", expectedTime, assignedTime);
    }
    
    @Test
    public void assignTime_whenGivenADayOfWeekAheadOfCurrentDay_returnsTheNextDateOfThatDay() {
        TimeSlot timeSlot = new TimeSlot(15, DateTimeConstants.SATURDAY);
        DateTime assignedTime = timeAssigner.assignTime(timeSlot);
        DateTime expectedTime = formatter.parseDateTime("4/1/2014 15:00");
        Assert.assertEquals("The assigned time should be three days in the future, at 3PM.", expectedTime, assignedTime);
    }
    
    @Test
    public void assignTime_whenGivenADayOfWeekBeforeCurrentDay_returnsTheNextDateOfThatDay() {
        TimeSlot timeSlot = new TimeSlot(23, DateTimeConstants.MONDAY);
        DateTime assignedTime = timeAssigner.assignTime(timeSlot);
        DateTime expectedTime = formatter.parseDateTime("6/1/2014 23:00");
        Assert.assertEquals("The assigned time should be five days in the future, at 11PM.", expectedTime, assignedTime);
    }
}
