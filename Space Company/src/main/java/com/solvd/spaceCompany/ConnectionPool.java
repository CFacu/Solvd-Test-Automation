package com.solvd.spaceCompany;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPool {
    private final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private final String DRIVER;
    private final String URL;
    private final String USER;
    private final String PASS;
    private final int POOL_SIZE;
    private AtomicInteger activeConnections = new AtomicInteger(0);
    private BlockingQueue<Connection> connectionPool;
    private static ConnectionPool instance;

    private ConnectionPool() {
        Properties properties = new Properties();
        try {
            FileInputStream file = new FileInputStream("src/main/resources/jdbc.properties");
            properties.load(file);
        } catch (IOException e) {
            LOGGER.error("JDBC properties file not found.", e);
        }

        DRIVER = properties.getProperty("driver");
        URL = properties.getProperty("url");
        USER = properties.getProperty("user");
        PASS = properties.getProperty("pass");
        POOL_SIZE = Integer.parseInt(properties.getProperty("poolSize"));

        connectionPool = new ArrayBlockingQueue<>(POOL_SIZE);
        try {
            Class.forName(DRIVER);
        }catch (ClassNotFoundException e){
            LOGGER.error("JDBC implementation not found.", e);
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }

    public Connection getConnection(){
        if (connectionPool.size() == 0 && activeConnections.get() < POOL_SIZE) {
            synchronized (ConnectionPool.class) {
                if (connectionPool.size() == 0 && activeConnections.get() < POOL_SIZE) {
                    try {
                        connectionPool.put(DriverManager.getConnection(URL, USER, PASS));
                    } catch (SQLException | InterruptedException e) {
                        LOGGER.error(e);
                    }
                    activeConnections.incrementAndGet();
                }
            }
        }
        return connectionPool.take();
    }

    public void closeAll() throws SQLException, InterruptedException {
        while (activeConnections.get() > 0) {
            Connection connection = connectionPool.take();
            connection.close();
            activeConnections.decrementAndGet();
        }
    }

    public void releaseConnection (Connection connection) {
        if (connection != null) {
            try {
                connectionPool.put(connection);
            } catch (InterruptedException e) {
                LOGGER.error(e);
            }
        }
    }
}
