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
// import java.time.LocalDate;
// import java.time.LocalDateTime;
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
// import hospital.program.Appointment;
import hospital.program.Department;
import hospital.program.Doctor;
// import hospital.program.Patient;

/**
 *
 * @author LENOVO
 */
public class DepartmentFrame extends BaseFrame {
  private int index = 1;
  private JTable table;
  private DefaultTableModel tableModel;

  public DepartmentFrame() {
    super("Department", true);
    setupContent();
    loadTableData();
  }

  protected void setupContent() {
    JLabel title = new JLabel("Department Panel");
    title.setFont(new Font("Arial", Font.BOLD, 24));
    title.setBounds(20, 20, 250, 30);
    content.add(title);

    JButton addButton = new JButton("Add Departement");
    addButton.setBounds(720, 60, 140, 30);
    addButton.addActionListener(e -> addDepartment());
    content.add(addButton);

    String[] columnNames = {"ID", "Name", "Head Doctor", "Edit", "Delete", "", ""};
    tableModel = new DefaultTableModel(columnNames, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
          return column == 3 || column == 4;
      }
    };
    table = new JTable(tableModel);

    table.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
    table.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JButton("Edit"), "edit"));
    table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
    table.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JButton("Delete"), "delete"));

    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBounds(40, 113, 820, 415);
    content.add(scrollPane);
  }

  private void loadTableData() {
    tableModel.setRowCount(0);
    List<Department> departments = Department.getDepartments();
    for (Department department : departments) {
      // Doctor doctor = department.getHeadDoctor();
      Object[] rowData = {
        index++,
        department.getName(),
        department.getHeadDoctor(),
        "Edit",
        "Delete",
        department.getDepartmentId(),
        department.getHeadDoctor().getId(),
      };
      tableModel.addRow(rowData);
    }

    table.getColumnModel().getColumn(5).setMaxWidth(0);
    table.getColumnModel().getColumn(5).setMinWidth(0);
    table.getColumnModel().getColumn(6).setMinWidth(0);
    table.getColumnModel().getColumn(6).setMaxWidth(0);
  }

  private void addDepartment() {
    JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));

    List<Doctor> doctors = Doctor.getDoctors();

    JTextField nameField = new JTextField();
    JComboBox<Doctor> doctorComboBox = new JComboBox<>(doctors.toArray(new Doctor[0]));

    panel.add(new JLabel("Department Name:"));
    panel.add(nameField);
    panel.add(new JLabel("Head Doctor:"));
    panel.add(doctorComboBox);

    int result = JOptionPane.showConfirmDialog(this, panel, "Add Departement", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    if (result == JOptionPane.OK_OPTION) {
      try {
        String name = nameField.getText().trim();
        Doctor selectedDoctor = (Doctor) doctorComboBox.getSelectedItem();

        if (name == null || selectedDoctor == null) {
          throw new IllegalArgumentException("All fields are required.");
        }

        Department department = new Department(Department.getLastId(), name, selectedDoctor);
        Department.addDepartment(department);

        tableModel.addRow(new Object[]{Department.getLastId(), name, selectedDoctor.getFirstName(), "Edit", "Delete"});

        JOptionPane.showMessageDialog(this, "Appointment added successfully!");
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  private void editDepartment(int row) {
    String name = (String) tableModel.getValueAt(row, 1);
    int departmentId = (int) tableModel.getValueAt(row, 5);
    int doctorId = (int) tableModel.getValueAt(row, 6);

    JTextField nameField = new JTextField(name);
    
    Doctor doctor = Doctor.getDoctorById(doctorId);
    JComboBox<Doctor> doctorComboBox = new JComboBox<>(Doctor.getDoctors().toArray(new Doctor[0]));
    doctorComboBox.setSelectedItem(doctor);

    Object[] message = {
      "Name:", nameField,
      "Head Doctor:", doctorComboBox,
    };

    int option = JOptionPane.showConfirmDialog(
      this, 
      message, 
      "Edit Doctor", 
      JOptionPane.OK_CANCEL_OPTION
    );

    if (option == JOptionPane.OK_OPTION) {
      Doctor selectedDoctor = (Doctor) doctorComboBox.getSelectedItem();

      boolean updated = Department.editDepartmentById(
        departmentId,
        name,
        selectedDoctor
      );

      if (updated) {
        JOptionPane.showMessageDialog(this, "Doctor data updated successfully!");
        loadTableData(); // Load
      } else {
        JOptionPane.showMessageDialog(this, "Failed to update doctor data. Please check the details.");
      }
    }
  }

  private void deleteDepartment(int row) {
    int departmentId = (int) tableModel.getValueAt(row, 0);

    int confirmation = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to delete Department with ID: " + departmentId + "?",
        "Confirm Delete",
        JOptionPane.YES_NO_OPTION
    );

    if (confirmation == JOptionPane.YES_OPTION) {
      boolean deleted = Department.deleteDepartmentById(departmentId);

      if (deleted) {
        tableModel.removeRow(row);

        JOptionPane.showMessageDialog(this, "Department deleted successfully.");
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
      return button.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if ("edit".equals(actionType)) {
        editDepartment(selectedRow);
      } else if ("delete".equals(actionType)) {
        deleteDepartment(selectedRow);
      }
      fireEditingStopped();
    }
  }

}
