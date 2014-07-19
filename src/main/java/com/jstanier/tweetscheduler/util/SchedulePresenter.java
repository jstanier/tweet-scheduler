package com.jstanier.tweetscheduler.util;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.joda.time.format.DateTimeFormat.forPattern;

import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

import com.jstanier.tweetscheduler.domain.ScheduledTweet;
import com.jstanier.tweetscheduler.scheduler.Schedule;

@Component
public class SchedulePresenter {

    private static DateTimeFormatter DATE_TIME_FORMATTER = forPattern("E, dd MMMM yyyy HH:mm");

    public String present(Schedule schedule) {
        checkNotNull(schedule);
        String presentation = "";
        for (ScheduledTweet scheduledTweet : schedule.getSchedule()) {
            String formattedDate = DATE_TIME_FORMATTER.print(scheduledTweet.getTimeToPost());
            presentation += formattedDate + " - " + scheduledTweet.getContent() + "\n";
        }
        return presentation;
    }
}
