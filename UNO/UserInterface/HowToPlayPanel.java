package UserInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
//Sources for scrollpane and properties: http://www.cs.emory.edu/~cheung/Courses/377/Syllabus/8-JDBC/GUI/components5.html and https://www.tutorialspoint.com/java-program-to-set-the-font-and-color-of-some-text-in-a-jtextpane-using-styles
import javax.swing.*;

public class HowToPlayPanel extends JPanel {

    private Driver driver;
    
    public HowToPlayPanel (Driver driver)
    
    {
      
      this.driver = driver;
      JButton returnButton = DesignUtils.createButton("Return to Start Menu");
            
      // Define the rulesTextArea's components and properties.
String rules = getGameRules();
 private String getGameRules() {
    	String rules = 
    		    "UNO Rules\n\n" +
    		    
    		    "Overview" +
    		    "- UNO is a card game played by 2-6 people.\n" +
    		    "- The objective is to get rid of all the cards in your hand.\n" +
    		    "- Players win a round by being the first to discard all their cards.\n" +
    		    "- The first player to reach a target number of points (defined by the host, ranging from 200 to 600) wins the overall game.\n\n" +
    		    
    		    "Gameplay\n" +
    		    "1. Players place one card (either a numbered or special card) each on top of the discard pile.\n" +
    		    "2. Cards played must match the current card at the top of the discard pile either by color or number.\n" +
    		    "3. If players don't have a match, they must draw a card and play a legal card, if possible.\n\n" +
    		    
    		    "Special Cards\n" +
    		    "- \"Draw Two\": The next player must draw two cards and skip their turn.\n" +
    		    "- \"Reverse\": The direction of play is reversed.\n" +
    		    "- \"Skip\": The next player is skipped.\n" +
    		    "- \"Wild\": The player can choose the color that continues play.\n" +
    		    "- \"Wild Draw Four\": The next player must draw four cards, and the current player chooses the color that continues play.\n\n" +
    		    
    		    "UNO Call\n" +
    		    "- When a player has only one card remaining, they must announce \"UNO!\"\n" +
    		    "- If a player fails to announce \"UNO\" and is caught by another player, they must draw multiple penalty cards.\n\n" +
    		    
    		    "Scoring\n" +
    		    "- Points are tracked by the value of the cards in each losing player's hand at the end of a round.\n" +
    		    "- The sum of these points is awarded to the winner of the round.";

        return (rules);
    }
  
      Font font = new Font("Times New Roman", Font.PLAIN, 23);
      JTextArea rulesTextArea = new JTextArea(10, 5);
      rulesTextArea.setBackground(Color.decode("#1E2448")); // Set the background color of the JLabel
      rulesTextArea.setFont(font);
      rulesTextArea.setForeground(Color.WHITE);
      rulesTextArea.setOpaque(true);
      rulesTextArea.setLineWrap(true);
      rulesTextArea.setWrapStyleWord(true);
      rulesTextArea.setText(rules);
      JScrollPane scrollPane = new JScrollPane(rulesTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
          JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scrollPane.setPreferredSize(new Dimension(50, 50));
      rulesTextArea.setEditable(false);
         
      // Load the UNO logo
      ImageIcon icon = DesignUtils.loadUnoLogo(1000, 200);
      
      // Set preferred size to 500x700
      setPreferredSize(new Dimension(500, 700));

      // Set BorderLayout for the HowToPlayPanel
      setLayout(new BorderLayout());

      // Create a JLabel for the title
      JLabel rulesTitleLabel = DesignUtils.createTitleLabel("How to Play UNO");
      
      // ActionListener for returnButton
      
      returnButton.addActionListener(e -> driver.showPanel("GameManagerPanel"));
      
            
       
      // Set maximum size for the button 
      Dimension buttonSize = new Dimension(300, 70);
    
      returnButton.setMaximumSize(new Dimension(buttonSize));
      
      // Create a JLabel with the resized image
      JLabel logoLabel = new JLabel(icon);
      logoLabel.setBackground(Color.decode("#1E2448")); // Set the background color of the JLabel
      logoLabel.setOpaque(true); // Make sure the background color is visible

      // Center the label horizontally
      logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
      
     // Create a JPanel for the buttons
      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
      buttonPanel.setBackground(Color.decode("#1E2448"));
      buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

      // Add buttons to the buttonPanel
      buttonPanel.add(Box.createVerticalGlue());
      buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
      buttonPanel.add(returnButton);
      buttonPanel.add(Box.createVerticalGlue());

      // Add the buttonPanel to the south of the HowToPlayPanel
      add(buttonPanel, BorderLayout.SOUTH);

      // Add logoLabel to the top of the HowToPlayPanel
    
      add(logoLabel, BorderLayout.NORTH);
      
      // Add scrollPane to the center of the HowToPlayPanel
      add(scrollPane, BorderLayout.CENTER);
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
    }
  
  
  
  
  
  
  
  
  
  
  
}
