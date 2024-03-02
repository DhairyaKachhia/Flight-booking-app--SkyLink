package com.example.skylink;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.skylink.business.Implementations.PassengerDataManager;
import com.example.skylink.objects.Interfaces.iPassengerData;
import com.example.skylink.persistence.Implementations.hsqldb.BookingHSQLDB;

public class PassengerDataManagerIntegratedTest {

    private PassengerDataManager passengerDataManager;
    private BookingHSQLDB bookingDatabase;

    @Before
    public void setUp() {
        String dbPath = "test.db"; // db path
        bookingDatabase = new BookingHSQLDB(dbPath); // Connect to db
        bookingDatabase.initialize();
        passengerDataManager = new PassengerDataManager(bookingDatabase); // Use real db to initialize PassengerDataManager
    }

    @Test
    public void testAddBooking_WithRealDatabase() {
        String title = "Mr";
        String firstName = "Yiming";
        String lastName = "Zang";
        String telephoneNumber = "2041234567";
        String emailAddress = "yiming@gmail.com";

        // add booking and return passenger data
        iPassengerData passengerData = passengerDataManager.addBooking(title, firstName, lastName, telephoneNumber, emailAddress);

        // Return passenger data
        assertNotNull(passengerData);
        assertEquals(title, passengerData.getTitle());
        assertEquals(firstName, passengerData.getFirstName());
        assertEquals(lastName, passengerData.getLastName());
        assertEquals(telephoneNumber, passengerData.getTelephoneNumber());
        assertEquals(emailAddress, passengerData.getEmailAddress());
    }


    @Test
    public void testFindBooking_ReturnsFalse_WhenBookingDoesNotExist() {
        String title = "Mr";
        String firstName = "Yiming";
        String lastName = "Zang";
        String telephoneNumber = "2041234567";
        String emailAddress = "yiming@gmail.com";

        // Search for non existing booking
        boolean bookingFound = passengerDataManager.findBooking(title, firstName, lastName, telephoneNumber, emailAddress);

        assertFalse(bookingFound); // No booking exists, return false
    }

    @After
    public void tearDown() {
        bookingDatabase.drop(); // CLear db
    }
}
