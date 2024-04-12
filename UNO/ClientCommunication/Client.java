package ClientCommunication;

import java.io.IOException;
import ocsf.client.AbstractClient;

public class Client extends AbstractClient {

    public Client(String host, int port) {
        super(host, port);
    }

    @Override
    protected void handleMessageFromServer(Object msg) {
        // Process message received from the server
        System.out.println("Received from server: " + msg);
        handleResponse(msg);
    }

    @Override
    protected void connectionEstablished() {
        // Called when a connection to the server has been established
        System.out.println("Connection established with the server.");
    }

    @Override
    protected void connectionClosed() {
        // Called when the connection to the server is closed
        System.out.println("Connection closed.");
    }

    public void sendRequest(Object request) {
        // Send a request to the server
        try {
            sendToServer(request);
        } catch (IOException e) {
            System.err.println("Error sending request to server: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void handleResponse(Object response) {
        // Handle responses from the server here
        // This method now serves as a router to process different kinds of responses
        System.out.println("Handling server response: " + response);
        // You can extend this method to handle different types of responses differently
    }

    // Your simulation methods would now be redundant since you'd be receiving real responses
    // You can replace the following methods with real implementations
    public boolean receiveLoginResponse() {
        // This should be processed dynamically with actual server responses
        return true;
    }

    public boolean receiveAccountCreationResponse() {
        // This should be processed dynamically with actual server responses
        return true; 
    }

    public Object receiveGameState() {
        // This should be updated dynamically from the server messages
        return "Game state updated";
    }

    public Object receiveLobbyStatus() {
        // This should be updated dynamically from the server messages
        return "Lobby status updated";
    }
}
