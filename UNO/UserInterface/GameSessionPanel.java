package UserInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameSessionPanel extends JPanel {
    private JLabel player1Label;
    private JLabel player2Label;
    private JLabel player3Label;
    private JLabel player4Label;
    private JLabel player5Label;
    private JLabel player6Label;
    private JButton drawButton;
    private JButton discardButton;
    private JButton unoButton;

    public GameSessionPanel(Driver driver) {
        setPreferredSize(new Dimension(1000, 800));
        setLayout(new BorderLayout());
        setBackground(Color.decode("#1E2448"));

        // Top Player Panels
        JPanel topPlayerPanel = new JPanel();
        topPlayerPanel.setLayout(new BoxLayout(topPlayerPanel, BoxLayout.X_AXIS));
        topPlayerPanel.setBackground(Color.decode("#1E2448"));
        topPlayerPanel.setBorder(DesignUtils.createEmptyBorder(10, 10, 10, 10));

        JPanel player1Panel = createPlayerPanel("Player 1");
        JPanel player2Panel = createPlayerPanel("Player 2");
        topPlayerPanel.add(player1Panel);
        topPlayerPanel.add(Box.createHorizontalStrut(20));
        topPlayerPanel.add(player2Panel);

        // Left Player Panel
        JPanel leftPlayerPanel = new JPanel();
        leftPlayerPanel.setLayout(new BoxLayout(leftPlayerPanel, BoxLayout.Y_AXIS));
        leftPlayerPanel.setBackground(Color.decode("#1E2448"));
        leftPlayerPanel.setBorder(DesignUtils.createEmptyBorder(10, 10, 10, 10));

        JPanel player3Panel = createPlayerPanel("Player 3");
        leftPlayerPanel.add(player3Panel);

        // Right Player Panel
        JPanel rightPlayerPanel = new JPanel();
        rightPlayerPanel.setLayout(new BoxLayout(rightPlayerPanel, BoxLayout.Y_AXIS));
        rightPlayerPanel.setBackground(Color.decode("#1E2448"));
        rightPlayerPanel.setBorder(DesignUtils.createEmptyBorder(10, 10, 10, 10));

        JPanel player4Panel = createPlayerPanel("Player 4");
        rightPlayerPanel.add(player4Panel);

        // Bottom Player Panels
        JPanel bottomPlayerPanel = new JPanel();
        bottomPlayerPanel.setLayout(new BoxLayout(bottomPlayerPanel, BoxLayout.X_AXIS));
        bottomPlayerPanel.setBackground(Color.decode("#1E2448"));
        bottomPlayerPanel.setBorder(DesignUtils.createEmptyBorder(10, 10, 10, 10));

        JPanel player5Panel = createPlayerPanel("Player 5");
        JPanel player6Panel = createPlayerPanel("You");
        bottomPlayerPanel.add(player5Panel);
        bottomPlayerPanel.add(Box.createHorizontalStrut(20));
        bottomPlayerPanel.add(player6Panel);

        // Draw Pile and Discard Pile
        JPanel drawDiscardPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        drawDiscardPanel.setBackground(Color.decode("#1E2448"));
        JPanel drawPanel = DesignUtils.createCardPanel();
        drawButton = DesignUtils.createSpecialButton("Draw");
        JPanel discardPanel = DesignUtils.createCardPanel();
        discardButton = DesignUtils.createSpecialButton("Discard");
        drawDiscardPanel.add(drawPanel);
        drawDiscardPanel.add(drawButton);
        drawDiscardPanel.add(discardPanel);
        drawDiscardPanel.add(discardButton);

        // Uno Button
        JPanel unoPanel = new JPanel();
        unoPanel.setLayout(new BoxLayout(unoPanel, BoxLayout.Y_AXIS));
        unoPanel.setBackground(Color.decode("#1E2448"));
        unoPanel.setBorder(DesignUtils.createEmptyBorder(10, 10, 10, 10));
        unoButton = DesignUtils.createSpecialButton("Uno");
        unoPanel.add(unoButton);

        // Add components to the main panel
        add(topPlayerPanel, BorderLayout.NORTH);
        add(leftPlayerPanel, BorderLayout.WEST);
        add(rightPlayerPanel, BorderLayout.EAST);
        add(bottomPlayerPanel, BorderLayout.SOUTH);
        add(drawDiscardPanel, BorderLayout.CENTER);
        add(unoPanel, BorderLayout.SOUTH);
    }

    private JPanel createPlayerPanel(String playerName) {
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.setBackground(Color.decode("#1E2448"));
        playerPanel.setBorder(DesignUtils.createEmptyBorder(10, 10, 10, 10));

        JLabel playerLabel = DesignUtils.createLabel(playerName);
        JPanel playerCardsPanel = DesignUtils.createCardPanel();

        playerPanel.add(playerLabel);
        playerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        playerPanel.add(playerCardsPanel);

        return playerPanel;
    }
}