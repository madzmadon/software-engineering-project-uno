package ServerCommunication;

import Database.CreateAccountData;
import Database.Database;
import Database.LoginData;
import GameLogic.ServerGameController;
import GameLogic.GameRequest;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Server extends AbstractServer {

    private final static int PORT = 12345;
    private final static String property_file_name = "./Database/db.properties";

    private Database database;
    private HashMap<ConnectionToClient, LoginData> users;
    private ServerGameController game;

    public Server()
    {

        super(PORT);
        this.setTimeout(500);

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
            if ((data.getUsername().length() >= 32) || (data.getPassword().length() >= 16))
            {

                // Set the value of the variable 'response' to invalid credentials.
                response = AccountResponse.INVALID_CREDENTIALS;

            }else if (database.verifyAccount(data))
            {

                // Add the client to the logged in users.
                this.users.put(connectionToClient, data);

                // Set the variable 'response' to success.
                response = AccountResponse.SUCCESS;

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

        } else if ((o instanceof GameRequest) && (this.users.containsKey(connectionToClient)))
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

    public static void main(String[] args) {

        // Declare variables.
        Server server = new Server();

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
