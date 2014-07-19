package com.jstanier;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import org.springframework.stereotype.Component;

@Component
public class InputReader {

    public Reader getInputReader(String filePath) {
        Reader reader = null;
        try {
            return new FileReader(filePath);
        } catch (FileNotFoundException e) {
            System.err.println("File not found. " + e);
        }
        return reader;
    }
}
