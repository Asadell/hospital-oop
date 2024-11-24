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
import hospital.program.Department;

/**
 *
 * @author LENOVO
 */
public class DepartmentFrame extends BaseFrame implements TableHandler {
  private int index;
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
    addButton.setFocusPainted(false);
    addButton.addActionListener(e -> addDepartment());
    content.add(addButton);

    String[] columnNames = {"NO", "Name", "Edit", "Delete", ""};
    tableModel = new DefaultTableModel(columnNames, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
          return column == 3 || column == 2;
      }
    };
    table = new JTable(tableModel);

    JButton editButton = new JButton("Edit");
    editButton.setFocusPainted(false);
    JButton deleteButton = new JButton("Delete");
    deleteButton.setFocusPainted(false);
    table.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
    table.getColumnModel().getColumn(2).setCellEditor(new ButtonEditor(editButton, "edit"));
    table.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
    table.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(deleteButton, "delete"));

    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBounds(40, 113, 820, 415);
    content.add(scrollPane);
  }

  public void loadTableData() {
    index = 1;
    tableModel.setRowCount(0);
    List<Department> departments = Department.getDepartments();
    for (Department department : departments) {
      Object[] rowData = {
        index++,
        department.getName(),
        "Edit",
        "Delete",
        department.getDepartmentId(),
      };
      tableModel.addRow(rowData);
    }

    table.getColumnModel().getColumn(4).setMaxWidth(0);
    table.getColumnModel().getColumn(4).setMinWidth(0);
  }

  private void addDepartment() {
    JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));

    JTextField nameField = new JTextField();

    panel.add(new JLabel("Department Name:"));
    panel.add(nameField);

    int result = JOptionPane.showConfirmDialog(this, panel, "Add Departement", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    if (result == JOptionPane.OK_OPTION) {
      try {
        String name = nameField.getText().trim();

        if (name.isEmpty()) {
          throw new IllegalArgumentException("All fields are required.");
        }
        if (checkName(name)) throw new Exception("Duplicate department name detected.");

        Department department = new Department(Department.getLastId(), name);
        Department.addDepartment(department);

        loadTableData(); // Load
        JOptionPane.showMessageDialog(this, "Department added successfully!");
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  private void editDepartment(int row) {
    String name = (String) tableModel.getValueAt(row, 1);
    int departmentId = (int) tableModel.getValueAt(row, 4);

    JTextField nameField = new JTextField(name);

    Object[] message = {
      "Name:", nameField,
    };

    int option = JOptionPane.showConfirmDialog(
      this, 
      message, 
      "Edit Doctor", 
      JOptionPane.OK_CANCEL_OPTION
    );

    try {
      String nameAns = nameField.getText().trim();
      if (nameAns.isEmpty()) throw new IllegalArgumentException("All fields are required.");
      if (checkName(nameAns)) throw new Exception("Duplicate department name detected.");

      if (option == JOptionPane.OK_OPTION) {
        boolean updated = Department.editDepartmentById(
          departmentId,
          nameAns
        );
  
        if (updated) {
          loadTableData(); // Load
          JOptionPane.showMessageDialog(this, "Department data updated successfully!");
        } else {
          JOptionPane.showMessageDialog(this, "Failed to update Department data. Please check the details.");
        }
      }
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  private void deleteDepartment(int row) {
    int departmentId = (int) tableModel.getValueAt(row, 4);

    int confirmation = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to delete Department with ID: " + departmentId + "?",
        "Confirm Delete",
        JOptionPane.YES_NO_OPTION
    );

    try {
      Department curDepartment = Department.getDepartmentById(departmentId);
      if (curDepartment.getDoctors() != null) {
        throw new Exception("Cannot delete the department as it still has assigned doctors.");
      } 
      if (confirmation == JOptionPane.YES_OPTION) {
        boolean deleted = Department.deleteDepartmentById(departmentId);
  
        if (deleted) {
          tableModel.removeRow(row);
          loadTableData();
  
          JOptionPane.showMessageDialog(this, "Department deleted successfully.");
        } else {
          JOptionPane.showMessageDialog(this, "Failed to delete Doctor. ID not found.");
        }
      }
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        editDepartment(selectedRow);
      } else if ("delete".equals(actionType)) {
        deleteDepartment(selectedRow);
      }
      fireEditingStopped();
    }
  }

  public boolean checkName(String name) {
    return Department.isDepartmentNameUsed(name);
  }
}
