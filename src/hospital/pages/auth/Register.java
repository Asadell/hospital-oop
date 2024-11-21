/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.pages.auth;

import hospital.pages.base.BaseFrame;
import hospital.program.Admin;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author LENOVO
 */
public class Register extends BaseFrame {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel titleLabel;

    public Register() {
        super("Register", false);
        setupContent();
    }

    @Override
    protected void setupContent() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBackground(Color.WHITE);
        formPanel.setBounds(200, 150, 880, 400);

        titleLabel = new JLabel("Login to Hospital Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(200, 20, 480, 50);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(titleLabel);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(220, 100, 100, 30);
        formPanel.add(firstNameLabel);

        firstNameField = new JTextField();
        firstNameField.setBounds(320, 100, 250, 30);
        firstNameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        formPanel.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(220, 140, 100, 30);
        formPanel.add(lastNameLabel);

        lastNameField = new JTextField();
        lastNameField.setBounds(320, 140, 250, 30);
        lastNameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        formPanel.add(lastNameField);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(220, 180, 100, 30);
        formPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(320, 180, 250, 30);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        formPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(220, 220, 100, 30);
        formPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(320, 220, 250, 30);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        formPanel.add(passwordField);

        registerButton = new JButton("Register");
        registerButton.setBounds(320, 260, 250, 40);
        registerButton.setBackground(new Color(255, 102, 102));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setFocusPainted(false);
        registerButton.addActionListener(e -> {
          int id = Admin.getAdminCount() + 1;
          String firstName = firstNameField.getText();
          String lastName = lastNameField.getText();
          String username = usernameField.getText();
          String password = new String(passwordField.getPassword());

          if (firstName.isEmpty() || lastName.isEmpty() || password.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fields cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
          } else if (Admin.isUserRegistered(username)) {
            JOptionPane.showMessageDialog(this, "User already registered!", "Error", JOptionPane.ERROR_MESSAGE);
          } else {
            Admin.register(id, firstName, lastName, username, password);
            JOptionPane.showMessageDialog(this, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            new Login();
            dispose();
          }
        });
        formPanel.add(registerButton);

        loginButton = new JButton("Login");
        loginButton.setBounds(320, 320, 250, 40);  
        loginButton.setBackground(new Color(0, 102, 204)); 
        loginButton.setForeground(Color.WHITE); 
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setFocusPainted(false); 
        loginButton.addActionListener(e -> {
          JOptionPane.showMessageDialog(this, "Redirecting to Login page...");
          new Login(); 
          dispose();
        });
        formPanel.add(loginButton);

        content.add(formPanel);
        content.setBackground(new Color(74, 73, 172));
        // content.setBackground(new Color(34, 34, 34));
        // content.setBackground(new Color(75, 73, 172));
    }
}
