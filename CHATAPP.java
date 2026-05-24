
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chat.app;
import java.util.Scanner;
/**
 *
 * @author Lisakhanya
 */
public class CHATAPP {

    public static void main(String[] args) {
            
        try (Scanner myScan = new Scanner(System.in)) {
            
             // Part 1: Login
            //User must register
            RegistrationFeature reg = new RegistrationFeature();
            reg.register(myScan);
            
            // User must login
            LoginFeature login = new LoginFeature(reg.getUsername(), reg.getPassword());
            login.login(myScan);
           
            // Part 2: Welcome message
            System.out.println("Welcome to the QuickChat.");
            
            // Ask how many messages the user wants to send
            System.out.print("How many messages would you like to send? ");
            int numMessages = Integer.parseInt(myScan.nextLine());

            int menuChoice = 0;

            while (menuChoice != 3) {
               
                System.out.println("Option 1: Send Messages");
                System.out.println("Option 2: Show recently sent messages");
                System.out.println("Option 3: Quit");
                System.out.print("Choose an option: ");
                menuChoice = Integer.parseInt(myScan.nextLine());

                if (menuChoice == 1) {
             
                    for (int i = 1; i <= numMessages; i++) {
                        System.out.println("Message " + i + " of " + numMessages + " ---");

                        // phone number of the recipient
                        System.out.print("Enter recipient phone number: ");
                        String recipient = myScan.nextLine();

                        // Text message
                        System.out.print("Enter your message: ");
                        String messageText = myScan.nextLine();

                      
                        Message msg = new Message(i, recipient, messageText);

                        // Check phone number of the recipient
                        System.out.println(msg.checkRecipientCell());

                        // Check the length of the message
                        System.out.println(msg.checkMessageLength());

                        System.out.println("Message ID:   " + msg.getMessageID());
                        System.out.println("Message Hash: " + msg.getMessageHash());

                        System.out.println("1) Send Message");
                        System.out.println("2) Disregard Message");
                        System.out.println("3) Store Message");
                        System.out.print("Choose an option: ");
                        int sendChoice = Integer.parseInt(myScan.nextLine());

                        System.out.println(msg.sentMessage(sendChoice));

                        System.out.println(msg.printMessages());
                    }

                    // Total messages sent 
                    System.out.println("Total messages sent: " + Message.returnTotalMessages());

                } else if (menuChoice == 2) {
                    System.out.println("Coming Soon.");

                } else if (menuChoice == 3) {
                    System.out.println("Goodbye!");

                } else {
                    System.out.println("Invalid option, please try again.");
                }
            }
        }
        }
    }
    
 

