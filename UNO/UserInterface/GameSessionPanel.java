package UserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ClientCommunication.GameSessionControl;
import GameLogic.Card;
import GameLogic.GameState;
import GameLogic.Player;
import ClientCommunication.Client;

public class GameSessionPanel extends JPanel {
<<<<<<< Updated upstream
	private GameSessionControl gameSessionControl;
    private JLabel player1Label;
    private JLabel player2Label;
    private JLabel player3Label;
    private JLabel player4Label;
    private JLabel player5Label;
    private JLabel player6Label;
    private JButton drawButton;
    private JButton discardButton;
    private JButton unoButton;

    public GameSessionPanel(Driver driver) {
        Client client = driver.getClient();
        gameSessionControl = new GameSessionControl(client);
        // TODO: Instead of showing all player panels. Only show one player point of view with their cards, and then a list of player names with their card amount
        
=======
    private GameSessionControl gameSessionControl;
    private JButton drawButton;
    private JButton discardButton;
    private JButton unoButton;
    private JPanel playerNamesPanel;
    private PlayerHandPanel playerHandPanel;
    private JTable scoreTable;
    private JLabel topCardLabel;
    private JLabel currentPlayerLabel;
    
    public GameSessionPanel(Driver driver) {
        Client client = driver.getClient();
        gameSessionControl = new GameSessionControl(client);
        // TODO: playerNamesAndHandCount Only show one player point of view with their cards, and then a list of player names with their card amount

>>>>>>> Stashed changes
        setPreferredSize(new Dimension(1000, 800));
        setLayout(new BorderLayout());
        setBackground(Color.decode("#1E2448"));

        // Top Player Panels
        JPanel topPlayerPanel = new JPanel();
        topPlayerPanel.setLayout(new BoxLayout(topPlayerPanel, BoxLayout.X_AXIS));
        topPlayerPanel.setBackground(Color.decode("#1E2448"));
        topPlayerPanel.setBorder(DesignUtils.createEmptyBorder(10, 10, 10, 10));
<<<<<<< Updated upstream

        JPanel player1Panel = createPlayerPanel("Player 1");
        JPanel player2Panel = createPlayerPanel("Player 2");
        topPlayerPanel.add(player1Panel);
        topPlayerPanel.add(Box.createHorizontalStrut(20));
        topPlayerPanel.add(player2Panel);

        // Left Player Panel
        JPanel leftPlayerPanel = new JPanel();
        leftPlayerPanel.setLayout(new BoxLayout(leftPlayerPanel, BoxLayout.Y_AXIS));
        leftPlayerPanel.setBackground(Color.decode("#1E2448"));
        leftPlayerPanel.setBorder(DesignUtils.createEmptyBorder(10, 10, 10, 10));

        JPanel player3Panel = createPlayerPanel("Player 3");
        leftPlayerPanel.add(player3Panel);

        // Right Player Panel
        JPanel rightPlayerPanel = new JPanel();
        rightPlayerPanel.setLayout(new BoxLayout(rightPlayerPanel, BoxLayout.Y_AXIS));
        rightPlayerPanel.setBackground(Color.decode("#1E2448"));
        rightPlayerPanel.setBorder(DesignUtils.createEmptyBorder(10, 10, 10, 10));

        JPanel player4Panel = createPlayerPanel("Player 4");
        rightPlayerPanel.add(player4Panel);
=======
        topPlayerPanel.add(playerNamesPanel);
        topPlayerPanel.add(currentPlayerLabel);
>>>>>>> Stashed changes

        // Bottom Player Panels
        JPanel bottomPlayerPanel = new JPanel();
        bottomPlayerPanel.setLayout(new BoxLayout(bottomPlayerPanel, BoxLayout.X_AXIS));
        bottomPlayerPanel.setBackground(Color.decode("#1E2448"));
        bottomPlayerPanel.setBorder(DesignUtils.createEmptyBorder(10, 10, 10, 10));

        JPanel player5Panel = createPlayerPanel("Player 5");
        JPanel player6Panel = createPlayerPanel("You");
        bottomPlayerPanel.add(player5Panel);
        bottomPlayerPanel.add(Box.createHorizontalStrut(20));
        bottomPlayerPanel.add(player6Panel);

        // Draw Pile and Discard Pile
        JPanel drawDiscardPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        drawDiscardPanel.setBackground(Color.decode("#1E2448"));
        JPanel drawPanel = DesignUtils.createCardPanel();
        drawButton = DesignUtils.createSpecialButton("Draw");
        JPanel discardPanel = DesignUtils.createCardPanel();
        discardPanel.add(topCardLabel);
        discardButton = DesignUtils.createSpecialButton("Discard");
        drawDiscardPanel.add(drawPanel);
        drawDiscardPanel.add(drawButton);
        drawDiscardPanel.add(discardPanel);
        drawDiscardPanel.add(discardButton);

        // Uno Button
        unoButton = DesignUtils.createSpecialButton("Uno");

        // Add components to the main panel
        add(topPlayerPanel, BorderLayout.NORTH);
        add(leftPlayerPanel, BorderLayout.WEST);
        add(rightPlayerPanel, BorderLayout.EAST);
        add(bottomPlayerPanel, BorderLayout.SOUTH);
        add(drawDiscardPanel, BorderLayout.CENTER);
        add(unoButton, BorderLayout.SOUTH);

        drawButton.addActionListener(e -> {
<<<<<<< Updated upstream
            Card drawnCard = gameSessionControl.drawCard(); // Simulate drawing a card
            Player currentPlayer = gameSessionControl.getCurrentPlayer();
            currentPlayer.drawCard(drawnCard); // Add the drawn card to the current player's hand
            // Update the UI to reflect the changes
=======
            gameSessionControl.drawCard();
>>>>>>> Stashed changes
        });

        discardButton.addActionListener(e -> {
<<<<<<< Updated upstream
            Player currentPlayer = gameSessionControl.getCurrentPlayer();
            List<Card> hand = currentPlayer.getHand(); // Get the current player's hand
            // Here you would implement a way for the player to select a card from their hand
            // For demonstration purposes, let's assume the first card in hand is selected
            if (!hand.isEmpty()) {
                Card selectedCard = hand.get(0); // Select the first card in hand (you may implement a selection mechanism)
                gameSessionControl.playCard(currentPlayer, selectedCard); // Discard the selected card
                // Update the UI to reflect the changes (optional)
            } else {
                // Handle error: No cards in hand to discard
                System.out.println("Error: No cards in hand to discard for " + currentPlayer.getName());
            }
=======
            // Get the selected card from the UI
            Card selectedCard = getSelectedCardFromUI();
            gameSessionControl.playCard(selectedCard);
>>>>>>> Stashed changes
        });

        unoButton.addActionListener(e -> {
<<<<<<< Updated upstream
            Player currentPlayer = gameSessionControl.getCurrentPlayer();
            if (currentPlayer.getHand().size() == 1) {
                gameSessionControl.callUno(currentPlayer); // Call UNO
                // Handle the UNO call (e.g., update UI, apply penalties if needed)
            } else {
                // Handle error: Player cannot call UNO because they have more than one card in hand
                System.out.println(currentPlayer.getName() + " cannot call UNO because they have more than one card in hand.");
            }
=======
            gameSessionControl.announceUno();
>>>>>>> Stashed changes
        });

    }

    private JPanel createPlayerPanel(String playerName) {
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.setBackground(Color.decode("#1E2448"));
        playerPanel.setBorder(DesignUtils.createEmptyBorder(10, 10, 10, 10));

        JLabel playerLabel = DesignUtils.createLabel(playerName);
        playerHandPanel = new PlayerHandPanel();
        playerPanel.add(playerLabel);
        playerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        playerPanel.add(playerHandPanel);

        return playerPanel;
    }

    public void updateTopCard(Card topCard) {
        topCardLabel = new JLabel(new ImageIcon(topCard.getCardURL()));
    }

    public void updateScores(Map<String, Integer> scores) {
        DefaultTableModel model = (DefaultTableModel) scoreTable.getModel();
        model.setRowCount(0); // Clear existing data

        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            model.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }
    }

    public void updateCurrentPlayer(String currentPlayerName) {
        currentPlayerLabel = new JLabel("Current Player: " + currentPlayerName);
    }

    public void updatePlayerHand(Player player) {
        playerHandPanel.updateHand(player.getHand());
    }

    public void updateUnoButton(List<Player> players) {
        boolean onePlayerHasOneCard = false;
        for (Player player : players) {
            if (player.getHand().size() == 1) {
                onePlayerHasOneCard = true;
                break;
            }
        }
        unoButton.setEnabled(onePlayerHasOneCard);
    }

    public void updatePlayerNames(List<Player> players) {
        playerNamesPanel.removeAll();
        for (Player player : players) {
            JLabel playerNameLabel = new JLabel(player.getPlayerName() + " (" + player.getHand().size() + " cards)");
            playerNameLabel.setForeground(Color.WHITE);
            playerNamesPanel.add(playerNameLabel);
        }
        revalidate();
        repaint();
    }

    private Card getSelectedCardFromUI() {
        return playerHandPanel.getSelectedCard(); // Return the selected card
    }
}