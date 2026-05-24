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
public class LoginFeature {
    
private final String storedUsername;
private final String storedPassword;

// Constructor receives registered data
public LoginFeature(String username, String password) {
this.storedUsername = username;
this.storedPassword = password;
}

public boolean login(Scanner myScan) {

System.out.println("=== LOGIN ===");

int attempts = 0;

//User will have only 3 attempts to enter correct information
while (attempts < 3) {

System.out.print("Enter Username: ");
String username = myScan.nextLine();

System.out.print("Enter Password: ");
String password = myScan.nextLine();

if (username.equals(storedUsername) && password.equals(storedPassword)) {
System.out.println("Login Successful. Welcome Back " + username);
return true;
} else {
attempts++;
System.out.println("Incorrect. Attempts Left: " + (3 - attempts));
}
}

System.out.println("Too Many Attempts, Account Locked.");
return false;

}
public boolean login(String username, String password) {
        return this.storedUsername.equals(username) && this.storedPassword.equals(password);
    }
}

