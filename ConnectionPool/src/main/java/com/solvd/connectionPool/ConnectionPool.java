package com.solvd.connectionPool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPool {
    private final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private LinkedBlockingQueue<MyThread> poolQueue;
    private final int POOL_SIZE = 10;
    private AtomicInteger activeConnections = new AtomicInteger(0);
    private static ConnectionPool INSTANCE;

    private ConnectionPool() {
        this.poolQueue = new LinkedBlockingQueue<>(POOL_SIZE);
    }

    public static ConnectionPool getInstance() {
        if (INSTANCE == null){
            INSTANCE = new ConnectionPool();
        }
        return INSTANCE;
    }

    public AtomicInteger getActiveConnections() {
        return activeConnections;
    }

    public int getPOOL_SIZE() {
        return POOL_SIZE;
    }

    public MyThread getConnection() {
        if (getActiveConnections().get() < getPOOL_SIZE()) {
            poolQueue.add(new MyThread("Mock Connection"));
            getActiveConnections().incrementAndGet();
        }
        MyThread conn = null;

        try {
            conn = poolQueue.take();
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
        return conn;
    }

    public void releaseConnection(MyThread connection) {
        try {
            poolQueue.put(connection);
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
    }

    public void closeAllConnections() {
        while (activeConnections.get() > 0) {
            MyThread connection;
            try {
                connection = poolQueue.take();
            } catch (InterruptedException e) {
                LOGGER.error(e);
            }
            activeConnections.decrementAndGet();
        }
    }

    @Override
    public String toString() {
        return "ConnectionPool{" +
                "connections=" + poolQueue.toString() + "}";
    }
}
