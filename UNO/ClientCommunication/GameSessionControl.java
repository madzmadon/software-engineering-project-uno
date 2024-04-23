package ClientCommunication;

import java.io.IOException;
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

    public GameSessionControl(Client client) {
        this.client = client;
        this.deck = new Deck(); // Initialize the deck
//        deck.shuffle(); // Shuffle the deck
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

    //TODO: Return game request ... LAST CARD PLAYED: Check for server to check if last card
    public void playCard() {
    	GameRequest playCardRequest = new GameRequest(RequestCode.PLAY_CARD);
    	try {
			client.sendRequest(playCardRequest);
			System.out.println("Play card Request");
		} catch (IOException e) {
			System.out.println("Play card Fail");
			e.printStackTrace();
		}
    }

    public Card drawCard() {
        GameRequest drawCardRequest = new GameRequest(RequestCode.DRAW_CARD);

        try {
			client.sendRequest(drawCardRequest);
			System.out.println("Draw card Request");
		} catch (IOException e) {
			System.out.println("Draw card Fail");
			e.printStackTrace();
		}

        return deck.drawCard();
    }

    public void announceUno() {
        GameRequest announceUnoRequest = new GameRequest(RequestCode.ANNOUNCE_UNO);

            try {
				client.sendRequest(announceUnoRequest);
				System.out.println("Announce Uno Request");
			} catch (IOException e) {
				System.out.println("Announce Uno Fail");
				e.printStackTrace();
			}
    }

}