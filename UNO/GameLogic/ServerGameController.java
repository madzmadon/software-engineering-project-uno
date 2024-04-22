package GameLogic;

import Database.LoginData;
import ServerCommunication.Server;
import ocsf.server.ConnectionToClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerGameController {

    private Server server;

    private HashMap<ConnectionToClient, Player> players;
    private boolean gameInProgress;
    private GameState state;
    private Deck deck;
    
    public ServerGameController(Server server)
    {

        // Initialize data fields.
        this.server = server;
        this.players = new HashMap<>();
        this.gameInProgress = false;
        this.deck = new Deck();

    }

    // Adds a player to the game.
    public void setPlayers(HashMap<ConnectionToClient, LoginData> players)
    {

        // Declare local variables.
        Player player = new Player();

        // Iterate over every associated pair in the argument 'players'.
        for (ConnectionToClient key : players.keySet())
        {

            // Create the player object.
            player = new Player();

            // Add the username as the player name.
            player.setPlayerName(players.get(key).getUsername());

            // Add the current player to the 'players' map.
            this.players.put(key, player);

        }

    }

    public void handleGameRequest(GameRequest request, ConnectionToClient caller)
    {

        // Determine the game request.
        switch (request.getRequest())
        {

            case START_GAME -> this.handleGameStart(request, caller);
            case START_SESSION -> this.handleStartSession();
            case PLAY_CARD -> this.handlePlayCard();
            case DRAW_CARD -> this.handleDrawCard();
            case ANNOUNCE_UNO -> this.handleAnnounceUno();

        }

    }

    // Handles the host starting a game.
    public void handleGameStart(GameRequest request, ConnectionToClient caller) {

        // Reset the game state.
        this.state = new GameState(request.getThreshold());

        // Ensure that enough players are present.
        if (this.server.getUsers().size() < 2)
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

        // Add all logged in players.
        for (ConnectionToClient key : this.server.getUsers().keySet())
        {

            // Create the player.
            Player player = new Player();

            // Set the player's name.
            player.setPlayerName(this.server.getUsers().get(key).getUsername());

            // Add the player to the 'players' map.
            this.players.put(key, player);

        }

        // Set the variable 'gameInProgress' to true.
        this.gameInProgress = true;

        // DEBUG: Displays a message indicating that the game has started.
        System.out.println("State> Game Started");

        // Start the game session.
        this.handleStartSession();

    }

    // Handles starting a session.
    private void handleStartSession()
    {

        // Reset the deck of cards.
        this.deck.reset();

        // DEBUG: Displays a message indicating that the game session has started.
        System.out.println("State> Session Started");

        // Iterate over the players and initialize their hands.
        for (ConnectionToClient connection : this.players.keySet())
        {

            // Obtain the player from the 'players' map.
            Player player = this.players.get(connection);

            // Create the hand of the player.
            ArrayList<Card> hand = new ArrayList<>();

            // Initialize the hand of the player.
            hand.add(deck.drawCard());
            hand.add(deck.drawCard());
            hand.add(deck.drawCard());
            hand.add(deck.drawCard());
            hand.add(deck.drawCard());
            hand.add(deck.drawCard());
            hand.add(deck.drawCard());

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

    }

    // Handles playing a card.
    private void handlePlayCard()
    {

        System.out.println("Play card.");

    }

    // Handles drawing a card.
    private void handleDrawCard()
    {

        System.out.println("Draw card.");

    }

    // Handles announcing Uno.
    private void handleAnnounceUno()
    {

        System.out.println("Announce Uno.");

    }

}