package com.ayush.hms.ui;

import com.ayush.hms.dao.UserDAO;

import java.util.Scanner;

public class Login {

    Scanner sc = new Scanner(System.in);
    UserDAO userDAO = new UserDAO();

    public boolean login() {

        System.out.println("\n==============================");
        System.out.println("         LOGIN");
        System.out.println("==============================");

        System.out.print("Username : ");
        String username = sc.nextLine();

        System.out.print("Password : ");
        String password = sc.nextLine();

        boolean success = userDAO.login(username, password);

        if (success) {
            System.out.println("\nLogin Successful!\n");
            return true;
        } else {
            System.out.println("\nInvalid Username or Password.");
            return false;
        }
    }
}