package GameLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
        initializeDeck();
    }

    private void initializeDeck() {
        for (Color color : Color.values()) {
            for (int i = 0; i < 10; i++) {
                cards.add(new NumberCard(color, i));
            }
            cards.add(new DrawTwoCard(color));
            cards.add(new ReverseCard(color));
            cards.add(new SkipCard(color));
        }
        for (int i = 0; i < 4; i++) {
            cards.add(new WildCard());
            cards.add(new WildDrawFourCard());
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        return cards.remove(0);
    }
    
    public void reset() {
    	this.cards = new ArrayList<Card>();
    }
}