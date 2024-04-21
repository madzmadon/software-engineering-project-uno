package ClientCommunication;

import Database.CreateAccountData;

public class CreateAccountControl {
    private Client client;
    private String username;
    private String password;

    public CreateAccountControl(Client client) {
        this.client = client;
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

        CreateAccountData data = new CreateAccountData();
        data.setUsername(username);
        data.setPassword(password);

        client.sendRequest(data);
        boolean creationSuccessful = client.receiveAccountCreationResponse();
        handleAccountCreationResponse(creationSuccessful);
        return creationSuccessful;
    }

    private boolean validateInput() {
        return !username.isEmpty() && !password.isEmpty();
    }

    private void handleAccountCreationResponse(boolean success) {
        if (success) {
            System.out.println("Account creation successful!");
        } else {
            System.out.println("Account creation failed. Please try again later.");
        }
    }
}