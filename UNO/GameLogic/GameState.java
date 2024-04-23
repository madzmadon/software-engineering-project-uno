package GameLogic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class GameState implements Serializable {

    // Game fields.
    private HashMap<String, Integer> scores;          // A list of the players with their associated id values.
    private Card top_card;                            // The card that exists on the top of the played stack.
    private CardColor top_card_color;				  // The color of the top card. Only applicable for wildcards.
    private boolean forward;                          // The order in which players take their turns, flipped if a reverse card is played.
    private String current_player_name;               // The index of the player whose turn it is currently.
    private int threshold;                            // Contains the number of points that a player must obtain to win.

    public GameState(int threshold)
    {

        // Initialize the fields.
        this.scores = new HashMap<>();
        this.forward = true;
        this.current_player_name = "";
        this.threshold = threshold;

    }

    // Sets the top card on the play stack.
    public void setTopCard(Card top_card)
    {
        this.top_card = top_card;
    }

    // Returns the top card on the play stack.
    public Card getTopCard()
    {
        return top_card;
    }
    
    // Sets the color of the top card. Only applicable when player plays a wildcard.
    public void setTopCardColor(CardColor color)
    {
    	this.top_card_color = color;
    }
    
    // Returns the color of the top card. Only applicable when player plays a wildcard. 
    public CardColor getTopCardColor()
    {
    	return top_card_color;
    }
    
    // Returns the direction that the players are going in.
    public boolean isForward()
    {
        return forward;
    }

    // Sets the direction that the players are going in.
    public void isForward(boolean forward)
    {
        this.forward = forward;
    }

    // Sets the name of the current player.
    public void setCurrentPlayerName(String name)
    {
        this.current_player_name = name;
    }

    // Returns the name of the current player.
    public String getCurrentPlayerName()
    {
        return current_player_name;
    }

    // Returns the scores of each player.
    public Map<String, Integer> getScores()
    {
        return scores;
    }

    // Sets the scores of each player.
    public void setScores(HashMap<String, Integer> scores)
    {
        this.scores = scores;
    }

    // Sets the threshold that a player must meet to win the game.
    public void setThreshold(int threshold) throws Exception
    {

        // Set the value for the threshold.
        this.threshold = threshold;

    }

    // Returns the threshold that a player must meet to win the game.
    public int getThreshold()
    {
        return threshold;
    }

}
