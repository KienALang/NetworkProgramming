package com.test.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection conn = null;

    public static Connection connect() {
        if (conn == null) {
            try {
                Class.forName(DBInfo.DB_DRIVER_STRING);
                conn = DriverManager.getConnection(DBInfo.CONNECTION_STRING);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static void close() {
        try {
            if (!conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
