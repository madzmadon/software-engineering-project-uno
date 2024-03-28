package UserInterface;

import javax.swing.*;
import java.awt.*;

public class StartUpPanel extends JPanel {
    private Driver driver;

    public StartUpPanel(Driver driver) {
        this.driver = driver;
        // Set preferred size to 1000x800
        setPreferredSize(new Dimension(1000, 800));

        // Set BorderLayout for the StartUpPanel
        setLayout(new BorderLayout());

        // Load the UNO logo
        ImageIcon icon = DesignUtils.loadUnoLogo(1000, 200);

        // Create a JLabel with the resized image
        JLabel titleLabel = new JLabel(icon);
        titleLabel.setBackground(Color.decode("#1E2448")); // Set the background color of the JLabel
        titleLabel.setOpaque(true); // Make sure the background color is visible

        // Center the label horizontally
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Create buttons
        JButton loginButton = DesignUtils.createSpecialButton("Login");
        JButton createAccountButton = DesignUtils.createButton("Create Account");
        
        // Add action listeners to the buttons
        loginButton.addActionListener(e -> driver.showPanel("GameManagerPanel"));
        createAccountButton.addActionListener(e -> driver.showPanel("CreateAccountPanel"));

        // Set maximum size for buttons to ensure uniformity
        Dimension buttonSize = new Dimension(300, 70);
        loginButton.setMaximumSize(new Dimension(buttonSize));
        createAccountButton.setMaximumSize(new Dimension(buttonSize));

        // Create a JPanel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.decode("#1E2448"));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add buttons to the buttonPanel
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(loginButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(createAccountButton);
        buttonPanel.add(Box.createVerticalGlue());

        // Add the buttonPanel to the center of StartUpPanel
        add(buttonPanel, BorderLayout.CENTER);

        // Add titleLabel to the top of StartUpPanel
        add(titleLabel, BorderLayout.NORTH);
    }
}
