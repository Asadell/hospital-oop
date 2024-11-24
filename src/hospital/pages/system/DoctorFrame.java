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
import hospital.program.Department;
import hospital.program.Doctor;

/**
 *
 * @author LENOVO
 */
public class DoctorFrame extends BaseFrame implements TableHandler {
  private int index;
  private JTable table;
  private DefaultTableModel tableModel;

  public DoctorFrame() {
    super("Doctor", true);
    setupContent();
    loadTableData();
  }

  protected void setupContent() {
    JLabel title = new JLabel("Doctor Panel");
    title.setFont(new Font("Arial", Font.BOLD, 24));
    title.setBounds(20, 20, 200, 30);
    content.add(title);

    JButton addButton = new JButton("Add Doctor");
    addButton.setBounds(740, 60, 120, 30);
    addButton.setFocusPainted(false);
    addButton.addActionListener(e -> addDoctor());
    content.add(addButton);

    String[] columnNames = {"NO", "First Name", "Last Name", "Medical License Number", "Department", "Edit", "Delete", ""};
    tableModel = new DefaultTableModel(columnNames, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return column == 5 || column == 6;
      }
    };
    table = new JTable(tableModel);

    JButton editButton = new JButton("Edit");
    editButton.setFocusPainted(false);
    JButton deleteButton = new JButton("Delete");
    deleteButton.setFocusPainted(false);
    table.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
    table.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(editButton, "edit"));
    table.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
    table.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(deleteButton, "delete"));

    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBounds(40, 113, 820, 415);
    content.add(scrollPane);
  }

  public void loadTableData() {
    index = 1;
    tableModel.setRowCount(0);
    List<Doctor> doctors = Doctor.getDoctors();
    for (Doctor doctor : doctors) {
      Object[] rowData = {
        index++,
        doctor.getFirstName(),
        doctor.getLastName(),
        doctor.getMedicalLicenseNumber(),
        doctor.getDepartment().getName(),
        "Edit",
        "Delete",
        doctor.getId(),
      };
      tableModel.addRow(rowData);
    }
    table.getColumnModel().getColumn(7).setMinWidth(0);
    table.getColumnModel().getColumn(7).setMaxWidth(0);
  }

  private void addDoctor() {
    JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

    List<Department> departments = Department.getDepartments();
    JComboBox<Department> departmentComboBox = new JComboBox<>(departments.toArray(new Department[0]));

    JTextField firstNameField = new JTextField();
    JTextField lastNameField = new JTextField();
    JTextField medicalLicenseNumberField = new JTextField();

    panel.add(new JLabel("First Name:"));
    panel.add(firstNameField);
    panel.add(new JLabel("Last Name:"));
    panel.add(lastNameField);
    panel.add(new JLabel("Medical License Number:"));
    panel.add(medicalLicenseNumberField);
    panel.add(new JLabel("Department"));
    panel.add(departmentComboBox);

    int result = JOptionPane.showConfirmDialog(this, panel, "Add Doctor", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    if (result == JOptionPane.OK_OPTION) {
      try {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String medicalLicenseNumber = medicalLicenseNumberField.getText().trim();
        Department selectedDepartment = (Department) departmentComboBox.getSelectedItem();

        if (firstName.isEmpty() || lastName.isEmpty() || medicalLicenseNumber.isEmpty() || selectedDepartment == null) {
          throw new IllegalArgumentException("All fields are required.");
        }

        if (checkLicense(medicalLicenseNumber)) throw new Exception("Duplicate medical license number detected.");

        Doctor.addDoctor(firstName, lastName, medicalLicenseNumber, selectedDepartment);

        tableModel.addRow(new Object[]{index++, firstName, lastName, medicalLicenseNumber, "Edit", "Delete"});
        loadTableData();

        JOptionPane.showMessageDialog(this, "Doctor added successfully!");
      } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
      } catch (IllegalArgumentException ex) {
          JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      } catch (Exception ex) {
          JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  private void editDoctor(int row) {
    int doctorId = (int) tableModel.getValueAt(row, 7);
    String firstName = (String) tableModel.getValueAt(row, 1);
    String lastName = (String) tableModel.getValueAt(row, 2);
    String medicalLicenseNumber = (String) tableModel.getValueAt(row, 3);
    Doctor currentDoctor = Doctor.getDoctorById(doctorId);

    Department oldDepartment = Department.findDepartmentByDoctor(currentDoctor);
    JComboBox<Department> departmentComboBox = new JComboBox<>(Department.getDepartments().toArray(new Department[0]));
    departmentComboBox.setSelectedItem(oldDepartment);

    JTextField firstNameField = new JTextField(firstName);
    JTextField lastNameField = new JTextField(lastName);
    JTextField medicalLicenseNumberField = new JTextField(medicalLicenseNumber);

    Object[] message = {
      "First Name:", firstNameField,
      "Last Name:", lastNameField,
      "Specializaion:", medicalLicenseNumberField,
      "Department:", departmentComboBox,
    };

    int option = JOptionPane.showConfirmDialog(
      this, 
      message,
      "Edit Doctor", 
      JOptionPane.OK_CANCEL_OPTION
    );

    try {
      String firstNameAns = firstNameField.getText().trim();
      String lastNameAns = lastNameField.getText().trim();
      String medicalLicenseNumberAns = medicalLicenseNumberField.getText().trim();
      Department newDepartment = (Department) departmentComboBox.getSelectedItem();

      if (firstNameAns.isEmpty() || lastNameAns.isEmpty() || medicalLicenseNumberAns.isEmpty()) {
        throw new IllegalArgumentException("All fields are required.");
      }
      if (checkLicense(medicalLicenseNumber)) throw new Exception("Duplicate medical license number detected.");

      if (option == JOptionPane.OK_OPTION) {
        boolean updated = Doctor.editDoctorById(
          doctorId,
          firstNameAns,
          lastNameAns,
          medicalLicenseNumberAns,
          newDepartment
        );

        if (updated) {
          loadTableData();
          JOptionPane.showMessageDialog(this, "Doctor data updated successfully!");
        } else {
          JOptionPane.showMessageDialog(this, "Failed to update doctor data. Please check the details.");
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

  private void deleteDoctor(int row) {
    int doctorId = (int) tableModel.getValueAt(row, 7);
    String firstName = (String) tableModel.getValueAt(row, 1);
    String lastName = (String) tableModel.getValueAt(row, 2);

    int confirmation = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to delete Dr. " + firstName + " " + lastName + "?",
        "Confirm Delete",
        JOptionPane.YES_NO_OPTION
    );

    if (confirmation == JOptionPane.YES_OPTION) {
      boolean deleted = Doctor.deleteDoctorById(doctorId);

      if (deleted) {
        tableModel.removeRow(row);
        loadTableData();

        JOptionPane.showMessageDialog(this, "Doctor deleted successfully.");
      } else {
        JOptionPane.showMessageDialog(this, "Failed to delete Doctor. ID not found.");
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
        editDoctor(selectedRow);
      } else if ("delete".equals(actionType)) {
        deleteDoctor(selectedRow);
      }
      fireEditingStopped();
    }
  }

  public boolean checkLicense(String licenseNumber) {
    return Doctor.isMedicalLicenseNumberUsed(licenseNumber);
  }
}
