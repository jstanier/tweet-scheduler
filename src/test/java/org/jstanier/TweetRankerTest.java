package org.jstanier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.jstanier.Tweet;
import com.jstanier.TweetRanker;

@RunWith(MockitoJUnitRunner.class)
public class TweetRankerTest {

    private static String PLACEHOLDER_CONTENT = "@jstanier Hey man!";
    private static final int DEFAULT_FAVOURITES = 0;
    private static final int DEFAULT_RETWEETS = 0;

    @InjectMocks
    private TweetRanker tweetRanker;

    @Test(expected = NullPointerException.class)
    public void tweetRanker_givenANullInput_throwsAnNPE() {
        tweetRanker.rankTweets(null);
    }

    @Test
    public void tweetRanker_givenAnEmptyInputList_returnsAnEmptyList() {
        List<Tweet> tweets = new ArrayList<Tweet>();
        List<Tweet> rankedTweets = tweetRanker.rankTweets(tweets);
        assertTrue("The ranked tweets list should contain no elements", rankedTweets.isEmpty());
    }

    @Test
    public void tweetRanker_givenASingleElementInputList_returnsASingleElementList() {
        List<Tweet> tweets = new ArrayList<Tweet>();
        Tweet tweet = createPlaceholderTweet();
        tweets.add(tweet);
        List<Tweet> rankedTweets = tweetRanker.rankTweets(tweets);
        assertTrue("The ranked tweets list should be the same size as the input (1)",
                rankedTweets.size() == tweets.size());
        assertEquals("The tweet in the ranked tweets list should be the same object as input",
                tweet, rankedTweets.get(0));
    }

    @Test
    public void tweetRanker_givenAListOfTweetsWhereOneIsPopular_thenThePopularTweetIsAtIndexOne() {
        List<Tweet> tweets = new ArrayList<Tweet>();
        Tweet popularTweet = createTweet(PLACEHOLDER_CONTENT, 100, 100);
        tweets.add(popularTweet);
        Tweet unpopularTweet = createPlaceholderTweet();
        tweets.add(unpopularTweet);
        List<Tweet> rankedTweets = tweetRanker.rankTweets(tweets);
        assertTrue("The list of ranked tweets should be the same size as the input (2)",
                rankedTweets.size() == tweets.size());
        assertEquals("The tweet at index 0 should be the unpopular tweet", unpopularTweet,
                rankedTweets.get(0));
        assertEquals("The tweet at index 1 should be the popular tweet", popularTweet,
                rankedTweets.get(1));
    }

    @Test
    public void tweetRanker_givenATweetWithFavouritesAndAnotherWithRetweets_thenTheMostPopularTweetIsTheRetweetedOne() {
        List<Tweet> tweets = new ArrayList<Tweet>();
        Tweet retweetedTweet = createTweet(PLACEHOLDER_CONTENT, 100, 0);
        tweets.add(retweetedTweet);
        Tweet favouritedTweet = createTweet(PLACEHOLDER_CONTENT, 0, 100);
        tweets.add(favouritedTweet);
        List<Tweet> rankedTweets = tweetRanker.rankTweets(tweets);
        assertTrue("The list should be the same size as the input (2)",
                rankedTweets.size() == tweets.size());
        assertEquals("The tweet at index 0 should be the favourited tweet", favouritedTweet,
                rankedTweets.get(0));
        assertEquals("The tweet at index 1 should be the retweeted tweet", retweetedTweet,
                rankedTweets.get(1));
    }

    private Tweet createPlaceholderTweet() {
        return createTweet(PLACEHOLDER_CONTENT, DEFAULT_RETWEETS, DEFAULT_FAVOURITES);
    }

    private Tweet createTweet(String content, Integer retweets, Integer favourites) {
        Tweet tweet = new Tweet();
        tweet.setTime(new DateTime());
        tweet.setContent(content);
        tweet.setRetweets(retweets);
        tweet.setFavourites(favourites);
        return tweet;
    }
}
