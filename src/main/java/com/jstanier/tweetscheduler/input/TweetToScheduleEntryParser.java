package com.jstanier.tweetscheduler.input;

import com.googlecode.jcsv.reader.CSVEntryParser;
import com.jstanier.tweetscheduler.domain.TweetToSchedule;

public class TweetToScheduleEntryParser implements
        CSVEntryParser<TweetToSchedule> {

    public TweetToSchedule parseEntry(String... data) {
        TweetToSchedule tweetToSchedule = new TweetToSchedule();
        tweetToSchedule.setRank(Integer.parseInt(data[0]));
        tweetToSchedule.setContent(data[1]);
        return tweetToSchedule;
    }
}
