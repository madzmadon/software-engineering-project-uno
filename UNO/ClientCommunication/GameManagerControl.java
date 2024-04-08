package ClientCommunication;

public class GameManagerControl {

<<<<<<< Updated upstream
=======
    // Constructor
    public GameManagerControl(Client client) {
        this.client = client;
        this.activeSessions = new HashMap<>();
    }

    // Method to start a new game session
    public void startGameSession(String lobbyId) {
        Object startGameRequest = "START_GAME_SESSION:" + lobbyId;
        client.sendRequest(startGameRequest);
        System.out.println("Game session start requested for lobby: " + lobbyId);

        // Create a new game session control for this lobby
        GameSessionControl gameSession = new GameSessionControl(client, lobbyId);
        activeSessions.put(lobbyId, gameSession);
    }

    // Method to end a game session
    public void endGameSession(String lobbyId) {
        Object endGameRequest = "END_GAME_SESSION:" + lobbyId;
        client.sendRequest(endGameRequest);
        System.out.println("Game session end requested for lobby: " + lobbyId);

        // Remove the session from active sessions
        activeSessions.remove(lobbyId);
    }

    // Method to update all game sessions based on server updates
    public void updateGameSessions() {
        for (Map.Entry<String, GameSessionControl> entry : activeSessions.entrySet()) {
            entry.getValue().updateGameState();
        }
    }
>>>>>>> Stashed changes
}
