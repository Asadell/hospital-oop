/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hospital;

import hospital.pages.auth.Login;
import hospital.pages.system.DashboardFrame;
import hospital.program.Admin;
import hospital.program.Session;

/**
 *
 * @author LENOVO
 */
public class HospitalOop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        initData();
        // new Login();
        new DashboardFrame();
    }
    
    private static void initData() {
        // Admin.register(1, "Satrio", "Asadel", "admin", "admin123");
        Admin admin = new Admin(1, "Satrio", "Asadel", "admin", "admin123");
        Session.setCurrentUser(admin);
    }
}
