package ClientCommunication;

import java.util.HashMap;
import java.util.Map;

public class GameManagerControl {
    private Client client;
    private Map<String, GameSessionControl> activeSessions;

    
    public GameManagerControl(Client client) {
        this.client = client;
        this.activeSessions = new HashMap<>();
    }

    
    public void startGameSession(String lobbyId) {
        Object startGameRequest = "START_GAME_SESSION:" + lobbyId;
        client.sendRequest(startGameRequest);
        System.out.println("Game session start requested for lobby: " + lobbyId);

        
        GameSessionControl gameSession = new GameSessionControl(client, lobbyId);
        activeSessions.put(lobbyId, gameSession);
    }

    
    public void endGameSession(String lobbyId) {
        Object endGameRequest = "END_GAME_SESSION:" + lobbyId;
        client.sendRequest(endGameRequest);
        System.out.println("Game session end requested for lobby: " + lobbyId);

        
        activeSessions.remove(lobbyId);
    }

    
    public void updateGameSessions() {
        for (Map.Entry<String, GameSessionControl> entry : activeSessions.entrySet()) {
            entry.getValue().updateGameState();
        }
    }
}
