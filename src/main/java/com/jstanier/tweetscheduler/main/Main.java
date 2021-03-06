package com.jstanier.tweetscheduler.main;

import java.io.Closeable;
import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.jstanier.tweetscheduler.input.InputRunner;

public class Main implements Closeable {

    private AnnotationConfigApplicationContext applicationContext;

    public Main(String pathToCsvFile) {
        applicationContext = new AnnotationConfigApplicationContext(Config.class);
        applicationContext.registerShutdownHook();
        runScheduler(pathToCsvFile);
    }

    private void runScheduler(String pathToCsvFile) {
        InputRunner inputRunner = applicationContext.getBean(InputRunner.class);
        inputRunner.run(pathToCsvFile);
    }

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        if (args.length != 1) {
            exitWithError("Expected a path to a CSV file as argument.");
        }
        new Main(args[0]);
    }

    private static void exitWithError(String error) {
        throw new IllegalArgumentException(error);
    }

    public void close() throws IOException {
        applicationContext.close();
    }
}
