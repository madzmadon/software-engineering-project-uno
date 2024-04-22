package ClientCommunication;

public class StartUpControl {
    private Client client;

    // Constructor to initialize the StartUpControl with a Client object
    public StartUpControl(Client client) {
        this.client = client;
    }

    // Method to handle the login process
    public void login(String username, String password) {
        LoginControl loginControl = new LoginControl(client, username, password);
        boolean loginSuccessful = loginControl.login();
        if (loginSuccessful) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed.");
        }
    }
}