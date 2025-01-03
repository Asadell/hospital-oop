/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.pages.system;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import hospital.pages.base.BaseFrame;
// import hospital.program.Appointment;
import hospital.program.Billing;
// import hospital.program.Doctor;
import hospital.program.Patient;

/**
 *
 * @author LENOVO
 */
public class BillingFrame extends BaseFrame implements TableHandler {
  private int index;
  private JTable table;
  private DefaultTableModel tableModel;

  public BillingFrame() {
    super("Billing", true);
    setupContent();
    loadTableData();
  }

  protected void setupContent() {
    JLabel title = new JLabel("Billing Panel");
    title.setFont(new Font("Arial", Font.BOLD, 24));
    title.setBounds(20, 20, 200, 30);
    content.add(title);

    String[] columnNames = {"NO", "Patient Name", "Amount", "Billing Date", "Status", "Edit", "", ""};
    tableModel = new DefaultTableModel(columnNames, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
          return column == 4 || column == 5;
      }
    };
    table = new JTable(tableModel);

    JButton editButton = new JButton("Edit");
    editButton.setFocusPainted(false);
    table.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
    table.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(editButton, "edit"));

    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBounds(40, 113, 820, 415);
    content.add(scrollPane);
  }

  public void loadTableData() {
    index = 1;
    tableModel.setRowCount(0);
    List<Billing> billings = Billing.getBillings();
    for (Billing billing : billings) {
      // Patient patient = billing.getPatient();
      Object[] rowData = {
        index++,
        billing.getPatient().toString(),
        billing.getAmount(),
        billing.getBillingDate(),
        billing.getPaymentStatus(),
        "Edit",
        billing.getBillingId(),
        billing.getPatient().getId(),
      };
      tableModel.addRow(rowData);
    }

    table.getColumnModel().getColumn(6).setMinWidth(0);
    table.getColumnModel().getColumn(6).setMaxWidth(0);
    table.getColumnModel().getColumn(7).setMaxWidth(0);
    table.getColumnModel().getColumn(7).setMinWidth(0);
  }

  private void editBilling(int row) {
    int appointmentId = (int) tableModel.getValueAt(row, 6);
    int patientId = (int) tableModel.getValueAt(row, 7);
    double amount = (double) tableModel.getValueAt(row, 2); 
    LocalDateTime appointmentDate = (LocalDateTime) tableModel.getValueAt(row, 3);
    String status = (String) tableModel.getValueAt(row, 4);

    Patient patient = Patient.getPatientById(patientId);

    JComboBox<Patient> patientComboBox = new JComboBox<>(Patient.getPatients().toArray(new Patient[0]));
    patientComboBox.setSelectedItem(patient);

    JTextField amountField = new JTextField(String.valueOf(amount));
    JTextField appointmentDateField = new JTextField(appointmentDate.toLocalDate().toString());

    String[] statuses = {"Unpaid", "Paid"};
    JComboBox<String> statusComboBox = new JComboBox<>(statuses);
    statusComboBox.setSelectedItem(status);

    patientComboBox.setEnabled(false);
    amountField.setEditable(false);
    appointmentDateField.setEditable(false);
    if (status.equals("Paid")) {
      statusComboBox.setEnabled(false);
    }

    Object[] message = {
      "Patient:", patientComboBox,
      "Amount:", amountField,
      "Appointment Date (YYYY-MM-DD):", appointmentDateField,
      "Status:", statusComboBox,
    };

    int option = JOptionPane.showConfirmDialog(
      this, 
      message, 
      "Edit Appointment", 
      JOptionPane.OK_CANCEL_OPTION
    );

    if (option == JOptionPane.OK_OPTION) {
      try {
        Patient selectedPatient = (Patient) patientComboBox.getSelectedItem();
        if (selectedPatient.getWallet() == null) throw new IllegalArgumentException("Wallet Not Found");
        
        double selectedAmount = amount;
        if (amountField.getText().matches("^-.*"))  throw new IllegalArgumentException("Amount cannot start with a '-' character.");
        try {
          selectedAmount = Double.parseDouble(amountField.getText()); 
        } catch (NumberFormatException e) {
          JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
          return; 
        }
        if(selectedAmount < 0) {
          throw new IllegalArgumentException("Amount cannot be less than zero");
        }
        
        if (selectedPatient.getWallet().getBalance() < selectedAmount) throw new IllegalArgumentException("Insufficient balance!");
        else {
          selectedPatient.getWallet().deductBalance(selectedAmount);
        }

        LocalDateTime newDate = LocalDateTime.parse(appointmentDateField.getText() + "T00:00:00"); 
        String newStatus = (String) statusComboBox.getSelectedItem();


        boolean updated = Billing.updateBillingById(
          appointmentId,
          selectedPatient,
          selectedAmount,
          newDate,
          newStatus
        );

        if (updated) {
          JOptionPane.showMessageDialog(this, "Billing updated successfully!");
          loadTableData();
        } else {
          JOptionPane.showMessageDialog(this, "Failed to update the billing.");
        }
      } catch (IllegalArgumentException ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Invalid input. Please check the fields and try again.", "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  private class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
      setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
      setText(value != null ? value.toString() : "Button");
      return this;
    }
  }

  private class ButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
    private JButton button;
    private String actionType;
    private int selectedRow;

    public ButtonEditor(JButton button, String actionType) {
      this.button = button;
      this.actionType = actionType;
      this.button.addActionListener(this);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
      selectedRow = row;
      button.setText(value != null ? value.toString() : "Bottun");
      return button;
    }

    @Override
    public Object getCellEditorValue() {
      return button.getText().trim();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if ("edit".equals(actionType)) {
        editBilling(selectedRow);
      } 
      fireEditingStopped();
    }
  }
}
