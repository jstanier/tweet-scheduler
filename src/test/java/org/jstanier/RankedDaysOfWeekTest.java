package org.jstanier;

import com.google.common.collect.ImmutableList;
import com.jstanier.RankedDaysOfWeek;
import java.util.List;
import org.joda.time.DateTimeConstants;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RankedDaysOfWeekTest {
    
    @InjectMocks
    private RankedDaysOfWeek rankedDaysOfWeek;
    
    @Test
    public void rankedDaysOfWeek_containsAllDaysOfWeekInExpectedOrder() {
        List<Integer> rankedDays = rankedDaysOfWeek.getRankedDaysOfWeek();
        ImmutableList<Integer> expectedRankedDays = ImmutableList.of(DateTimeConstants.THURSDAY, 
                DateTimeConstants.FRIDAY, 
                DateTimeConstants.SUNDAY,
                DateTimeConstants.TUESDAY,
                DateTimeConstants.MONDAY,
                DateTimeConstants.WEDNESDAY, 
                DateTimeConstants.SUNDAY);
        Assert.assertEquals(expectedRankedDays, rankedDays);
    }
}
