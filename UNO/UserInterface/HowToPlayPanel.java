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
      String  rules = "UNO is a card game played by 2-6 people. A round of UNO consists of playing matching colors and/or numbers to a faceup discard pile in order to get rid of the cards in players’ hands. Players place one card (either a numbered or special card) each on top of the discard pile. Special cards include “Draw Two,” “Reverse,” “Skip,”  “Wild,” and “Wild Draw Four.” If players don’t have a match for the current card at the top of the discard pile, they draw a card, and make a play legal card, if possible. Points will be tracked by the value of the hand of each losing player, with the sum of the points going to the winner. Players win a round by getting rid of all their cards. Importantly, a player must first announce 'UNO!' if he or she has a single card; a penalty can be applied by the other players as long as they notice that the player who did not make the announcement does not do so within a period of ??? seconds. The first player to reach a target number of points defined by the host wins the overall game. Point totals can range from 200 to 600.\r\n";
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
