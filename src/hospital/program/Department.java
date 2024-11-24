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
  private List<Doctor> doctors; // Assosiation
  private static int lastId = 1;
  private static List<Department> departments = new ArrayList<>();

  public Department(int departmentId, String name) {
    this.departmentId = departmentId;
    this.name = name;
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

  public static void addDepartment(String name) {
    int id = lastId++;
    Department Department = new Department(id, name);
    addDepartment(Department);
  }

  public static boolean editDepartmentById(int id, String name) {
    for (Department department : departments) {
      if (department.getDepartmentId() == id) {
        department.setName(name);
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

  public static Department getDepartmentByName(String name) {
    for (Department department : departments) {
      if (department.getName().equals(name)) {
        return department;
      }
    }

    return null;
  }

  public static Department findDepartmentByDoctorName(String doctorName) {
    for (Department department : departments) {
        for (Doctor doctor : department.doctors) {
            if (doctor.getFirstName().equalsIgnoreCase(doctorName)) {
                return department;
            }
        }
    }
    return null;
  }

  public static Department findDepartmentByDoctor(Doctor doctor) {
    String doctorName = doctor.getFirstName();
    for (Department department : departments) {
        for (Doctor currDoctor : department.doctors) {
            if (currDoctor.getFirstName().equalsIgnoreCase(doctorName)) {
                return department;
            }
        }
    }
    return null;
  }

  public static void showAllDepartment() {
    for (Department department : departments) {
      System.out.println("Department: " + department.getName());
        for (Doctor currDoctor : department.doctors) {
          System.out.println(currDoctor.getFirstName());
        }
      System.out.println("hu\n");
    }
  }

  public static boolean isDepartmentNameUsed(String departmentName) {
    for (Department department : departments) {
      if (department.getName().equals(departmentName)) {
        return true;
      }
    }

    return false;
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

  public List<Doctor> getDoctors() {
    return doctors;
  }

  public void setDoctors(List<Doctor> doctors) {
    this.doctors = doctors;
  }
}
