package ClientCommunication;

public class LoginControl {
    private String username;
    private String password;
    private Client client;

    public LoginControl(String username, String password, Client client) {
        this.username = username;
        this.password = password;
        this.client = client;
    }

    public void sendLoginRequest() {
        client.sendLoginRequest(username, password);
    }
}
