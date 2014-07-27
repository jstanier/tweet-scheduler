package org.jstanier.tweetscheduler.input;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.jstanier.tweetscheduler.input.InputParser;
import com.jstanier.tweetscheduler.input.InputRunner;
import com.jstanier.tweetscheduler.scheduler.TweetScheduler;
import com.jstanier.tweetscheduler.util.SchedulePresenter;

@RunWith(MockitoJUnitRunner.class)
public class InputRunnerTest {

    @Mock
    private TweetScheduler tweetScheduler;

    @Mock
    private SchedulePresenter schedulePresenter;

    @Mock
    private InputParser inputParser;
    
    @InjectMocks
    private InputRunner inputRunner;
    
    @Test(expected = NullPointerException.class)
    public void run_whenGivenANullFilePath_thenANullPointerExceptionIsThrown() {
    	inputRunner.run(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void run_whenGivenAnInvalidFilePath_then() {
    	inputRunner.run("not a valid path");
    }
}
