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
import Database.LoginData;
import GameLogic.Card;
import GameLogic.GameState;
import GameLogic.Player;
import ClientCommunication.Client;

public class GameSessionPanel extends JPanel {
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

        setPreferredSize(new Dimension(1000, 800));
        setLayout(new BorderLayout());
        setBackground(Color.decode("#1E2448"));

        // Top Panel with player information
        JPanel playerInformationPanel = new JPanel();
        playerInformationPanel.setLayout(new BoxLayout(playerInformationPanel, BoxLayout.X_AXIS));
        playerInformationPanel.setBackground(Color.decode("#1E2448"));
        playerInformationPanel.setBorder(DesignUtils.createEmptyBorder(10, 10, 10, 10));
        playerInformationPanel.add(playerNamesPanel);
        playerInformationPanel.add(currentPlayerLabel);

        // Active first person player with their cards
        JPanel firstPersonPlayerPanel = new JPanel();
        firstPersonPlayerPanel.setLayout(new BoxLayout(firstPersonPlayerPanel, BoxLayout.X_AXIS));
        firstPersonPlayerPanel.setBackground(Color.decode("#1E2448"));
        firstPersonPlayerPanel.setBorder(DesignUtils.createEmptyBorder(10, 10, 10, 10));

        JPanel player1Panel = createPlayerPanel("You");
        firstPersonPlayerPanel.add(Box.createHorizontalStrut(20));
        firstPersonPlayerPanel.add(player1Panel);

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
        add(playerInformationPanel, BorderLayout.NORTH);
        add(firstPersonPlayerPanel, BorderLayout.SOUTH);
        add(drawDiscardPanel, BorderLayout.CENTER);
        add(unoButton, BorderLayout.SOUTH);

        drawButton.addActionListener(e -> {
            gameSessionControl.drawCard();
        });

        discardButton.addActionListener(e -> {
            // Get the selected card from the UI
            Card selectedCard = getSelectedCardFromUI();
            gameSessionControl.playCard(selectedCard);
        });

        unoButton.addActionListener(e -> {
            gameSessionControl.announceUno();
        });

    }

    private JPanel createPlayerPanel(String playerName) {
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.setBackground(Color.decode("#1E2448"));
        playerPanel.setBorder(DesignUtils.createEmptyBorder(10, 10, 10, 10));

        JLabel playerLabel = DesignUtils.createLabel(playerName);
        playerHandPanel = new PlayerHandPanel(); // Pass the LoginData to the PlayerHandPanel constructor
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