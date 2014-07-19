package com.jstanier.tweetscheduler.ranking;

import static java.util.Collections.sort;

import java.util.List;

import org.springframework.stereotype.Component;

import com.jstanier.tweetscheduler.domain.TweetToSchedule;

@Component
public class TweetToScheduleRanker {

    public List<TweetToSchedule> rankTweetsToSchedule(List<TweetToSchedule> tweetsToSchedule) {
        sort(tweetsToSchedule);
        return tweetsToSchedule;
    }
}
