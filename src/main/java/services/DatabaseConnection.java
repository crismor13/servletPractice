package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	// Step 1: Create a static instance of the class (Singleton instance)
    private static DatabaseConnection instance;
    
    // Step 2: Create a private Connection object
    private Connection connection;
    
    // Database connection details
    private String url = "jdbc:mysql://localhost:3306/servlets_db";
    private String username = "root";
    private String password = "0000";

    // Step 3: Private constructor to prevent instantiation from other classes
    private DatabaseConnection() throws SQLException {
        try {
            // Step 4: Register the JDBC driver (optional for newer versions)
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Step 5: Establish the database connection
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new SQLException("Failed to create database connection.");
        }
    }

    // Step 6: Public method to provide access to the instance
    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        System.out.println("Conexión cond Db exitosa!");
        return instance;
    }

    // Step 7: Public method to access the Connection object
    public Connection getConnection() {
        return connection;
    }
    
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
