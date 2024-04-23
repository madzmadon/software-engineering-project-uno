package GameLogic;

import java.io.Serializable;

public class GameResponse implements Serializable {

    private ResponseCode response;
    private Player player;

    public GameResponse(ResponseCode response, Player player)
    {

        this.response = response;
        this.player = player;

    }

    // Sets the player for the response.
    public void setPlayer(Player player)
    {
        this.player = player;
    }

    // Sets the response code.
    public void setResponse(ResponseCode response)
    {
        this.response = response;
    }

    // Returns the player from the response.
    public Player getPlayer()
    {
        return player;
    }

    // Returns the response code from the response.
    public ResponseCode getResponse()
    {
        return response;
    }

}
