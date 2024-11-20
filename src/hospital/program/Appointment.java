/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.program;

/**
 *
 * @author LENOVO
 */
public class Appointment implements Schedulable { // Implementation
  private int appointmentId;
  private Patient patient; // Association
  private Doctor doctor;   // Association
  private String appointmentDate;
  private String status;

  public Appointment(int appointmentId, Patient patient, Doctor doctor, String appointmentDate, String status) {
    this.appointmentId = appointmentId;
    this.patient = patient;
    this.doctor = doctor;
    this.appointmentDate = appointmentDate;
    this.status = status;
  }

  public String getSchedule() {
    return appointmentDate;
  }

  public void setSchedule(String schedule) {
    this.appointmentDate = schedule;
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
