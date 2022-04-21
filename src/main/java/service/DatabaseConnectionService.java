package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum DatabaseConnectionService {
    DB_INSTANCE;
    private final String DRIVER_URL = "com.mysql.cj.jdbc.Driver";
    private final String DATABASE_URL =
            "jdbc:mysql://localhost:3306/airport_management_system";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";

    public Connection createConnection() {

        Connection con = null;

        try {
            return con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}