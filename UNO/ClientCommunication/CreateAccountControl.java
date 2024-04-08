package ClientCommunication;

public class CreateAccountControl {

<<<<<<< Updated upstream
=======
    // Constructor
    public CreateAccountControl(Client client, String username, String password, String email) {
        this.client = client;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Method to send account creation request to the server
    public boolean createAccount() {
        if (!validateInput()) {
            System.out.println("Validation failed. Please check your input and try again.");
            return false;
        }

        // Create a request object
        Object accountCreationRequest = constructAccountRequest();

        // Send the request to the server
        client.sendRequest(accountCreationRequest);

        // Assume this method is meant to receive the response specifically for account creation
        boolean creationSuccessful = client.receiveAccountCreationResponse();

        // Handle the response
        handleAccountCreationResponse(creationSuccessful);

        return creationSuccessful;
    }

    private boolean validateInput() {
        return !username.isEmpty() && !password.isEmpty() && email.contains("@");
    }

    private Object constructAccountRequest() {
        return new String[]{"CREATE_ACCOUNT", username, password, email};
    }

    private void handleAccountCreationResponse(boolean success) {
        if (success) {
            System.out.println("Account creation successful!");
        } else {
            System.out.println("Account creation failed. Please try again later.");
        }
    }
>>>>>>> Stashed changes
}
