/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.pages;

import hospital.util.ColorPalette;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author LENOVO
 */
public class HomePage {
    private JFrame frame = new JFrame("Home Page");
    private ColorPalette color = new ColorPalette();
    
    private JPanel background = new JPanel();
    private JPanel sidebar = new JPanel();
    private JPanel header = new JPanel();
    private JPanel content = new JPanel();
    
    public HomePage() {
        initComponents();
        System.out.println("done");
    }

    private void initComponents() {
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
//        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // background
        background.setBackground(color.BLACK);
        background.setBounds(0, 0, 1280, 720);
        background.setLayout(null);
        
        // header
        header.setBackground(color.WHITE);
        header.setBounds(0, 0, 1280, 72);
        header.setLayout(null);
        
        // sidebar
        sidebar.setBackground(color.WHITE);
        sidebar.setBounds(0, 72, 300, 648);
        sidebar.setLayout(null);
        
        // content
        content.setBackground(color.BACKGROUND);
        content.setBounds(300, 72, 980, 648);
        content.setLayout(null);
        
        frame.add(header);
        frame.add(sidebar);
        frame.add(content);
        frame.add(background);
        
        frame.setVisible(true);
    }
}
