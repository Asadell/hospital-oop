/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.pages.auth;

import javax.swing.*;
import java.awt.*;
import hospital.pages.base.BaseFrame;
import hospital.pages.system.DashboardFrame;
import hospital.program.Admin;
import hospital.program.Session;
import hospital.util.ColorPalette;

/**
 *
 * @author LENOVO
 */
public class Login extends BaseFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel titleLabel;

    public Login() {
        super("Login", false); 
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

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(220, 100, 100, 30);
        formPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(320, 100, 250, 30);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        formPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(220, 150, 100, 30);
        formPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(320, 150, 250, 30);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        formPanel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(320, 200, 250, 40);
        loginButton.setBackground(ColorPalette.RED);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(e -> handleLogin());
        formPanel.add(loginButton);

        registerButton = new JButton("Register");
        registerButton.setBounds(320, 260, 250, 40); 
        registerButton.setBackground(ColorPalette.BLUE);
        registerButton.setForeground(Color.WHITE); 
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setFocusPainted(false); 
        registerButton.addActionListener(e -> goToRegister());
        formPanel.add(registerButton);

        content.add(formPanel);
        content.setBackground(ColorPalette.PRIMARY_PURPLE);
        // content.setBackground(new Color(34, 34, 34));
        // content.setBackground(new Color(75, 73, 172));
    }

    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        System.out.printf("%s = %s", username, password);

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fields cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Admin loggedAdmin = Admin.login(username, password);
        
        if (loggedAdmin != null) {
            Session.setCurrentUser(loggedAdmin);
            JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            new DashboardFrame();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void goToRegister() {
        new Register();
        dispose();
    }
}
