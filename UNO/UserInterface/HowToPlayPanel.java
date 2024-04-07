package UserInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.*;

public class HowToPlayPanel extends JPanel {

    private Driver driver;
    
    public HowToPlayPanel (Driver driver)
    
    {
      
      this.driver = driver;
      
      JButton rulesButton = DesignUtils.createSpecialButton("Rules");
      JButton returnButton = DesignUtils.createButton("Return to Start Menu");
      
      
      // Load the UNO logo
      ImageIcon icon = DesignUtils.loadUnoLogo(1000, 200);
      
      // Set preferred size to 1000x800
      setPreferredSize(new Dimension(1000, 800));

      // Set BorderLayout for the HowToPlayPanel
      setLayout(new BorderLayout());

      // Create a JLabel for the title
      JLabel rulesTitleLabel = DesignUtils.createTitleLabel("How to Play UNO");
      
      // ActionListeners
      
      returnButton.addActionListener(e -> driver.showPanel("StartUpPanel"));
      
      rulesButton.addActionListener(new ActionListener()
      {
      
      @Override
      public void actionPerformed(ActionEvent e)
      
      {
        //Source for accessing a URL--https://stackoverflow.com/questions/10037644/opening-a-url-from-a-jbutton-in-simple-java-program
        try
        {
          URI uri = new URI("https://service.mattel.com/instruction_sheets/42001pr.pdf");
          Desktop desktop = Desktop.getDesktop();
          desktop.browse(uri);
        } 
        
        catch (URISyntaxException e1)
        
        {
          // TODO Auto-generated catch block
          e1.printStackTrace();
          
        } catch (IOException e1)
        {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
        
        
      }
        
      });
      
      // Set maximum size for buttons to ensure uniformity
      Dimension buttonSize = new Dimension(300, 70);
      rulesButton.setMaximumSize(new Dimension(buttonSize));
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
      buttonPanel.add(rulesButton);
      buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
      buttonPanel.add(returnButton);
      buttonPanel.add(Box.createVerticalGlue());

      // Add the buttonPanel to the center of StartUpPanel
      add(buttonPanel, BorderLayout.CENTER);

      // Add logoLabel to the top of StartUpPanel
    
      add(logoLabel, BorderLayout.NORTH);
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
    }
  
  
  
  
  
  
  
  
  
  
  
}
