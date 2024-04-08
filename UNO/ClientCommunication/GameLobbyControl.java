
package ClientCommunication;

import java.util.ArrayList;
import java.util.List;

public class GameLobbyControl {
    private Client client;
    private List<String> lobbyPlayers = new ArrayList<>();

    
    public GameLobbyControl(Client client) {
        this.client = client;
    }

    
    public void createLobby() {
        Object createLobbyRequest = "CREATE_LOBBY";
        client.sendRequest(createLobbyRequest);
        System.out.println("Lobby creation requested.");
    }

    
    public void joinLobby(String lobbyId) {
        Object joinLobbyRequest = "JOIN_LOBBY:" + lobbyId;
        client.sendRequest(joinLobbyRequest);
        System.out.println("Requested to join lobby: " + lobbyId);
    }

    
    public void leaveLobby(String lobbyId) {
        Object leaveLobbyRequest = "LEAVE_LOBBY:" + lobbyId;
        client.sendRequest(leaveLobbyRequest);
        System.out.println("Requested to leave lobby: " + lobbyId);
    }

   
    public void updateLobbyStatus() {
        Object lobbyStatus = client.receiveLobbyStatus();
        handleLobbyStatusUpdate(lobbyStatus);
    }

    
    private void handleLobbyStatusUpdate(Object lobbyStatus) {
        System.out.println("Lobby status updated: " + lobbyStatus);
        
    }
}
