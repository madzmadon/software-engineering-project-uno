package ClientCommunication;

import java.sql.*;

public class UserDataAccess {

    // Database connection URL, username, and password
	private final String url = "jdbc:mysql://localhost:3306/MyAppData?useSSL=false";

    private final String user = "yourUsername";
    private final String password = "yourPassword";

    private Connection connect() throws SQLException {
        // Establishes and returns a connection to the database
        return DriverManager.getConnection(url, user, password);
    }

    public boolean createUser(String username, String password, String email) {
        // SQL query to insert a new user into the database
        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, email);
            pstmt.executeUpdate();
            return true; // Return true if the user is successfully added
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false; // Return false if an error occurs
        }
    }

    public boolean validateLogin(String username, String password) {
        // SQL query to check if a user exists with the given username and password
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Return true if a matching user is found
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false; // Return false if an error occurs
        }
    }
}
