package com.jstanier.tweetscheduler.ranking;

import static java.util.Collections.sort;

import java.util.List;

import org.springframework.stereotype.Component;

import com.jstanier.tweetscheduler.domain.Tweet;

@Component
public class TweetRanker {

    public List<Tweet> rankTweets(List<Tweet> tweets) {
        sort(tweets);
        return tweets;
    }
}
