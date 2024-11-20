/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.program;

/**
 *
 * @author LENOVO
 */
public class Billing {
  private int billingId;
  private Patient patient; // Assosiation
  private double amount;
  private String billingDate;
  private String paymentStatus;

  public Billing(int billingId, Patient patient, double amount, String billingDate, String paymentStatus) {
    this.billingId = billingId;
    this.patient = patient;
    this.amount = amount;
    this.billingDate = billingDate;
    this.paymentStatus = paymentStatus;
  }

  public void generateBill() {
    System.out.println("Bill generated for patient: " + patient.getFirstName());
  }

  public int getBillingId() {
    return billingId;
  }
  public void setBillingId(int billingId) {
    this.billingId = billingId;
  }
  public Patient getPatient() {
    return patient;
  }
  public void setPatient(Patient patient) {
    this.patient = patient;
  }
  public double getAmount() {
    return amount;
  }
  public void setAmount(double amount) {
    this.amount = amount;
  }
  public String getBillingDate() {
    return billingDate;
  }
  public void setBillingDate(String billingDate) {
    this.billingDate = billingDate;
  }
  public String getPaymentStatus() {
    return paymentStatus;
  }
  public void setPaymentStatus(String paymentStatus) {
    this.paymentStatus = paymentStatus;
  }
}
