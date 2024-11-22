/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.program;

/**
 *
 * @author LENOVO
 */
public class Session {
  private static int currentUserId;
  private static String currentUserName;

  public static void setCurrentUser(Admin admin) { // Dependency
    if (admin != null) {
      currentUserId = admin.getId();
      currentUserName = admin.getFirstName();
    }
  }

  public static int getCurrentUserId() {
    return currentUserId;
  }

  public static String getCurrentUserName() {
    return currentUserName;
  }

  public static void clear() {
    currentUserId = -1;
    currentUserName = null;
  }
}
