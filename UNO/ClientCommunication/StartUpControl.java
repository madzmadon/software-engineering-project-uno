package ClientCommunication;

public class StartUpControl {

private Client client;

public StartUpControl(Client client) {
    this.client = client;
}


public boolean login(String username, String password) {
    LoginControl loginControl = new LoginControl(client, username, password);
    client.sendRequest(loginControl);
    boolean loginSuccessful = client.receiveLoginResponse();
    handleLoginResponse(loginSuccessful);
    return loginSuccessful;
}

       private void handleLoginResponse(boolean loginSuccessful) {
          if (loginSuccessful) {
         
           System.out.println("Login successful!");
          
         } else {
         
         System.out.println("Login failed. Please check your credentials.");
         
                 }
}   


}
