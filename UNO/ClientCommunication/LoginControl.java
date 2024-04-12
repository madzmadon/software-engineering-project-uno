package ClientCommunication;

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
        Object loginRequest = "LOGIN:" + username + ":" + password;
        client.sendRequest(loginRequest);
        return client.receiveLoginResponse();
    }
}
