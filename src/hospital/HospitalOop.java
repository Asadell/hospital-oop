/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hospital;

import java.time.LocalDateTime;
    
import hospital.pages.auth.Login;
 import hospital.pages.system.DashboardFrame;
import hospital.program.Admin;
import hospital.program.Appointment;
import hospital.program.Department;
import hospital.program.Doctor;
import hospital.program.Inventory;
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
        new Login();
        // new DashboardFrame();
    }
    
    private static void initData() {
        Admin.register(1, "Satrio", "Asadel", "admin", "admin123");
        Patient.addPatient("John", "Doe", "1990-01-01", "Male", "123 Main St", "081234567890", "XYZ Insurance");
        Patient.addPatient("Jane", "Smith", "1992-02-22", "Female", "456 Elm St", "082134567891", "ABC Insurance");
        Patient.addPatient("Michael", "Brown", "1988-03-03", "Male", "789 Pine St", "083234567892", "DEF Insurance");
        Patient.addPatient("Emily", "Davis", "1995-04-14", "Female", "101 Maple St", "084234567893", "GHI Insurance");
        Patient.addPatient("Chris", "Wilson", "1985-05-05", "Male", "202 Oak St", "085234567894", "JKL Insurance");
        Patient.addPatient("Laura", "Johnson", "1991-06-26", "Female", "303 Birch St", "086234567895", "MNO Insurance");
        Patient.addPatient("David", "Miller", "1980-07-07", "Male", "404 Cedar St", "087234567896", "PQR Insurance");
        Patient.addPatient("Anna", "Anderson", "1998-08-18", "Female", "505 Spruce St", "088234567897", "STU Insurance");
        Patient.addPatient("Robert", "Thomas", "1983-09-09", "Male", "606 Walnut St", "089234567898", "VWX Insurance");
        Patient.addPatient("Sophia", "Martin", "1996-10-10", "Female", "707 Chestnut St", "081234567899", "YZA Insurance");


        Department.addDepartment("Cardiology");
        Department.addDepartment("Neurology");
        Department.addDepartment("Orthopedics");
        Department.addDepartment("Pediatrics");
        Department.addDepartment("Gastroenterology");

        Department department1 = Department.getDepartmentById(1);
        Department department2 = Department.getDepartmentById(2);
        Department department3 = Department.getDepartmentById(3);
        Department department4 = Department.getDepartmentById(4);
        Department department5 = Department.getDepartmentById(5);

        LocalDateTime scheduleDate = LocalDateTime.of(2024, 11, 21, 14, 30);
        Doctor.addDoctor("Ahmad", "Suryadi", "IDN-MD-2024-0001", department1);
        Doctor.addDoctor("Siti", "Nurhaliza", "IDN-MD-2024-0002", department2);
        Doctor.addDoctor("Budi", "Santoso", "IDN-MD-2024-0003", department3);
        Doctor.addDoctor("Indah", "Pratiwi", "IDN-MD-2024-0004", department4);
        Doctor.addDoctor("Rizki", "Hakim", "IDN-MD-2024-0005", department5);
        Doctor.addDoctor("Lestari", "Putri", "IDN-MD-2024-0006", department1);
        Doctor.addDoctor("Dian", "Hartono", "IDN-MD-2024-0007", department2);
        Doctor.addDoctor("Agung", "Prasetyo", "IDN-MD-2024-0008", department3);
        Doctor.addDoctor("Citra", "Wijaya", "IDN-MD-2024-0009", department4);
        Doctor.addDoctor("Fajar", "Hidayat", "IDN-MD-2024-0010", department5);
        

        Patient patient1 = Patient.getPatientById(1);
        Patient patient2 = Patient.getPatientById(2);
        Patient patient3 = Patient.getPatientById(3);
        Patient patient4 = Patient.getPatientById(4);
        Patient patient5 = Patient.getPatientById(5);
        Patient patient6 = Patient.getPatientById(6);
        Patient patient7 = Patient.getPatientById(7);
        Patient patient8 = Patient.getPatientById(8);
        Patient patient9 = Patient.getPatientById(9);
        Patient patient10 = Patient.getPatientById(10);

        Doctor doctor11 = Doctor.getDoctorById(11);
        Doctor doctor12 = Doctor.getDoctorById(12);
        Doctor doctor13 = Doctor.getDoctorById(13);
        Doctor doctor14 = Doctor.getDoctorById(14);
        Doctor doctor15 = Doctor.getDoctorById(15);
        Doctor doctor16 = Doctor.getDoctorById(16);
        Doctor doctor17 = Doctor.getDoctorById(17);
        Doctor doctor18 = Doctor.getDoctorById(18);
        Doctor doctor19 = Doctor.getDoctorById(19);
        Doctor doctor20 = Doctor.getDoctorById(20);

        LocalDateTime appointmentDate = LocalDateTime.of(2024, 11, 21, 14, 30);
        Appointment.addAppointment(patient1, doctor11, appointmentDate, "Scheduled");
        Appointment.addAppointment(patient2, doctor12, appointmentDate, "Scheduled");
        Appointment.addAppointment(patient3, doctor13, appointmentDate, "Scheduled");
        Appointment.addAppointment(patient4, doctor14, appointmentDate, "Scheduled");
        Appointment.addAppointment(patient5, doctor15, appointmentDate, "Scheduled");
        Appointment.addAppointment(patient6, doctor16, appointmentDate, "Scheduled");
        Appointment.addAppointment(patient7, doctor17, appointmentDate, "Scheduled");
        Appointment.addAppointment(patient8, doctor18, appointmentDate, "Scheduled");
        Appointment.addAppointment(patient9, doctor19, appointmentDate, "Scheduled");
        Appointment.addAppointment(patient10, doctor20, appointmentDate, "Scheduled");

        // department2.addDoctor(doctor12);
        // department1.addDoctor(doctor11);
        // department3.addDoctor(doctor13);
        // department4.addDoctor(doctor14);
        // department1.addDoctor(doctor16);
        // department5.addDoctor(doctor15);
        // department3.addDoctor(doctor18);
        // department2.addDoctor(doctor17);
        // department4.addDoctor(doctor19);
        // department5.addDoctor(doctor20);

        Inventory.addInventory("Paracetamol", 100, LocalDateTime.of(2024, 12, 30, 0, 0), department1);
        Inventory.addInventory("Syringes", 200, LocalDateTime.of(2025, 1, 15, 0, 0), department2);
        Inventory.addInventory("Stethoscope", 50, LocalDateTime.of(2027, 6, 30, 0, 0), department3);
        Inventory.addInventory("X-ray Film", 30, LocalDateTime.of(2025, 3, 20, 0, 0), department4);
        Inventory.addInventory("Blood Bags", 70, LocalDateTime.of(2024, 11, 25, 0, 0), department5);
        Inventory.addInventory("Insulin", 120, LocalDateTime.of(2024, 12, 10, 0, 0), department1);
        Inventory.addInventory("Scalpel Blades", 80, LocalDateTime.of(2025, 8, 5, 0, 0), department2);
        Inventory.addInventory("ECG Electrodes", 90, LocalDateTime.of(2024, 9, 15, 0, 0), department3);
        Inventory.addInventory("MRI Contrast Dye", 20, LocalDateTime.of(2025, 2, 1, 0, 0), department4);
        Inventory.addInventory("Defibrillator Pads", 40, LocalDateTime.of(2026, 4, 30, 0, 0), department5);

        // Admin admin = new Admin(1, "Satrio", "Asadel", "admin", "admin123");
        // Session.setCurrentUser(admin);
    }
}
