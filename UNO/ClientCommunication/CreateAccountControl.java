package ClientCommunication;

import Database.CreateAccountData;

public class CreateAccountControl {
    private String username;
    private String password;
    private Client client;

    public CreateAccountControl(String username, String password, Client client) {
        this.username = username;
        this.password = password;
        this.client = client;
    }

    public void sendCreateAccountRequest() {
        client.sendCreateAccountRequest(username, password);
    }
}
