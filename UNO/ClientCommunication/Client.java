package ClientCommunication;

import GameLogic.Card;
import GameLogic.GameLogic;
import GameLogic.Player;
import ServerCommunication.Server;

public class Client {
    private Server serverCommunication;
    private GameLogic gameLogic;

    public Client(Server serverCommunication, GameLogic gameLogic) {
        this.serverCommunication = serverCommunication;
        this.gameLogic = gameLogic;
    }

    public void sendLoginRequest(String username, String password) {
        serverCommunication.handleLoginRequest(username, password);
    }

    public void sendCreateAccountRequest(String username, String password) {
        serverCommunication.handleCreateAccountRequest(username, password);
    }

    public void sendStartGameRequest() {
        gameLogic.startGame();
        serverCommunication.handleStartGameRequest(gameLogic);
    }

    public void sendJoinGameRequest() {
        Player player = gameLogic.addPlayer(new Player());
        serverCommunication.handleJoinGameRequest(gameLogic, player);
    }

    public void sendShowHowToPlayRequest() {
        serverCommunication.handleShowHowToPlayRequest(gameLogic);
    }

    public void sendLogoutRequest() {
        gameLogic.removePlayer(gameLogic.getCurrentPlayer());
        serverCommunication.handleLogoutRequest(gameLogic);
    }

    public void sendPlayCardRequest(Card card) {
        if (gameLogic.playCard(gameLogic.getCurrentPlayer(), card)) {
            serverCommunication.handlePlayCardRequest(gameLogic, card);
        }
    }

    public void sendDrawCardRequest() {
        gameLogic.drawCard(gameLogic.getCurrentPlayer());
        serverCommunication.handleDrawCardRequest(gameLogic);
    }

    public void sendUnoCallRequest() {
        serverCommunication.handleUnoCallRequest(gameLogic);
    }

    public void sendForfeitRequest() {
        gameLogic.removePlayer(gameLogic.getCurrentPlayer());
        serverCommunication.handleForfeitRequest(gameLogic);
    }

    public void sendStartGameSessionRequest() {
        serverCommunication.handleStartGameSessionRequest(gameLogic);
    }

    public void sendLeaveGameSessionRequest() {
        gameLogic.removePlayer(gameLogic.getCurrentPlayer());
        serverCommunication.handleLeaveGameSessionRequest(gameLogic);
    }
}