package ClientCommunication;

import java.util.ArrayList;
import java.util.List;

import GameLogic.Card;
import GameLogic.GameRequest;
import GameLogic.RequestCode;

public class GameLobbyControl {
    private Client client;
    //TODO: SET UP SCORE PRESENTATION IN LOBBY (Initially just show my scores, not opponents)
    private List<String> lobbyPlayers = new ArrayList<>();

    // Constructor to initialize the GameLobbyControl with a Client object
    public GameLobbyControl(Client client) {
        this.client = client;
    }

    //TODO: DOUBLE CHECK ON START GAME WITH MULTIPLE PEOPLE BUTTON PRESSING START GAME
    // Method to create a new game lobby
    public void startGame() {
        GameRequest createLobbyRequest = new GameRequest(RequestCode.START_GAME);
        client.sendRequest(createLobbyRequest);
        System.out.println("Game start requested.");
    }

    // Method to leave a game lobby
    public void logOut(String lobbyId) {
        GameRequest leaveLobbyRequest = new GameRequest(RequestCode.LOG_OUT);
        client.sendRequest(leaveLobbyRequest);
        System.out.println("Requested to leave lobby and logout.");
    }

}