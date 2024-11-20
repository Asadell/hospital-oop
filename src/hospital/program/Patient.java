/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.program;

/**
 *
 * @author LENOVO
 */
public class Patient extends Person {
  private String dob;
  private String gender;
  private String address;
  private String phone;
  private String insuranceInfo;
  private Wallet wallet; // Aggregation

  public Patient(int id, String firstName, String lastName, String dob, String gender, String address, String phone, String insuranceInfo) {
    super(id, firstName, lastName);
    this.dob = dob;
    this.gender = gender;
    this.address = address;
    this.phone = phone;
    this.insuranceInfo = insuranceInfo;
    this.wallet = new Wallet(id); // Aggregation
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
