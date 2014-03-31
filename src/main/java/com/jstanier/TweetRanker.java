package com.jstanier;

import static java.util.Collections.sort;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TweetRanker {

    public List<Tweet> rankTweets(List<Tweet> tweets) {
        sort(tweets);
        return tweets;
    }
}
