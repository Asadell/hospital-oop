package hospital.pages.auth;

import javax.swing.*;
import java.awt.*;
import hospital.pages.base.BaseFrame;
import hospital.pages.system.Dashboard;
import hospital.program.Admin;

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
        // Panel utama dengan background putih
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBackground(Color.WHITE);
        formPanel.setBounds(200, 150, 880, 400);

        // Title label
        titleLabel = new JLabel("Login to Hospital Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(200, 20, 480, 50);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(titleLabel);

        // Username Label dan TextField
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(220, 100, 100, 30);
        formPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(320, 100, 250, 30);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        formPanel.add(usernameField);

        // Password Label dan PasswordField
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(220, 150, 100, 30);
        formPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(320, 150, 250, 30);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        formPanel.add(passwordField);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setBounds(320, 200, 250, 40);
        loginButton.setBackground(new Color(0, 102, 204));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            System.out.printf("%s = %s", username, password);

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fields cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (Admin.login(username, password)) {
                JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                new Dashboard();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        formPanel.add(loginButton);

        registerButton = new JButton("Register");
        registerButton.setBounds(320, 260, 250, 40); 
        registerButton.setBackground(new Color(255, 102, 102));
        registerButton.setForeground(Color.WHITE); 
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setFocusPainted(false); 
        registerButton.addActionListener(e -> {
            new Register();
            dispose();
        });
        formPanel.add(registerButton);

        // Menambahkan form panel ke konten utama
        content.add(formPanel);
        content.setBackground(new Color(34, 34, 34));
        // content.setBackground(new Color(75, 73, 172));
    }
}
