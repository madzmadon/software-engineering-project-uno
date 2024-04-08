package ClientCommunication;

public class Client {

<<<<<<< Updated upstream
=======
	public void sendRequest(Object request)
	{
	//simulate sending request to the server
		System.out.println("Sending request to the server:" +request); 
		
	//simulate receiving response from the server
		Object response="Response from the server";
		
	//Handle the response 
	handleResponse(response);
		
	}
	private void handleResponse(Object response) {
        // Simulate handling the response
        System.out.println("Received response from the server: " + response);
    }
	
    public boolean receiveLoginResponse() {
        // Simulate receiving the response from the server
        // For the sake of simulation, let's assume login is always successful
        return true;
    }
    public boolean receiveAccountCreationResponse() {
        // Simulate receiving the response from the server for account creation
        return true; 
    }
    
    public Object receiveGameState() {
        // Simulate receiving a game state update from the server
        Object gameState = "Current game state data";
        return gameState;
    }
    
    public Object receiveLobbyStatus() {
        // Simulate receiving a lobby status update from the server
        Object lobbyStatus = "Lobby status data";  
        return lobbyStatus;
    }
   
>>>>>>> Stashed changes
}
