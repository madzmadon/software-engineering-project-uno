package GameLogic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {

    private String player_name;
    private int score;
    private ArrayList<Card> hand;
    private boolean safe;

    public Player()
    {

    }

    // Set the player's name.
    public void setPlayerName(String player_name)
    {
        this.player_name = player_name;
    }

    // Returns the player's name.
    public String getPlayerName()
    {
        return player_name;
    }

    // Sets the score of the player.
    public void setScore(int score)
    {
        this.score = score;
    }

    // Returns the score of the player.
    public int getScore()
    {
        return score;
    }

    // Utility method to reset the cards within the player's hand.
    public void emptyHand()
    {

        // Iterate over every card within the player's hand.
        for (Card card : hand)
        {

            // Remove the current card from the player's hand.
            this.hand.remove(card);

        }

    }

    // Returns the cards within the player's hand.
    public ArrayList<Card> getHand()
    {
        return hand;
    }

    // Sets the hand of the player.
    public void setHand(ArrayList<Card> hand)
    {
        this.hand = hand;
    }

    // Sets the flag that determines if the player has called their current Uno.
    public void isSafe(boolean safe)
    {
        this.safe = safe;
    }

    // Returns the value of the flag that determines if the player has called their Uno.
    public boolean isSafe()
    {
        return safe;
    }

    // Sums the points of every card within the player's hand.
    public int getSumOfCards()
    {

        // Declare local variables.
        int sum = 0;

        // Iterate over every card within the hand.
        for (Card card : this.hand)
        {

            // Add the point value for the current card to 'sum'.
            sum += card.getPointValue();

        }

        return sum;

    }

}
