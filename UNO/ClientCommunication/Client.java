package ClientCommunication;

import Database.LoginData;
import GameLogic.*;
import ServerCommunication.AccountResponse;
import UserInterface.Driver;
import UserInterface.GameLobbyPanel;
import UserInterface.GameManagerPanel;
import UserInterface.GameSessionPanel;
import UserInterface.StartUpPanel;
import ocsf.client.AbstractClient;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Client extends AbstractClient {

    private final static int PORT = 12345;
    private final static String ADDRESS = "localhost";
    private Driver driver;
	public boolean isLoginResponse;
    
    public Client(Driver driver) {
        super(ADDRESS, PORT);
        this.driver = driver;
    }
    
    @Override
    protected void handleMessageFromServer(Object o) {
        // Determine the type being returned from the server.
    	if (o instanceof AccountResponse) {
    	    AccountResponse response = (AccountResponse) o;
    	    handleAccountResponse(response); 
    	} else if (o instanceof GameResponse) {
    		GameResponse response = (GameResponse) o;
    		handleGameResponse(response);
        } else if (o instanceof GameState) {
            GameState gameState = (GameState) o;
            handleGameStateUpdate(gameState);
            System.out.println("Server responded with the game state");
        } else if (o instanceof Player) {
        	Player player = (Player) o;
            handlePlayerUpdate(player);
            System.out.println("Player object received.");
        } else if (o instanceof String) {
        	String response = (String) o;
        	handleWinnerAnnouncement(response);
            System.out.println("winner received.");
        } else if (o instanceof List<?>) {
            @SuppressWarnings("unchecked")
			List<String> lobbyPlayers = (List<String>) o;
            updateLobbyPlayers(lobbyPlayers);
        }
    }

    @Override
    protected void connectionEstablished() {
        super.connectionEstablished();
//
//        // Attempt to send the game request information to the server.
//        try {
//            // Send the request to the server.
//            sendToServer(data);
//        } catch (IOException exception) {
//            // Display the exception information.
//            exception.printStackTrace();
//        }
    }

    public void sendRequest(Object request) throws IOException {
        sendToServer(request);
    }

    public Object receiveGameState() {
        // Simulating receiving a game state update from the server
        return new Object();
    }
    
    private void handleAccountResponse(AccountResponse response) {
        String status;
        System.out.println("Account Response: " + response.name());
        switch (response) {
            case SUCCESS:
                if (isLoginResponse) {
                    status = "Login successful.";
                    driver.showPanel(new GameManagerPanel(driver));
                } else {
                    status = "Account creation successful.";
                    driver.showPanel(new StartUpPanel(driver));
                }
                break;
            case ACCOUNT_NOT_EXISTENT:
                status = "This account does not exist. Please create an account.";
                break;
            case INVALID_CREDENTIALS:
                status = "Please verify your passwords match and all fields have values.";
                break;
            case ALREADY_EXISTS:
                status = "This username already exists. Please log in or use a different username.";
                driver.showPanel(new StartUpPanel(driver));
                break;
            default:
                status = "Unknown account response.";
                break;
        }
        driver.sendStatusToPanel(status);
    }
    
    private void handleGameResponse(GameResponse response) {
        if (response.getResponse() == ResponseCode.SUCCESS) {
            // Handle successful game start/join
            System.out.println("Game started/joined successfully.");

            // Determine the current panel and open the respective panel on success
            JPanel currentPanel = driver.getCurrentPanel();
            if (currentPanel instanceof StartUpPanel) {
                driver.showPanel(new GameManagerPanel(driver));
            } else if (currentPanel instanceof GameManagerPanel) {
                driver.showPanel(new GameLobbyPanel(driver));
            } else if (currentPanel instanceof GameLobbyPanel) {
                driver.showPanel(new GameSessionPanel(driver));
            } else if (currentPanel instanceof GameSessionPanel) {
                driver.showPanel(new GameLobbyPanel(driver));
            }
            
        } else if (response.getResponse() == ResponseCode.FAILED) {
            // Handle failed game start/join
            System.out.println("Failed to start/join game.");
            JOptionPane.showMessageDialog(null, "Failed to start/join game.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void handleGameStateUpdate(GameState gameState) {
        GameSessionPanel gameSessionPanel = (GameSessionPanel) driver.getCurrentPanel();

        // Update the top card
        Card topCard = gameState.getTopCard();
        gameSessionPanel.updateTopCard(topCard);

        // Update the scores
        Map<String, Integer> scores = gameState.getScores();
        gameSessionPanel.updateScores(scores);

        // Update the current player
        String currentPlayerName = gameState.getCurrentPlayerName();
        gameSessionPanel.updateCurrentPlayer(currentPlayerName);

    }
    
    private void handlePlayerUpdate(Player player) {
        GameSessionPanel gameSessionPanel = (GameSessionPanel) driver.getCurrentPanel();
        gameSessionPanel.updatePlayerHand(player);
    }

    private void handleWinnerAnnouncement(String winner) {
        // Create a JFrame to hold the custom dialog
        JFrame frame = new JFrame("Game Over");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JButton
        JButton button = new JButton("OK");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                driver.showPanel(new GameManagerPanel(driver));
            }
        });

        // Create a JPanel to hold the button
        JPanel panel = new JPanel();
        panel.add(button);

        // Create a custom dialog
        JDialog dialog = new JDialog(frame, "Game Over", true);
        dialog.setLayout(new BorderLayout());
        dialog.add(new JLabel("The winner is: " + winner), BorderLayout.CENTER);
        dialog.add(panel, BorderLayout.SOUTH);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }
    
    public boolean receiveLoginResponse() {
        // This should be processed dynamically with actual server responses
        return true;
    }

    public boolean receiveAccountCreationResponse() {
        // This should be processed dynamically with actual server responses
        return true; 
    }

    public Object receiveLobbyStatus() {
        // This should be updated dynamically from the server messages
        return "Lobby status updated";
    }
    
    private void updateLobbyPlayers(List<String> players) {
        GameLobbyPanel gameLobbyPanel = (GameLobbyPanel) driver.getCurrentPanel();
        gameLobbyPanel.updateNamesAndScores(players);
    }
}