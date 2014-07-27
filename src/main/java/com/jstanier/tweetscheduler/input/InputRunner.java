package com.jstanier.tweetscheduler.input;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jstanier.tweetscheduler.domain.TweetToSchedule;
import com.jstanier.tweetscheduler.scheduler.Schedule;
import com.jstanier.tweetscheduler.scheduler.TweetScheduler;
import com.jstanier.tweetscheduler.util.SchedulePresenter;

@Component
public class InputRunner {

	@Autowired
	private TweetScheduler tweetScheduler;

	@Autowired
	private SchedulePresenter schedulePresenter;

	@Autowired
	private InputParser inputParser;

	public void run(String pathToCsvFile) {
		try {
			checkPathExists(pathToCsvFile);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
		List<TweetToSchedule> tweetsToSchedule = inputParser
				.parseInput(pathToCsvFile);
		Schedule schedule = tweetScheduler.scheduleTweets(tweetsToSchedule);
		System.out.println(schedulePresenter.present(schedule));
		printWhetherSchedulingWasSuccessful(tweetScheduler);
	}

	private void checkPathExists(String pathToCsvFile) throws FileNotFoundException {
		checkNotNull(pathToCsvFile);
		File file = new File(pathToCsvFile);
		if (!file.exists()) {
			throw new FileNotFoundException("No file found at " + pathToCsvFile);
		}
	}

	private void printWhetherSchedulingWasSuccessful(
			TweetScheduler tweetScheduler) {
		int numberOfTweetsNotScheduled = tweetScheduler
				.getNumberOfTweetsNotScheduled();
		if (numberOfTweetsNotScheduled > 0) {
			System.out.println(numberOfTweetsNotScheduled
					+ " tweets could not be scheduled.");
		} else {
			System.out.println("All tweets were scheduled successfully.");
		}
	}
}
