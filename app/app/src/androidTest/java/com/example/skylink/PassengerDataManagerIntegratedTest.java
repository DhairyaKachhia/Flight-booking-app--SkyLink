package com.example.skylink;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.skylink.application.Services;
import com.example.skylink.business.Implementations.PassengerDataManager;
import com.example.skylink.objects.Interfaces.iPassengerData;
import com.example.skylink.persistence.Implementations.hsqldb.BookingStub;
import com.example.skylink.persistence.Interfaces.iBookingDB;
import com.example.skylink.presentation.User_Auth.SignInActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class PassengerDataManagerIntegratedTest {
    private iBookingDB bookingDatabase;
    private PassengerDataManager passengerDataManager;
    @Rule
    public ActivityScenarioRule<SignInActivity> activityRule =
            new ActivityScenarioRule<>(SignInActivity.class);
    @Before
    public void setUp() {
        bookingDatabase = Services.getBookDatabase();
        passengerDataManager = new PassengerDataManager(bookingDatabase);
    }

    // General Test Cases

    @Test
    public void testAddBooking() {
        String title = "Mr";
        String firstName = "yiming";
        String lastName = "zang";
        String telephoneNumber = "1234567890";
        String emailAddress = "yiming@outlook.com";

        iPassengerData result = passengerDataManager.addBooking(title, firstName, lastName, telephoneNumber, emailAddress);
        // Verify that the booking was added successfully
        assertNotNull(result);
        Assert.assertEquals(title, result.getTitle());
        Assert.assertEquals(firstName, result.getFirstName());
        Assert.assertEquals(lastName, result.getLastName());
        Assert.assertEquals(telephoneNumber, result.getTelephoneNumber());
        Assert.assertEquals(emailAddress, result.getEmailAddress());
    }

    @Test
    public void testFindBooking() {
        String title = "Mr";
        String firstName = "yiming";
        String lastName = "zang";
        String telephoneNumber = "1234567890";
        String emailAddress = "yiming@outlook.com";

        // Add a booking first
        passengerDataManager.addBooking(title, firstName, lastName, telephoneNumber, emailAddress);

        boolean bookingFound = passengerDataManager.findBooking(title, firstName, lastName, telephoneNumber, emailAddress);
        assertTrue(bookingFound);
    }

    @Test
    public void testAddEmptyBooking() {
        // Test adding empty booking information
        String title = "";
        String firstName = "";
        String lastName = "";
        String telephoneNumber = "";
        String emailAddress = "";

        iPassengerData result = passengerDataManager.addBooking(title, firstName, lastName, telephoneNumber, emailAddress);
        assertNull(result); // Booking should not be added successfully
    }

    @Test
    public void testFindNonExistentBooking() {
        // Test finding a booking that doesn't exist
        String title = "Mr";
        String firstName = "yiming";
        String lastName = "zang";
        String telephoneNumber = "1234567890";
        String emailAddress = "yiming@outlook.com";

        boolean bookingFound = passengerDataManager.findBooking(title, firstName, lastName, telephoneNumber, emailAddress);
        assertFalse(bookingFound); // Booking should not be found
    }

    @Test
    public void testAddBookingWithSpecialCharacters() {
        // Test adding booking with special characters
        String title = "Mr";
        String firstName = "yiming";
        String lastName = "zang";
        String telephoneNumber = "1234567890";
        String emailAddress = "yiming@outlook.com";

        // Add booking with special characters in first name
        String specialFirstName = firstName + "!@#$%^&*()";
        iPassengerData result = passengerDataManager.addBooking(title, specialFirstName, lastName, telephoneNumber, emailAddress);
        assertNotNull(result);
        Assert.assertEquals(specialFirstName, result.getFirstName());
    }

    @Test
    public void testAddBookingWithLongStrings() {
        // Test adding booking with long strings
        String title = "Mr";
        String firstName = "yiming";
        String lastName = "zang";
        String telephoneNumber = "1234567890";
        String emailAddress = "yiming@outlook.com";

        // Add booking with long strings
        String longFirstName = firstName.repeat(100000);
        iPassengerData result = passengerDataManager.addBooking(title, longFirstName, lastName, telephoneNumber, emailAddress);
        assertNotNull(result);
        Assert.assertEquals(longFirstName, result.getFirstName());
    }
}
