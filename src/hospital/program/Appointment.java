/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.program;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class Appointment { // Implementation
  private int appointmentId;
  private Patient patient; // Association
  private Doctor doctor;   // Association
  private LocalDateTime appointmentDate;
  private String status;
  private static int lastId = 1;
  private static List<Appointment> appointments = new ArrayList<>();

  public Appointment(int appointmentId, Patient patient, Doctor doctor, LocalDateTime appointmentDate, String status) {
    this.appointmentId = appointmentId;
    this.patient = patient;
    this.doctor = doctor;
    this.appointmentDate = appointmentDate;
    this.status = status;

    LocalDateTime billingDate = LocalDateTime.of(2024, 11, 21, 14, 30);
    Billing.addBilling(patient, 0, billingDate, "Unpaid");
  }

  public static void addAppointment(Appointment appointment) {
    if (appointment != null) {
      appointments.add(appointment);
      System.out.println("Appointment added successfully!");
    } else {
      System.out.println("Appointment cannot be null!");
    }
  }

  public static void addAppointment(Patient patient, Doctor doctor, LocalDateTime appointmentDate, String status) {
    int id = lastId++;
    Appointment Appointment = new Appointment(id, patient, doctor, appointmentDate, status);
    addAppointment(Appointment);
  }

  public static boolean updateAppointmentById(int appointmentId, Patient patient, Doctor doctor, LocalDateTime appointmentDate, String status) {
    for (Appointment appointment : appointments) {
      if (appointment.getAppointmentId() == appointmentId) {
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setSchedule(appointmentDate);
        appointment.setStatus(status);
        return true;
      }
    }
    return false;
  }
  
  public static int getLastId() {
    return lastId;
  }

  public static List<Appointment> getAppointments() {
    return appointments;
  }

  public LocalDateTime getAppointmentDate() {
    return appointmentDate;
  }

  public void setSchedule(LocalDateTime appointmentDate) {
    this.appointmentDate = appointmentDate;
  }

  public int getAppointmentId() {
    return appointmentId;
  }

  public void setAppointmentId(int appointmentId) {
    this.appointmentId = appointmentId;
  }

  public Patient getPatient() {
    return patient;
  }

  public void setPatient(Patient patient) {
    this.patient = patient;
  }

  public Doctor getDoctor() {
    return doctor;
  }

  public void setDoctor(Doctor doctor) {
    this.doctor = doctor;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
