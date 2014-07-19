package com.jstanier;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.googlecode.jcsv.CSVStrategy;
import com.googlecode.jcsv.reader.CSVReader;
import com.googlecode.jcsv.reader.internal.CSVReaderBuilder;

@Component
public class InputParser {

    @Autowired
    private InputReader inputReader;

    public List<TweetToSchedule> parseInput(String pathToCsvFile) {
        return parseCsvFile(pathToCsvFile);
    }

    private List<TweetToSchedule> parseCsvFile(String pathToCsvFile) {
        List<TweetToSchedule> csvData = null;
        Reader reader = inputReader.getInputReader(pathToCsvFile);
        CSVReader<TweetToSchedule> csvParser = createCSVReader(reader);
        try {
            csvData = csvParser.readAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return csvData;
    }

    private CSVReader<TweetToSchedule> createCSVReader(Reader reader) {
        return (CSVReader<TweetToSchedule>) new CSVReaderBuilder(reader)
                .strategy(CSVStrategy.UK_DEFAULT)
                .entryParser(new TweetToScheduleEntryParser()).build();
    }
}
