package com.solvd.connectionPool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ThreadPoolExecutor {
    private final static Logger LOGGER = LogManager.getLogger(ThreadPoolExecutor.class);
    public static void main(String[] args) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        List<MyThread> threads = new ArrayList<>(15);

        for (int i = 0; i < connectionPool.getPOOL_SIZE(); i++) {
            threads.add(new MyThread("Thread " + i));
        }
        connectionPool.getConnection();
        threads.forEach(MyThread::run);
        threads.forEach(connectionPool::releaseConnection);
    }
}
