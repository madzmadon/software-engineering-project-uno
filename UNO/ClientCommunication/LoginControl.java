package ClientCommunication;

import java.io.IOException;

public class LoginControl {
    private Client client;
    private String username;
    private String password;

    // Constructor to initialize the LoginControl with a Client object, username, and password
    public LoginControl(Client client, String username, String password) {
        this.client = client;
        this.username = username;
        this.password = password;
    }

    // Method to perform login by sending login details to the server
    public boolean login() {
        // Create a LoginData object with the username and password
        LoginData loginData = new LoginData(username, password);
        
        // Attempt to send the login data to the server
        try {
            // Open connection if not already opened
            if (!client.isConnected()) {
                client.openConnection();
            }

            // Send the login data to the server
            client.sendToServer(loginData);

            // Login successful if no exception occurred
            return true;
        } catch (IOException e) {
            // If an exception occurred, login failed
            e.printStackTrace();
            return false;
        }
    }
}
