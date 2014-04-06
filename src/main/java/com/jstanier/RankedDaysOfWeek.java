package com.jstanier;

import com.google.common.collect.ImmutableList;
import java.util.List;
import org.joda.time.DateTimeConstants;
import org.springframework.stereotype.Component;

@Component
public class RankedDaysOfWeek {
    
    private final List<Integer> rankedDaysOfWeek;

    public RankedDaysOfWeek() {
        rankedDaysOfWeek = ImmutableList.of(DateTimeConstants.THURSDAY, 
                DateTimeConstants.FRIDAY, 
                DateTimeConstants.SUNDAY,
                DateTimeConstants.TUESDAY, 
                DateTimeConstants.MONDAY, 
                DateTimeConstants.WEDNESDAY, 
                DateTimeConstants.SUNDAY);
    }
    
    public List<Integer> getRankedDaysOfWeek() {
        return rankedDaysOfWeek;
    }
}
