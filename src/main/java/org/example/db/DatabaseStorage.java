package org.example.db;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;


public class DatabaseStorage {
    private static final String DB_URL = "jdbc:h2:~/test";
    private static final String DB_PASSWORD = "";
    private static final String DB_USER_NAME="sa";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (Objects.isNull(connection)) {
            connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
        }

        if (!connection.isValid(1000)) {
            connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
        }

        return connection;
    }
}
