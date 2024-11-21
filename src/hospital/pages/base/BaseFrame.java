/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.pages.base;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hospital.pages.components.Sidebar;
import hospital.program.Session;
import hospital.util.ColorPalette;

/**
 *
 * @author LENOVO
 */
public abstract class BaseFrame extends JFrame {
    private boolean isDashboard;
    protected ColorPalette color = new ColorPalette();
    protected JPanel header = new JPanel();
    protected JPanel backgroundContent = new JPanel();
    protected JPanel content = new JPanel();

    public BaseFrame(String title, boolean isDashboard) {
        super(title);
        this.isDashboard = isDashboard;
        
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        initLayout();
    }

    protected abstract void setupContent();
    
    private void initLayout() {
        if (isDashboard) {
            header.setBackground(color.WHITE);
            header.setBounds(0, 0, 1280, 60);
            header.setLayout(null);

            ImageIcon logo = new ImageIcon(getClass().getResource("/hospital/images/logo.png"));
            JLabel logoLabel = new JLabel(logo);
            logoLabel.setBounds(68, 8, 170, 50);
            header.add(logoLabel);

            JLabel adminName = new JLabel("Hi, " + Session.getCurrentUserName());
            adminName.setBounds(1100, 10, 180, 40);
            adminName.setFont(new Font("Arial", Font.BOLD, 20));
            adminName.setForeground(color.TEXT_MAIN);
            header.add(adminName);

            Sidebar sidebarPanel = new Sidebar();
            sidebarPanel.setBounds(0, 60, 300, 660);
            sidebarPanel.setLayout(null);

            backgroundContent.setBounds(300, 60, 980, 660);
            backgroundContent.setBackground(color.BACKGROUND);
            backgroundContent.setLayout(null);

            content.setBounds(40, 36, 900, 560);
            content.setBackground(color.WHITE);
            content.setLayout(null);

            backgroundContent.add(content);
            add(header);
            add(sidebarPanel);
            add(backgroundContent);
        } else {
            content.setBounds(0, 0, 1280, 720);
            content.setBackground(color.PRIMARY_PURPLE);
            content.setLayout(null);
            add(content);
        }

        setVisible(true);
    }
}
