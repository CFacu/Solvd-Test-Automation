package com.solvd.connectionPool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyThread implements Runnable{

    private final Logger LOGGER = LogManager.getLogger(MyThread.class);
    private String threadName;
    private ConnectionPool pool;

    public MyThread() {}

    public MyThread(String threadName, ConnectionPool pool) {
        this.threadName = threadName;
        this.pool = pool;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String name) {
        this.threadName = name;
    }

    @Override
    public String toString() {
        return threadName;
    }

    @Override
    public synchronized void run(){
        String connection = null;
        try {
            connection = pool.getConnection();
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
        LOGGER.info("Executing " + threadName);
        try {
            Thread.sleep(3000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.releaseConnection(connection);

    }
}
