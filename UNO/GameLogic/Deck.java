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

        // Initialize the color cards.
        for (int i = 0; i < 4; ++i)
        {

            // Obtain the current color.
            CardColor color = CardColor.values()[i];
            
            // Create all of the number cards.
            for (int j = 1; j <= 19; ++j)
            {

                // Create the current card.
                Card card = new Card(color, CardFaceValue.values()[(j % 10)]);

                // Add the new card to the 'cards' list.
                this.cards.add(card);

            }

            // Create the special color cards.
            for (int j = 0; j < 2; ++j)
            {

                // Create the skip, reverse, and draw two cards.
                Card skip = new Card(color, CardFaceValue.SKIP);
                Card reverse = new Card(color, CardFaceValue.REVERSE);
                Card draw_two = new Card(color, CardFaceValue.PLUS_TWO);

                // Add the cards to the 'cards' list.
                this.cards.add(skip);
                this.cards.add(reverse);
                this.cards.add(draw_two);

            }

        }

        // Add the wildcards.
        for (int i = 0; i < 4; ++i)
        {

            // Create the wild card and draw four card.
            Card wildcard = new Card(CardColor.NONE, CardFaceValue.WILDCARD);
            Card draw_four = new Card(CardColor.NONE, CardFaceValue.PLUS_FOUR);

            // Add the cards to the 'cards' list.
            this.cards.add(wildcard);
            this.cards.add(draw_four);

        }

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
