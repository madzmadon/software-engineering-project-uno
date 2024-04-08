package ClientCommunication;

public class LoginControl {

<<<<<<< Updated upstream
=======
    // Constructor
    public LoginControl(Client client, String username, String password) {
        this.client = client;
        this.username = username;
        this.password = password;
    }

    public boolean login() {
        Object loginRequest = "LOGIN:" + username + ":" + password;
        client.sendRequest(loginRequest);
        return client.receiveLoginResponse();
    }

>>>>>>> Stashed changes
}
