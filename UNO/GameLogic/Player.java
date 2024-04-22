package GameLogic;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;
    private int points;

    public Player() {
        this.hand = new ArrayList<>();
        this.points = 0;
    }

	public void setPlayerName(String name) {
    	this.name = name;
    }

    public void drawCard(Card card) {
        hand.add(card);
    }

    public boolean playCard(Card card) {
        updatePoints(card);
		return hand.remove(card);
    }

    private void updatePoints(Card card) {
        points += card.getPoints();
    }

    public List<Card> getHand() {
        return new ArrayList<>(hand);
    }
    
    public void setHand(List<Card> playerHand) {
    	this.hand = playerHand;
    }

    public int getPoints() {
        return points;
    }
    
    public String getName() {
        return name;
    }
}