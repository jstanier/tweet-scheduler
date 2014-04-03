package com.jstanier;

import org.apache.commons.lang3.builder.CompareToBuilder;

public class TweetToSchedule implements Comparable<TweetToSchedule> {

    private Integer rank;
    private String content;

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int compareTo(TweetToSchedule that) {
        return new CompareToBuilder().append(this.rank, that.getRank()).toComparison();
    }
}
