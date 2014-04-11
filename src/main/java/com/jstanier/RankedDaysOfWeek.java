package com.jstanier;

import java.util.List;

import org.joda.time.DateTimeConstants;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableList;

@Component
public class RankedDaysOfWeek {

    private final List<Integer> rankedDaysOfWeek;

    public RankedDaysOfWeek() {
        rankedDaysOfWeek = ImmutableList.of(DateTimeConstants.THURSDAY, DateTimeConstants.FRIDAY,
                DateTimeConstants.SUNDAY, DateTimeConstants.SATURDAY, DateTimeConstants.TUESDAY,
                DateTimeConstants.WEDNESDAY, DateTimeConstants.MONDAY);
    }

    public List<Integer> getRankedDaysOfWeek() {
        return rankedDaysOfWeek;
    }
}
