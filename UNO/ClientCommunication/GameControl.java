package ClientCommunication;

public class GameControl {
    private Client client;

    
    public GameControl(Client client) {
        this.client = client;
    }

    
    public void startGame() {
        Object startGameRequest = "START_GAME";
        client.sendRequest(startGameRequest);
        System.out.println("Game start requested.");
    }

    
    public void sendPlayerAction(Object action) {
        client.sendRequest(action);
        System.out.println("Player action sent: " + action);
    }

    
    public void updateGameState() {
        
        Object gameState = client.receiveGameState();
        handleGameStateUpdate(gameState);
    }

    
    private void handleGameStateUpdate(Object gameState) {
        
        System.out.println("Received game state update: " + gameState);
    }
}
