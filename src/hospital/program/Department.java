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
public class Department {
  private int departmentId;
  private String name;
  private Doctor headDoctor; // Association
  private List<Doctor> doctors; 
  private List<Inventory> inventories;

  public Department(int departmentId, String name, Doctor headDoctor) {
    this.departmentId = departmentId;
    this.name = name;
    this.headDoctor = headDoctor;
    this.doctors = new ArrayList<>();
    this.inventories = new ArrayList<>();
  }

  public List<Inventory> getInventories() {
    return inventories;
  }

  public void setInventories(List<Inventory> inventories) {
    this.inventories = inventories;
  }

  public void addDoctor(Doctor doctor) {
    this.doctors.add(doctor);
  }

  public void removeDoctor(Doctor doctor) {
    this.doctors.remove(doctor);
  }

  public int getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(int departmentId) {
    this.departmentId = departmentId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Doctor getHeadDoctor() {
    return headDoctor;
  }

  public void setHeadDoctor(Doctor headDoctor) {
    this.headDoctor = headDoctor;
  }

  public List<Doctor> getDoctors() {
    return doctors;
  }

  public void setDoctors(List<Doctor> doctors) {
    this.doctors = doctors;
  }
}
