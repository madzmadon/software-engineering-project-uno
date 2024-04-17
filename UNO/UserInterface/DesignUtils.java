package UserInterface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class DesignUtils {
    private static final Color DEFAULT_BUTTON_BACKGROUND = Color.decode("#1A1F44");
    private static final Color DEFAULT_BUTTON_BORDER_COLOR = Color.decode("#5169A2");
    private static final Color SPECIAL_BUTTON_BACKGROUND = Color.decode("#1A1F44");
    private static final Color SPECIAL_BUTTON_BORDER_COLOR = Color.decode("#00B20D");
    private static final int BUTTON_WIDTH = 300;
    private static final int BUTTON_HEIGHT = 70;

    public static ImageIcon loadUnoLogo(int width, int height) {
        return createResizedIcon("/images/UNOLogo.png", width, height);
    }

    public static ImageIcon loadCardImage(String fileName) {
        return createResizedIcon("/images/cards/" + fileName, 100, 150);
    }

    public static JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Space Mono", Font.PLAIN, 24));
        button.setForeground(Color.WHITE);
        button.setBackground(DEFAULT_BUTTON_BACKGROUND);
        button.setBorder(BorderFactory.createLineBorder(DEFAULT_BUTTON_BORDER_COLOR, 2));
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        return button;
    }

    public static JButton createSpecialButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Space Mono", Font.PLAIN, 24));
        button.setForeground(Color.WHITE);
        button.setBackground(SPECIAL_BUTTON_BACKGROUND);
        button.setBorder(BorderFactory.createLineBorder(SPECIAL_BUTTON_BORDER_COLOR, 2));
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        return button;
    }

    public static ImageIcon createResizedIcon(String imagePath, int width, int height) {
        ImageIcon icon = null;
        try {
            InputStream inputStream = DesignUtils.class.getResourceAsStream(imagePath);
            Image img = ImageIO.read(inputStream);
            Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            icon = new ImageIcon(scaledImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return icon;
    }
    
    public static void styleTextField(JTextField textField) {
        textField.setFont(new Font("Space Mono", Font.PLAIN, 14));
        textField.setForeground(Color.WHITE);
        textField.setBackground(Color.decode("#1A1F44"));
        textField.setBorder(BorderFactory.createLineBorder(DEFAULT_BUTTON_BORDER_COLOR, 2));
        textField.setCaretColor(Color.WHITE);
    }

    public static JTextField createTextField() {
        JTextField textField = new JTextField();
        styleTextField(textField);
        return textField;
    }
    
    public static JPasswordField createPasswordField() {
        JPasswordField passwordField = new JPasswordField();
        styleTextField(passwordField);
        return passwordField;
    }
    
    public static JLabel createTitleLabel(String text) {
        JLabel titleLabel = new JLabel(text);
        titleLabel.setFont(new Font("Space Mono", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBackground(Color.decode("#1E2448"));
        titleLabel.setOpaque(true);
        return titleLabel;
    }
    
    public static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Space Mono", Font.PLAIN, 18));
        label.setForeground(Color.WHITE);
        return label;
    }
}
