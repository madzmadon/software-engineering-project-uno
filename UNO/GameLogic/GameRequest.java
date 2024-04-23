package GameLogic;

import java.io.Serializable;

public class GameRequest implements Serializable {

    private RequestCode request;
    private CardColor color;
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
    
    // Sets the color of the top card. Only applicable when player plays a wildcard.
    public void setCardColor(CardColor color)
    {
    	this.color = color;
    }
    
    // Returns the color of the new top card. Only applicable when the player plays a wildcard.
    public CardColor getCardColor()
    {
    	return color;
    }

}