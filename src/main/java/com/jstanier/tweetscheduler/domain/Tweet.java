package com.jstanier.tweetscheduler.domain;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.joda.time.DateTime;

public class Tweet implements Comparable<Tweet> {

    private DateTime time;
    private String content;
    private Integer retweets;
    private Integer favourites;

    public DateTime getTime() {
        return time;
    }

    public void setTime(DateTime time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRetweets() {
        return retweets;
    }

    public void setRetweets(Integer retweets) {
        this.retweets = retweets;
    }

    public Integer getFavourites() {
        return favourites;
    }

    public void setFavourites(Integer favourites) {
        this.favourites = favourites;
    }

    public int compareTo(Tweet that) {
        return new CompareToBuilder()
            .append(this.retweets, (Integer) (that.getRetweets() * 2))
            .append(this.favourites, that.getFavourites())
            .toComparison();
    }
}
