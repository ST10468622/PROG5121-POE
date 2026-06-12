/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chat.app;

import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Lisakhanya
 */
public class Message {

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    private String messageStatus;

    private static final String JSON_FILE = "storedMessages.json";

    private static int totalMessagesSent = 0;

    //  Constructor
    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    private String generateMessageID() {
        Random random = new Random();
        long id = (long) (random.nextDouble() * 9000000000L) + 1000000000L;
        return String.valueOf(id);
    }

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    public String checkRecipientCell() {
        if (recipient.startsWith("+") && recipient.length() <= 12) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain "
                    + "an international code. Please correct the number and try again.";
        }
    }

    public String createMessageHash() {

        String firstTwoID = messageID.substring(0, 2);

        String[] words = messageText.trim().split(" ");

        String firstWord = words[0].replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z0-9]", "").toUpperCase();

        messageHash = firstTwoID + ":" + messageNumber + ":" + firstWord + lastWord;
        return messageHash;
    }

    // Message should not exceed 250 characters
    public String checkMessageLength() {
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int over = messageText.length() - 250;
            return "Message exceeds 250 characters by " + over + "; please reduce the size.";
        }
    }

    public String sentMessage(int choice) {
        switch (choice) {
            case 1:
                messageStatus = "Sent";
                totalMessagesSent++;
                return "Message successfully sent.";
            case 2:
                messageStatus = "Disregarded";
                return "Press 0 to delete the message.";
            case 3:
                messageStatus = "Stored";
                return "Message successfully stored.";
            default:
                return "Invalid option selected.";
        }
    }
    // Store message in a JSON file 
   
    public void storeMessage() {
        try {
            // Read existing messages from the file if it already exists
            JSONArray messagesArray;

            if (Files.exists(Paths.get(JSON_FILE))) {
                String existingContent = new String(Files.readAllBytes(Paths.get(JSON_FILE)));
                messagesArray = new JSONArray(existingContent);
            } else {
                // File does not exist yet so start a fresh empty list
                messagesArray = new JSONArray();
            }

            // Package the message details into a JSON object
            JSONObject messageObject = new JSONObject();
            messageObject.put("messageID", messageID);
            messageObject.put("messageNumber", messageNumber);
            messageObject.put("messageHash", messageHash);
            messageObject.put("recipient", recipient);
            messageObject.put("messageText", messageText);
            messageObject.put("messageStatus", messageStatus);

            // Add this message to the list
            messagesArray.put(messageObject);

            // Save the updated list back to the file
            FileWriter fileWriter = new FileWriter(JSON_FILE);
            fileWriter.write(messagesArray.toString(4));
            fileWriter.flush();
            fileWriter.close();

            System.out.println("Message successfully stored in file.");

        } catch (IOException e) {
            System.out.println("Error storing message: " + e.getMessage());
        }
    }
    public String printMessages() {
        return "--- Message Details ---"
                + "Message ID:     " + messageID
                + "Message Hash:   " + messageHash
                + "Recipient:      " + recipient
                + "Message:        " + messageText
                + "Status:         " + messageStatus;
    }

    public static int returnTotalMessages() {
        return totalMessagesSent;
    }

    // GETTERS
    public String getMessageID() {
        return messageID;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public int getMessageNumber() {
        return messageNumber;
    }
}
