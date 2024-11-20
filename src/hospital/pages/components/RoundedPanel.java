/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.pages.components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 *
 * @author LENOVO
 */
public class RoundedPanel extends JPanel {
    private int arc = 20; // Sudut rounded
    private Color backgroundColor = Color.WHITE; // Warna latar belakang panel
    private Color shadowColor = new Color(0, 0, 0, 50); // Warna shadow dengan transparansi

    public RoundedPanel() {
        setOpaque(false); // Agar panel dapat menggambar latar belakang kustom
        setPreferredSize(new Dimension(500, 350)); // Ukuran panel
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Mengaktifkan anti-aliasing untuk tampilan yang lebih halus
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Menggambar shadow terlebih dahulu
        g2d.setColor(shadowColor);
        g2d.fillRoundRect(20, 20, getWidth() - 40, getHeight() - 40, arc, arc); // Shadow dengan offset yang lebih besar

        // Menggambar panel dengan border rounded
        g2d.setColor(backgroundColor);
        g2d.fillRoundRect(0, 0, getWidth() - 10, getHeight() - 10, arc, arc);// Panel yang sebenarnya
    }
}
