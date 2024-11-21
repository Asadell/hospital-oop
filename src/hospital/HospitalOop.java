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
        // new Login();
        new DashboardFrame();
    }
    
    private static void initData() {
        // Admin.register(1, "Satrio", "Asadel", "admin", "admin123");
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

        LocalDateTime scheduleDate = LocalDateTime.of(2024, 11, 21, 14, 30);
        Doctor.addDoctor("Ahmad", "Suryadi", "Cardiology", scheduleDate);
        Doctor.addDoctor("Siti", "Nurhaliza", "Dermatology", scheduleDate);
        Doctor.addDoctor("Budi", "Santoso", "Pediatrics", scheduleDate);
        Doctor.addDoctor("Indah", "Pratiwi", "Gynecology", scheduleDate);
        Doctor.addDoctor("Rizki", "Hakim", "Neurology", scheduleDate);
        Doctor.addDoctor("Lestari", "Putri", "Orthopedics", scheduleDate);
        Doctor.addDoctor("Dian", "Hartono", "Ophthalmology", scheduleDate);
        Doctor.addDoctor("Agung", "Prasetyo", "Psychiatry", scheduleDate);
        Doctor.addDoctor("Citra", "Wijaya", "General Practitioner", scheduleDate);
        Doctor.addDoctor("Fajar", "Hidayat", "Pulmonology", scheduleDate);

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

        Department.addDepartment("Cardiology", doctor11);
        Department.addDepartment("Neurology", doctor12);
        Department.addDepartment("Orthopedics", doctor13);
        Department.addDepartment("Pediatrics", doctor14);
        Department.addDepartment("Gastroenterology", doctor15);

        Department department1 = Department.getDepartmentById(1);
        Department department2 = Department.getDepartmentById(2);
        Department department3 = Department.getDepartmentById(3);
        Department department4 = Department.getDepartmentById(4);
        Department department5 = Department.getDepartmentById(5);

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

        Admin admin = new Admin(1, "Satrio", "Asadel", "admin", "admin123");
        Session.setCurrentUser(admin);
    }
}
