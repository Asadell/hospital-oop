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
import hospital.program.Doctor;
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
        Doctor.addDoctor("Ahmad", "Suryadi", "Cardiology", "Monday, Wednesday, Friday - 08:00 to 14:00");
        Doctor.addDoctor("Siti", "Nurhaliza", "Dermatology", "Tuesday, Thursday - 10:00 to 16:00");
        Doctor.addDoctor("Budi", "Santoso", "Pediatrics", "Monday, Thursday - 09:00 to 13:00");
        Doctor.addDoctor("Indah", "Pratiwi", "Gynecology", "Tuesday, Friday - 10:00 to 14:00");
        Doctor.addDoctor("Rizki", "Hakim", "Neurology", "Wednesday, Saturday - 08:00 to 12:00");
        Doctor.addDoctor("Lestari", "Putri", "Orthopedics", "Monday, Friday - 14:00 to 18:00");
        Doctor.addDoctor("Dian", "Hartono", "Ophthalmology", "Tuesday, Thursday - 08:00 to 12:00");
        Doctor.addDoctor("Agung", "Prasetyo", "Psychiatry", "Monday, Wednesday - 10:00 to 15:00");
        Doctor.addDoctor("Citra", "Wijaya", "General Practitioner", "Everyday - 09:00 to 18:00");
        Doctor.addDoctor("Fajar", "Hidayat", "Pulmonology", "Wednesday, Saturday - 10:00 to 14:00");

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

        Admin admin = new Admin(1, "Satrio", "Asadel", "admin", "admin123");
        Session.setCurrentUser(admin);
    }
}
