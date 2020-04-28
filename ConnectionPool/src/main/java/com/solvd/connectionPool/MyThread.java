package com.solvd.connectionPool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyThread implements Runnable{

    private final Logger LOGGER = LogManager.getLogger(MyThread.class);
    private String name;

    public MyThread() {}

    public MyThread(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void run(){
        try {
            LOGGER.info("Started " + this.name);
            Thread.sleep(2000);
            LOGGER.info("Finished " + this.name);
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
    }
}
