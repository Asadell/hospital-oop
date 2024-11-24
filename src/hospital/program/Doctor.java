/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.program;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class Doctor extends Person { // Inheritance, Implementation
  private String medicalLicenseNumber;
  private static List<Doctor> doctors = new ArrayList<>();

  public Doctor(){}

  public Doctor(int id, String firstName, String lastName, String medicalLicenseNumber) {
    super(id, firstName, lastName);
    this.medicalLicenseNumber = medicalLicenseNumber;
  }

  public String toString() {
    Department department = getDepartment();
    return getFirstName() + " " + getLastName() + " (" + department.getName() + ")";
  }

  public static List<Doctor> getDoctors() {
    return doctors;
  }

  public Department getDepartment() {
    return Department.findDepartmentByDoctor(this);
  }

  public static void addDoctor(Doctor doctor, Department department) {
    if (doctor != null) {
      doctors.add(doctor);
      department.addDoctor(doctor);
      System.out.println("Doctor added successfully!");
    } else {
      System.out.println("Doctor cannot be null!");
    }
  }

  public static void addDoctor(String firstName, String lastName, String medicalLicenseNumber, Department department) {
    int id = lastId++;
    Doctor doctor = new Doctor(id, firstName, lastName, medicalLicenseNumber);
    addDoctor(doctor, department);
  }

  public static boolean editDoctorById(int id, String firstName, String lastName, String medicalLicenseNumber, Department newDepartment) {
    for (Doctor doctor : doctors) {
      if (doctor.getId() == id) {
        Department oldDepartment = doctor.getDepartment();

        if (oldDepartment != null && oldDepartment != newDepartment) {
          oldDepartment.removeDoctor(doctor);
        }

        doctor.setFirstName(firstName);
        doctor.setLastName(lastName);
        doctor.setMedicalLicenseNumber(medicalLicenseNumber);

        if (newDepartment != null && !newDepartment.getDoctors().contains(doctor)) {
          newDepartment.addDoctor(doctor);
        }
        System.out.println(oldDepartment.getName()+" "+ newDepartment.getName());

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

  public static boolean isMedicalLicenseNumberUsed(String licenseNumber) {
      for (Doctor doctor : doctors) {
          if (doctor.getMedicalLicenseNumber().equals(licenseNumber)) {
              return true;
          }
      }

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

  public String getMedicalLicenseNumber() {
    return medicalLicenseNumber;
  }

  public void setMedicalLicenseNumber(String medicalLicenseNumber) {
    this.medicalLicenseNumber = medicalLicenseNumber;
  }
}
