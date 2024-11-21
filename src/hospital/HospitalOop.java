/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hospital;

import hospital.pages.auth.Login;
import hospital.pages.system.DashboardFrame;
import hospital.program.Admin;
import hospital.program.Patient;
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
        Patient.addPatient("John", "Doe", "1990-01-01", "Male", "123 Main St", "555-1234", "XYZ Insurance");
        Patient.addPatient("Jane", "Smith", "1992-02-02", "Female", "456 Elm St", "555-5678", "ABC Insurance");
        Patient.addPatient("Michael", "Brown", "1988-03-03", "Male", "789 Pine St", "555-8765", "DEF Insurance");
        Patient.addPatient("Emily", "Davis", "1995-04-04", "Female", "101 Maple St", "555-4321", "GHI Insurance");
        Patient.addPatient("Chris", "Wilson", "1985-05-05", "Male", "202 Oak St", "555-6789", "JKL Insurance");
        Patient.addPatient("Laura", "Johnson", "1991-06-06", "Female", "303 Birch St", "555-9876", "MNO Insurance");
        Patient.addPatient("David", "Miller", "1980-07-07", "Male", "404 Cedar St", "555-3456", "PQR Insurance");
        Patient.addPatient("Anna", "Anderson", "1998-08-08", "Female", "505 Spruce St", "555-6543", "STU Insurance");
        Patient.addPatient("Robert", "Thomas", "1983-09-09", "Male", "606 Walnut St", "555-1111", "VWX Insurance");
        Patient.addPatient("Sophia", "Martin", "1996-10-10", "Female", "707 Chestnut St", "555-2222", "YZA Insurance");
        // Admin.register(1, "Satrio", "Asadel", "admin", "admin123");
        Admin admin = new Admin(1, "Satrio", "Asadel", "admin", "admin123");
        Session.setCurrentUser(admin);
    }
}
