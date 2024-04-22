package ClientCommunication;

import java.io.IOException;

import Database.LoginData;

public class LoginControl {
    private Client client;

    // Constructor to initialize the LoginControl with a Client object
    public LoginControl(Client client) {
        this.client = client;
    }

    // Method to perform login by sending login details to the server
    public boolean login(String username, String password) {
        if (!validateInput(username, password)) {
            System.out.println("Validation failed. Please check your input and try again.");
            return false;
        }
        // Create a LoginData object with the username and password
        LoginData loginData = new LoginData(username, password);

        // Attempt to send the login data to the server
        try {
        	//DOUBLE CHECK IF NEEDED
            // Open connection if not already opened
            if (!client.isConnected()) {
                client.openConnection();
            }

            // Send the login data to the server
            client.sendRequest(loginData);

            // Login successful if no exception occurred
            return true;
        } catch (IOException e) {
            // If an exception occurred, login failed
            e.printStackTrace();
            return false;
        }
    }
    
    private boolean validateInput(String username, String password) {
        return !username.isEmpty() && !password.isEmpty();
    }
}