package UserInterface;

import javax.swing.*;
import java.awt.*;

public class Driver extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
   
    public Driver() {
        setTitle("UNO Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 800));

        // Create the card layout and panel
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create the StartUpPanel
        StartUpPanel startUpPanel = new StartUpPanel(this);
        cardPanel.add(startUpPanel, "StartUpPanel");

        // Create an instance of GameManagerPanel
        GameManagerPanel gameManagerPanel = new GameManagerPanel(this);
        cardPanel.add(gameManagerPanel, "GameManagerPanel");

        // Create an instance of CreateAccountPanel
        CreateAccountPanel createAccountPanel = new CreateAccountPanel(this);
        cardPanel.add(createAccountPanel, "CreateAccountPanel");

        //Create an instance of LoginPanel
        LoginPanel loginPanel = new LoginPanel(this);
        cardPanel.add(loginPanel, "LoginPanel");

        //Create an instance of HowToPlayPanel
        HowToPlayPanel howToPlayPanel = new HowToPlayPanel(this);
        cardPanel.add(howToPlayPanel, "HowToPlayPanel");

        // Initially show the GameManagerPanel
        showPanel("GameManagerPanel");

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
