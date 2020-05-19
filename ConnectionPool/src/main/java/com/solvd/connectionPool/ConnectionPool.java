package com.solvd.connectionPool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPool {
    private final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private LinkedBlockingQueue<String> poolQueue;
    private final Integer POOL_SIZE = 10;
    private AtomicInteger activeConnections = new AtomicInteger(0);
    private static ConnectionPool INSTANCE;

    private ConnectionPool() {
        this.poolQueue = new LinkedBlockingQueue<>(POOL_SIZE);
    }

    public static ConnectionPool getInstance() {
        if (INSTANCE == null){
            synchronized (ConnectionPool.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ConnectionPool();
                }
            }
        }
        return INSTANCE;
    }

    public AtomicInteger getActiveConnections() {
        return activeConnections;
    }

    public int getPOOL_SIZE() {
        return POOL_SIZE;
    }

    public String getConnection() throws InterruptedException {
        if (poolQueue.size() == 0 && activeConnections.get() < POOL_SIZE) {
            synchronized (ConnectionPool.class) {
                if (poolQueue.size() == 0 && activeConnections.get() < POOL_SIZE) {
                    INSTANCE.init();
                    getActiveConnections().incrementAndGet();
                }
            }
        }
        return poolQueue.take();
    }

    public void releaseConnection(String connection) {
        if (connection != null) {
            try {
                poolQueue.put(connection);
            } catch (InterruptedException e) {
                LOGGER.error(e);
            }
        }
    }

    public void closeAllConnections() {
        while (activeConnections.get() > 0) {
            String connection;
            try {
                connection = poolQueue.take();
            } catch (InterruptedException e) {
                LOGGER.error(e);
            }
            activeConnections.decrementAndGet();
        }
    }

    public void init(){
        try {
            poolQueue.put(" Connection " + getActiveConnections());
        } catch (InterruptedException e) {
            LOGGER.info(e);
        }

    }
}
