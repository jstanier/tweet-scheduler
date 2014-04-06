package com.jstanier;

import com.google.common.collect.ImmutableList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class RankedTimesOfWeek {
    
    private final List<Integer> rankedTimesOfWeek;

    public RankedTimesOfWeek() {
        rankedTimesOfWeek = ImmutableList.of(14,20,21,17,9,8,15,23,0);
    }
    
    public List<Integer> getRankedTimesOfWeek() {
        return rankedTimesOfWeek;
    }
}
