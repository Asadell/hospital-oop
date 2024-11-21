/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.pages.system;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;

import hospital.pages.base.BaseFrame;

/**
 *
 * @author LENOVO
 */
public class DepartmentFrame extends BaseFrame {
  public DepartmentFrame() {
    super("Department", true);
    setupContent();
  }

  protected void setupContent() {
    JLabel title = new JLabel("Department Panel");
    title.setFont(new Font("Arial", Font.BOLD, 24));
    title.setBounds(20, 20, 200, 30);
    content.add(title);

    JButton addButton = new JButton("Add Departement");
    addButton.setBounds(740, 60, 120, 30);
    addButton.addActionListener(e -> addDepartment());
    content.add(addButton);
  }

  private void addDepartment() {
    System.out.println("duh");
  }
}
