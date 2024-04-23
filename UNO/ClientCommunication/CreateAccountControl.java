package ClientCommunication;

import Database.CreateAccountData;

public class CreateAccountControl {

    private Client client;

    public CreateAccountControl(Client client) {
        this.client = client;
    }

    public void createAccount(String username, String password) {
        // Send the account creation request with the new username and password
        CreateAccountData accountData = new CreateAccountData();
        accountData.setUsername(username);
        accountData.setPassword(password);

        // Set isLoginResponse to false before sending the account creation request
        client.isLoginResponse = false;

        try {
            client.sendRequest(accountData);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception, e.g., display an error message
        }
    }

}