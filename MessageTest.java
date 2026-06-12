/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chat.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Lisakhanya
 */
public class MessageTest {

    // Part 2: Test setup
    private Message message1;
    private Message message2;

    // Part 3 : Test setup
    private Message testMessage1;
    private Message testMessage2;
    private Message testMessage3;
    private Message testMessage4;
    private Message testMessage5;

    private ArrayList<String> sentMessages;
    private ArrayList<String> storedMessages;
    private ArrayList<String> disregardedMessages;
    private ArrayList<String> messageHashes;
    private ArrayList<String> messageIDs;

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

    // Part 3: Helper setup method 
    private void setUpPart3() {

        // POE Part 3 test data table
        testMessage1 = new Message(1, "+27834557896", "Did you get the cake?");
        testMessage2 = new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        testMessage3 = new Message(3, "+27834484567", "Yohoooo, I am at your gate.");
        testMessage4 = new Message(4, "0838884567", "It is dinner time !");
        testMessage5 = new Message(5, "+27838884567", "Ok, I am leaving without you.");

        // Statuses using your existing sentMessage() method
        
        testMessage1.sentMessage(1); // Sent
        testMessage2.sentMessage(3); // Stored
        testMessage3.sentMessage(2); // Disregarded
        testMessage4.sentMessage(1); // Sent
        testMessage5.sentMessage(3); // Stored

        //  parallel arrays
        
        sentMessages = new ArrayList<>();
        storedMessages = new ArrayList<>();
        disregardedMessages = new ArrayList<>();
        messageHashes = new ArrayList<>();
        messageIDs = new ArrayList<>();

        for (Message msg : new Message[]{testMessage1, testMessage2, testMessage3, testMessage4, testMessage5}) {
            messageHashes.add(msg.getMessageHash());
            messageIDs.add(msg.getMessageID());

            switch (msg.getMessageStatus()) {
                case "Sent" ->
                    sentMessages.add(msg.getMessageText());
                case "Stored" ->
                    storedMessages.add(msg.getMessageText());
                case "Disregarded" ->
                    disregardedMessages.add(msg.getMessageText());
            }
        }
    }

    // Part 3: Sent messages array 
    
    @Test
    public void testSentMessagesArrayPopulatedCorrectly() {
        setUpPart3();

        assertTrue(sentMessages.contains("Did you get the cake?"),
                "Sent array should contain 'Did you get the cake?'");

        assertTrue(sentMessages.contains("It is dinner time !"),
                "Sent array should contain 'It is dinner time !'");

        assertEquals(2, sentMessages.size(),
                "Sent array should contain exactly 2 messages");

        assertFalse(sentMessages.contains(
                "Where are you? You are late! I have asked you to be on time."),
                "Stored message should not appear in sent array");

        assertFalse(sentMessages.contains("Yohoooo, I am at your gate."),
                "Disregarded message should not appear in sent array");
    }

    // Part 3: Display the longest message
    
    @Test
    public void testDisplayLongestMessage() {
        setUpPart3();

        String expected = "Where are you? You are late! I have asked you to be on time.";
        String longest = "";

        for (Message msg : new Message[]{testMessage1, testMessage2, testMessage3, testMessage4, testMessage5}) {
            if (msg.getMessageText().length() > longest.length()) {
                longest = msg.getMessageText();
            }
        }

        assertEquals(expected, longest,
                "The longest message should be from Message 2");
    }

    // PART 3: Search for a message by recipient number
    
    @Test
    public void testSearchMessageByRecipient() {
        setUpPart3();

        String searchRecipient = "0838884567";
        String expected = "It is dinner time !";
        String found = "";

        for (Message msg : new Message[]{testMessage1, testMessage2, testMessage3, testMessage4, testMessage5}) {
            if (msg.getRecipient().equals(searchRecipient)) {
                found = msg.getMessageText();
                break;
            }
        }

        assertEquals(expected, found,
                "Search by '0838884567' should return 'It is dinner time !'");
    }

    // Part 3: Search all messages for a particular recipient
    
    @Test
    public void testSearchAllMessagesForRecipient() {
        setUpPart3();

        String searchRecipient = "+27838884567";
        ArrayList<String> results = new ArrayList<>();

        for (Message msg : new Message[]{testMessage1, testMessage2, testMessage3, testMessage4, testMessage5}) {
            if (msg.getRecipient().equals(searchRecipient)) {
                results.add(msg.getMessageText());
            }
        }

        assertEquals(2, results.size(),
                "Should find exactly 2 messages for +27838884567");

        assertTrue(results.contains(
                "Where are you? You are late! I have asked you to be on time."),
                "Results should contain Message 2");

        assertTrue(results.contains("Ok, I am leaving without you."),
                "Results should contain Message 5");
    }

    // Part 3: Delete a message using its message hash
    @Test
    public void testDeleteMessageByHash() {
        setUpPart3();

        String hashToDelete = testMessage2.getMessageHash();
        int hashIndex = messageHashes.indexOf(hashToDelete);

        assertTrue(hashIndex != -1,
                "Hash for Message 2 should exist in the messageHashes array");

        String deletedMessage = testMessage2.getMessageText();

        // Remove from parallel arrays
        storedMessages.remove(deletedMessage);
        messageHashes.remove(hashIndex);

        // Verify deletion
        assertFalse(storedMessages.contains(
                "Where are you? You are late! I have asked you to be on time."),
                "Deleted message should no longer be in storedMessages array");

        assertEquals(
                "Where are you? You are late! I have asked you to be on time.",
                deletedMessage,
                "The deleted message text should match Message 2");
    }

    // Part 3: Display report - Hash, Recipient, Message for all sent messages
    @Test
    public void testDisplayReport() {
        setUpPart3();

        StringBuilder report = new StringBuilder();

        for (Message msg : new Message[]{testMessage1, testMessage2, testMessage3, testMessage4, testMessage5}) {
            if (msg.getMessageStatus().equals("Sent")) {
                report.append("Message Hash: ").append(msg.getMessageHash()).append("");
                report.append("Recipient:").append(msg.getRecipient()).append("");
                report.append("Message: ").append(msg.getMessageText()).append("");
                report.append("----------------------------");
            }
        }

        String result = report.toString();

        assertTrue(result.contains("Did you get the cake?"),
                "Report should include Message 1 text");

        assertTrue(result.contains("It is dinner time !"),
                "Report should include Message 4 text");

        assertTrue(result.contains("+27834557896"),
                "Report should include Message 1 recipient");

        assertTrue(result.contains("0838884567"),
                "Report should include Message 4 recipient");

        assertTrue(result.contains(testMessage1.getMessageHash()),
                "Report should include Message 1 hash");

        assertTrue(result.contains(testMessage4.getMessageHash()),
                "Report should include Message 4 hash");
    }

    // Part 3: Stored messages array correctly populated
    @Test
    public void testStoredMessagesArrayPopulatedCorrectly() {
        setUpPart3();

        assertTrue(storedMessages.contains(
                "Where are you? You are late! I have asked you to be on time."),
                "Stored array should contain Message 2");

        assertTrue(storedMessages.contains("Ok, I am leaving without you."),
                "Stored array should contain Message 5");

        assertEquals(2, storedMessages.size(),
                "Stored array should contain exactly 2 messages");
    }

    // Part 3: Disregarded messages array correctly populated
    @Test
    public void testDisregardedMessagesArrayPopulatedCorrectly() {
        setUpPart3();

        assertTrue(disregardedMessages.contains("Yohoooo, I am at your gate."),
                "Disregarded array should contain Message 3");

        assertEquals(1, disregardedMessages.size(),
                "Disregarded array should contain exactly 1 message");
    }
}
