package com.ayush.hms.dao;

import com.ayush.hms.database.DBConnection;
import com.ayush.hms.model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PatientDAO {

    public boolean addPatient(Patient patient) {

        String sql = "INSERT INTO patients(name, age, gender, phone, address) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, patient.getName());
            ps.setInt(2, patient.getAge());
            ps.setString(3, patient.getGender());
            ps.setString(4, patient.getPhone());
            ps.setString(5, patient.getAddress());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            System.out.println("Database Error : " + e.getMessage());
        }

        return false;
    }
    public void viewPatients() {

        String sql = "SELECT * FROM patients";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n==============================================================");
            System.out.printf("%-5s %-15s %-5s %-10s %-15s %-20s%n",
                    "ID", "Name", "Age", "Gender", "Phone", "Address");
            System.out.println("==============================================================");

            while (rs.next()) {

                System.out.printf("%-5d %-15s %-5d %-10s %-15s %-20s%n",

                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("phone"),
                        rs.getString("address"));

            }

            System.out.println("==============================================================");

        } catch (Exception e) {

            System.out.println("Database Error : " + e.getMessage());

        }
    }
    public boolean updatePatient(int id, String phone, String address) {

        String sql = "UPDATE patients SET phone=?, address=? WHERE id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, phone);
            ps.setString(2, address);
            ps.setInt(3, id);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            System.out.println("Database Error : " + e.getMessage());
        }

        return false;
    }
    public boolean deletePatient(int id) {

        String sql = "DELETE FROM patients WHERE id=?";

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
    public void searchPatient(String name) {

        String sql = "SELECT * FROM patients WHERE name LIKE ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, "%" + name + "%");

            ResultSet rs = ps.executeQuery();

            System.out.println("\n==============================================================");
            System.out.printf("%-5s %-15s %-5s %-10s %-15s %-20s%n",
                    "ID", "Name", "Age", "Gender", "Phone", "Address");
            System.out.println("==============================================================");

            while (rs.next()) {

                System.out.printf("%-5d %-15s %-5d %-10s %-15s %-20s%n",

                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("phone"),
                        rs.getString("address"));
            }

            System.out.println("==============================================================");

        } catch (Exception e) {
            System.out.println("Database Error : " + e.getMessage());
        }
    }
}