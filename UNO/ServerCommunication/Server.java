package ServerCommunication;

import Database.*;
import GameLogic.*;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Server extends AbstractServer {

    private final static int PORT = 12345;
    private final static String property_file_name = "./Database/db.properties";

    private int threshold;

    private Database database;
    private HashMap<ConnectionToClient, LoginData> users;
    private ServerGameController game;

    public Server(int threshold)
    {

        super(PORT);
        this.setTimeout(500);
        this.threshold = threshold;

        // Initialize the data fields.
        this.database = new Database(property_file_name);
        this.users = new HashMap<>();
        this.game = new ServerGameController(this);

    }

    @Override
    protected void handleMessageFromClient(Object o, ConnectionToClient connectionToClient) {

        // Determine the object being received by the client.
        if (o instanceof LoginData)
        {

            // Declare the instance variables.
            AccountResponse response = AccountResponse.SUCCESS;

            // Convert the object to a 'LoginData' object.
            LoginData data = (LoginData)o;

            // Determine if the client's login credentials are correct.
            if ((data.getUsername().length() >= 32) || (data.getPassword().length() >= 32))
            {

                // Set the value of the variable 'response' to invalid credentials.
                response = AccountResponse.INVALID_CREDENTIALS;

            }else if (database.verifyAccount(data))
            {

            	// Determine if the user is already logged in.
            	if (!this.alreadyLoggedIn(data))
            	{
            	
            		// Add the client to the logged in users.
            		this.users.put(connectionToClient, data);

                	// Set the variable 'response' to success.
                	response = AccountResponse.SUCCESS;

            	} else 
            	{
            		
            		// Set the variable 'response' to fail.
            		response = AccountResponse.FAIL;
            		
            	}
            	
            } else
            {

                // Set the variable 'response' to account not existant.
                response = AccountResponse.ACCOUNT_NOT_EXISTENT;

            }

            // Attempt to send the response to the client.
            try {

                // Send the message to the client.
                connectionToClient.sendToClient(response);

            } catch (IOException exception)
            {

                // Display the error message to the user.
                exception.printStackTrace();

            }

        } else if (o instanceof CreateAccountData)
        {

            // Declare instance variables.
            AccountResponse response = AccountResponse.SUCCESS;

            // Convert the object to a 'CreateAccountData' object.
            CreateAccountData data = (CreateAccountData)o;

            // Attempt to create the client's account.
            if ((data.getUsername().length() >= 32) || (data.getPassword().length() >= 32))
            {

                // Set the variable 'response' to invalid credentials.
                response = AccountResponse.INVALID_CREDENTIALS;

            } else if (database.createAccount(data))
            {

                // Add the user to the 'users' map.
                this.users.put(connectionToClient, new LoginData(data.getUsername(), data.getPassword()));

                // Set the variable 'response' to success.
                response = AccountResponse.SUCCESS;

            } else
            {

                // Set the variable 'response' already existent.
                response = AccountResponse.ALREADY_EXISTS;

            }

            // Attempt to send the response to the user.
            try {

                // Send the message to the client.
                connectionToClient.sendToClient(response);

            } catch (IOException exception)
            {

                // Display the error message to the user.
                exception.printStackTrace();

            }

        } else if (o instanceof GameRequest)
        {

            // Convert the object to a 'GameRequest' object.
            GameRequest request = (GameRequest)o;

            this.game.handleGameRequest(request, connectionToClient);

        }

    }

    // Returns the user associated list.
    public Map<ConnectionToClient, LoginData> getUsers()
    {

        // Declare local variables.
        HashMap<ConnectionToClient, LoginData> sanitized_users = new HashMap<>();

        // Remove all users that are dead.
        for (ConnectionToClient connection : this.users.keySet())
        {

            // Determine if the current connection is alive.
            if (connection.isAlive())
            {

                // Add the current connection to 'users'.
                sanitized_users.put(connection, this.users.get(connection));

            }

        }

        // Reset the 'users' map in the server.
        this.users = sanitized_users;

        return sanitized_users;
        
    }
    
    public boolean alreadyLoggedIn(LoginData data)
    {
    	
    	// Declare local variables.
    	boolean flag = false;
    	
    	// Iterate over the logged in users.
    	for (LoginData user : this.getUsers().values())
    	{
    		
    		// Determine if the current user is the same as 'data'.
    		if (user.getUsername().equals(data.getUsername()))
    		{
    			
    			// Set the variable 'flag' to true.
    			flag = true;
    			
    		}
    		
    	}
    	
    	return flag;
    	
    }
    
    public void logout(LoginData data)
    {
    	
    	// Ensure that the user is already logged in.
    	if (alreadyLoggedIn(data))
    	{
    		
    		// remove the user from the 'users' list.
    		this.users.remove(this.users.get(data));
    		
    	}
    	
    }

    // Returns the threshold of points to win.
    public int getThreshold()
    {
        return threshold;
    }

    public static void main(String[] args) {

        // Declare variables.
        Server server = null;
        int threshold = 0;

        // Ensure that the command line arguments include the threshold.
        if (args.length < 1)
        {
        	
            // Display an error message indicating that the server requires an integer for the threshold.
            System.out.println("Missing required argument: Threshold (int) - Specifies the minimum number of points to win a game.");

            return;

        }
        
        // Obtain the threshold from the user.
        threshold = Integer.parseInt(args[0]);
        
        // Ensure that the threshold is at minimum 200 and maximum of 600.
        if (threshold < 200 || threshold > 600)
        {
        	
        	// Display a message indicating that the threshold is out of range.
        	System.out.println("Out of range - The threshold amount must be between 200 and 600 inclusive.");
        	
        	return;
        	
        }
        
        // Declare variables.
        server = new Server(threshold);

        // Attempt to start the server.
        try
        {

            // Start the server.
            server.listen();

        } catch (IOException exception)
        {

            // Display the exception information to the user.
            exception.printStackTrace();

        }

    }

}