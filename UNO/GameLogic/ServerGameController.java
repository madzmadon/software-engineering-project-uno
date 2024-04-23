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
    private GameState state;
    private Deck deck;

    private final int MIN_PLAYER_COUNT = 2;
    private final int MAX_CARDS_IN_HAND = 25;

    public ServerGameController(Server server)
    {

        // Initialize data fields.
        this.server = server;
        this.players = new HashMap<>();
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

        // Determine the game request.
        switch (request.getRequest())
        {

            case JOIN_GAME -> this.handleJoinGame(caller);
            case START_GAME -> this.handleGameStart(request, caller);
            case START_SESSION -> this.handleSessionStart();
            case PLAY_CARD -> this.handlePlayCard(request, caller);
            case DRAW_CARD -> this.handleDrawCard(request, caller);
            case ANNOUNCE_UNO -> this.handleAnnounceUno(request, caller);

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

    // Handles the player starting a game.
    public void handleGameStart(GameRequest request, ConnectionToClient caller) {

        // Reset the game state.
        this.state = new GameState(this.server.getThreshold());

        // Ensure that enough players are present and that the calling user is verified.
        if (this.server.getUsers().size() < 2 || !this.verifyPlayer(caller))
        {

            // Attempt to send a message to the user.
            try {

                // Send message to the user indicating that the number of users is too few.
                caller.sendToClient(new GameResponse(ResponseCode.FAILED, null));

            } catch (IOException exception)
            {

                // Display the error to the user.
                exception.printStackTrace();

                // Terminate the program.
                System.exit(-1);

            }

            return;

        }

        // Set the variable 'gameInProgress' to true.
        this.gameInProgress = true;

        // DEBUG: Displays a message indicating that the game has started.
        System.out.println("State> Game Started");

        // Start the game session.
        this.handleSessionStart();

    }

    // Handles starting a session.
    private void handleSessionStart()
    {

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

                // Terminate the program.
                System.exit(-1);

            }

        }

        // DEBUG: Displays a message indicating that the game session has started.
        System.out.println("State> Session Started");

    }

    // Handles playing a card.
    private void handlePlayCard(GameRequest request, ConnectionToClient caller)
    {

        // Obtain the card from the request.
        Card card = request.getCard();

        // Determine the type of card being played.
        switch (card.getFaceValue())
        {

            case SKIP -> handleSkipCard();
            case REVERSE -> handleReverseCard();
            case PLUS_TWO -> handleDrawTwo();
            case PLUS_FOUR -> handleDrawFour();
            case WILDCARD -> handleWildCard();
            default -> handleNumberCard(request, caller);

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

        // Attempt to send the response to the user.
        try
        {

            // Send the response to the player.
            caller.sendToClient(response);

        } catch (IOException exception)
        {

            // Display the error message to the user.
            exception.printStackTrace();

            // Terminate the program.
            System.exit(-1);

        }

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

        // Attempt to send the game response to the player.
        try
        {

            caller.sendToClient(response);

        } catch (IOException exception)
        {

            // Display the error message to the user.
            exception.printStackTrace();

            // Terminate the program.
            System.exit(-1);

        }

    }

    // Handles the game ending.
    private void eventGameEnds()
    {

        System.out.println("Game has ended.");
            
    }

    /*
     * Handlers for specific cards:
     *  - handleNumberCard   :   Handles basic number cards.
     *  - handleSkipCard     :   Handles skip cards.
     *  - handleReverseCard  :   Handles reverse cards.
     *  - handleDrawTwo      :   Handles draw two cards.
     *  - handleDrawFour     :   Handles draw four cards.
     *  - handleWildCard     :   Handles wildcards.
    */

    private void handleNumberCard(GameRequest request, ConnectionToClient caller)
    {

        // Declare local variables.
        GameResponse response = null;

        // Ensure that the player has the card in their hand and that the card matches the top card.
        if (this.state.getTopCard().cardMatches(request.getCard()))
        {

            // Send the top card back to the deck.
            this.deck.returnToDeck(this.state.getTopCard());

            // Set the top card to that of the player's.
            this.state.setTopCard(request.getCard());

        } else
        {

            // Set the response to fail.
            response = new GameResponse(ResponseCode.FAILED, this.players.get(caller));

        }

        // Attempt to send the response to the user.
        try
        {

            // Send the response to the player.
            caller.sendToClient(response);

        } catch (IOException exception)
        {

            // Display the error message to the user.
            exception.printStackTrace();

        }

    }

    private void handleSkipCard()
    {



    }

    private void handleReverseCard()
    {

    }

    private void handleDrawTwo()
    {

    }

    private void handleDrawFour()
    {

    }

    private void handleWildCard()
    {

    }

}
