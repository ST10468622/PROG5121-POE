/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chat.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Lisakhanya
 */
public class MessageTest {

    // Part 2: Test setup
    private Message message1;
    private Message message2;

    @BeforeEach
    public void setUp() {

        message1 = new Message(1, "+27718693002", "Hi Tyrrant, can you join us for dinner tonight?");
        message2 = new Message(2, "08575975889", "Hi Michaela, did you receive the payment?");
    }

    // Length of message tests
    @Test
    public void testMessageLengthUnder250CharsSuccess() {

        String result = message1.checkMessageLength();
        assertEquals("Message ready to send.", result);
    }

    @Test
    public void testMessageLengthOver250CharsFailure() {
        // Message shouldn't be longer than 250 characters
        String longMessage = "This is a very long message that is definitely going to exceed "
                + "the two hundred and fifty character limit that has been set for "
                + "this chat application and should therefore return an error message "
                + "telling the user to reduce the size of their message immediately.";

        Message longMsg = new Message(3, "+27718693002", longMessage);
        String result = longMsg.checkMessageLength();

        int over = longMessage.length() - 250;
        assertEquals("Message exceeds 250 characters by " + over + "; please reduce the size.", result);
    }

    // Phone number of the recipient
    @Test
    public void testRecipientCellCorrectlyFormatted() {

        String result = message1.checkRecipientCell();
        assertEquals("Cell phone number successfully captured.", result);
    }

    @Test
    public void testRecipientCellIncorrectlyFormatted() {

        String result = message2.checkRecipientCell();
        assertEquals("Cell phone number is incorrectly formatted or does not contain "
                + "an international code. Please correct the number and try again.", result);
    }

    @Test
    public void testMessageHashCorrectForMessage1() {

        String hash = message1.getMessageHash();
        assertTrue(hash.endsWith(":HITONIGHT"),
                "Hash should end with :HITONIGHT but was: " + hash);
    }

    @Test
    public void testMessageHashCorrectForMessage2() {

        String hash = message2.getMessageHash();

        assertTrue(hash.endsWith(":HIPAYMENT"),
                "Hash should end with :HIPAYMENT but was: " + hash);
    }

    @Test
    public void testMessageHashesInLoop() {

        Message[] messages = {message1, message2};
        String[] expectedEndings = {":HITONIGHT", ":HIPAYMENT"};

        for (int i = 0; i < messages.length; i++) {
            String hash = messages[i].getMessageHash();
            assertTrue(hash.endsWith(expectedEndings[i]),
                    "Hash " + (i + 1) + " should end with " + expectedEndings[i]
                    + " but was: " + hash);
        }
    }

    @Test
    public void testMessageIDIsCreated() {

        String id = message1.getMessageID();
        assertNotNull(id, "Message ID should not be null");
        assertFalse(id.isEmpty(), "Message ID should not be empty");
        System.out.println("Message ID generated: " + id);
    }

    @Test
    public void testMessageIDIsNotMoreThan10Characters() {

        assertTrue(message1.checkMessageID(),
                "Message ID should not be more than 10 characters");
    }

    @Test
    public void testSendMessageOption1SendMessage() {

        String result = message1.sentMessage(1);
        assertEquals("Message successfully sent.", result);
    }

    @Test
    public void testSendMessageOption2DisregardMessage() {

        String result = message2.sentMessage(2);
        assertEquals("Press 0 to delete the message.", result);
    }

    @Test
    public void testSendMessageOption3StoreMessage() {
        // User selects option 3 = Store
        Message message3 = new Message(3, "+27718693002", "Test store message.");
        String result = message3.sentMessage(3);
        assertEquals("Message successfully stored.", result);
    }

    // The total messages that were sent
    @Test
    public void testTotalMessagesSentIncrementsCorrectly() {
        // Reset by creating fresh messages
        Message testMsg1 = new Message(1, "+27718693002", "First test message.");
        Message testMsg2 = new Message(2, "+27718693002", "Second test message.");

        int before = Message.returnTotalMessages();

        testMsg1.sentMessage(1);
        testMsg2.sentMessage(1);

        int after = Message.returnTotalMessages();
        assertEquals(before + 2, after,
                "Total messages sent should increase by 2 after sending 2 messages");
    }
}
