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
import hospital.program.Doctor;
import hospital.program.Doctor;
import hospital.program.Doctor;

/**
 *
 * @author LENOVO
 */
public class DoctorFrame extends BaseFrame {
  private int index = 1;
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
    addButton.addActionListener(e -> addDoctor());
    content.add(addButton);

    String[] columnNames = {"ID", "First Name", "Last Name", "Specialization", "Schedule", "Edit", "Delete"};
    tableModel = new DefaultTableModel(columnNames, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
          return column == 5 || column == 6;
      }
    };
    table = new JTable(tableModel);

    table.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
    table.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JButton("Edit"), "edit"));
    table.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
    table.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new JButton("Delete"), "delete"));

    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBounds(40, 113, 820, 415);
    content.add(scrollPane);
  }

  private void loadTableData() {
    tableModel.setRowCount(0);
    List<Doctor> doctors = Doctor.getDoctors();
    for (Doctor doctor : doctors) {
      Object[] rowData = {
        doctor.getId(),
        doctor.getFirstName(),
        doctor.getLastName(),
        doctor.getSpecialization(),
        doctor.getSchedule(),
        "Edit",
        "Delete"
      };
      tableModel.addRow(rowData);
    }
  }

  private void addDoctor() {
    JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
    JTextField firstNameField = new JTextField();
    JTextField lastNameField = new JTextField();
    JTextField specializationField = new JTextField();
    JTextField scheduleField = new JTextField();

    panel.add(new JLabel("First Name:"));
    panel.add(firstNameField);
    panel.add(new JLabel("Last Name:"));
    panel.add(lastNameField);
    panel.add(new JLabel("Specializaion:"));
    panel.add(specializationField);
    panel.add(new JLabel("Schedule (YYYY-MM-DD):"));
    panel.add(scheduleField);

    int result = JOptionPane.showConfirmDialog(this, panel, "Add Doctor", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    if (result == JOptionPane.OK_OPTION) {
      try {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String specialization = specializationField.getText().trim();
        String schedule = scheduleField.getText().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || specialization.isEmpty() || schedule.isEmpty()) {
            throw new IllegalArgumentException("All fields are required.");
        }

        Doctor.addDoctor(firstName, lastName, specialization, schedule);
        tableModel.addRow(new Object[]{index++, firstName, lastName, specialization, schedule, "Edit", "Delete"});

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
        editDoctor(selectedRow);
      } else if ("delete".equals(actionType)) {
        deleteDoctor(selectedRow);
      }
      fireEditingStopped();
    }
  }

  private void editDoctor(int row) {
    int doctorId = (int) tableModel.getValueAt(row, 0);
    String firstName = (String) tableModel.getValueAt(row, 1);
    String lastName = (String) tableModel.getValueAt(row, 2);
    String specialization = (String) tableModel.getValueAt(row, 3);
    String schedule = (String) tableModel.getValueAt(row, 4);

    JTextField firstNameField = new JTextField(firstName);
    JTextField lastNameField = new JTextField(lastName);
    JTextField specializationField = new JTextField(specialization);
    JTextField scheduleField = new JTextField(schedule);

    Object[] message = {
      "First Name:", firstNameField,
      "Last Name:", lastNameField,
      "Specializaion:", specializationField,
      "ScheduleDate of Birth (YYYY-MM-DD):", scheduleField,
    };

    int option = JOptionPane.showConfirmDialog(
      this, 
      message, 
      "Edit Doctor", 
      JOptionPane.OK_CANCEL_OPTION
    );

    if (option == JOptionPane.OK_OPTION) {
      boolean updated = Doctor.editDoctorById(
        doctorId,
        firstNameField.getText(),
        lastNameField.getText(),
        specializationField.getText(),
        scheduleField.getText()
      );

      if (updated) {
        JOptionPane.showMessageDialog(this, "Doctor data updated successfully!");
        loadTableData(); // Load
      } else {
        JOptionPane.showMessageDialog(this, "Failed to update doctor data. Please check the details.");
      }
    }
  }

  private void deleteDoctor(int row) {
    int doctorId = (int) tableModel.getValueAt(row, 0);

    int confirmation = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to delete Doctor with ID: " + doctorId + "?",
        "Confirm Delete",
        JOptionPane.YES_NO_OPTION
    );

    if (confirmation == JOptionPane.YES_OPTION) {
      boolean deleted = Doctor.deleteDoctorById(doctorId);

      if (deleted) {
        tableModel.removeRow(row);

        JOptionPane.showMessageDialog(this, "Doctor deleted successfully.");
      } else {
        JOptionPane.showMessageDialog(this, "Failed to delete Doctor. ID not found.");
      }
    }
  }
}
