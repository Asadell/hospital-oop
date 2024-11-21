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
public class Doctor extends Person implements Schedulable { // Inheritance, Implementation
  private String specialization;
  private LocalDateTime schedule;
  private static List<Doctor> doctors = new ArrayList<>();

  public Doctor(){}

  public Doctor(int id, String firstName, String lastName, String specialization, LocalDateTime schedule) {
    super(id, firstName, lastName);
    this.specialization = specialization;
    this.schedule = schedule;
  }

  public String toString() {
      return getFirstName() + " " + getLastName() + " (" + specialization + ")";
  }

  public static List<Doctor> getDoctors() {
    return doctors;
  }

  public static void addDoctor(Doctor doctor) {
    if (doctor != null) {
      doctors.add(doctor);
      System.out.println("Doctor added successfully!");
    } else {
      System.out.println("Doctor cannot be null!");
    }
  }

  public static void addDoctor(String firstName, String lastName, String specialization, LocalDateTime schedule) {
    int id = lastId++;
    Doctor doctor = new Doctor(id, firstName, lastName, specialization,  schedule);
    addDoctor(doctor);
  }

  public static boolean editDoctorById(int id, String firstName, String lastName, String specialization, LocalDateTime schedule) {
    for (Doctor doctor : doctors) {
      if (doctor.getId() == id) {
        doctor.setFirstName(firstName);
        doctor.setLastName(lastName);
        doctor.setSpecialization(specialization);
        doctor.setSchedule(schedule);
        System.out.println("Doctor with ID " + id + " updated successfully!");
        return true;
      }
    }
    System.out.println("Doctor with ID " + id + " not found!");
    return false;
  }

  public static boolean deleteDoctorById(int id) {
    for (int i = 0; i < doctors.size(); i++) {
      if (doctors.get(i).getId() == id) {
        doctors.remove(i);
        System.out.println("Doctor with ID " + id + " deleted successfully!");
        return true;
      }
    }
    System.out.println("Doctor with ID " + id + " not found!");
    return false;
  }

  public static Doctor getDoctorById(int id) {
    for (Doctor doctor : doctors) {
      if (doctor.getId() == id) {
        return doctor;
      }
    }

    return null;
  }

  public String getSpecialization() {
    return specialization;
  }

  public void setSpecialization(String specialization) {
    this.specialization = specialization;
  }

  public LocalDateTime getSchedule() {
    return schedule;
  }

  public void setSchedule(LocalDateTime schedule) {
    this.schedule = schedule;
  }
}
