package com.example.swifterrand;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DATABASE_PATH = "jdbc:sqlite/path/to/swift_errands.db";
    private static final int MAX_CONNECTIONS = 10;

    private SQLiteDatabase database;
    private List<Connection> connectionPool;

    public DatabaseManager() {
        initializeConnectionPool();
    }

    private void initializeConnectionPool() {
        connectionPool = new ArrayList<>();
        // Create a connection pool with a maximum number of connections
        for (int i = 0; i < MAX_CONNECTIONS; i++) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_PATH);
                // Add the connection to the connection pool
                connectionPool.add(connection);
            } catch (SQLException | java.sql.SQLException e) {
                // Handle connection initialization error
                e.printStackTrace();
            }
        }
    }

    public Connection getConnectionFromPool() throws SQLException {
        if (connectionPool.isEmpty()) {
            // Handle when no connections are available in the pool
            throw new SQLException("No available connections in the pool.");
        }

        // Get a connection from the connection pool
        Connection connection = connectionPool.remove(0);
        return connection;
    }

    public void releaseConnection(Connection connection) {
        // Close the connection and return it to the connection pool
        try {
            connection.close();
            connectionPool.add(connection);
        } catch (SQLException | java.sql.SQLException e) {
            // Handle connection release error
            e.printStackTrace();
        }
    }
}

