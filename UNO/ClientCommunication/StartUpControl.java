package ClientCommunication;

public class StartUpControl {

<<<<<<< Updated upstream
=======
private Client client;

public StartUpControl(Client client) {
    this.client = client;
}

//function to create a login request
public boolean login(String username, String password) {
    LoginControl loginControl = new LoginControl(client, username, password);
    client.sendRequest(loginControl);
    boolean loginSuccessful = client.receiveLoginResponse();
    handleLoginResponse(loginSuccessful);
    return loginSuccessful;
}

       private void handleLoginResponse(boolean loginSuccessful) {
          if (loginSuccessful) {
          // Handle successful login
           System.out.println("Login successful!");
          // Proceed with further actions for successful login
         } else {
         // Handle failed login
         System.out.println("Login failed. Please check your credentials.");
         // Handle failed login appropriately
                 }
}   
>>>>>>> Stashed changes
}
