package UserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import ClientCommunication.GameLobbyControl;
import ClientCommunication.Client;

import javax.swing.*;

public class GameLobbyPanel extends JPanel {

    private GameLobbyControl gameLobbyControl;
    private JLabel logoLabel;
    private JButton startGameButton;
    private JList<String> namesAndScoresJList;
    private DefaultListModel<String> listModel;
    private Driver driver;

    public GameLobbyPanel(Driver driver) {
        this.driver = driver;
        Client client = driver.getClient();
        gameLobbyControl = new GameLobbyControl(client);

        // Set preferred size to 1000x800
        setPreferredSize(new Dimension(1000, 800));

        // Set BorderLayout for the GameManagerPanel
        setLayout(new BorderLayout());

        // Load the UNO logo
        ImageIcon icon = DesignUtils.loadUnoLogo(800, 200);

        // Create a JLabel with the resized image as the title
        logoLabel = new JLabel(icon);
        logoLabel.setBackground(Color.decode("#1E2448")); // Set the background color of the JLabel
        logoLabel.setOpaque(true); // Make sure the background color is visible

        // Create a JPanel for the game lobby
        JPanel gameLobbyPanel = new JPanel();
        gameLobbyPanel.setLayout(new BoxLayout(gameLobbyPanel, BoxLayout.Y_AXIS));
        gameLobbyPanel.setBackground(Color.decode("#1E2448"));
        gameLobbyPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create Start Game Session Button
        startGameButton = DesignUtils.createSpecialButton("Start Game Session");
        JButton leaveGameSessionButton = DesignUtils.createButton("Leave Game Session");

        // Set maximum size for buttons
        Dimension buttonSize = new Dimension(300, 70);
        startGameButton.setMaximumSize(buttonSize);
        leaveGameSessionButton.setMaximumSize(buttonSize);

        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLobbyControl.startGame();
            }
        });

        leaveGameSessionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLobbyControl.leaveGame();
                driver.showPanel(new StartUpPanel(driver));
            }
        });

        // Create a JList to display the names and scores
        listModel = new DefaultListModel<>();
        namesAndScoresJList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(namesAndScoresJList);

        // Create a JPanel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.decode("#1E2448"));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        buttonPanel.add(startGameButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(leaveGameSessionButton);

        // Create a JPanel to contain the title label and button panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.decode("#1E2448"));
        contentPanel.add(logoLabel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the content panel to the GameLobbyPanel
        add(contentPanel, BorderLayout.CENTER);
    }

    public void updateNamesAndScores(List<String> players) {
        listModel.clear();
        for (String player : players) {
            listModel.addElement(player);
        }
    }
}