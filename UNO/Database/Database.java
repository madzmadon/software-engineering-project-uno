package Database;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Database {

    private Connection connection;
    private String key;

    public Database(String property_file_name)
    {

        // Declare local variables.
        Properties properties = new Properties();
        FileReader reader = null;
        String url, username, password;

        // Attempt to read the properties file.
        try
        {

            // Read the contents of the file into the file reader.
            reader = new FileReader(property_file_name);

            // Load the data from the file into the 'properties' object.
            properties.load(reader);

            // Close the connection to the file.
            reader.close();

        }
        catch (IOException exception)
        {

            // Display the exception information to the user.
            exception.printStackTrace();

            // Terminate the program with an error code.
            System.exit(-1);

        }

        // Obtain the url, username, password, and key from the properties file.
        url = properties.getProperty("url");
        username = properties.getProperty("user");
        password = properties.getProperty("password");
        key = properties.getProperty("key");

        // Attempt to connect to the database.
        try
        {

            // Connect to the database.
            connection = DriverManager.getConnection(url, username, password);

        }
        catch (SQLException exception)
        {

            // Display the exception information to the user.
            exception.printStackTrace();

            // Terminate the program with an error code.
            System.exit(-1);

        }

    }
    
    public Connection getConnection()
    {
    	return connection;
    }

    public boolean verifyAccount(LoginData data)
    {

        // Declare local variables.
        Statement statement = null;
        ResultSet results = null;
        boolean flag = false;

        // Attempt to retrieve users and passwords from the database.
        try
        {

            // Create the statement from the connection.
            statement = connection.createStatement();

            // Run the query.
            results = statement.executeQuery("SELECT COUNT(*) FROM uno_user WHERE username='" + data.getUsername() + "' AND password='" + data.getPassword() + "';");

            // Seek to the first record.
            results.next();

            // Determine if the login data matched any account.
            flag = (results.getInt(1) > 0);

        }
        catch (SQLException exception)
        {

            // Display the exception information to the user.
            exception.printStackTrace();

            return false;

        }

        return flag;

    }

    public boolean createAccount(CreateAccountData data)
    {

        // Declare local variables.
        Statement statement = null;

        // Ensure that the login data is of valid length.
        if (data.getUsername().isEmpty() || data.getPassword().isEmpty())
        {
            return false;
        }

        // Ensure that there is not an already existent account with the same login data.
        if (this.verifyAccount(new LoginData(data.getUsername(), data.getPassword())))
        {
            return false;
        }

        // Attempt to create the account with the data.
        try
        {

            // Create the statement from the connection.
            statement = connection.createStatement();

            // Execute the insert statement.
            statement.execute("INSERT INTO uno_user VALUES('" + data.getUsername() + "', '" + data.getPassword() + "');");

        }
        catch (SQLException exception)
        {

            // Display the exception to the user.
            exception.printStackTrace();

            return false;

        }

        return true;

    }

}
