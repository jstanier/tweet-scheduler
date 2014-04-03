package com.jstanier;

import static java.util.Collections.sort;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TweetToScheduleRanker {

    public List<TweetToSchedule> rankTweetsToSchedule(List<TweetToSchedule> tweetsToSchedule) {
        sort(tweetsToSchedule);
        return tweetsToSchedule;
    }
}
