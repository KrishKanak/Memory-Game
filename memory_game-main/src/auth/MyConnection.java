package auth;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

    // Method to get the connection with the database
    public static Connection getConnection() throws ClassNotFoundException {
        try {
            String serverName = "localhost";
            String schema = "memory_gameDB";       // Enter the DB name
            String url = "jdbc:mysql://" + serverName + "/" + schema;
            String username = "root";       // Enter the connection username
            String password = "password";       // Enter the connection password
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, username, password);
        }

        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return null;
    }

    public static void closeConnection(Connection conn) throws SQLException{
        conn.close();
    }
}
