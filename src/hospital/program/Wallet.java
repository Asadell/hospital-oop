/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.program;

/**
 *
 * @author LENOVO
 */
public class Wallet {
  private int walletId;
  private double balance;

  public Wallet(int walletId) {
    this.walletId = walletId;
    this.balance = 0.0; // Default balance
  }

  public void addBalance(double amount) {
    this.balance += amount;
  }

  public void deductBalance(double amount) {
    if (this.balance >= amount) {
      this.balance -= amount;
    } else {
      System.out.println("Insufficient balance!");
    }
  }

  public int getWalletId() {
    return walletId;
  }
  public void setWalletId(int walletId) {
    this.walletId = walletId;
  }
  public double getBalance() {
    return balance;
  }
  public void setBalance(double balance) {
    this.balance = balance;
  }
}
