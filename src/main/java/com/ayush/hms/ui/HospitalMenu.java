package com.ayush.hms.ui;

import com.ayush.hms.dao.PatientDAO;
import com.ayush.hms.model.Patient;

import java.util.Scanner;

import com.ayush.hms.dao.DoctorDAO;
import com.ayush.hms.model.Doctor;

import com.ayush.hms.dao.AppointmentDAO;
import com.ayush.hms.model.Appointment;

public class HospitalMenu {

    Scanner sc = new Scanner(System.in);
    PatientDAO patientDAO = new PatientDAO();
    DoctorDAO doctorDAO = new DoctorDAO();
    AppointmentDAO appointmentDAO = new AppointmentDAO();

    public void start() {

        while (true) {

            System.out.println("\n==========================================");
            System.out.println("      HOSPITAL MANAGEMENT SYSTEM");
            System.out.println("==========================================");

            System.out.println("\nPATIENT");
            System.out.println("1. Add Patient");
            System.out.println("2. View Patients");
            System.out.println("3. Update Patient");
            System.out.println("4. Delete Patient");
            System.out.println("5. Search Patient");

            System.out.println("\nDOCTOR");
            System.out.println("6. Add Doctor");
            System.out.println("7. View Doctors");
            System.out.println("8. Search Doctor");
            System.out.println("9. Update Doctor");
            System.out.println("10. Delete Doctor");

            System.out.println("\nAPPOINTMENT");
            System.out.println("11. Book Appointment");
            System.out.println("12. View Appointments");
            System.out.println("13. Cancel Appointment");

            System.out.println("\n0. Exit");
            System.out.print("Enter Choice : ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addPatient();
                    break;

                case 2:
                    patientDAO.viewPatients();
                    break;
                case 3:
                    updatePatient();
                    break;

                case 4:
                    deletePatient();
                    break;
                case 5:
                    searchPatient();
                    break;

                case 6:
                    addDoctor();
                    break;

                case 7:
                    doctorDAO.viewDoctors();
                    break;

                case 8:
                    searchDoctor();
                    break;

                case 9:
                    updateDoctor();
                    break;
                case 10:
                    deleteDoctor();
                    break;
                case 11:
                    bookAppointment();
                    break;
                case 12:
                    appointmentDAO.viewAppointments();
                    break;
                case 13:
                    cancelAppointment();
                    break;
                case 0:
                    System.out.println("Thank You!");
                    return;

                default:
                    System.out.println("Invalid Choice");
            }

        }

    }

    private void addPatient() {

        Patient patient = new Patient();

        System.out.print("Enter Name : ");
        patient.setName(sc.nextLine());

        System.out.print("Enter Age : ");
        patient.setAge(sc.nextInt());
        sc.nextLine();

        System.out.print("Enter Gender : ");
        patient.setGender(sc.nextLine());

        System.out.print("Enter Phone : ");
        patient.setPhone(sc.nextLine());

        System.out.print("Enter Address : ");
        patient.setAddress(sc.nextLine());

        boolean success = patientDAO.addPatient(patient);

        if (success) {
            System.out.println("Patient Added Successfully.");
        } else {
            System.out.println("Failed to Add Patient.");
        }

    }
    private void updatePatient() {

        System.out.print("Enter Patient ID : ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter New Phone : ");
        String phone = sc.nextLine();

        System.out.print("Enter New Address : ");
        String address = sc.nextLine();

        boolean success = patientDAO.updatePatient(id, phone, address);

        if (success) {
            System.out.println("Patient Updated Successfully.");
        } else {
            System.out.println("Patient Not Found.");
        }
    }
    private void deletePatient() {

        System.out.print("Enter Patient ID : ");
        int id = sc.nextInt();
        sc.nextLine();

        boolean success = patientDAO.deletePatient(id);

        if (success) {
            System.out.println("Patient Deleted Successfully.");
        } else {
            System.out.println("Patient Not Found.");
        }
    }
    private void searchPatient() {

        System.out.print("Enter Patient Name : ");
        String name = sc.nextLine();

        patientDAO.searchPatient(name);
    }
    private void addDoctor() {


        System.out.print("Doctor Name : ");
        String name = sc.nextLine();

        System.out.print("Specialization : ");
        String specialization = sc.nextLine();

        System.out.print("Experience : ");
        String experience = sc.nextLine();

        System.out.print("Phone : ");
        String phone = sc.nextLine();

        Doctor doctor = new Doctor();

        doctor.setName(name);
        doctor.setSpecialization(specialization);
        doctor.setExperience(experience);
        doctor.setPhone(phone);

        boolean success = doctorDAO.addDoctor(doctor);

        if (success) {
            System.out.println("Doctor Added Successfully.");
        } else {
            System.out.println("Failed to Add Doctor.");
        }
    }
    private void searchDoctor() {

        System.out.print("Enter Doctor Name : ");
        String name = sc.nextLine();

        doctorDAO.searchDoctor(name);
    }
    private void updateDoctor() {

        System.out.print("Enter Doctor ID : ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter New Specialization : ");
        String specialization = sc.nextLine();

        System.out.print("Enter New Phone : ");
        String phone = sc.nextLine();

        boolean success = doctorDAO.updateDoctor(id, specialization, phone);

        if (success) {
            System.out.println("Doctor Updated Successfully.");
        } else {
            System.out.println("Doctor Not Found.");
        }
    }
    private void deleteDoctor() {

        System.out.print("Enter Doctor ID : ");
        int id = sc.nextInt();
        sc.nextLine();

        boolean success = doctorDAO.deleteDoctor(id);

        if (success) {
            System.out.println("Doctor Deleted Successfully.");
        } else {
            System.out.println("Doctor Not Found.");
        }
    }
    private void bookAppointment() {

        Appointment appointment = new Appointment();

        System.out.print("Enter Patient ID : ");
        appointment.setPatientId(sc.nextInt());

        System.out.print("Enter Doctor ID : ");
        appointment.setDoctorId(sc.nextInt());
        sc.nextLine();

        System.out.print("Enter Appointment Date (YYYY-MM-DD) : ");
        appointment.setAppointmentDate(sc.nextLine());

        appointment.setStatus("Scheduled");

        boolean success = appointmentDAO.bookAppointment(appointment);

        if (success) {
            System.out.println("Appointment Booked Successfully.");
        } else {
            System.out.println("Failed to Book Appointment.");
        }
    }
    private void cancelAppointment() {

        System.out.print("Enter Appointment ID : ");
        int id = sc.nextInt();
        sc.nextLine();

        boolean success = appointmentDAO.cancelAppointment(id);

        if (success) {
            System.out.println("Appointment Cancelled Successfully.");
        } else {
            System.out.println("Appointment Not Found.");
        }
    }
}