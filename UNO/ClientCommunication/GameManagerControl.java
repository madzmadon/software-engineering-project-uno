package ClientCommunication;

import GameLogic.GameRequest;
import GameLogic.RequestCode;

public class GameManagerControl {
    private Client client;
    private GameLobbyControl gameLobby;

    // Constructor to initialize the GameManagerControl with a Client object
    public GameManagerControl(Client client) {
        this.client = client;
    }

    //UPDATE NAME TO RELFECT REQUEST
    // Method to start a new game
    public void startGame() {
        GameRequest startSessionRequest = new GameRequest(RequestCode.JOIN_GAME);
        client.sendRequest(startSessionRequest);
        System.out.println("Join game requested");

        // Initialize a new game session control
        gameLobby = new GameLobbyControl(client);
    }

    //UPDATE NAME TO REFLECT REQUEST, CREATE HANDLING THE UI TO UPDATE A PLAYER LEAVING
    // Method to end the current game
    public void leaveGame() {
        if (gameLobby != null) {
        	//NEED A REQUEST TO LOG USER OUT AND REMOVE THEM FROM SESSION
            GameRequest endSessionRequest = new GameRequest(RequestCode.LOG_OUT);
            client.sendRequest(endSessionRequest);
            System.out.println("Left game.");
        }
    }

}