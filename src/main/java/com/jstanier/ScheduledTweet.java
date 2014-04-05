package com.jstanier;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.joda.time.DateTime;

public class ScheduledTweet implements Comparable<ScheduledTweet>{
    
    private DateTime timeToPost;
    private String content;
    
    public DateTime getTimeToPost() {
        return timeToPost;
    }

    public void setTimeToPost(DateTime timeToPost) {
        this.timeToPost = timeToPost;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int compareTo(ScheduledTweet that) {
        return new CompareToBuilder()
                .append(this.timeToPost, that.getTimeToPost())
                .toComparison();
    }
}
