package Database;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Database {

    private Connection connection;

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
        PreparedStatement statement = null;
        ResultSet results = null;
        boolean flag = false;

        // Verify the username and password.
        if (!this.verifyCredentials(data.getUsername(), data.getPassword()))
        {
        	
        	return false;
        	
        }
        
        // Attempt to retrieve users and passwords from the database.
        try
        {

            // Create the statement from the connection.
            statement = connection.prepareStatement("SELECT COUNT(*) FROM uno_user WHERE username=? AND password=?");

            // Add the username and password to the query.
            statement.setString(1, data.getUsername());
            statement.setString(2, data.getPassword());
            
            // Execute the query.
            results = statement.executeQuery();
            
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

        } finally
        {
        	
        	// Attempt to close the connections.
        	try
        	{
        		
        		// Ensure that the results and statement objects exist.
        		if (results != null)
        		{
        			
        			// Close the results object.
        			results.close();
        			
        		}
        		if (statement != null)
        		{
        			
        			// Close the statement object.
        			statement.close();
        			
        		}
        		
        	} catch (SQLException exception)
        	{
        		
        		// Display the error message to the user.
        		exception.printStackTrace();
        		
        	}
        	
        }

        return flag;

    }

    public boolean createAccount(CreateAccountData data)
    {

        // Declare local variables.
        PreparedStatement statement = null;

        // Ensure that there is not an already existent account with the same login data.
        if (this.verifyAccount(new LoginData(data.getUsername(), data.getPassword())))
        {
        	return false;
        }
        
        // Verify the username and password.
        if (!this.verifyCredentials(data.getUsername(), data.getPassword()))
        {
        	return false;
        }
        
        // Attempt to create the account with the data.
        try
        {

            // Create the statement from the connection.
            statement = connection.prepareStatement("INSERT INTO uno_user VALUES(?, ?);");

            // Add the username and password to the statement.
            statement.setString(1, data.getUsername());
            statement.setString(2, data.getPassword());
            
            // Execute the insert statement.
            statement.execute(); 
            
        }
        catch (SQLException exception)
        {

            // Display the exception to the user.
            exception.printStackTrace();

            return false;

        }

        return true;

    }

    private boolean verifyCredentials(String username, String password)
    {
    	return ((!username.isEmpty() && !password.isEmpty()) && (username.length() <= 32 && password.length() <= 32));
    }
    
}
