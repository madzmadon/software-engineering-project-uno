package UserInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Source for JList implementation: https://www.geeksforgeeks.org/java-swing-jlist-with-examples/
import javax.swing.*;

public class GameLobbyPanel extends JPanel{

  private JLabel logoLabel;
  private JButton startGameButton;
  private String namesAndScores;
  private JList namesAndScoresJList;
  private Driver driver;

  public GameLobbyPanel(Driver driver, String lobbyId)
  {
    this.driver=driver;

    // Set preferred size to 1000x800
    setPreferredSize(new Dimension(1000, 800));

    // Set BorderLayout for the GameManagerPanel
    setLayout(new BorderLayout());

    // Load the UNO logo
    ImageIcon icon = DesignUtils.loadUnoLogo(800, 200);

    // Set up the title of the panel
    // JLabel panelTitleLabel = DesignUtils.createTitleLabel("Game Lobby");

    // Create a JLabel with the resized image as the title
    JLabel logoLabel = new JLabel(icon);
    logoLabel.setBackground(Color.decode("#1E2448")); // Set the background color of the JLabel
    logoLabel.setOpaque(true); // Make sure the background color is visible

    //Create a JTextArea to display the names and scores
    JTextArea namesScoresJTextArea = new JTextArea();

    namesScoresJTextArea.setText(namesAndScores);


    // Set preferred size to 100x100
    setPreferredSize(new Dimension(100, 100));

    // Create a JPanel for the game lobby
    JPanel gameLobbyPanel = new JPanel();
    gameLobbyPanel.setLayout(new BoxLayout(gameLobbyPanel, BoxLayout.Y_AXIS));
    gameLobbyPanel.setBackground(Color.decode("#1E2448"));
    gameLobbyPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    // Create Start Game Session Button
    JButton startGameSessionButton = DesignUtils.createSpecialButton("Start Game Session");

    // Set maximum size for button
    Dimension buttonSize = new Dimension(300, 70);

    //Action listener for accessRulesButton (show GameSessionPanel for now)
    startGameSessionButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            driver.showPanel(new GameSessionPanel(driver, lobbyId));
        }
    });
    startGameSessionButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            driver.showPanel(new GameSessionPanel(driver, lobbyId));
        }
    });

    // Create a JPanel for the buttons
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
    buttonPanel.setBackground(Color.decode("#1E2448"));
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    // Create a JPanel to contain the title label and button panel
    JPanel contentPanel = new JPanel(new BorderLayout());
    contentPanel.setBackground(Color.decode("#1E2448"));
    contentPanel.add(logoLabel, BorderLayout.NORTH);
    contentPanel.add(buttonPanel, BorderLayout.CENTER);

    // Add the content panel to the GameManagerPanel
    add(contentPanel, BorderLayout.CENTER);

    add(buttonPanel, BorderLayout.SOUTH);


  }

  public void setNamesAndScores(String namesAndScores)
  {

     this.namesAndScores=namesAndScores;

  }
}

  //Idea(s) for possible implementation:
  //DefaultListModel<String> dlm = new DefaultListModel<>();
  //JList Cells?

