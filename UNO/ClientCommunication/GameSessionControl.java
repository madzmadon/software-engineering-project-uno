package ClientCommunication;

import java.util.ArrayList;
import java.util.List;

import GameLogic.Deck;
import GameLogic.GameRequest;
import GameLogic.Player;
import GameLogic.RequestCode;
import GameLogic.Card;

public class GameSessionControl {
    private Client client;
    private List<Player> players = new ArrayList<>();
    private int currentPlayerIndex = 0;
    private List<Card> discardPile = new ArrayList<>();
    private Deck deck;

    public GameSessionControl(Client client, String lobbyId) {
        this.client = client;
        this.lobbyId = lobbyId;
        this.deck = new Deck(); // Initialize the deck
        deck.shuffle(); // Shuffle the deck
    }
    // TODO: SELECT A COLOR FOR WILD CARDS
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

    //TODO: USE SERVER VALIDATION CHECK FOR CARD
    public boolean isValidMove(Card card) {
        if (discardPile.isEmpty()) {
            return true; // First card can be played
        }

        Card topCard = discardPile.get(discardPile.size() - 1);
        //CHECK FOR MATCH
        return card.getColor() == topCard.getColor() || card.getSymbol().equals(topCard.getSymbol());
    }

    //TODO: Return game request ... LAST CARD PLAYED: Check for server to check if last card
    public void playCard(Player player, Card card) {
        if (isValidMove(card)) {
            boolean removed = player.playCard(card);
            if (removed) {
                GameRequest playCardRequest = new GameRequest(RequestCode.PLAY_CARD);
                playCardRequest.setCard(card);

                client.sendRequest(playCardRequest);
                discardPile.add(card);
                nextTurn();
            } else {
                // Handle error: Card not found in player's hand
                System.out.println("Error: " + player.getName() + " does not have the card " + card.getSymbol());
            }
        } else {
            // Handle invalid move
            System.out.println("Invalid move by " + player.getName());
        }
    }

    public Card drawCard() {
        GameRequest drawCardRequest = new GameRequest(RequestCode.DRAW_CARD);

        client.sendRequest(drawCardRequest);

        return deck.drawCard();
    }

    // TODO: Repurpose server logic
    public void callUno(Player player) {
        if (player.getHand().size() == 1) {
            GameRequest announceUnoRequest = new GameRequest(RequestCode.ANNOUNCE_UNO);

            client.sendRequest(announceUnoRequest);

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

    private Player getNextPlayer() {
        return players.get((currentPlayerIndex + 1) % players.size());
    }
}