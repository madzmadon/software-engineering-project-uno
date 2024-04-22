package ClientCommunication;

import java.util.ArrayList;
import java.util.List;

import GameLogic.GameRequest;
import GameLogic.RequestCode;

public class GameLobbyControl {
    private Client client;
    private List<String> lobbyPlayers = new ArrayList<>();

    // Constructor to initialize the GameLobbyControl with a Client object
    public GameLobbyControl(Client client) {
        this.client = client;
    }

    // Method to create a new game lobby
    public void createLobby() {
        GameRequest createLobbyRequest = new GameRequest(RequestCode.START_GAME);
        client.sendToServer(createLobbyRequest);
        System.out.println("Game creation requested.");
    }
    // Method to join an existing game lobby
    public void joinLobby(String lobbyId) {
        GameRequest joinLobbyRequest = new GameRequest(RequestCode.JOIN_GAME);
        client.sendToServer(joinLobbyRequest);
        System.out.println("Requested to join lobby: " + lobbyId);
    }


    // Method to leave a game lobby
    public void leaveLobby(String lobbyId) {
        GameRequest leaveLobbyRequest = new GameRequest(RequestCode.LEAVE_GAME);
        client.sendToServer(leaveLobbyRequest);
        System.out.println("Requested to leave lobby and logout: " + lobbyId);
    }

}
