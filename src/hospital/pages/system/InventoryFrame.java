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
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import hospital.program.Billing;
import hospital.program.Department;
import hospital.program.Doctor;
import hospital.program.Inventory;
import hospital.program.Patient;

/**
 *
 * @author LENOVO
 */
public class InventoryFrame extends BaseFrame {
  private int index = 1;
  private JTable table;
  private DefaultTableModel tableModel;

  public InventoryFrame() {
    super("Inventory", true);
    setupContent();
    loadTableData();
  }

  protected void setupContent() {
    JLabel title = new JLabel("Inventory Panel");
    title.setFont(new Font("Arial", Font.BOLD, 24));
    title.setBounds(20, 20, 200, 30);
    content.add(title);

    JButton addButton = new JButton("Add Inventory");
    addButton.setBounds(740, 60, 120, 30);
    addButton.addActionListener(e -> addInventory());
    content.add(addButton);

    String[] columnNames = {"ID", "Item Name", "Quantity", "Expiration Date", "Department", "Edit", "Delete", "", ""};
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

  private void addInventory() {
    JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

    List<Department> departments = Department.getDepartments();
    JComboBox<Department> departmentComboBox = new JComboBox<>(departments.toArray(new Department[0]));

    JTextField nameField = new JTextField();
    JTextField quantityField = new JTextField();
    JTextField expirationDateField = new JTextField("YYYY-MM-DD");

    panel.add(new JLabel("Item Name:"));
    panel.add(nameField);
    panel.add(new JLabel("Quantity:"));
    panel.add(quantityField);
    panel.add(new JLabel("Expiration Date (YYYY-MM-DD):"));
    panel.add(expirationDateField);
    panel.add(new JLabel("Department:"));
    panel.add(departmentComboBox);

    int result = JOptionPane.showConfirmDialog(this, panel, "Add Inventory", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    if (result == JOptionPane.OK_OPTION) {
      try {
        String name = nameField.getText().trim();
        String quantityText = quantityField.getText().trim();
        int quantity = Integer.parseInt(quantityText);
        LocalDate expDate = LocalDate.parse(expirationDateField.getText().trim());
        Department department = (Department) departmentComboBox.getSelectedItem();

        if (name.isEmpty() || quantityText.isEmpty()) {
          throw new IllegalArgumentException("All fields are required.");
        }

        LocalDateTime dateTime = expDate.atStartOfDay();
        Inventory inventory = new Inventory(Inventory.getLastId(), name, quantity, dateTime, department);

        Inventory.addInventory(inventory);
        tableModel.addRow(new Object[]{index++, name, quantity, dateTime, department, "Edit", "Delete"});

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

  private void loadTableData() {
    tableModel.setRowCount(0);
    List<Inventory> inventories = Inventory.getInventories();
    for (Inventory inventory : inventories) {
      Object[] rowData = {
        index++,
        inventory.getItemName(),
        inventory.getQuantity(),
        inventory.getExpirationDate(),
        inventory.getDepartment().getName(),
        "Edit",
        "Delete",
        inventory.getInventoryId(),
        inventory.getDepartment().getDepartmentId(),
      };
      tableModel.addRow(rowData);

      table.getColumnModel().getColumn(7).setMinWidth(0);
      table.getColumnModel().getColumn(7).setMaxWidth(0);
      table.getColumnModel().getColumn(8).setMinWidth(0);
      table.getColumnModel().getColumn(8).setMaxWidth(0);
    }
  }

  private void editInventory(int row) {
    int inventoryId = (int) tableModel.getValueAt(row, 7);
    int departmentId = (int) tableModel.getValueAt(row, 8);
    String itemName = (String) tableModel.getValueAt(row, 1);
    int quantity = (int) tableModel.getValueAt(row, 2); 
    LocalDateTime expirationDate = (LocalDateTime) tableModel.getValueAt(row, 3);
    String status = (String) tableModel.getValueAt(row, 4);

    Department department = Department.getDepartmentById(departmentId);

    JComboBox<Department> departmentComboBox = new JComboBox<>(Department.getDepartments().toArray(new Department[0]));
    departmentComboBox.setSelectedItem(department);

    JTextField nameField = new JTextField(itemName);
    JTextField quantityField = new JTextField(String.valueOf(quantity));
    JTextField expirationDateField = new JTextField(expirationDate.toLocalDate().toString());

    Object[] message = {
      "Item Name:", nameField,
      "Quantity:", quantityField,
      "Expiration Date (YYYY-MM-DD):", expirationDateField,
      "Department:", departmentComboBox,
    };

    int option = JOptionPane.showConfirmDialog(
      this, 
      message, 
      "Edit Inventory", 
      JOptionPane.OK_CANCEL_OPTION
    );

    if (option == JOptionPane.OK_OPTION) {
      try {
        
        Department selectedDepartment = (Department) departmentComboBox.getSelectedItem();
        int selectedAmount = quantity;
        try {
            selectedAmount = Integer.parseInt(quantityField.getText()); // Mengonversi String ke int
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Keluar dari metode jika parsing gagal
        }
        LocalDateTime newDate = LocalDateTime.parse(expirationDateField.getText() + "T00:00:00"); // Convert string to LocalDateTime
        String newNameItem = (String) nameField.getText();

        boolean updated = Inventory.updateInventoryById(
          departmentId,
          newNameItem,
          selectedAmount,
          expirationDate,
          selectedDepartment
        );

        if (updated) {
          JOptionPane.showMessageDialog(this, "Inventory updated successfully!");
          loadTableData();
        } else {
          JOptionPane.showMessageDialog(this, "Failed to update the inventory.");
        }
      } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Invalid input. Please check the fields and try again.", "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  private void deleteDoctor(int row) {
    int inventoryId = (int) tableModel.getValueAt(row, 8);

    int confirmation = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to delete Inventory with ID: " + inventoryId + "?",
        "Confirm Delete",
        JOptionPane.YES_NO_OPTION
    );

    if (confirmation == JOptionPane.YES_OPTION) {
      boolean deleted = Inventory.deleteInventoryById(inventoryId);

      if (deleted) {
        tableModel.removeRow(row);

        JOptionPane.showMessageDialog(this, "Inventory deleted successfully.");
      } else {
        JOptionPane.showMessageDialog(this, "Failed to delete Inventory. ID not found.");
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
        editInventory(selectedRow);
      } else if ("delete".equals(actionType)) {
        deleteDoctor(selectedRow);
      }
      fireEditingStopped();
    }
  }
}
