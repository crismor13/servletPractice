package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AuthService {

    private DatabaseConnection dbConnection;  // Assume this handles database connections

    public AuthService() throws SQLException {
        this.dbConnection = DatabaseConnection.getInstance();
    }

    public boolean authenticate(String username, String password) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        try {
            connection = dbConnection.getConnection();  // Retrieve database connection
            String query = "SELECT username, password FROM users WHERE username = ?";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            resultSet = stmt.executeQuery();

            // Check if a user was found
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");

                // In production, you should compare hashed passwords
                if (storedPassword.equals(password)) {
                    return true;  // User authenticated successfully
                }
            }

        } finally {
            if (resultSet != null) resultSet.close();
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
        return false;  // Authentication failed
    }

}

