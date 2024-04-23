package UserInterface;

import javax.swing.*;

import ClientCommunication.Client;

import java.awt.*;

import javax.swing.*;
import java.awt.*;

public class Driver extends JFrame {
    private JPanel currentPanel;
    Client client = new Client(this);
    public Driver() {
        setTitle("UNO Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Show the initial panel (e.g., StartUpPanel)
        showPanel(new StartUpPanel(this));
    }

    public void showPanel(JPanel panel) {
        if (currentPanel != null) {
            remove(currentPanel);
        }
        currentPanel = panel;
        add(currentPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    
    public void sendStatusToPanel(String response) {
    	JOptionPane.showMessageDialog(null, response, "Error!", JOptionPane.ERROR_MESSAGE);
    }
    
    public Client getClient() {
        // Return an instance of the Client class
        return client;
    }

    public JPanel getCurrentPanel() {
        return currentPanel;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Driver().setVisible(true);
            }
        });
    }
}
