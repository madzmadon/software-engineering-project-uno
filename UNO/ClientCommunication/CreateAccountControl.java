package ClientCommunication;

public class CreateAccountControl {
    private Client client;
    private String username;
    private String password;
    private String email;

    
    public CreateAccountControl(Client client, String username, String password, String email) {
        this.client = client;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    
    public boolean createAccount() {
        if (!validateInput()) {
            System.out.println("Validation failed. Please check your input and try again.");
            return false;
        }

        
        Object accountCreationRequest = constructAccountRequest();

        
        client.sendRequest(accountCreationRequest);

        
        boolean creationSuccessful = client.receiveAccountCreationResponse();

       
        handleAccountCreationResponse(creationSuccessful);

        return creationSuccessful;
    }

    private boolean validateInput() {
        return !username.isEmpty() && !password.isEmpty() && email.contains("@");
    }

    private Object constructAccountRequest() {
        return new String[]{"CREATE_ACCOUNT", username, password, email};
    }

    private void handleAccountCreationResponse(boolean success) {
        if (success) {
            System.out.println("Account creation successful!");
        } else {
            System.out.println("Account creation failed. Please try again later.");
        }
    }
}
