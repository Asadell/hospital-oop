package hospital.pages.components;

import hospital.pages.auth.Login;
import hospital.pages.system.AppointmentFrame;
import hospital.pages.system.BillingFrame;
import hospital.pages.system.DashboardFrame;
import hospital.pages.system.DepartmentFrame;
import hospital.pages.system.DoctorFrame;
import hospital.pages.system.InventoryFrame;
import hospital.pages.system.PatientFrame;
import hospital.pages.system.WalletFrame;
import hospital.program.Session;
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
        patient.addActionListener(e -> goToPatient());
        
        JButton doctor = createSidebarButton("Doctor", 140);
        doctor.addActionListener(e -> goToDoctor());
        
        JButton appoinment = createSidebarButton("Appointment", 200);
        appoinment.addActionListener(e -> goToAppointment());
        
        JButton billing = createSidebarButton("Billing", 260);
        billing.addActionListener(e -> goToBilling());
        
        JButton inventory = createSidebarButton("Inventory", 320);
        inventory.addActionListener(e -> goToInventory());
        
        JButton department = createSidebarButton("Department", 380);
        department.addActionListener(e -> goToDepartment());
        
        JButton wallet = createSidebarButton("Wallet", 440);
        wallet.addActionListener(e -> goToWallet());
        
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
        SwingUtilities.getWindowAncestor(this).dispose();
        new DashboardFrame();
    }
    
    private void goToPatient() {
        SwingUtilities.getWindowAncestor(this).dispose();
        new PatientFrame();
    }

    private void goToDoctor() {
        SwingUtilities.getWindowAncestor(this).dispose();
        new DoctorFrame();
    }
    
    private void goToAppointment() {
        SwingUtilities.getWindowAncestor(this).dispose();
        new AppointmentFrame();
    }
    
    private void goToBilling() {
        SwingUtilities.getWindowAncestor(this).dispose();
        new BillingFrame();
    }
    
    private void goToInventory() {
        SwingUtilities.getWindowAncestor(this).dispose();
        new InventoryFrame();
    }
    
    private void goToDepartment() {
        SwingUtilities.getWindowAncestor(this).dispose();
        new DepartmentFrame();
    }
    
    private void goToWallet() {
        SwingUtilities.getWindowAncestor(this).dispose();
        new WalletFrame();
    }

    private void goToLogout() {
        Session.clear();
        SwingUtilities.getWindowAncestor(this).dispose();
        new Login();
    }
}
