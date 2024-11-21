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
public class Billing {
  private int billingId;
  private Patient patient; // Assosiation
  private double amount;
  private LocalDateTime billingDate;
  private String paymentStatus;
  private static int lastId = 1;
  private static List<Billing> billings = new ArrayList<>();

  public Billing(int billingId, Patient patient, double amount, LocalDateTime billingDate, String paymentStatus) {
    this.billingId = billingId;
    this.patient = patient;
    this.amount = amount;
    this.billingDate = billingDate;
    this.paymentStatus = paymentStatus;
  }

  private static void addBilling(Billing billing) {
    if (billing != null) {
      billings.add(billing);
      System.out.println("Billing added successfully!");
    } else {
      System.out.println("Billing cannot be null!");
    }
  }

  public static void addBilling(Patient patient, double amount, LocalDateTime billingDate, String paymentStatus) {
    int id = lastId++;
    Billing billing = new Billing(id, patient, amount, billingDate, paymentStatus);
    addBilling(billing);
  }

  public static List<Billing> getBillings() {
    return billings;
  }

  public static boolean updateBillingById(int billingId, Patient patient, double amount, LocalDateTime billingDate, String paymentStatus) {
    for (Billing billing : billings) {
      if (billing.getBillingId() == billingId) {
        billing.setPatient(patient);
        billing.setAmount(amount);
        billing.setBillingDate(billingDate);
        billing.setPaymentStatus(paymentStatus);
        return true; // Billing updated successfully
      }
    }
    return false; // Billing not found
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
  public LocalDateTime getBillingDate() {
    return billingDate;
  }
  public void setBillingDate(LocalDateTime billingDate) {
    this.billingDate = billingDate;
  }
  public String getPaymentStatus() {
    return paymentStatus;
  }
  public void setPaymentStatus(String paymentStatus) {
    this.paymentStatus = paymentStatus;
  }
}
