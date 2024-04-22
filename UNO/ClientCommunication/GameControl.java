package ClientCommunication;

import GameLogic.GameRequest;
import GameLogic.RequestCode;

public class GameControl {
    private Client client;
    private int gameId;  // Field to store the game ID

    // Constructor to initialize the GameControl with a Client and a game ID
    public GameControl(Client client, int gameId) {
        this.client = client;
        this.gameId = gameId;  // Initialize the game ID
    }

    // Method to start a game session
    public void startGame() {
        GameRequest startGameRequest = new GameRequest(RequestCode.START_GAME);
        startGameRequest.setThreshold(200); // Set the desired threshold value
        client.handleMessageFromServer(startGameRequest);
        System.out.println("Game start requested for game ID: " + gameId);
    }
    // Method to send a move or action to the server
    public void sendPlayerAction(GameRequest request) {
        client.handleMessageFromServer(request);
        System.out.println("Player action sent for game ID " + gameId + ": " + request.getRequest());
    }

    // Method to get the game ID
    public int getGameId() {
        return this.gameId;
    }
}
