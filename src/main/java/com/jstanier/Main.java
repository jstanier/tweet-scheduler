package com.jstanier;

import java.io.Closeable;
import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main implements Closeable {

    private AnnotationConfigApplicationContext applicationContext;

    public Main() {
        applicationContext = new AnnotationConfigApplicationContext(Config.class);
        applicationContext.registerShutdownHook();
    }

    public static void main(String[] args) {
        Main main = new Main();
    }

    public void close() throws IOException {
        applicationContext.close();
    }
}
