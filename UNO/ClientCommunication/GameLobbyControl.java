package ClientCommunication;

import java.util.ArrayList;
import java.util.List;

public class GameLobbyControl {
    private Client client;
    private List<String> lobbyPlayers = new ArrayList<>();

    // Constructor to initialize the GameLobbyControl with a Client object
    public GameLobbyControl(Client client) {
        this.client = client;
    }

    // Method to create a new game lobby
    public void createLobby() {
        Object createLobbyRequest = "CREATE_LOBBY";
        client.sendRequest(createLobbyRequest);
        System.out.println("Lobby creation requested.");
    }

    // Method to join an existing game lobby
    public void joinLobby(String lobbyId) {
        Object joinLobbyRequest = "JOIN_LOBBY:" + lobbyId;
        client.sendRequest(joinLobbyRequest);
        System.out.println("Requested to join lobby: " + lobbyId);
    }

    // Method to leave a game lobby
    public void leaveLobby(String lobbyId) {
        Object leaveLobbyRequest = "LEAVE_LOBBY:" + lobbyId;
        client.sendRequest(leaveLobbyRequest);
        System.out.println("Requested to leave lobby: " + lobbyId);
    }

    // Method to update the status of the lobby based on server updates
    public void updateLobbyStatus() {
        Object lobbyStatus = client.receiveLobbyStatus();
        handleLobbyStatusUpdate(lobbyStatus);
    }

    // Private method to handle updates to the lobby status
    private void handleLobbyStatusUpdate(Object lobbyStatus) {
        System.out.println("Lobby status updated: " + lobbyStatus);
    }
}
