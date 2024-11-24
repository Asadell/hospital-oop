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
import hospital.program.Appointment;
import hospital.program.Doctor;
import hospital.program.Patient;

/**
 *
 * @author LENOVO
 */
public class AppointmentFrame extends BaseFrame implements TableHandler {
  private int index;
  private JTable table;
  private DefaultTableModel tableModel;
  String[] statuses = {"Scheduled", "Completed", "Canceled"};
  
  public AppointmentFrame() {
    super("Appointment", true);
    setupContent();
    loadTableData();
  }

  protected void setupContent() {
    JLabel title = new JLabel("Appointment Panel");
    title.setFont(new Font("Arial", Font.BOLD, 24));
    title.setBounds(20, 20, 260, 30);
    content.add(title);

    JButton addButton = new JButton("Add Appointment");
    addButton.setBounds(720, 60, 140, 30);
    addButton.setFocusPainted(false);
    addButton.addActionListener(e -> addAppointment());
    content.add(addButton);

    String[] columnNames = {"NO", "Patient Name", "Doctor Name", "Schedule", "Status", "Edit", "", "", ""};
    tableModel = new DefaultTableModel(columnNames, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
          return column == 5;
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
    List<Appointment> appointments = Appointment.getAppointments();
    for (Appointment appointment : appointments) {
      Object[] rowData = {
        index++,
        appointment.getPatient().toString(),
        appointment.getDoctor().toString(),
        appointment.getSchedule(),
        appointment.getStatus(),
        "Edit",
        appointment.getAppointmentId(),
        appointment.getPatient().getId(),
        appointment.getDoctor().getId(),
      };
      tableModel.addRow(rowData);
    }

    table.getColumnModel().getColumn(6).setMinWidth(0);
    table.getColumnModel().getColumn(6).setMaxWidth(0);
    table.getColumnModel().getColumn(7).setMaxWidth(0);
    table.getColumnModel().getColumn(7).setMinWidth(0);
    table.getColumnModel().getColumn(8).setMinWidth(0);
    table.getColumnModel().getColumn(8).setMaxWidth(0);
  }

  private void addAppointment() {
    JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

    List<Patient> patients = Patient.getPatients();
    List<Doctor> doctors = Doctor.getDoctors();

    JComboBox<Patient> patientComboBox = new JComboBox<>(patients.toArray(new Patient[3]));
    JComboBox<Doctor> doctorComboBox = new JComboBox<>(doctors.toArray(new Doctor[0]));

    JTextField dateField = new JTextField("YYYY-MM-DD");

    JComboBox<String> statusComboBox = new JComboBox<>(statuses);

    panel.add(new JLabel("Patient:"));
    panel.add(patientComboBox);
    panel.add(new JLabel("Doctor:"));
    panel.add(doctorComboBox);
    panel.add(new JLabel("Date (YYYY-MM-DD):"));
    panel.add(dateField);
    panel.add(new JLabel("Status:"));
    panel.add(statusComboBox);

    int result = JOptionPane.showConfirmDialog(this, panel, "Add Appointment", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    if (result == JOptionPane.OK_OPTION) {
      try {
        Patient selectedPatient = (Patient) patientComboBox.getSelectedItem();
        Doctor selectedDoctor = (Doctor) doctorComboBox.getSelectedItem();
        LocalDate appointmentDate = LocalDate.parse(dateField.getText().trim());
        String status = (String) statusComboBox.getSelectedItem();

        if (selectedPatient == null || selectedDoctor == null || status == null) {
          throw new IllegalArgumentException("All fields are required.");
        }

        LocalDateTime dateTime = appointmentDate.atStartOfDay();
        Appointment appointment = new Appointment(Appointment.getLastId(), selectedPatient, selectedDoctor, dateTime, status);
        Appointment.addAppointment(appointment);

        tableModel.addRow(new Object[]{appointment.getAppointmentId(), selectedPatient.getFirstName(), selectedDoctor.getFirstName(), dateTime, status});

        JOptionPane.showMessageDialog(this, "Appointment added successfully!");
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  private void editAppointment(int row) {
    int appointmentId = (int) tableModel.getValueAt(row, 6);
    int patientId = (int) tableModel.getValueAt(row, 7);
    int doctorId = (int) tableModel.getValueAt(row, 8);  
    LocalDateTime appointmentDate = (LocalDateTime) tableModel.getValueAt(row, 3);
    String status = (String) tableModel.getValueAt(row, 4);

    Patient patient = Patient.getPatientById(patientId);
    Doctor doctor = Doctor.getDoctorById(doctorId);

    JComboBox<Patient> patientComboBox = new JComboBox<>(Patient.getPatients().toArray(new Patient[0]));
    patientComboBox.setSelectedItem(patient);

    JComboBox<Doctor> doctorComboBox = new JComboBox<>(Doctor.getDoctors().toArray(new Doctor[0]));
    doctorComboBox.setSelectedItem(doctor);

    JTextField appointmentDateField = new JTextField(appointmentDate.toLocalDate().toString());

    JComboBox<String> statusComboBox = new JComboBox<>(statuses);
    statusComboBox.setSelectedItem(status);

    if (status.equals("Completed") || status.equals("Canceled")) {
      patientComboBox.setEnabled(false);
      statusComboBox.setEnabled(false);
      doctorComboBox.setEnabled(false);
      appointmentDateField.setEditable(false);
    }

    Object[] message = {
      "Patient:", patientComboBox,
      "Doctor:", doctorComboBox,
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
        Doctor selectedDoctor = (Doctor) doctorComboBox.getSelectedItem();
        LocalDateTime newDate = LocalDateTime.parse(appointmentDateField.getText() + "T00:00:00");
        String newStatus = (String) statusComboBox.getSelectedItem();

        boolean updated = Appointment.updateAppointmentById(
          appointmentId,
          selectedPatient,
          selectedDoctor,
          newDate,
          newStatus
        );

        if (updated) {
          JOptionPane.showMessageDialog(this, "Appointment updated successfully!");
          loadTableData();
        } else {
          JOptionPane.showMessageDialog(this, "Failed to update the appointment.");
        }
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
        editAppointment(selectedRow);
      } 
      fireEditingStopped();
    }
  }
}
