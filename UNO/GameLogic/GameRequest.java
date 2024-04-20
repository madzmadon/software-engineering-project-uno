package GameLogic;

import java.io.Serializable;

public class GameRequest implements Serializable {

    private RequestCode request;
    private int threshold;
    private Card card;

    public GameRequest(RequestCode request)
    {
        this.request = request;
    }

    // Returns the request code from the request.
    public RequestCode getRequest()
    {
        return request;
    }

    // Sets the card to the request.
    public void setCard(Card card)
    {
        this.card = card;
    }

    // Returns the card from the request.
    public Card getCard()
    {
        return card;
    }

    // Sets the threshold for the game.
    public void setThreshold(int threshold)
    {
        this.threshold = threshold;
    }

    // Returns the threshold for the game.
    public int getThreshold()
    {
        return threshold;
    }

}
