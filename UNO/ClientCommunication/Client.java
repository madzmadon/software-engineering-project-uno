package ClientCommunication;

import Database.LoginData;
import GameLogic.*;
import ServerCommunication.AccountResponse;
import UserInterface.Driver;
import ocsf.client.AbstractClient;

import java.io.IOException;

public class Client extends AbstractClient {

    private final static int PORT = 12345;
    private final static String ADDRESS = "localhost";
    private Driver driver;
    
    public Client(Driver driver) {
        super(ADDRESS, PORT);
        this.driver = driver;
    }

    @Override
    protected void handleMessageFromServer(Object o) {
        // Determine the type being returned from the server.
        if (o instanceof AccountResponse) {
            // Convert the object to an 'AccountResponse' object.
            AccountResponse response = (AccountResponse) o;

            System.out.println("Account Response: " + response.name());
            
            // Call the processAccountCreationResponse method
            processAccountCreationResponse(response);
        } else if (o instanceof GameResponse) {
        	//SUCCESS OR FAIL
        	//TODO: messages for all panels
            System.out.println("Server responded with a game response");
        } else if (o instanceof GameState) {
        	//TODO: top card (on discardPile) update image, player hand
        	//TODO: UPDATE GAMESTATE OBJECT WITH ITS UPDATED DATA
            System.out.println("Server responded with the game state");
        } else if (o instanceof Player) {
        	//TODO: UPDATE PLAYER OBJECT WITH ITS UPDATED DATA
            System.out.println("Player object received.");
        } else if (o instanceof String) {
        	//TODO: SHOW LOBBY PANEL AND DISABLE STARTGAME BUTTON AND DISPLAY WINNER
        	//TODO:CREATE AND ADD A NEW BUTTON TO LOBBY PANEL CALLED "Return to Menu"
            System.out.println("winner received.");
        }
    }

    @Override
    protected void connectionEstablished() {
        super.connectionEstablished();

        // Declare local variables.
        LoginData data = new LoginData("Admin2", "Password!");

        // Attempt to send the game request information to the server.
        try {
            // Send the request to the server.
            sendToServer(data);
        } catch (IOException exception) {
            // Display the exception information.
            exception.printStackTrace();
        }
    }

    public void sendRequest(Object request) {
        try {
            sendToServer(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object receiveGameState() {
        // Simulating receiving a game state update from the server
        return new Object();
    }
    
    public void processAccountCreationResponse(AccountResponse response) {
        if (response == AccountResponse.SUCCESS) {
        	System.out.println("Account creation success: " + response.name());
        } else {
            System.out.println("Account creation failed: " + response.name());
        }
    }
    
    //ADD A METHOD TO RETURN SUCCESS OR FAILURES FOR REQUESTS TO PANELS

    //    public static void main(String[] args) {
    //
    //        // Declare variables.
    //     	Client client = new Client();
    //
    //        // Attempt to connect to the server.
    //        try
    //        {
    //
    //            // Connect to the server.
    //            client.openConnection();
    //
    //        } catch (IOException exception)
    //        {
    //
    //            // Display the exception information to the user.
    //            exception.printStackTrace();
    //
    //        }
    //
    //    }
}