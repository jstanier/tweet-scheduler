package org.jstanier.tweetscheduler.ranking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.jstanier.tweetscheduler.domain.TweetToSchedule;
import com.jstanier.tweetscheduler.ranking.TweetToScheduleRanker;

@RunWith(MockitoJUnitRunner.class)
public class TweetToScheduleRankerTest {

    private static final int PLACEHOLDER_RANK = 0;
    private static final String PLACEHOLDER_CONTENT = "@jstanier woah!";
    @InjectMocks
    private TweetToScheduleRanker tweetToScheduleRanker;

    @Test(expected = NullPointerException.class)
    public void tweetToScheduleRanker_givenANullInput_throwsNPE() {
        tweetToScheduleRanker.rankTweetsToSchedule(null);
    }

    @Test
    public void tweetToScheduleRanker_givenAnEmptyInputList_returnsAnEmptyList() {
        ArrayList<TweetToSchedule> tweetsToSchedule = new ArrayList<TweetToSchedule>();
        List<TweetToSchedule> rankedTweets = tweetToScheduleRanker
            .rankTweetsToSchedule(tweetsToSchedule);
        assertTrue("The list should contain no elements.", rankedTweets.isEmpty());
    }

    @Test
    public void tweetToScheduleRanker_givenASingleElementInputList_returnsASingleElementList() {
        List<TweetToSchedule> tweetsToSchedule = new ArrayList<TweetToSchedule>();
        TweetToSchedule tweetToSchedule = createPlaceholderTweetToSchedule();
        tweetsToSchedule.add(tweetToSchedule);
        List<TweetToSchedule> rankedTweets = tweetToScheduleRanker
            .rankTweetsToSchedule(tweetsToSchedule);
        assertTrue("The ranked tweets list should be the same size as the input (1)",
                rankedTweets.size() == tweetsToSchedule.size());
        assertEquals("The tweet in the ranked tweets list should be the same object as input",
                tweetToSchedule, rankedTweets.get(0));
    }

    @Test
    public void tweetToScheduleRanker_givenAListOfTweetsWithRank_thenTheyAreSortedInRankOrder() {
        List<TweetToSchedule> tweetsToSchedule = new ArrayList<TweetToSchedule>();
        TweetToSchedule mostImportantTweetToSchedule = createTweet(PLACEHOLDER_CONTENT, 1);
        tweetsToSchedule.add(mostImportantTweetToSchedule);
        TweetToSchedule leastImportantTweetToSchedule = createTweet(PLACEHOLDER_CONTENT, 0);
        tweetsToSchedule.add(leastImportantTweetToSchedule);
        List<TweetToSchedule> rankedTweets = tweetToScheduleRanker
            .rankTweetsToSchedule(tweetsToSchedule);
        assertTrue("The ranked tweet list should be the same size as the input (2)",
                rankedTweets.size() == rankedTweets.size());
        assertEquals("The tweet at index 0 should be the lowest ranked tweet",
                leastImportantTweetToSchedule, rankedTweets.get(0));
        assertEquals("The tweet at index 1 should be the highest ranked tweet",
                mostImportantTweetToSchedule, rankedTweets.get(1));
    }

    private TweetToSchedule createPlaceholderTweetToSchedule() {
        return createTweet(PLACEHOLDER_CONTENT, PLACEHOLDER_RANK);
    }

    private TweetToSchedule createTweet(String content, int rank) {
        TweetToSchedule tweetToSchedule = new TweetToSchedule();
        tweetToSchedule.setContent(content);
        tweetToSchedule.setRank(rank);
        return tweetToSchedule;
    }
}
