package ClientCommunication;

import java.util.ArrayList;
import java.util.List;

public class GameSessionControl {
    private Client client;
    private String lobbyId;
    private List<Player> players = new ArrayList<>();
    private int currentPlayerIndex = 0;
    private List<Card> discardPile = new ArrayList<>();

    public GameSessionControl(Client client, String lobbyId) {
        this.client = client;
        this.lobbyId = lobbyId;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public void nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    public boolean isValidMove(Card card) {
        Card topCard = discardPile.get(discardPile.size() - 1);
        return card.getColor().equals(topCard.getColor()) || card.getValue().equals(topCard.getValue());
    }

    public void playCard(Player player, Card card) {
        if (isValidMove(card)) {
            player.removeCard(card);
            discardPile.add(card);
            nextTurn();
        } else {
            // Handle invalid move
            System.out.println("Invalid move by " + player.getName());
        }
    }

    public Card drawCard() {
        // This method needs to pull a card from a deck, which is not yet implemented
        return new Card("Red", "5");  // Placeholder
    }

    public void callUno(Player player) {
        if (player.getHand().size() == 1) {
            System.out.println(player.getName() + " called UNO!");
        } else {
            // Handle error (e.g., penalty)
            System.out.println(player.getName() + " called UNO incorrectly!");
        }
    }

    public boolean isGameOver() {
        return players.stream().anyMatch(player -> player.getHand().isEmpty());
    }

    public void broadcastMessage(String message) {
        System.out.println("Broadcasting: " + message);
        // Here you would actually send a message to all clients
    }

    public void updateGameState() {
        Object gameStateUpdate = client.receiveGameState();  // Simulating receiving a game state
        System.out.println("Game state updated for lobby " + lobbyId + ": " + gameStateUpdate);
    }

    private Player getNextPlayer() {
        return players.get((currentPlayerIndex + 1) % players.size());
    }
}
