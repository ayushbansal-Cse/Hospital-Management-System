package com.ayush.hms.dao;

import com.ayush.hms.database.DBConnection;
import com.ayush.hms.model.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DoctorDAO {

    public boolean addDoctor(Doctor doctor) {

        String sql = "INSERT INTO doctors(name, specialization, experience, phone) VALUES (?, ?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, doctor.getName());
            ps.setString(2, doctor.getSpecialization());
            ps.setString(3, doctor.getExperience());
            ps.setString(4, doctor.getPhone());



            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            System.out.println("Database Error : " + e.getMessage());
        }

        return false;
    }
    public void viewDoctors() {

        String sql = "SELECT * FROM doctors";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n==========================================================");
            System.out.printf("%-5s %-20s %-20s %-12s %-15s%n",
                    "ID", "Name", "Specialization", "Experience", "Phone");
            System.out.println("==========================================================");

            while (rs.next()) {

                System.out.printf("%-5d %-20s %-20s %-12s %-15s%n",

                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("specialization"),
                        rs.getString("experience"),
                        rs.getString("phone"));

            }

            System.out.println("==========================================================");

        } catch (Exception e) {
            System.out.println("Database Error : " + e.getMessage());
        }
    }
    public void searchDoctor(String name) {

        String sql = "SELECT * FROM doctors WHERE name LIKE ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, "%" + name + "%");

            ResultSet rs = ps.executeQuery();

            System.out.println("\n==========================================================");
            System.out.printf("%-5s %-20s %-20s %-15s%n",
                    "ID", "Name", "Specialization", "Phone");
            System.out.println("==========================================================");

            while (rs.next()) {

                System.out.printf("%-5d %-20s %-20s %-15s%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("specialization"),
                        rs.getString("phone"));
            }

            System.out.println("==========================================================");

        } catch (Exception e) {
            System.out.println("Database Error : " + e.getMessage());
        }
    }
    public boolean updateDoctor(int id, String specialization, String phone) {

        String sql = "UPDATE doctors SET specialization=?, phone=? WHERE id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, specialization);
            ps.setString(2, phone);
            ps.setInt(3, id);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean deleteDoctor(int id) {

        String sql = "DELETE FROM doctors WHERE id=?";

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