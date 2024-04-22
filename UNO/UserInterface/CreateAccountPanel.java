package UserInterface;

import javax.swing.*;

import ClientCommunication.Client;
import ClientCommunication.CreateAccountControl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccountPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private CreateAccountControl createAccountControl;

    public CreateAccountPanel(Driver driver) {
        Client client = driver.getClient();
        createAccountControl = new CreateAccountControl(client);

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
        passwordField = DesignUtils.createPasswordField();
        confirmPasswordField = DesignUtils.createPasswordField();

        // Add components to the form panel
        formPanel.add(DesignUtils.createLabel("Username:"));
        formPanel.add(usernameField);
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
                driver.showPanel(new StartUpPanel(driver));
            }
        });

        // Create submit button
        JButton submitButton = DesignUtils.createSpecialButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Update the CreateAccountControl instance with the user-entered values
                    createAccountControl.setUsername(username);
                    createAccountControl.setPassword(password);

                    // Call the createAccount method of CreateAccountControl
                    boolean creationSuccessful = createAccountControl.createAccount();

                    if (creationSuccessful) {
                        JOptionPane.showMessageDialog(null, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        driver.showPanel(new StartUpPanel(driver));
                    } else {
                        JOptionPane.showMessageDialog(null, "Account creation failed. Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
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
