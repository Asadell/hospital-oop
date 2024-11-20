/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.pages.base;

import javax.swing.JFrame;
import javax.swing.JPanel;

import hospital.util.ColorPalette;

/**
 *
 * @author LENOVO
 */
public abstract class BaseFrame extends JFrame {
    private ColorPalette color = new ColorPalette();
    private JPanel sidebar = new JPanel();
    private JPanel header = new JPanel();
    private JPanel content = new JPanel();
    
    private boolean isDashboard;
    
    public BaseFrame(String title, boolean isDashboard) {
        super(title);
        this.isDashboard = isDashboard;
        
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setLayout(null);
//        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        initLayout();
    }

    protected abstract void setupContent();
    
    private void initLayout() {
        if (isDashboard) {
            header.setBackground(color.WHITE);
            header.setBounds(0, 0, 1280, 72);
            header.setLayout(null);

            sidebar.setBackground(color.WHITE);
            sidebar.setBounds(0, 72, 300, 648);
            sidebar.setLayout(null);

            add(header);
            add(sidebar);

            content.setBounds(300, 72, 980, 648);
        } else {
            content.setBounds(0, 0, 1280, 720);
        }
        content.setBackground(color.BACKGROUND);
        content.setLayout(null);

        add(content);

        setVisible(true);
    }
}
