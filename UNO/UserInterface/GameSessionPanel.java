package UserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ClientCommunication.GameSessionControl;
import GameLogic.Player;
import GameLogic.Card;
import ClientCommunication.Client;

public class GameSessionPanel extends JPanel {
	private GameSessionControl gameSessionControl;
    private JLabel player1Label;
    private JButton drawButton;
    private JButton discardButton;
    private JButton unoButton;
    private JLabel playerNamesAndHandCount;

    public GameSessionPanel(Driver driver) {
        Client client = driver.getClient();
        gameSessionControl = new GameSessionControl(client);
<<<<<<< HEAD
        // TODO: playerNamesAndHandCount Only show one player point of view with their cards, and then a list of player names with their card amount
=======
        // TODO: Instead of showing all player panels. Only show one player point of view with their cards, and then a list of player names with their card amount
>>>>>>> 09c0f88e80612be86b6419b71c0e6d4818506f31
        
        setPreferredSize(new Dimension(1000, 800));
        setLayout(new BorderLayout());
        setBackground(Color.decode("#1E2448"));

        // Top Player Panels
        JPanel topPlayerPanel = new JPanel();
        topPlayerPanel.setLayout(new BoxLayout(topPlayerPanel, BoxLayout.X_AXIS));
        topPlayerPanel.setBackground(Color.decode("#1E2448"));
        topPlayerPanel.setBorder(DesignUtils.createEmptyBorder(10, 10, 10, 10));
        topPlayerPanel.add(playerNamesAndHandCount);

        // Bottom Player Panels
        JPanel bottomPlayerPanel = new JPanel();
        bottomPlayerPanel.setLayout(new BoxLayout(bottomPlayerPanel, BoxLayout.X_AXIS));
        bottomPlayerPanel.setBackground(Color.decode("#1E2448"));
        bottomPlayerPanel.setBorder(DesignUtils.createEmptyBorder(10, 10, 10, 10));

        JPanel player1Panel = createPlayerPanel("You");
        bottomPlayerPanel.add(Box.createHorizontalStrut(20));
        bottomPlayerPanel.add(player1Panel);

        // Draw Pile and Discard Pile
        JPanel drawDiscardPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        drawDiscardPanel.setBackground(Color.decode("#1E2448"));
        JPanel drawPanel = DesignUtils.createCardPanel();
        drawButton = DesignUtils.createSpecialButton("Draw");
        JPanel discardPanel = DesignUtils.createCardPanel();
        discardButton = DesignUtils.createSpecialButton("Discard");
        drawDiscardPanel.add(drawPanel);
        drawDiscardPanel.add(drawButton);
        drawDiscardPanel.add(discardPanel);
        drawDiscardPanel.add(discardButton);

        // Uno Button
        JPanel unoPanel = new JPanel();
        unoPanel.setLayout(new BoxLayout(unoPanel, BoxLayout.Y_AXIS));
        unoPanel.setBackground(Color.decode("#1E2448"));
        unoPanel.setBorder(DesignUtils.createEmptyBorder(10, 10, 10, 10));
        unoButton = DesignUtils.createSpecialButton("Uno");
        unoPanel.add(unoButton);

        // Add components to the main panel
        add(topPlayerPanel, BorderLayout.NORTH);
        add(bottomPlayerPanel, BorderLayout.SOUTH);
        add(drawDiscardPanel, BorderLayout.CENTER);
        add(unoPanel, BorderLayout.SOUTH);
    

        drawButton.addActionListener(e -> {
            gameSessionControl.drawCard(); // Simulate drawing a card
            // Update the UI to reflect the changes
        });


        discardButton.addActionListener(e -> {
        		gameSessionControl.playCard(); // Discard the selected card
                // Update the UI to reflect the changes
        });


        unoButton.addActionListener(e -> {
        	gameSessionControl.announceUno(); // Call UNO
        });

    }
    private JPanel createPlayerPanel(String playerName) {
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.setBackground(Color.decode("#1E2448"));
        playerPanel.setBorder(DesignUtils.createEmptyBorder(10, 10, 10, 10));

        JLabel playerLabel = DesignUtils.createLabel(playerName);
        JPanel playerCardsPanel = DesignUtils.createCardPanel();

        playerPanel.add(playerLabel);
        playerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        playerPanel.add(playerCardsPanel);

        return playerPanel;
    }
}