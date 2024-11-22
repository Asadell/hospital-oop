/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.pages.system;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import hospital.pages.base.BaseFrame;
// import hospital.program.Department;
import hospital.program.Patient;

/**
 *
 * @author LENOVO
 */
public class WalletFrame extends BaseFrame { 
  private int index = 1;
  private JTable table;
  private DefaultTableModel tableModel;

  public WalletFrame() {
    super("Patient", true);
    setupContent();
    loadTableData();
  }

  protected void setupContent() {
    JLabel title = new JLabel("Dashboard Panel");
    title.setFont(new Font("Arial", Font.BOLD, 24));
    title.setBounds(20, 20, 200, 30);
    content.add(title);

    JButton addButton = new JButton("Add Wallet");
    addButton.setBounds(740, 60, 120, 30);
    addButton.addActionListener(e -> addWallet());
    content.add(addButton);

    // String[] columnNames = {"ID", "Full Name", "Balance", "Edit", "Delete", ""};
    String[] columnNames = {"ID", "Full Name", "Balance"};
    tableModel = new DefaultTableModel(columnNames, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
          return column == 3;
      }
    };

    table = new JTable(tableModel);

    // table.getColumnModel().getColumn(8).setCellRenderer(new ButtonRenderer());
    // table.getColumnModel().getColumn(8).setCellEditor(new ButtonEditor(new JButton("Edit"), "edit"));
    // table.getColumnModel().getColumn(9).setCellRenderer(new ButtonRenderer());
    // table.getColumnModel().getColumn(9).setCellEditor(new ButtonEditor(new JButton("Delete"), "delete"));

    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBounds(40, 113, 820, 415);
    content.add(scrollPane);
  }

  private void addWallet() {
    JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));

    List<Patient> patients = Patient.getPatients();
    JComboBox<Patient> patientComboBox = new JComboBox<>(patients.toArray(new Patient[0]));
    JTextField balanceField = new JTextField();

    panel.add(new JLabel("Patient Name:"));
    panel.add(patientComboBox);
    panel.add(new JLabel("Balance:"));
    panel.add(balanceField);

    int result = JOptionPane.showConfirmDialog(this, panel, "Add Wallet", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    if (result == JOptionPane.OK_OPTION) {
      try {
        Patient patient = (Patient) patientComboBox.getSelectedItem();
        String balanceText = balanceField.getText().trim();
        int newBalance = Integer.parseInt(balanceText);

        if (balanceText.isEmpty()) {
            throw new IllegalArgumentException("All fields are required.");
        }

        if (!patient.hasWallet()) patient.createWallet();
        patient.getWallet().addBalance(newBalance);
        tableModel.addRow(new Object[]{index++, patient.getFirstName() + " " + patient.getLastName(), patient.getWallet().getBalance(), "Edit", "Delete"});

        JOptionPane.showMessageDialog(this, "Wallet added successfully!");
      } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
      } catch (IllegalArgumentException ex) {
          JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      } catch (Exception ex) {
          JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  private void loadTableData() {
    tableModel.setRowCount(0);
    List<Patient> patients = Patient.getPatientsWithWallet();
    for (Patient patient : patients) {
      Object[] rowData = {
        index++,
        patient.getFirstName() + " " + patient.getLastName(),
        patient.getWallet().getBalance(),
        // "Edit",
        // "Delete",
        // patient.getId(),
      };
      tableModel.addRow(rowData);
      // table.getColumnModel().getColumn(5).setMinWidth(0);
      // table.getColumnModel().getColumn(5).setMaxWidth(0);
    }
  }
}
