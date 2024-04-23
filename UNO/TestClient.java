import java.io.IOException;
import java.util.Scanner;

import Database.LoginData;
import GameLogic.Card;
import GameLogic.GameRequest;
import GameLogic.GameResponse;
import GameLogic.GameState;
import GameLogic.Player;
import GameLogic.RequestCode;
import ServerCommunication.AccountResponse;
import ocsf.client.AbstractClient;

public class TestClient extends AbstractClient {
	
	private final static int PORT = 12345;
	public static Scanner in;
	private Player player;
	
	public TestClient(String host)
	{
		super(host, PORT);
	}

	@Override
	protected void handleMessageFromServer(Object o) {

		// Declare local variables.
		
		// Determine the type of object.
		if (o instanceof AccountResponse)
		{
			
			// Convert the object to an AccountResponse object.
			AccountResponse response = (AccountResponse)o;
			
			// Display a message indicating the result of the account login.
			System.out.println("Account Login: " + response.name());
			
		} else if (o instanceof Player)
		{
			
			// Convert the object to an instance of 'Player'.
			this.player = (Player)o;
			
			// Display the player name.
			System.out.println("Player Name: " + this.player.getPlayerName());
			
			// Display the number of cards within their hand.
			System.out.println("Size of Hand: " + player.getHand().size());
			
			// Display the cards within the player's hand.
			for (Card card : player.getHand())
			{
				
				// Display the URL of the current card.
				System.out.println(card.getCardURL());
				
			}
			
		} else if (o instanceof GameResponse)
		{
			
			// Convert the object to an instance of 'GameResponse'.
			GameResponse response = (GameResponse)o;
			
			// Display the game response code.
			System.out.println("Response: " + response.getResponse().name());
			
		} else if (o instanceof GameState)
		{
			
			
			
		}
		
		//Run the UI.
		this.UI();
		
	}
	
	@Override
	public void connectionEstablished()
	{
		
		// DEBUG: Display a message indicating that the client has connected to the server.
		System.out.println("Client has connected to server.");
		
	}
	
	@Override
	public void connectionClosed()
	{
		
		// DEBUG: Display a message indicating that the user has disconnected from the server.
		System.out.println("Client has disconnected from the server.");
		
	}
	
	public void UI()
	{
		
		// Declare local variables.
		String cmd = "";
		GameRequest request = null;
		Card card = null;
		
		// Prompt the user for a command.
		System.out.print("\n> ");
		cmd = in.nextLine();
		
		// Convert the command to all upper case.
		cmd = cmd.toUpperCase();
		
		// Determine the command entered by the user.
		if (cmd.equals("JOIN GAME"))
		{
		
			// Set the request to join game.
			request = new GameRequest(RequestCode.JOIN_GAME);
			
		} else if (cmd.equals("START GAME"))
		{
			
			// Set the request to start the game.
			request = new GameRequest(RequestCode.START_GAME);
			
		} else if (cmd.equals("START SESSION"))
		{
			
			// Set the request to start the session.
			request = new GameRequest(RequestCode.START_SESSION);
			
		} else if (cmd.startsWith("PLAY CARD"))
		{
			
			// Prompt the user for the card index.
			System.out.print("Enter the card index: ");
			card = this.player.getHand().get(in.nextInt());
			
			// Set the request to play a card.
			request = new GameRequest(RequestCode.PLAY_CARD);
			request.setCard(card);
			
		} else if (cmd.equals("DRAW"))
		{
			
			// Set the request to draw a card.
			request = new GameRequest(RequestCode.DRAW_CARD);
			
		}
		
		// Attempt to send the request to the server.
		try
		{
			
			// Send the request to the server.
			this.sendToServer(request);
			
		} catch (IOException exception)
		{
			
			// Display the error message to the user.
			exception.printStackTrace();
			
		}
		
	}
	
	public static void main(String[] args)
	{
		
		// Declare local variables.
		TestClient client;
		String host = "";
		String username = "";
		String password = "";
		LoginData data = null;
		
		// Ensure that the user entered a host.
		if (args.length < 1)
		{
			
			// Display a message indicating that a command line argument is required.
			System.out.println("Missing argument - Requires the IP address of the server.");
			
			return;
			
		}
		
		// Obtain the IP address from the command line argument.
		host = args[0];
		
		// Create the client.
		client = new TestClient(host);
		
		// Set up the scanner.
		client.in = new Scanner(System.in);
		
		// Prompt the user for their username.
		System.out.print("Username: ");
		username = in.nextLine();
		
		// Prompt the user for their password.
		System.out.print("Password: ");
		password = in.nextLine();
		
		// Create the login data.
		data = new LoginData(username, password);
		
		// Start listening to the server.
		try
		{
			
			// Connect the client to the server.
			client.openConnection();
			
			// Attempt to login.
			client.sendToServer(data);
			
		} catch (IOException exception)
		{
			
			// Display the error message to the user.
			exception.printStackTrace();
			
		}
		
	}
	
}
