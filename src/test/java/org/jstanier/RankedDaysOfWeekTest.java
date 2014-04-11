package org.jstanier;

import java.util.List;

import org.joda.time.DateTimeConstants;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.ImmutableList;
import com.jstanier.RankedDaysOfWeek;

@RunWith(MockitoJUnitRunner.class)
public class RankedDaysOfWeekTest {

    @InjectMocks
    private RankedDaysOfWeek rankedDaysOfWeek;

    @Test
    public void rankedDaysOfWeek_containsAllDaysOfWeekInExpectedOrder() {
        List<Integer> rankedDays = rankedDaysOfWeek.getRankedDaysOfWeek();
        ImmutableList<Integer> expectedRankedDays = ImmutableList.of(DateTimeConstants.THURSDAY,
                DateTimeConstants.FRIDAY, DateTimeConstants.SUNDAY, DateTimeConstants.SATURDAY,
                DateTimeConstants.TUESDAY, DateTimeConstants.WEDNESDAY, DateTimeConstants.MONDAY);
        Assert.assertEquals(expectedRankedDays, rankedDays);
    }
}
