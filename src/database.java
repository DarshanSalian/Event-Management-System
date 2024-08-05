
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class database {
    public static void createDatabase() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/"; // JDBC URL for the MySQL server
        String username = "root"; // Your MySQL username
        String password = "12@#HardhiK"; // Your MySQL password
        String databaseName = "Login"; // Name of the new database to create
    
        Connection connection = null;
        Statement statement = null;
    
        try {
            // Connect to the MySQL server
            connection = DriverManager.getConnection(jdbcUrl, username, password);
    
            // Create a statement
            statement = connection.createStatement();
    
            // SQL command to create a new database if it doesn't exist
            String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS " + databaseName;
    
            // Execute the SQL command to create the database
            statement.executeUpdate(createDatabaseSQL);
            System.out.println("Database created successfully: " + databaseName);
    
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
}