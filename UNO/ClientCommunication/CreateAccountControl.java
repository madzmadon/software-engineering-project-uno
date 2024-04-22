package ClientCommunication;

import java.io.IOException;

import Database.CreateAccountData;
import ServerCommunication.AccountResponse;

public class CreateAccountControl {
    private Client client;
    private String username;
    private String password;
    private boolean accountCreationResponseReceived;

    public CreateAccountControl(Client client) {
        this.client = client;
        this.accountCreationResponseReceived = false;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean createAccount() {
        if (!validateInput()) {
            System.out.println("Validation failed. Please check your input and try again.");
            return false;
        }

        // Send the account creation request with the new username and password
        try {
            client.openConnection(); // Open connection if not already opened
            CreateAccountData accountData = new CreateAccountData();
            accountData.setUsername(username);
            accountData.setPassword(password);
            client.sendToServer(accountData);
        } catch (IOException e) {
            // Handle connection or I/O errors
            e.printStackTrace();
            return false;
        }

        // Wait for the response in handleMessageFromServer
        waitForResponse();

        // Check if account creation response was received
        return accountCreationResponseReceived;
    }

    private void waitForResponse() {
        // Pause the thread for a short time
        try {
            Thread.sleep(1000); // Wait for 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean validateInput() {
        return !username.isEmpty() && !password.isEmpty();
    }

    // This method is called from handleMessageFromServer
    public void processAccountCreationResponse(AccountResponse response) {
        if (response == AccountResponse.SUCCESS) {
            accountCreationResponseReceived = true;
        } else {
            System.out.println("Account creation failed: " + response.name());
        }
    }
}
