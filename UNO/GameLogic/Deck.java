package GameLogic;

import java.util.ArrayList;

public class Deck {

    private ArrayList<Card> cards;

    public Deck()
    {

        // Initialize the cards in the deck.
        this.reset();

    }

    // Resets all the cards in the deck.
    public void reset()
    {

        // Empty the cards in the deck.
        this.cards = new ArrayList<>();

        // TODO: Initialize all cards and insert them into the deck.

    }

    // Draws a card from the deck.
    public Card drawCard()
    {

        // Declare local variables.
        Card card = null;
        int index = 0;

        // Ensure that the deck is not empty.
        if (!this.cards.isEmpty())
        {

            // Select a random card index.
            index = (int)(Math.random() % this.getSizeOfDeck());

            // Get the card from the deck.
            card = this.cards.get(index);

            // Remove the card from the deck.
            this.cards.remove(index);

        }

        return card;

    }

    // Returns a card to the deck.
    public void returnToDeck(Card card)
    {

        // Add the card back into the deck.
        this.cards.add(card);

    }

    // Returns the number of cards within the deck.
    public int getSizeOfDeck()
    {

        return cards.size();

    }

}
