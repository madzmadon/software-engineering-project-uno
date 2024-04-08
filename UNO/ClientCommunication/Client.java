package ClientCommunication;

import java.io.IOException;
import java.io.ObjectInputStream;

public class Client {

	  private ObjectInputStream inputStream;

	public void sendRequest(Object request)
	{
	
		System.out.println("Sending request to the server:" +request); 
		
	
		Object response="Response from the server";
		
	
	handleResponse(response);
		
	}
	private void handleResponse(Object response) {
       
        System.out.println("Received response from the server: " + response);
    }
	
    public boolean receiveLoginResponse() {
       
        return true;
    }
    public boolean receiveAccountCreationResponse() {
       
        return true; 
    }
    
    public Object receiveGameState() {
        
        Object gameState = "Current game state data";
        return gameState;
    }
    
    public Object receiveLobbyStatus() {
       
        Object lobbyStatus = "Lobby status data";  
        return lobbyStatus;
    }
   
}
