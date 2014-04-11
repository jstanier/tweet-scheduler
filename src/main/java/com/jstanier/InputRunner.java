package com.jstanier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InputRunner {

    @Autowired
    private TweetScheduler tweetScheduler;

    @Autowired
    private SchedulePresenter schedulePresenter;

    @Autowired
    private InputParser inputParser;

    public void run(String pathToCsvFile) {
        List<TweetToSchedule> tweetsToSchedule = inputParser.parseInput(pathToCsvFile);
        Schedule schedule = tweetScheduler.scheduleTweets(tweetsToSchedule);
        System.out.println(schedulePresenter.present(schedule));
        printWhetherSchedulingWasSuccessful(tweetScheduler);
    }

    private void printWhetherSchedulingWasSuccessful(TweetScheduler tweetScheduler) {
        int numberOfTweetsNotScheduled = tweetScheduler.getNumberOfTweetsNotScheduled();
        if (numberOfTweetsNotScheduled > 0) {
            System.out.println(numberOfTweetsNotScheduled + " tweets could not be scheduled.");
        } else {
            System.out.println("All tweets were scheduled successfully.");
        }
    }
}
