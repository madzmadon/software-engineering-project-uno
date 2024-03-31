package UserInterface;

import javax.swing.*;
import java.awt.*;

public class Driver extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    private StartUpPanel startUpPanel;
    private LoginPanel loginPanel;
    
    public Driver() {
        setTitle("UNO Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 800));

        // Create the card layout and panel
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create the StartUpPanel
        startUpPanel = new StartUpPanel(this);
        
        //Create LoginPanel
        loginPanel = new LoginPanel(this);
        
        // Create an instance of GameManagerPanel
        GameManagerPanel gameManagerPanel = new GameManagerPanel(this);
        
        // Create an instance of CreateAccountPanel
        CreateAccountPanel createAccountPanel = new CreateAccountPanel(this);
        
        //Create an instance of LoginPanel
        LoginPanel loginPanel = new LoginPanel(this);

        // Add panels to the card panel
        cardPanel.add(startUpPanel, "StartUpPanel");
        cardPanel.add(gameManagerPanel, "GameManagerPanel");
        cardPanel.add(createAccountPanel, "CreateAccountPanel");
        cardPanel.add(loginPanel);
        
        // Initially show the StartUpPanel
        showPanel("StartUpPanel");

        // Add the card panel to the frame
        add(cardPanel);

        pack();
        setLocationRelativeTo(null);
    }

    public void showPanel(String panelName) {
        cardLayout.show(cardPanel, panelName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Driver driver = new Driver();
            driver.setVisible(true);
        });
    }
}
