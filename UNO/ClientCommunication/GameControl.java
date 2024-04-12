package ClientCommunication;

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
        Object startGameRequest = "START_GAME:" + gameId;
        client.sendRequest(startGameRequest);
        System.out.println("Game start requested for game ID: " + gameId);
    }

    // Method to send a move or action to the server
    public void sendPlayerAction(Object action) {
        client.sendRequest(action);
        System.out.println("Player action sent for game ID " + gameId + ": " + action);
    }

    // Method to receive game state updates from the server
    public void updateGameState() {
        // Simulate receiving a game state update from the server
        Object gameState = client.receiveGameState();
        handleGameStateUpdate(gameState);
    }

    // Private method to handle updates to the game state
    private void handleGameStateUpdate(Object gameState) {
        // Processing game state update
        System.out.println("Received game state update for game ID " + gameId + ": " + gameState);
    }

    // Method to get the game ID
    public int getGameId() {
        return this.gameId;
    }
}
