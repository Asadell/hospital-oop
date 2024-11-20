/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hospital;

import hospital.pages.auth.Login;
import hospital.program.Admin;

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
        new Login();
    }
    
    private static void initData() {
        Admin.register(1, "Satrio", "Asadel", "admin", "admin123");
    }
}
