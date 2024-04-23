package ClientCommunication;

import java.io.IOException;
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
        GameRequest startGameRequest = new GameRequest(RequestCode.START_GAME);
        try {
			client.sendRequest(startGameRequest);
		} catch (IOException e) {
			System.out.println("Error trying to start game.");
			e.printStackTrace();
		}
        System.out.println("Start game requested.");
    }

    //TODO: LEAVE GAME SHOULD LOG YOU OUT ADD FUNCTIONALITY TO SERVER
    // Method to leave a game lobby
    public void leaveGame() {
    	GameRequest leaveGameRequest = new GameRequest(RequestCode.LEAVE_GAME);
        try {
			client.sendRequest(leaveGameRequest);
		} catch (IOException e) {
			System.out.println("Error trying to leave game.");
			e.printStackTrace();
		}
        System.out.println("Leave game requested.");
        
    }

}