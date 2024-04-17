package GameLogic;

import java.util.ArrayList;
import java.util.List;

public class DiscardPile {
    private List<Card> pile;
    private Color currentColor;

    public DiscardPile() {
        this.pile = new ArrayList<>();
        this.currentColor = null;
    }

    public void addCard(Card card) {
        pile.add(card);
        if (card instanceof WildCard || card instanceof WildDrawFourCard) {
            currentColor = null;
        } else {
            currentColor = card.getColor();
        }
    }

    public boolean canPlayCard(Card card) {
        if (pile.isEmpty()) {
            return true;
        }
        Card topCard = pile.get(pile.size() - 1);
        return card.getColor() == topCard.getColor() || card.getSymbol() == topCard.getSymbol() ||
               card instanceof WildCard || card instanceof WildDrawFourCard;
    }

    public void setColor(Color color) {
        currentColor = color;
    }

    public Color getCurrentColor() {
        return currentColor;
    }
}
