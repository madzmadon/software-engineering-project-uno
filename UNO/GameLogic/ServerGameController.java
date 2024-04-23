package GameLogic;

import Database.LoginData;
import ServerCommunication.Server;
import ocsf.server.ConnectionToClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ServerGameController {

    private Server server;

    private HashMap<ConnectionToClient, Player> players;
    private boolean gameInProgress;
    private int currentIndex;
    private GameState state;
    private Deck deck;

    private final int MIN_PLAYER_COUNT = 2;
    private final int MAX_CARDS_IN_HAND = 25;

    public ServerGameController(Server server)
    {

        // Initialize data fields.
        this.server = server;
        this.players = new HashMap<>();
        this.currentIndex = 0;
        this.gameInProgress = false;
        this.deck = new Deck();
        
    }

    // Ensures that a user is valid.
    public boolean verifyPlayer(ConnectionToClient user)
    {
        return ((user != null) && (this.server.getUsers().containsKey(user)) && (this.players.containsKey(user)));
    }
    
    // Returns the keys of still active users.
    public Set<ConnectionToClient> getPlayerKeys()
    {

        // Declare local variables.
        Set<ConnectionToClient> keys = this.players.keySet();

        // Remove null keys.
        keys.remove(null);

        return keys;
        
    }

    // Handles all game requests from users/players.
    public void handleGameRequest(GameRequest request, ConnectionToClient caller)
    {

        System.out.println("Receiving game request.");

        // Ensure that the user is logged in.
        if (this.server.getUsers().containsKey(caller))
        {
        
        	// Determine the game request.
        	switch (request.getRequest())
        	{

            	case JOIN_GAME -> this.handleJoinGame(caller);
            	case START_GAME -> this.handleGameStart(request, caller);
            	case START_SESSION -> this.handleSessionStart(caller);
            	case PLAY_CARD -> this.handlePlayCard(request, caller);
            	case DRAW_CARD -> this.handleDrawCard(request, caller);
            	case ANNOUNCE_UNO -> this.handleAnnounceUno(request, caller);
            	case LEAVE_GAME -> this.handleLeaveGame(caller);
            	
        	}
        
        } else
        {
        	
        	// Send the fail message to the user.
        	this.sendResponse(new GameResponse(ResponseCode.FAILED, null), caller);
        	
        }

    }

    /*
     * Handlers for specific game requests:
     *  - handleJoinGame      :   Handles the JOIN_GAME request by users.
     *  - handleGameStart     :   Starts the game.
     *  - handleSessionStart  :   Starts a session within a game.
     *  - handlePlayCard      :   Plays a card, will be split into other operations in the cards section.
     *  - handleDrawCard      :   Allocates a card from the deck to the calling player.
     *  - handleAnnounceUno   :   Announces Uno to protect oneself or attack others.
     *  - eventGameEnds       :   Handles the end of the game automatically or when the number of player's drops below two.
    */

    // Handles the users joining the game.
    public void handleJoinGame(ConnectionToClient caller)
    {

        // Declare local variables.
        GameResponse response = null;

        // Ensure that the user is logged in and that the game has not started yet.
        if ((!gameInProgress) && (!this.server.getUsers().containsKey(caller)))
        {

            // Set the response to failed.
            response = new GameResponse(ResponseCode.FAILED, null);

        } else 
        {

            // Add the player to the 'players' list.
            this.players.put(caller, new Player());

            // Set the response to success.
            response = new GameResponse(ResponseCode.SUCCESS, null);

        }

        // Send the response to the player.
        this.sendResponse(response, caller);

    }

    // Handles the player starting a game.
    public void handleGameStart(GameRequest request, ConnectionToClient caller) {

        // Reset the game state.
        this.state = new GameState(this.server.getThreshold());

        // Ensure that enough players are present and that the calling user is verified.
        if (this.server.getUsers().size() < 2 || !this.verifyPlayer(caller) || this.gameInProgress)
        {

            // Send the fail message to the user.
        	this.sendResponse(new GameResponse(ResponseCode.FAILED, null), caller);

            return;

        }

        // Set the variable 'gameInProgress' to true.
        this.gameInProgress = true;

        // DEBUG: Displays a message indicating that the game has started.
        System.out.println("State> Game Started");

        // Start the game session.
        this.handleSessionStart(caller);

    }

    // Handles starting a session.
    private void handleSessionStart(ConnectionToClient caller)
    {

    	// Ensure that a game is not already in progress.
    	if (!this.gameInProgress)
    	{
    		
    		// Send the fail message to the player.
    		this.sendResponse(new GameResponse(ResponseCode.FAILED, null), caller);
    		
    	} else 
    	{
    	
    		// Reset the current index.
    		this.currentIndex = 0;
    		
    		// Reset the deck of cards.
    		this.deck.reset();

        	// Iterate over the players and initialize their hands.
        	for (ConnectionToClient connection : this.getPlayerKeys())
        	{

        		// Obtain the player from the 'players' map.
            	Player player = this.players.get(connection);

            	// Create the hand of the player.
            	ArrayList<Card> hand = new ArrayList<>();

            	// Initialize the hand of the player.
            	for (int i = 0; i < 7; ++i)
            	{

                	// Add a card to the hand of the current player.
                	hand.add(deck.drawCard());

            	}

            
            	// Set the hand to the player object.
            	player.setHand(hand);

            	// Update the player object in the 'players' map.
            	this.players.put(connection, player);

            	// Attempt to send the player object to the associated client.
            	try {

                	// Send the player object to the associated client.
                	connection.sendToClient(player);

            	} catch (IOException exception)
            	{

                	// Display the error message to the user.
                	exception.printStackTrace();

            	}

        	}

        	// DEBUG: Displays a message indicating that the game session has started.
        	System.out.println("State> Session Started");

    	}
        
    }

    // Handles playing a card.
    private void handlePlayCard(GameRequest request, ConnectionToClient caller)
    {
    	
    	// Obtain the card from the request.
		Card card = request.getCard();
    	
    	// Ensure that the user is a player and the card matches the top card, but is not matching and draw two or four.
    	if (this.players.containsKey(caller) && card.cardMatches(this.state.getTopCard()) && !((card.getFaceValue() == this.state.getTopCard().getFaceValue()) && (card.getFaceValue() == CardFaceValue.PLUS_FOUR || card.getFaceValue() == CardFaceValue.PLUS_TWO)))
    	{

    		// Get the player object.
    		Player player = this.players.get(caller);
    		
            // Send the top card back to the deck.
            this.deck.returnToDeck(this.state.getTopCard());

            // Set the top card to that of the player's.
            this.state.setTopCard(request.getCard());
            
            // Remove the card from the player's hand.
            ArrayList<Card> hand = player.getHand();
            hand.remove(card);
            player.setHand(hand);
            
            // Create the response.
            GameResponse response = new GameResponse(ResponseCode.SUCCESS, player);
    		
        	// Determine the type of card being played.
        	switch (card.getFaceValue())
        	{

            	case SKIP -> handleSkipCard();
            	case REVERSE -> handleReverseCard();
            	case PLUS_TWO -> handleDrawTwo();
            	case PLUS_FOUR -> handleDrawFour(request, caller);
            	case WILDCARD -> handleWildCard(request, caller);

        	}        	
        	
        	// Determine if the player has run out of cards.
        	if (player.getHand().size() <= 0)
        	{
        		
        		// The session has complete.
        		this.handleSessionEnds(player);
        		
        	} else 
        	{
        	
        		// Send the response to the player.
        		this.sendResponse(response, caller);
        	
        	}
        	
    	} else 
    	{
    		
    		// Send the response to the player.
    		this.sendResponse(new GameResponse(ResponseCode.FAILED, null), caller);
    		
    	}

    }

    // Handles drawing a card.
    private void handleDrawCard(GameRequest request, ConnectionToClient caller)
    {

        // Get the player.
        Player player = this.players.get(caller);
        GameResponse response = null;

        // Set the 'safe' flag in the player to false.
        player.isSafe(false);

        // Ensure that the number of cards in the player's hand does not exceed that of the maximum number of allowed cards.
        if ((player.getHand().size() + 1) > MAX_CARDS_IN_HAND || this.deck.getSizeOfDeck() < 1)
        {

            // Set the game response to fail.
            response = new GameResponse(ResponseCode.FAILED, player);

        } else
        {

            // Get the player's hand.
            ArrayList<Card> hand = player.getHand();

            // Add the card to the player's hand.
            hand.add(deck.drawCard());

            // Set the hand to the player.
            player.setHand(hand);
            
            // Set the game response to success.
            response = new GameResponse(ResponseCode.SUCCESS, player);

        }

        // Send the response to the user.
        this.sendResponse(response, caller);

    }

    // Handles announcing Uno.
    private void handleAnnounceUno(GameRequest request, ConnectionToClient caller)
    {

        // Declare local variables.
        Player player = this.players.get(caller);
        GameResponse response = null;

        // Determine if the player has only one card.
        if (player.getHand().size() == 1)
        {

            // Set the player's safe flag to true.
            player.isSafe(true);

            // Reset the player object in the 'players' list.
            this.players.put(caller, player);

        }
        
        // Iterate over all players.
        for (ConnectionToClient client : this.getPlayerKeys())
        {

            // Get the current player.
            Player current = this.players.get(client);

            // Determine if the current player has an uno.
            if (!current.isSafe() && current.getHand().size() == 1)
            {
             
                // Get the hand of the current player.
                ArrayList<Card> hand = current.getHand();

                // Add seven cards to the player's hand.
                for (int i = 0; i < 7; ++i)
                {

                    // Ensure that the deck can add another card.
                    if (deck.getSizeOfDeck() > 0)
                    {

                        // Allocate a card to the current player's hand.
                        hand.add(deck.drawCard());

                    }

                }

                // Add the hand back to the player.
                current.setHand(hand);

                // Reset the player in the 'players' list.
                this.players.put(client, current);

                // Attempt to send the updated client information to the player.
                try
                {

                    // Send the updated player object to the client.
                    client.sendToClient(current);

                } catch (IOException exception)
                {

                    // Display the error message to the user.
                    exception.printStackTrace();

                }

            }

        }

        // Set the response value.
        response = new GameResponse(ResponseCode.SUCCESS, player);

        // Send the response to the client.
        this.sendResponse(response, caller);
        
    }

    // Handles the game ending.
    private void eventGameEnds()
    {

        System.out.println("Game has ended.");
            
    }
    
    public void handleLeaveGame(ConnectionToClient caller)
    {
    	
    	// Obtain the player from the 'players' list.
    	Player player = this.players.get(caller);
    	
    	// Remove the player from the list.
    	this.players.remove(player);
    	
    	// Deallocate all cards within the player's hand.
    	for (Card card : player.getHand())
    	{
    		
    		// Return the card to the deck.
    		this.deck.returnToDeck(card);
    		
    	}
    	
    	// Remove the user from the logged in users.
    	this.server.logout(this.server.getUsers().get(caller));
    	
    	// Determine if the number of remaining players is less then the minimum number of players.
    	if (this.players.size() < this.MIN_PLAYER_COUNT)
    	{
    		
    		// Terminate the game.
    		this.eventGameEnds();
    		
    	}
    	
    }

    /*
     * Handlers for specific cards:
     *  - handleSkipCard     :   Handles skip cards.
     *  - handleReverseCard  :   Handles reverse cards.
     *  - handleDrawTwo      :   Handles draw two cards.
     *  - handleDrawFour     :   Handles draw four cards.
     *  - handleWildCard     :   Handles wildcards.
    */

    private void handleSkipCard()
    {

    	// Seek to the player after the next player.
    	this.changePlayerTurn();
    	this.changePlayerTurn();

    }

    private void handleReverseCard()
    {

    	// Flip the value of flag 'forward'.
    	this.state.isForward(!this.state.isForward());
    	
    }

    private void handleDrawTwo()
    {
    	
    	// Seek to the next player.
    	this.changePlayerTurn();
    	
    	// Get the current player.
    	Player player = this.getCurrentPlayer();
    	
    	// Add two cards to the current player.
    	ArrayList<Card> hand = player.getHand();
    	hand.add(this.deck.drawCard());
    	hand.add(this.deck.drawCard());
    	player.setHand(hand);
    	
    	// Attempt to send the player object to the player.
    	try
    	{
    		
    		// Send the player object to the player.
    		this.getAssociatedConnection(player).sendToClient(player);
    		
    	} catch (IOException exception)
    	{
    		
    		// Display the error message.
    		exception.printStackTrace();
    		
    	}
    	
    }

    private void handleDrawFour(GameRequest request, ConnectionToClient caller)
    {
    	
    	// Declare local variables.
    	Player player = null;
    
    	// Ensure that the card color is set.
    	if (request.getCardColor() == CardColor.NONE)
    	{
    	
    		// Seek to the next player.
    		this.changePlayerTurn();
    	
    		// Get the next player.
    		player = this.getCurrentPlayer();
    	
    		// Add four cards to the current player.
    		ArrayList<Card> hand = player.getHand();
    		hand.add(this.deck.drawCard());
    		hand.add(this.deck.drawCard());
    		hand.add(this.deck.drawCard());
    		hand.add(this.deck.drawCard());
    		player.setHand(hand);
    	
    		// Set the top card in the state to null and the color to the request color.
    		this.state.setTopCard(null);
    		this.state.setTopCardColor(request.getCardColor());
    	
    		// Attempt to send the player object to the player.
    		try
    		{
    		
    			// Send the player object to the player.
    			this.getAssociatedConnection(player).sendToClient(player);
    		
    		} catch (IOException exception)
    		{
    		
    			// Display the error message.
    			exception.printStackTrace();
    		
    		}
    	
    	} else
    	{
    		
    		// Send the fail message to the player.
    		this.sendResponse(new GameResponse(ResponseCode.FAILED, null), caller);
    		
    	}
    	
    }

    private void handleWildCard(GameRequest request, ConnectionToClient caller)
    {

    	// Ensure that the card color is set.
    	if (request.getCardColor() != CardColor.NONE)
    	{
    		
    		// Set the top card to null and the top card color to the request color.
    		this.state.setTopCard(null);
    		this.state.setTopCardColor(request.getCardColor());
    		
    		
    		
    	} else
    	{
    	
    		// Send the fail message to the player.
    		this.sendResponse(new GameResponse(ResponseCode.FAILED, null), caller);
    		
    	}
    	
    }
    
    // Sum all cards from the other players and add the amount to the player's score.
	private void handleSessionEnds(Player winner)
    {
    	
    	// Declare local variables.
		int sum = 0;
		
		// Iterate over every player within the game.
		for (Player current : this.players.values())
		{
			
			// Add the sum of the cards in the current player's hand to 'sum'.
			sum += current.getSumOfCards();
			
		}
		
		// Add the value of 'sum' to the score of the winning player.
		winner.setScore(winner.getScore() + sum);
		
		// Determine if the winner of the round has exceeded the threshold.
		if (winner.getScore() > this.server.getThreshold())
		{
				 
			// Broadcast the name of the winner to all players.
			this.server.sendToAllClients(winner);
		
			// handle game ends event.
			this.eventGameEnds();
			
			return;
			
		}
    	
		// Broadcast the waiting for next session response.
		this.server.sendToAllClients(new GameResponse(ResponseCode.WAIT_FOR_NEXT_SESSION, null));
		
    }

    // Changes the current player.
    private void changePlayerTurn()
    {
    	
    	// Determine if the turn is increasing or decreasing.
    	if (this.state.isForward())
    	{
    		
    		// Increase the current index.
    		++this.currentIndex;
    		
    	} else 
    	{
    		
    		// Decrease the current index.
    		--this.currentIndex;
    		
    	}
    	
    	// Ensure that the current index is not out of range.
    	this.currentIndex = this.currentIndex % this.players.size();
    	
    }
    
    // Returns the current player object.
    private Player getCurrentPlayer()
    {
    	
    	ArrayList<Player> player_queue = new ArrayList<>(this.players.values());
    	
    	return player_queue.get(currentIndex);
    	
    }
    
    private ConnectionToClient getAssociatedConnection(Player player)
    {
    
    	// Declare local variables.
    	ConnectionToClient connection = null;
    	
    	// Iterate over the players.
    	for (ConnectionToClient conn : this.players.keySet())
    	{
    		
    		// Check if the current player is the same as the iteration
    		if (player.getPlayerName().equals(this.players.get(conn).getPlayerName()))
    		{
    			
    			// Set the connection.
    			connection = conn;
    			
    			break;
    			
    		}
    		
    	}
    	
    	return connection;
    	
    }
    
    // Utility method to send game response objects to a user. 
    private void sendResponse(GameResponse response, ConnectionToClient caller)
    {
    	
    	// Attempt to send the response to the user.
    	try
    	{
    		
    		// Send the message to the user.
    		caller.sendToClient(response);
    		
    	} catch (IOException exception)
    	{
    		
    		// Display the error message to the user.
    		exception.printStackTrace();
    		
    	}
    	
    }
    
}
