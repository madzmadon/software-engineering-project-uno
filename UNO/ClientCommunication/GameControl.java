package ClientCommunication;

public class GameControl {

<<<<<<< Updated upstream
=======
    // Constructor to initialize the GameControl with a Client
    public GameControl(Client client) {
        this.client = client;
    }

    // Method to start a game session
    public void startGame() {
        Object startGameRequest = "START_GAME";
        client.sendRequest(startGameRequest);
        System.out.println("Game start requested.");
    }

    // Method to send a move or action to the server
    public void sendPlayerAction(Object action) {
        client.sendRequest(action);
        System.out.println("Player action sent: " + action);
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
        System.out.println("Received game state update: " + gameState);
    }
>>>>>>> Stashed changes
}
