package ClientCommunication;

import java.io.IOException;

import Database.CreateAccountData;
import GameLogic.GameRequest;
import GameLogic.RequestCode;
import ServerCommunication.AccountResponse;

public class CreateAccountControl {

    private Client client;

    public CreateAccountControl(Client client) {
        this.client = client;
    }

    public void createAccount(String username, String password) {

        // Send the account creation request with the new username and password

            //client.openConnection(); // Open connection if not already opened

            CreateAccountData accountData = new CreateAccountData();
            accountData.setUsername(username);
            accountData.setPassword(password);
//            try {
            client.sendRequest(accountData);
//            } catch(IOException exception) {
//            	exception.printStackTrace();
//            }
      
    }
    // ERROR WHEN CREATING ACCOUNT REGARDING A METHOD MISSING CALLED THIS
    public void receiveAccountCreationResponse(AccountResponse response) {
        if (response == AccountResponse.SUCCESS) {
        	System.out.println("Account creation success: " + response.name());
        } else {
            System.out.println("Account creation failed: " + response.name());
        }
    }

}