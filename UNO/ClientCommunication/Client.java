import Database.LoginData;
import GameLogic.*;
import ServerCommunication.AccountResponse;
import ocsf.client.AbstractClient;

import java.io.IOException;

/*
 * Disclaimer: This code would typically interface with the UI; however, that is outside the scope of this experiment.
 */

public class Client extends AbstractClient {

    private final static int PORT = 12345;
    private final static String ADDRESS = "localhost";

    public Client()
    {
        super(ADDRESS, PORT);
    }

    @Override
    protected void handleMessageFromServer(Object o) {

        // Determine the type being returned from the server.
        if (o instanceof AccountResponse)
        {

            // Convert the object to an 'AccountResponse' object.
            AccountResponse response = (AccountResponse)o;

            // DEBUG: Display the connection information to the user.
            System.out.println("Account Response: " + response.name());

        } else if (o instanceof GameResponse)
        {

            System.out.println("Server responded with a game response");

        } else if (o instanceof GameState)
        {

            System.out.println("Server responded with the game state");

        } else if (o instanceof Player)
        {

            System.out.println("Player object received.");

        }

    }

    @Override
    protected void connectionEstablished() {
        super.connectionEstablished();

        // Declare local variables.
        LoginData data = new LoginData("Admin2", "Password!");

        GameRequest request1 = new GameRequest(RequestCode.START_GAME);
        GameRequest request2 = new GameRequest(RequestCode.PLAY_CARD);
        GameRequest request3 = new GameRequest(RequestCode.DRAW_CARD);
        GameRequest request4 = new GameRequest(RequestCode.ANNOUNCE_UNO);

        request1.setThreshold(200);

        // Attempt to send the game request information to the server.
        try {

            // Send the request to the server.
            sendToServer(data);
            sendToServer(request1);
            sendToServer(request2);
            sendToServer(request3);
            sendToServer(request4);

        } catch (IOException exception)
        {

            // Display the exception information.
            exception.printStackTrace();

        }

    }
// public class Client extends AbstractClient {

//     public Client(String host, int port) {
//         super(host, port);
//     }

//     @Override
//     protected void handleMessageFromServer(Object msg) {
//         // Process message received from the server
//         System.out.println("Received from server: " + msg);
//         handleResponse(msg);
//     }

//     @Override
//     protected void connectionEstablished() {
//         // Called when a connection to the server has been established
//         System.out.println("Connection established with the server.");
//     }

//     @Override
//     protected void connectionClosed() {
//         // Called when the connection to the server is closed
//         System.out.println("Connection closed.");
//     }

//     public void sendRequest(Object request) {
//         // Send a request to the server
//         try {
//             sendToServer(request);
//         } catch (IOException e) {
//             System.err.println("Error sending request to server: " + e.getMessage());
//             e.printStackTrace();
//         }
//     }

//     private void handleResponse(Object response) {
//         // Handle responses from the server here
//         // This method now serves as a router to process different kinds of responses
//         System.out.println("Handling server response: " + response);
//         // You can extend this method to handle different types of responses differently
//     }

//     // Your simulation methods would now be redundant since you'd be receiving real responses
//     // You can replace the following methods with real implementations
//     public boolean receiveLoginResponse() {
//         // This should be processed dynamically with actual server responses
//         return true;
//     }

//     public boolean receiveAccountCreationResponse() {
//         // This should be processed dynamically with actual server responses
//         return true; 
//     }

//     public Object receiveGameState() {
//         // This should be updated dynamically from the server messages
//         return "Game state updated";
//     }

//     public Object receiveLobbyStatus() {
//         // This should be updated dynamically from the server messages
//         return "Lobby status updated";
//     }
// }

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