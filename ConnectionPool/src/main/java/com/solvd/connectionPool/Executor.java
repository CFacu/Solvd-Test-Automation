package com.solvd.connectionPool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Executor {
    private final static Logger LOGGER = LogManager.getLogger(Executor.class);
    public static void main(String[] args) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        ExecutorService service = Executors.newFixedThreadPool(15);

        Stream.iterate(0, i -> i + 1).limit(15).forEach(n -> service.execute(new MyThread("Thread " + n, connectionPool)));

        service.shutdown();

        try {
            service.awaitTermination(3000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
    }
}
