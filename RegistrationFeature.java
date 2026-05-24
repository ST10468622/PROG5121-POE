
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.chat.app;
import java.util.Scanner;
/**
 *
 * @author Lisakhanya
 */
public class RegistrationFeature {
    //Stores the information that will be registered
    
    private String storedFirstName;
    private String storedLastName;
    private String storedUsername;
    private String storedPassword;
    private String storedCellNumber;

    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPassword(String password) {

        if (password.length() < 8) return false;

        boolean hasUpper = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            if (Character.isUpperCase(ch)) {
                hasUpper = true;
            } else if (Character.isDigit(ch)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(ch)) {
                hasSpecial = true;
            }
        }

        return hasUpper && hasNumber && hasSpecial;
    }

    public boolean checkCellNumber(String phone) {

        if (!phone.startsWith("+27")) return false;
        if (phone.length() != 12) return false;

        for (int i = 3; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public void register(Scanner myScan) {

        System.out.println("=== REGISTRATION ===");

        //Will capture first name and last name of user
        System.out.print("Enter First Name: ");
        storedFirstName = myScan.nextLine();
        System.out.println("First Name Succesfully Captured");

        System.out.print("Enter Last Name: ");
        storedLastName = myScan.nextLine();
        System.out.println("Last Name Successfully Captured");

        //If username does not meet requirements, will ask user to try again
        while (true) {
            System.out.print("Create Username: ");
            String username = myScan.nextLine();

            if (checkUserName(username)) {
             System.out.println("Username Successfully Created");
                storedUsername = username;
                break;
            } else {
                System.out.println("Invalid Username, Try Again...");
            }
        }
       //If password does not meet requirements, will ask user to try again
        while (true) {
            System.out.print("Create Password: ");
            String password = myScan.nextLine();

            if (checkPassword(password)) {
              System.out.println("Password Successfully Created");
                storedPassword = password;
                break;
            } else {
                System.out.println("Invalid Password, Try Again...");
            }
        }

         //If phone number does not meet requirements, will ask user to try again
        while (true) {
            System.out.print("Enter Phone Number (+27): ");
            String phone = myScan.nextLine();

            if (checkCellNumber(phone)) {
            System.out.println("Phone Number Successfully Captured");
                storedCellNumber = phone;
                break;
            } else {
                System.out.println("Invalid Phone Number, Try Again...");
            }
        }

        System.out.println("Registration Successful!");
    }

    // Getters to pass data to login
    public String getFirstName() {
        return storedFirstName;
    }
    
    public String getLastName() {
        return storedLastName;
    }
    
    public String getUsername() {
        return storedUsername;
    }

    public String getPassword() {
        return storedPassword;
    }
    
    public String getCellNumber() {
        return storedCellNumber;
    }
}
