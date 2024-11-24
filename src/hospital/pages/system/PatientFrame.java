/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.pages.system;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import hospital.pages.base.BaseFrame;
import hospital.program.Patient;

/**
 *
 * @author LENOVO
 */
public class PatientFrame extends BaseFrame implements TableHandler {
  private static int index;
  private JTable table;
  private DefaultTableModel tableModel;
  String[] genders = {"Male", "Female"};

  public PatientFrame() {
    super("Patient", true);
    setupContent();
    loadTableData();
  }

  protected void setupContent() {
    JLabel title = new JLabel("Patient Panel");
    title.setFont(new Font("Arial", Font.BOLD, 24));
    title.setBounds(20, 20, 200, 30);
    content.add(title);

    JButton addButton = new JButton("Add Patient");
    addButton.setBounds(740, 60, 120, 30);
    addButton.setFocusPainted(false);
    addButton.addActionListener(e -> addPatient());
    content.add(addButton);

    String[] columnNames = {"NO", "First Name", "Last Name", "DOB", "Gender", "Address", "Phone", "Insurance", "Edit", "Delete", ""};
    tableModel = new DefaultTableModel(columnNames, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
          return column == 8 || column == 9;
      }
    };

    table = new JTable(tableModel);

    JButton editButton = new JButton("Edit");
    editButton.setFocusPainted(false);
    JButton deleteButton = new JButton("Delete");
    deleteButton.setFocusPainted(false);
    table.getColumnModel().getColumn(8).setCellRenderer(new ButtonRenderer());
    table.getColumnModel().getColumn(8).setCellEditor(new ButtonEditor(editButton, "edit"));
    table.getColumnModel().getColumn(9).setCellRenderer(new ButtonRenderer());
    table.getColumnModel().getColumn(9).setCellEditor(new ButtonEditor(deleteButton, "delete"));

    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBounds(40, 113, 820, 415);
    content.add(scrollPane);
  }

  public void loadTableData() {
    tableModel.setRowCount(0);
    List<Patient> patients = Patient.getPatients();
    index = 1;
    for (Patient patient : patients) {
      Object[] rowData = {
        index++,
        patient.getFirstName(),
        patient.getLastName(),
        patient.getDob(),
        patient.getGender(),
        patient.getAddress(),
        patient.getPhone(),
        patient.getInsuranceInfo(),
        "Edit",
        "Delete",
        patient.getId(),
      };
      tableModel.addRow(rowData);
    }
    table.getColumnModel().getColumn(10).setMinWidth(0);
    table.getColumnModel().getColumn(10).setMaxWidth(0);
  }

  private void addPatient() {
    JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
    JTextField firstNameField = new JTextField();
    JTextField lastNameField = new JTextField();
    JTextField dobField = new JTextField("YYYY-MM-DD");
    JComboBox<String> gendersComboBox = new JComboBox<>(genders);
    JTextField addressField = new JTextField();
    JTextField phoneField = new JTextField();
    JTextField insuranceField = new JTextField();

    panel.add(new JLabel("First Name:"));
    panel.add(firstNameField);
    panel.add(new JLabel("Last Name:"));
    panel.add(lastNameField);
    panel.add(new JLabel("Date of Birth (YYYY-MM-DD):"));
    panel.add(dobField);
    panel.add(new JLabel("Gender (Male/Female):"));
    panel.add(gendersComboBox);
    panel.add(new JLabel("Address:"));
    panel.add(addressField);
    panel.add(new JLabel("Phone Number (08..):"));
    panel.add(phoneField);
    panel.add(new JLabel("Insurance Info:"));
    panel.add(insuranceField);

    int result = JOptionPane.showConfirmDialog(this, panel, "Add Patient", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    if (result == JOptionPane.OK_OPTION) {
      try {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String dob = dobField.getText().trim();
        String gender = (String) gendersComboBox.getSelectedItem();
        String address = addressField.getText().trim();
        String phone = phoneField.getText().trim();
        String insuranceInfo = insuranceField.getText().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || dob.isEmpty() || gender.isEmpty() || address.isEmpty() || phone.isEmpty() || insuranceInfo.isEmpty()) {
          throw new IllegalArgumentException("All fields are required.");
        }

        if (!phone.matches("^08\\d{8,15}$")) {
          throw new IllegalArgumentException("Phone number format is invalid.");
        }
        if (checkPhone(phone)) throw new Exception("Duplicate medical license number detected.");

        Patient.addPatient(firstName, lastName, dob, gender, address, phone, insuranceInfo);
        // tableModel.addRow(new Object[]{PatientFrame.index++, firstName, lastName, dob, gender, address, phone, insuranceInfo, "Edit", "Delete"});
        loadTableData();
        
        JOptionPane.showMessageDialog(this, "Patient added successfully!");
      } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
      } catch (IllegalArgumentException ex) {
          JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      } catch (Exception ex) {
          JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  private void editPatient(int row) {
    int patientId = (int) tableModel.getValueAt(row, 10);
    String firstName = (String) tableModel.getValueAt(row, 1);
    String lastName = (String) tableModel.getValueAt(row, 2);
    String dob = (String) tableModel.getValueAt(row, 3);
    String gender = (String) tableModel.getValueAt(row, 4);
    String address = (String) tableModel.getValueAt(row, 5);
    String phone = (String) tableModel.getValueAt(row, 6);
    String insuranceInfo = (String) tableModel.getValueAt(row, 7);

    JTextField firstNameField = new JTextField(firstName);
    JTextField lastNameField = new JTextField(lastName);
    JTextField dobField = new JTextField(dob);
    // JTextField genderField = new JTextField(gender);
    JTextField addressField = new JTextField(address);
    JTextField phoneField = new JTextField(phone);
    JTextField insuranceField = new JTextField(insuranceInfo);

    JComboBox<String> gendersComboBox = new JComboBox<>(genders);
    gendersComboBox.setSelectedItem(gender);

    Object[] message = {
      "First Name:", firstNameField,
      "Last Name:", lastNameField,
      "Date of Birth (YYYY-MM-DD):", dobField,
      "Gender:", gendersComboBox,
      "Address:", addressField,
      "Phone (08..):", phoneField,
      "Insurance Info:", insuranceField
    };

    int option = JOptionPane.showConfirmDialog(
      this, 
      message, 
      "Edit Patient", 
      JOptionPane.OK_CANCEL_OPTION
    );

    try {
      String firstNameAns = firstNameField.getText().trim();
      String lastNameAns = lastNameField.getText().trim();
      String dobAns = dobField.getText().trim();
      String genderAns = (String) gendersComboBox.getSelectedItem();
      String addressAns = addressField.getText().trim();
      String phoneAns = phoneField.getText().trim();
      String insuranceInfoAns = insuranceField.getText().trim();

      if (firstNameAns.isEmpty() || lastNameAns.isEmpty() || dobAns.isEmpty() || genderAns.isEmpty() || addressAns.isEmpty() || phoneAns.isEmpty() || insuranceInfoAns.isEmpty()) {
        throw new IllegalArgumentException("All fields are required.");
      }

      System.out.println("masuk?");
      if (!phoneAns.matches("^08\\d{8,15}$")) {
        throw new IllegalArgumentException("Phone number format is invalid.");
      }
      
      
      if (option == JOptionPane.OK_OPTION) {
        if (checkPhone(phoneAns)) throw new Exception("Duplicate medical license number detected.");
        boolean updated = Patient.editPatientById(
          patientId,
          firstNameAns,
          lastNameAns,
          dobAns,
          genderAns,
          addressAns,
          phoneAns,
          insuranceInfoAns
        );
  
        if (updated) {
          loadTableData(); // Load
          JOptionPane.showMessageDialog(this, "Patient data updated successfully!");
        } else {
          JOptionPane.showMessageDialog(this, "Failed to update patient data. Please check the details.");
        }
      }
    } catch (NumberFormatException ex) {
      JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (IllegalArgumentException ex) {
      JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  private void deletePatient(int row) {
    int patientId = (int) tableModel.getValueAt(row, 10);
    String firstName = (String) tableModel.getValueAt(row, 1);
    String lastName = (String) tableModel.getValueAt(row, 2);

    int confirmation = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to delete patient: " + firstName + " " + lastName + "?",
        "Confirm Delete",
        JOptionPane.YES_NO_OPTION
    );

    if (confirmation == JOptionPane.YES_OPTION) {
      boolean deleted = Patient.deletePatientById(patientId);

      if (deleted) {
        tableModel.removeRow(row);
        loadTableData();

        JOptionPane.showMessageDialog(this, "Patient deleted successfully.");
      } else {
        JOptionPane.showMessageDialog(this, "Failed to delete Patient. ID not found.");
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
        editPatient(selectedRow);
      } else if ("delete".equals(actionType)) {
        deletePatient(selectedRow);
      }
      fireEditingStopped();
    }
  }

  public boolean checkPhone(String phoneNumber) {
    return Patient.isPhoneNumberUsed(phoneNumber);
  }
}
