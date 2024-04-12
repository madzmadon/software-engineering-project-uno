package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccountPanel extends JPanel {
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
       
    public CreateAccountPanel(Driver driver) {
        // Set preferred size to 1000x800
        setPreferredSize(new Dimension(1000, 800));

        // Set BorderLayout for the CreateAccountPanel
        setLayout(new BorderLayout());

        // Create a JLabel for the title
        JLabel titleLabel = DesignUtils.createTitleLabel("Create Account");

        // Create a JPanel for the form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.decode("#1E2448"));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create form fields
        usernameField = DesignUtils.createTextField();
        emailField = DesignUtils.createTextField();
        passwordField = DesignUtils.createPasswordField();
        confirmPasswordField = DesignUtils.createPasswordField();

        // Add components to the form panel
        formPanel.add(DesignUtils.createLabel("Username:"));
        formPanel.add(usernameField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(DesignUtils.createLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(DesignUtils.createLabel("Password:"));
        formPanel.add(passwordField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(DesignUtils.createLabel("Confirm Password:"));
        formPanel.add(confirmPasswordField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Create buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.setBackground(Color.decode("#1E2448"));

        // Create cancel button
        JButton cancelButton = DesignUtils.createButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Return to the StartUpPanel
                driver.showPanel("StartUpPanel");
            }
        });

        // Create submit button
        JButton submitButton = DesignUtils.createSpecialButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle form submission here
                String username = usernameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Validate and create the account
                    // Perform the necessary validation and account creation
                    // For now, let's display a success message
                    JOptionPane.showMessageDialog(null, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Return to the StartUpPanel
                    driver.showPanel("StartUpPanel");
                }
            }
        });

        // Add buttons to the buttons panel
        buttonsPanel.add(cancelButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonsPanel.add(submitButton);

        // Add components to the CreateAccountPanel
        add(titleLabel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }
}
