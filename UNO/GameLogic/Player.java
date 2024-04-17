package GameLogic;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;
    private int points;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.points = 0;
    }

    public void drawCard(Card card) {
        hand.add(card);
    }

    public void playCard(Card card) {
        hand.remove(card);
        updatePoints(card);
    }

    private void updatePoints(Card card) {
        points += card.getPoints();
    }

    public List<Card> getHand() {
        return new ArrayList<>(hand);
    }

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }
}