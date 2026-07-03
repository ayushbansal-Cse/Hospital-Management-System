package com.ayush.hms.dao;

import com.ayush.hms.database.DBConnection;
import com.ayush.hms.model.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AppointmentDAO {

    public boolean bookAppointment(Appointment appointment) {

        String sql = "INSERT INTO appointments(patient_id, doctor_id, appointment_date, status) VALUES (?, ?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, appointment.getPatientId());
            ps.setInt(2, appointment.getDoctorId());
            ps.setString(3, appointment.getAppointmentDate());
            ps.setString(4, appointment.getStatus());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            System.out.println("Database Error : " + e.getMessage());
        }

        return false;
    }
    public void viewAppointments() {

        String sql = """
            SELECT a.id,
                   p.name AS patient_name,
                   d.name AS doctor_name,
                   a.appointment_date,
                   a.status
            FROM appointments a
            JOIN patients p ON a.patient_id = p.id
            JOIN doctors d ON a.doctor_id = d.id
            """;

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n==========================================================================");
            System.out.printf("%-5s %-20s %-20s %-15s %-15s%n",
                    "ID", "Patient", "Doctor", "Date", "Status");
            System.out.println("==========================================================================");

            while (rs.next()) {

                System.out.printf("%-5d %-20s %-20s %-15s %-15s%n",

                        rs.getInt("id"),
                        rs.getString("patient_name"),
                        rs.getString("doctor_name"),
                        rs.getString("appointment_date"),
                        rs.getString("status"));
            }

            System.out.println("==========================================================================");

        } catch (Exception e) {
            System.out.println("Database Error : " + e.getMessage());
        }
    }
    public boolean cancelAppointment(int id) {

        String sql = "DELETE FROM appointments WHERE id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            System.out.println("Database Error : " + e.getMessage());
        }

        return false;
    }
}