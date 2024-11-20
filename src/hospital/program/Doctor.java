/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.program;

/**
 *
 * @author LENOVO
 */
public class Doctor extends Person implements Schedulable { // Inheritance, Implementation
  private String specialization;
  private String schedule;

  public Doctor(int id, String firstName, String lastName, String specialization, String schedule) {
    super(id, firstName, lastName);
    this.specialization = specialization;
    this.schedule = schedule;
  }

  public String getSpecialization() {
    return specialization;
  }

  public void setSpecialization(String specialization) {
    this.specialization = specialization;
  }

  public String getSchedule() {
    return schedule;
  }

  public void setSchedule(String schedule) {
    this.schedule = schedule;
  }
}
