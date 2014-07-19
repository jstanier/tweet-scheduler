package org.jstanier.tweetscheduler.ranking;

import com.google.common.collect.ImmutableList;
import com.jstanier.tweetscheduler.ranking.RankedTimesOfWeek;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RankedTimesOfWeekTest {
    
    @InjectMocks
    private RankedTimesOfWeek rankedTimesOfWeek;
    
    @Test
    public void rankedTimesOfWeek_containsTimesInExpectedOrder() {
        ImmutableList<Integer> expectedTimes = ImmutableList.of(14,20,21,17,9,8,15,23,0);
        List<Integer> rankedTimes = rankedTimesOfWeek.getRankedTimesOfWeek();
        Assert.assertEquals(expectedTimes, rankedTimes);
    }
}
