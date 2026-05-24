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
public class RegistrationFeatureTest {
    
   private RegistrationFeature registration;

    @BeforeEach
    public void setUp() {
        registration = new RegistrationFeature();
    }

    // ===== USERNAME TESTS (assertEquals) =====

    @Test
    public void testUsernameCorrectlyFormatted() {
        String result = registration.checkUserName("kyl_1")
                ? "Welcome <user first name>, <user last name> it is great to see you."
                : "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        assertEquals("Welcome <user first name>, <user last name> it is great to see you.", result);
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        String result = registration.checkUserName("kyle!!!!!!!")
                ? "Welcome <user first name>, <user last name> it is great to see you."
                : "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.", result);
    }

    // ===== PASSWORD TESTS (assertEquals) =====

    @Test
    public void testPasswordMeetsComplexityRequirements() {
        String result = registration.checkPassword("Ch&&sec@ke99!")
                ? "Password successfully captured."
                : "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        assertEquals("Password successfully captured.", result);
    }

    @Test
    public void testPasswordDoesNotMeetComplexityRequirements() {
        String result = registration.checkPassword("password")
                ? "Password successfully captured."
                : "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.", result);
    }

    // ===== CELL NUMBER TESTS (assertEquals) =====

    @Test
    public void testCellNumberCorrectlyFormatted() {
        String result = registration.checkCellNumber("+27838968976")
                ? "Cell number successfully captured."
                : "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        assertEquals("Cell number successfully captured.", result);
    }

    @Test
    public void testCellNumberIncorrectlyFormatted() {
        String result = registration.checkCellNumber("08966553")
                ? "Cell number successfully captured."
                : "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        assertEquals("Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.", result);
    }

    // ===== LOGIN TESTS (assertTrue/assertFalse) =====

    @Test
    public void testLoginSuccessful() {
        LoginFeature login = new LoginFeature("kyl_1", "Ch&&sec@ke99!");
        assertTrue(login.login("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        LoginFeature login = new LoginFeature("kyl_1", "Ch&&sec@ke99!");
        assertFalse(login.login("kyl_1", "wrongpassword"));
    }

    // ===== assertTrue/assertFalse TESTS =====

    @Test
    public void testUsernameCorrectlyFormattedReturnsTrue() {
        assertTrue(registration.checkUserName("kyl_1"));
    }

    @Test
    public void testUsernameIncorrectlyFormattedReturnsFalse() {
        assertFalse(registration.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testPasswordMeetsRequirementsReturnsTrue() {
        assertTrue(registration.checkPassword("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordDoesNotMeetRequirementsReturnsFalse() {
        assertFalse(registration.checkPassword("password"));
    }

    @Test
    public void testCellNumberCorrectlyFormattedReturnsTrue() {
        assertTrue(registration.checkCellNumber("+27838968976"));
    }

    @Test
    public void testCellNumberIncorrectlyFormattedReturnsFalse() {
        assertFalse(registration.checkCellNumber("08966553"));
    }
} 