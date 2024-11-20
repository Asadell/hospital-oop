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
public class Admin extends Person {
    private String username;
    private String password;
    private static List<Admin> admins = new ArrayList<>();

    public Admin(int id, String firstName, String lastName, String username, String password) {
      super(id, firstName, lastName);
      this.username = username;
      this.password = password;
    }

    public static int getAdminCount() {
      return admins.size();
    }

    public static void register(int id, String firstName, String lastName, String username, String password) {
        if (!isUserRegistered(username)) {
          admins.add(new Admin(id, firstName, lastName, username, password));
        }
    }

    public static Admin login(String username, String password) {
      for (Admin user : admins) {
        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
          return user;
        }
      }

      return null;
    }

    public static boolean isUserRegistered(String username) {
        for (Admin user : admins) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }

        return false;
    }

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }
}
