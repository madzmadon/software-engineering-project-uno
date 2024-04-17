package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameManagerPanel extends JPanel {
    public GameManagerPanel(Driver driver) {
        // Set preferred size to 1000x800
        setPreferredSize(new Dimension(1000, 800));

        // Set BorderLayout for the GameManagerPanel
        setLayout(new BorderLayout());

        // Load the UNO logo
        ImageIcon icon = DesignUtils.loadUnoLogo(1000, 200);

        // Create a JLabel with the resized image as the title
        JLabel titleLabel = new JLabel(icon);
        titleLabel.setBackground(Color.decode("#1E2448")); // Set the background color of the JLabel
        titleLabel.setOpaque(true); // Make sure the background color is visible

        // Center the label horizontally
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Create buttons
        JButton startGameButton = DesignUtils.createSpecialButton("Start Game");
        JButton joinGameButton = DesignUtils.createSpecialButton("Join Game");
        JButton howToPlayButton = DesignUtils.createButton("How to Play");
        JButton logoutButton = DesignUtils.createButton("Log out");
       
        
        // Set maximum size for buttons to ensure uniformity
        Dimension buttonSize = new Dimension(300, 70);
        startGameButton.setMaximumSize(buttonSize);
        joinGameButton.setMaximumSize(buttonSize);
        howToPlayButton.setMaximumSize(buttonSize);
        logoutButton.setMaximumSize(buttonSize);
       
        startGameButton.addActionListener(e -> driver.showPanel("GameLobbyPanel"));
        joinGameButton.addActionListener(e -> driver.showPanel("GameLobbyPanel"));
        //Action listener for accessRulesButton
        howToPlayButton.addActionListener(e -> driver.showPanel("HowToPlayPanel"));
        
        // Add action listener to logout button
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show the StartUpPanel
                driver.showPanel("StartUpPanel");
            }
        });

        // Create a JPanel with BoxLayout for button alignment
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.decode("#1E2448"));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add buttons to the buttonPanel with vertical and horizontal center alignment
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(startGameButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(joinGameButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(howToPlayButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(logoutButton);
        buttonPanel.add(Box.createVerticalGlue());

        // Create a JPanel to contain the title label and button panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.decode("#1E2448"));
        contentPanel.add(titleLabel, BorderLayout.NORTH);
        contentPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add the content panel to the GameManagerPanel
        add(contentPanel, BorderLayout.CENTER);
    }
}
