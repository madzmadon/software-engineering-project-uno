package ClientCommunication;

import java.io.IOException;

import GameLogic.GameRequest;
import GameLogic.RequestCode;

public class GameManagerControl {
    private Client client;
    private GameLobbyControl gameLobby;

    // Constructor to initialize the GameManagerControl with a Client object
    public GameManagerControl(Client client) {
        this.client = client;
    }

    // Method to join a new game
    public void joinGame() {
        GameRequest startSessionRequest = new GameRequest(RequestCode.JOIN_GAME);
        try {
			client.sendRequest(startSessionRequest);
		} catch (IOException e) {
			System.out.println("Error trying to join game.");
			e.printStackTrace();
		}
        System.out.println("Join game requested");
    }

    // Method to leave and log out
    public void logOut() {
        if (gameLobby != null) {
        	//TODO: Possibly LOG_OUT instead of LEAVE_GAME
            GameRequest leaveGameRequest = new GameRequest(RequestCode.LEAVE_GAME);
            try {
				client.sendRequest(leaveGameRequest);
			} catch (IOException e) {
				System.out.println("Error logging out.");
				e.printStackTrace();
			}
            System.out.println("Log out requested.");
        }
    }

}