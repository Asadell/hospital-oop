package hospital.pages.components;

import hospital.util.ColorPalette;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author LENOVO
 */
public class Sidebar extends JPanel {
    
    private ColorPalette color = new ColorPalette();
    
    public Sidebar() {
        setBackground(color.WHITE);
        setBounds(0, 60, 300, 660);
        setLayout(null);

        JButton dashboard = createSidebarButton("Dashboard", 20);
        dashboard.addActionListener(e -> goToDashboard());
        
        JButton patient = createSidebarButton("Patient", 80);
        patient.addActionListener(e -> goToSettings());
        
        JButton doctor = createSidebarButton("Doctor", 140);
        doctor.addActionListener(e -> goToSettings());
        
        JButton appoinment = createSidebarButton("Appointment", 200);
        appoinment.addActionListener(e -> goToSettings());
        
        JButton billing = createSidebarButton("Billing", 260);
        billing.addActionListener(e -> goToSettings());
        
        JButton inventory = createSidebarButton("Inventory", 320);
        inventory.addActionListener(e -> goToSettings());
        
        JButton department = createSidebarButton("Department", 380);
        department.addActionListener(e -> goToSettings());
        
        JButton wallet = createSidebarButton("Wallet", 440);
        wallet.addActionListener(e -> goToSettings());
        
        JButton logout = createSidebarButton("Logout", 500);
        logout.addActionListener(e -> goToLogout());

        add(dashboard);
        add(patient);
        add(doctor);
        add(appoinment);
        add(billing);
        add(inventory);
        add(department);
        add(wallet);
        add(logout);
    }

    private JButton createSidebarButton(String text, int yPosition) {
        JButton button = new JButton(text);
        button.setBounds(20, yPosition, 250, 40);
        button.setBackground(color.PRIMARY_BLUE);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(color.SUP_BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(color.PRIMARY_BLUE);
            }
        });

        return button;
    }
    
    private void goToDashboard() {
        System.out.println("Navigating to Dashboard");
    }
    
    private void goToSettings() {
        System.out.println("Navigating to Settings");
    }

    private void goToLogout() {
        System.out.println("Logging out...");
        System.exit(0);
    }
}
