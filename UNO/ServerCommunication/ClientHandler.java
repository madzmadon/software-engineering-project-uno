import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import ClientCommunication.GameSessionControl;
import GameLogic.GameSessionData;
import GameLogic.Card; 


public class ClientHandler<GameSessionControl, String, GameSessionData> implements Runnable {
    private Socket clientSocket;
    private BufferedReader input;
    private PrintWriter output;
    private Server server;
    // You will need an instance of playerName and gameSession
    private String playerName; // Should be declared as volatile if accessed by multiple threads
    private GameSessionControl gameSessionControl; // Should be declared as volatile if accessed by multiple threads
    private GameSessionData gameSessionData; // Should be declared as volatile if accessed by multiple threads

    public ClientHandler(Socket socket, UnoServerControl unoServerControl) {
        this.clientSocket = socket;
        this.server = unoServerControl;
        try {
            this.input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.output = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Error setting up client handler: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            String clientMessage;
            while ((clientMessage = input.readLine()) != null) {
                System.out.println("Received from client: " + clientMessage);

                String response = handleClientMessage(clientMessage);

                // Send response back to client
                output.println(response);
            }
        } catch (IOException e) {
            System.out.println("Error handling client message: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Error closing client socket: " + e.getMessage());
            }
        }
    }

    private String handleClientMessage(String message) {
        // Split the message into parts
        String[] parts = message.split(" ");
        String command = parts[0];

        switch (command) {
            case "JOIN":
                // Extract the player name from the message
                playerName = parts[1]; 
                // Call the joinGame() 
                joinGame();
                return "JOINED";
            case "PLAY":
                // Extract the card index from the message
                int cardIndex = Integer.parseInt(parts[1]);
                // Call the playCard() method to handle the player playing a card
                playCard(cardIndex);
                return "PLAYED";
            case "DRAW":
                // Call the drawCard() method to handle the player drawing a card
                drawCard();
                return "DRAWN";
            case "UNO":
                // Call the callUno() method to handle the player calling UNO
                callUno();
                return "UNO_CALLED";
            ault:
            	private String handleClientMessage(String message) {
                // Split the message into parts
                String[] parts = message.split(" ");
                String command = parts[0];

                switch (command) {
                    case "JOIN":
                        
                        playerName = parts[1];
                       
                        joinGame();
                        return "JOINED";
                    case "PLAY":
            
                        int cardIndex = Integer.parseInt(parts[1]);
                   
                        playCard(cardIndex);
                        return "PLAYED";
                    case "DRAW":
                        
                        drawCard();
                        return "DRAWN";
                    case "UNO":
                        
                        callUno();
                        return "UNO_CALLED";
                    //I need to add more commands
                    default:
                        return "INVALID_COMMAND";
                }
            }


    private void joinGame() {
        // Find an available game session or create a new one
        gameSessionControl = server.getAvailableSession();
        gameSessionControl.addPlayer(this); 
        gameSessionData = gameSessionControl.getGameSessionData(); 
        output.println("JOINED " + gameSessionData.getId());
    }

    private void playCard(int cardIndex) {
        if (gameSessionControl != null && gameSessionControl.getCurrentPlayer() == this) {
            Card card = gameSessionData.getPlayerHand(this).get(cardIndex); // Make sure this method is thread-safe
            if (gameSessionControl.isValidMove(card)) {
                gameSessionControl.playCard(this, card);
                output.println("PLAYED " + card);
                gameSessionControl.broadcastMessage("PLAYED " + playerName + " " + card); 
                if (gameSessionControl.isGameOver()) {
                    gameSessionControl.broadcastMessage("GAME_OVER " + playerName); 
                    server.removeSession(gameSessionControl); 
                } else {
                    gameSessionControl.nextTurn(); 
                }
            } else {
                output.println("INVALID_MOVE");
            }
        } else {
            output.println("NOT_YOUR_TURN");
        }
    }

    private void drawCard() {
        if (gameSessionControl != null && gameSessionControl.getCurrentPlayer() == this) {
            Card card = gameSessionControl.drawCard(); 
            output.println("DRAWN " + card);
            gameSessionControl.broadcastMessage("DRAWN " + playerName); 
        } else {
            output.println("NOT_YOUR_TURN");
        }
    }

    private void callUno() {
        if (gameSessionControl != null && gameSessionControl.getCurrentPlayer() == this) {
            gameSessionControl.callUno(this); 
            output.println("UNO_CALLED");
            gameSessionControl.broadcastMessage("UNO_CALLED " + playerName); 
        } else {
            output.println("NOT_YOUR_TURN");
        }
    }
}
