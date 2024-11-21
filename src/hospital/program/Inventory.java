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
public class Inventory {
  private int inventoryId;
  private String itemName;
  private int quantity;
  private LocalDateTime expirationDate;
  private Department department;
  private static int lastId = 1;

  public static int getLastId() {
    return lastId;
  }
  private static List<Inventory> inventories = new ArrayList<>();

  public Inventory(int inventoryId, String itemName, int quantity, LocalDateTime expirationDate, Department department) {
    this.inventoryId = inventoryId;
    this.itemName = itemName;
    this.quantity = quantity;
    this.expirationDate = expirationDate;
    this.department = department;
  }

  public static void addInventory(Inventory inventory) {
    if (inventory != null) {
      inventories.add(inventory);
      System.out.println("Inventory added successfully!");
    } else {
      System.out.println("Inventory cannot be null!");
    }
  }

  public static void addInventory(String itemName, int quantity, LocalDateTime expirationDate, Department department) {
    int id = lastId++;
    Inventory inventory = new Inventory(id, itemName, quantity, expirationDate, department);
    addInventory(inventory);
  }

  public static boolean updateInventoryById(int InventoryId, String itemName, int quantity, LocalDateTime expirationDate, Department department) {
    for (Inventory inventory : inventories) {
      if (inventory.getInventoryId() == InventoryId) {
        inventory.setItemName(itemName);
        inventory.setQuantity(quantity);
        inventory.setExpirationDate(expirationDate);
        inventory.setDepartment(department);
        return true; // Inventory updated successfully
      }
    }
    return false; // Inventory not found
  }

  public static boolean deleteInventoryById(int id) {
    for (int i = 0; i < inventories.size(); i++) {
      if (inventories.get(i).getInventoryId() == id) {
        inventories.remove(i);
        System.out.println("Inventory with ID " + id + " deleted successfully!");
        return true;
      }
    }
    System.out.println("Inventory with ID " + id + " not found!");
    return false;
  }

  public static List<Inventory> getInventories() {
    return inventories;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
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
  public LocalDateTime getExpirationDate() {
    return expirationDate;
  }
  public void setExpirationDate(LocalDateTime expirationDate) {
    this.expirationDate = expirationDate;
  }
}
