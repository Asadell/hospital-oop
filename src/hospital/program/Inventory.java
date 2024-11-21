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
public class Inventory {
  private int inventoryId;
  private String itemName;
  private int quantity;
  private String expirationDate;
  private Department department;

  public Inventory(int inventoryId, String itemName, int quantity, String expirationDate, Department department) {
    this.inventoryId = inventoryId;
    this.itemName = itemName;
    this.quantity = quantity;
    this.expirationDate = expirationDate;
    this.department = department;
  }

  public boolean isExpired() {
    return false;
  }

  public int getInventoryId() {
    return inventoryId;
  }
  public void setInventoryId(int inventoryId) {
    this.inventoryId = inventoryId;
  }
  public String getItemName() {
    return itemName;
  }
  public void setItemName(String itemName) {
    this.itemName = itemName;
  }
  public int getQuantity() {
    return quantity;
  }
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
  public String getExpirationDate() {
    return expirationDate;
  }
  public void setExpirationDate(String expirationDate) {
    this.expirationDate = expirationDate;
  }
}
