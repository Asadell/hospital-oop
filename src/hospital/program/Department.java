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
  private Doctor headDoctor = new Doctor(); // Composition
  private List<Doctor> doctors; 
  private static int lastId = 1;
  private static List<Department> departments = new ArrayList<>();

  public Department(int departmentId, String name, Doctor headDoctor) {
    this.departmentId = departmentId;
    this.name = name;
    this.headDoctor = headDoctor;
    this.doctors = new ArrayList<>();
  }

  public static void addDepartment(Department department) {
    if (department != null) {
      departments.add(department);
      System.out.println("Department added successfully!");
    } else {
      System.out.println("Department cannot be null!");
    }
  }

  public static void addDepartment(String name, Doctor headDoctor) {
    int id = lastId++;
    Department Department = new Department(id, name, headDoctor);
    addDepartment(Department);
  }

  public static boolean editDepartmentById(int id, String name, Doctor headDoctor) {
    for (Department department : departments) {
      if (department.getDepartmentId() == id) {
        department.setName(name);
        department.setHeadDoctor(headDoctor);
        System.out.println("Department with ID " + id + " updated successfully!");
        return true;
      }
    }
    System.out.println("Department with ID " + id + " not found!");
    return false;
  }

  public static boolean deleteDepartmentById(int id) {
    for (int i = 0; i < departments.size(); i++) {
      if (departments.get(i).getDepartmentId() == id) {
        departments.remove(i);
        System.out.println("Department with ID " + id + " deleted successfully!");
        return true;
      }
    }
    System.out.println("Department with ID " + id + " not found!");
    return false;
  }

  public static Department getDepartmentById(int id) {
    for (Department department : departments) {
      if (department.getDepartmentId() == id) {
        return department;
      }
    }

    return null;
  }

  public static int getLastId() {
    return lastId;
  }

  public static List<Department> getDepartments() {
    return departments;
  }

  public String toString() {
    return getName();
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
