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
public class Patient extends Person { // Inheritance
  private String dob;
  private String gender;
  private String address;
  private String phone;
  private String insuranceInfo;
  private Wallet wallet; // Aggregation
  private static List<Patient> patients = new ArrayList<>();

  public Patient(int id, String firstName, String lastName, String dob, String gender, String address, String phone, String insuranceInfo) {
    super(id, firstName, lastName);
    this.dob = dob;
    this.gender = gender;
    this.address = address;
    this.phone = phone;
    this.insuranceInfo = insuranceInfo;
  }

  public void createWallet() {
    this.wallet = new Wallet(getId()); // Aggregation
  }

  public static void addPatient(Patient patient) {
    if (patient != null) {
      // patient.createWallet();
      patients.add(patient);
      System.out.println("Patient added successfully!");
    } else {
      System.out.println("Patient cannot be null!");
    }
  }

  public static void addPatient(String firstName, String lastName, String dob, String gender, String address, String phone, String insuranceInfo) {
    int id = lastId++;
    Patient patient = new Patient(id, firstName, lastName, dob, gender, address, phone, insuranceInfo);
    addPatient(patient);
  }

  public static List<Patient> getPatients() {
    return patients;
  }

  public static List<Patient> getPatientsWithWallet() {
    List<Patient> patientsWithWallet = new ArrayList<>();
    for (Patient patient : patients) {
      if (patient.hasWallet()) {
        patientsWithWallet.add(patient);
      }
    }
    return patientsWithWallet;
}

  public static boolean editPatientById(int id, String firstName, String lastName, String dob, String gender, String address, String phone, String insuranceInfo) {
    for (Patient patient : patients) {
      if (patient.getId() == id) {
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setDob(dob);
        patient.setGender(gender);
        patient.setAddress(address);
        patient.setPhone(phone);
        patient.setInsuranceInfo(insuranceInfo);
        System.out.println("Patient with ID " + id + " updated successfully!");
        return true;
      }
    }
    System.out.println("Patient with ID " + id + " not found!");
    return false;
  }

  public static boolean deletePatientById(int id) {
    for (int i = 0; i < patients.size(); i++) {
      if (patients.get(i).getId() == id) {
        patients.remove(i);
        System.out.println("Patient with ID " + id + " deleted successfully!");
        return true;
      }
    }
    System.out.println("Patient with ID " + id + " not found!");
    return false;
  }

  public static Patient getPatientById(int id) {
    for (Patient patient : patients) {
      if (patient.getId() == id) {
        return patient;
      }
    }

    return null;
  }

  public boolean hasWallet() {
    return this.wallet != null;
  }

  public String getDob() {
    return dob;
  }

  public void setDob(String dob) {
    this.dob = dob;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getInsuranceInfo() {
    return insuranceInfo;
  }

  public void setInsuranceInfo(String insuranceInfo) {
    this.insuranceInfo = insuranceInfo;
  }

  public Wallet getWallet() {
    return wallet;
  }

  public void setWallet(Wallet wallet) {
    this.wallet = wallet;
  }
}
