package com.jstanier;

import static com.google.common.collect.Lists.newArrayList;
import static com.googlecode.jcsv.reader.internal.CSVReaderBuilder.newDefaultReader;
import static java.lang.Integer.parseInt;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.springframework.stereotype.Component;

import com.googlecode.jcsv.reader.CSVReader;

@Component
public class InputParser {

    public List<TweetToSchedule> parseInput(String pathToCsvFile) {
        List<String[]> csvData = parseCsvFile(pathToCsvFile);
        return parseTweetsToScheduleFromCSV(csvData);
    }

    private List<String[]> parseCsvFile(String pathToCsvFile) {
        List<String[]> csvData = null;
        try {
            Reader reader = new FileReader(pathToCsvFile);
            CSVReader<String[]> csvParser = newDefaultReader(reader);
            csvData = csvParser.readAll();
        } catch (FileNotFoundException e) {
            exitWithError("File not found at " + pathToCsvFile);
        } catch (IOException e) {
            exitWithError("IO exception when reading" + pathToCsvFile);
        }
        return csvData;
    }

    private List<TweetToSchedule> parseTweetsToScheduleFromCSV(List<String[]> csvData) {
        List<TweetToSchedule> tweetsToSchedule = newArrayList();
        for (String[] csvTweet : csvData) {
            TweetToSchedule tweetToSchedule = new TweetToSchedule();
            tweetToSchedule.setRank(parseInt(csvTweet[0]));
            tweetToSchedule.setContent(csvTweet[1]);
            tweetsToSchedule.add(tweetToSchedule);
        }
        return tweetsToSchedule;
    }

    private void exitWithError(String error) {
        System.err.println(error);
        System.exit(1);
    }
}
