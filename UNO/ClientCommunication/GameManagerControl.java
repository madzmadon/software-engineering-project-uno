package ClientCommunication;

import java.util.HashMap;
import java.util.Map;

import GameLogic.GameRequest;
import GameLogic.RequestCode;

public class GameManagerControl {
    private Client client;
    private Map<String, GameSessionControl> activeSessions;

    // Constructor to initialize the GameManagerControl with a Client object
    public GameManagerControl(Client client) {
        this.client = client;
        this.activeSessions = new HashMap<>();
    }

    // Method to start a new game session for a specific lobby
    public void startGameSession(String lobbyId) {
        GameRequest startSessionRequest = new GameRequest(RequestCode.START_SESSION);
        client.handleMessageFromServer(startSessionRequest);
        System.out.println("Game session start requested for lobby: " + lobbyId);

        // Initialize and store a new game session control for this lobby
        GameSessionControl gameSession = new GameSessionControl(client, lobbyId);
        activeSessions.put(lobbyId, gameSession);
    }

    // Method to end a specific game session
    public void endGameSession(String lobbyId) {
    	GameRequest endSessionRequest = new GameRequest(RequestCode.END_SESSION);
        client.handleMessageFromServer(endSessionRequest);
        System.out.println("Game session end requested for lobby: " + lobbyId);

        // Remove the session from active sessions
        activeSessions.remove(lobbyId);
    }

}
