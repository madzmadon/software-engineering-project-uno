package GameLogic;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    private List<Player> players;
    private Deck deck;
    private DiscardPile discardPile;
    private int targetPoints;
    private int currentPlayerIndex;

    public GameLogic(int targetPoints) {
        this.players = new ArrayList<>();
        this.deck = new Deck();
        this.discardPile = new DiscardPile();
        this.targetPoints = targetPoints;
        this.currentPlayerIndex = 0;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void startGame() {
        deck.shuffle();
        dealCards();
        discardPile.addCard(deck.drawCard());
        currentPlayerIndex = 0;
    }

    private void dealCards() {
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                player.drawCard(deck.drawCard());
            }
        }
    }

    public boolean playCard(Player player, Card card) {
        if (discardPile.canPlayCard(card)) {
            discardPile.addCard(card);
            player.playCard(card);
            advanceToNextPlayer();
            return true;
        }
        return false;
    }

    public void drawCard(Player player) {
        player.drawCard(deck.drawCard());
        advanceToNextPlayer();
    }

    public void forceDrawCards(Player player, int count) {
        for (int i = 0; i < count; i++) {
            player.drawCard(deck.drawCard());
        }
        advanceToNextPlayer();
    }

    public void reverseDirection() {
        currentPlayerIndex = (currentPlayerIndex + players.size() - 1) % players.size();
    }

    public void skipCurrentPlayer() {
        advanceToNextPlayer();
    }

    public void changeColor(Color color) {
        discardPile.setColor(color);
    }

    private void advanceToNextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public boolean isGameOver() {
        for (Player player : players) {
            if (player.getPoints() >= targetPoints) {
                return true;
            }
        }
        return false;
    }

    public int getWinnerIndex() {
        int winnerIndex = 0;
        int minPoints = Integer.MAX_VALUE;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getPoints() < minPoints) {
                minPoints = players.get(i).getPoints();
                winnerIndex = i;
            }
        }
        return winnerIndex;
    }
    
    public String getGameRules() {
        // Return the game rules as a string
        return "The game rules are...";
    }
    
    public void removePlayer(Player player) {
        // Remove the player from the game
        players.remove(player);
    }
}