package UserInterface;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 //Incorporates Dr. Smith's lab5out.

public class LoginPanel extends JPanel{

  
private JTextField usernameField;
private JTextField passwordField;
private JLabel errorLabel;
private Driver driver;

public String getUsername()
{
  return usernameField.getText();
  
}

public String getPassword()
{
  return passwordField.getText();
}

public void setError(String error)
{
  errorLabel.setText(error);
  
}

public LoginPanel(Driver driver) 
{
  this.driver=driver;
  
  //Set preferred size for LoginPanel to 1000 X 800
    setPreferredSize(new Dimension (1000, 800));
    
  //Set BorderLayout for the LoginPanel
    setLayout(new BorderLayout());
  
  //Set title JLabel
  JLabel title =  DesignUtils.createTitleLabel("Login to Your Account");
  
  //Set errorLabel
  errorLabel = new JLabel("", JLabel.CENTER);
  errorLabel.setForeground(Color.RED);
  
  // Create a JPanel for the form
  JPanel formPanel = new JPanel();
  formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
  formPanel.setBackground(Color.decode("#1E2448"));
  formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

//Create form fields
  usernameField = DesignUtils.createTextField();
  passwordField = DesignUtils.createPasswordField();
 
//Add components to Form Panel
  
  //Add usernameField to Form Panel
  formPanel.add(DesignUtils.createLabel("Username:"));
  formPanel.add(usernameField);
  formPanel.add(Box.createRigidArea (new Dimension(0, 10)));
  
  //Add passwordField to Form Panel
  formPanel.add(DesignUtils.createLabel("Password:"));
  formPanel.add(passwordField);
  formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
  
//Create loginButtonsPanel
  JPanel loginButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
  loginButtonsPanel.setBackground(Color.decode("#1E2448"));
  
  //Create loginButton
  JButton loginButton = DesignUtils.createSpecialButton("Login");
  loginButton.addActionListener(new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e)
    {
      String username = usernameField.getText();
      String userPassword = passwordField.getText();
      
      
      if (userPassword.equals("") || username.equals(""))
      {
        JOptionPane.showMessageDialog(null, "You must enter a username and password!", "Error!", JOptionPane.ERROR_MESSAGE);
      }
      //Display the GameManagerPanel if credentials match an existing user's
    else
      driver.showPanel(new GameManagerPanel(driver));
      
    }
    
    
  });
  
  //Create cancelLoginButton
  JButton cancelLoginButton = DesignUtils.createButton("Cancel");
  cancelLoginButton.addActionListener(new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e)
    {
    //Return to the StartUpPanel
       driver.showPanel(new StartUpPanel(driver));
    }
    
    
  });
  
  //Add buttons to loginButtonPanel
  loginButtonsPanel.add(cancelLoginButton);
  loginButtonsPanel.add(loginButton);
  
  
   //Add components to LoginPanel
  add(title, BorderLayout.NORTH);
  add(formPanel, BorderLayout.CENTER);
  add(loginButtonsPanel, BorderLayout.SOUTH);
  
}
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}
