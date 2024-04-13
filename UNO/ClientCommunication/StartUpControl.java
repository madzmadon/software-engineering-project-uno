package ClientCommunication;

public class StartUpControl {
    private Client client;

    // Constructor to initialize the StartUpControl with a Client object
    public StartUpControl(Client client) {
        this.client = client;
    }

    // Method to handle the login process
    public boolean login(String username, String password) {
        LoginControl loginControl = new LoginControl(client, username, password);
        client.sendRequest(loginControl);
        boolean loginSuccessful = client.receiveLoginResponse();
        handleLoginResponse(loginSuccessful);
        return loginSuccessful;
    }

    // Private method to handle the response from the login process
    private void handleLoginResponse(boolean loginSuccessful) {
        if (loginSuccessful) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed. Please check your credentials.");
        }
    }
}
