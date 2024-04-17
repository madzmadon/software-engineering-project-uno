package ServerCommunication;

import ClientCommunication.Client;
import GameLogic.Card;
import GameLogic.GameLogic;
import GameLogic.Player;

public class Server {
    private GameLogic gameLogic;
    private Client clientCommunication;

    public Server(GameLogic gameLogic, Client clientCommunication) {
        this.gameLogic = gameLogic;
        this.clientCommunication = clientCommunication;
    }

    public void handleLoginRequest(String username, String password) {
        // Authenticate the user and send the response back to the client
        boolean loginSuccessful = gameLogic.authenticateUser(username, password);
        clientCommunication.updateLoginResult(loginSuccessful);
    }

    public void handleCreateAccountRequest(String username, String password) {
        // Create a new user account and send the response back to the client
        boolean accountCreationSuccessful = gameLogic.createAccount(username, password);
        clientCommunication.updateAccountCreationResult(accountCreationSuccessful);
    }

    public void handleStartGameRequest(GameLogic gameLogic) {
        // Start a new game session and send the game state to the client
        gameLogic.startGame();
        clientCommunication.updateGameState(gameLogic);
    }

    public void handleJoinGameRequest(GameLogic gameLogic, Player player) {
        // Add the player to the existing game session and send the updated game state to the client
        gameLogic.addPlayer(player);
        clientCommunication.updateGameState(gameLogic);
        clientCommunication.updatePlayerData(player);
    }

    public void handleShowHowToPlayRequest(GameLogic gameLogic) {
        // Send the game rules to the client
        clientCommunication.updateGameRules(gameLogic.getGameRules());
    }

    public void handleLogoutRequest(GameLogic gameLogic) {
        // Remove the player from the game session and send the updated game state to the client
        clientCommunication.updateGameState(gameLogic);
    }

    public void handlePlayCardRequest(GameLogic gameLogic, Card card) {
        // Handle the card play and send the updated game state to the client
        gameLogic.playCard(gameLogic.getCurrentPlayer(), card);
        clientCommunication.updateGameState(gameLogic);
    }

    public void handleDrawCardRequest(GameLogic gameLogic) {
        // Handle the card draw and send the updated game state to the client
        gameLogic.drawCard(gameLogic.getCurrentPlayer());
        clientCommunication.updateGameState(gameLogic);
    }

    public void handleUnoCallRequest(GameLogic gameLogic) {
        // Handle the Uno call and send the updated game state to the client
        clientCommunication.updateGameState(gameLogic);
    }

    public void handleForfeitRequest(GameLogic gameLogic) {
        // Handle the game forfeit and send the updated game state to the client
        clientCommunication.updateGameState(gameLogic);
    }

    public void handleStartGameSessionRequest(GameLogic gameLogic) {
        // Start a new game session and send the game state to the client
        gameLogic.startGame();
        clientCommunication.updateGameState(gameLogic);
    }

    public void handleLeaveGameSessionRequest(GameLogic gameLogic) {
        // Remove the player from the game session and send the updated game state to the client
        clientCommunication.updateGameState(gameLogic);
    }
    
    public void handleStartGameRequest() {
        // Start a new game session and send the game state to the client
        gameLogic.startGame();
        clientCommunication.updateGameState(gameLogic);
    }
}