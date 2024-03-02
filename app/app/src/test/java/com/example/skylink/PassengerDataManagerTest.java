package com.example.skylink;

import com.example.skylink.objects.Interfaces.iPassengerData;
import com.example.skylink.persistence.Interfaces.iBookingDB;
import org.junit.Before;
import org.junit.Test;
import com.example.skylink.business.Implementations.PassengerDataManager;
import static org.junit.Assert.*;

public class PassengerDataManagerTest {
    private PassengerDataManager passengerDataManager;
    private MockBookingDatabase mockBookingDatabase;

    @Before
    public void setUp() {
        mockBookingDatabase = new MockBookingDatabase();
        passengerDataManager = new PassengerDataManager(mockBookingDatabase);
    }

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
        assertEquals(title, result.getTitle());
        assertEquals(firstName, result.getFirstName());
        assertEquals(lastName, result.getLastName());
        assertEquals(telephoneNumber, result.getTelephoneNumber());
        assertEquals(emailAddress, result.getEmailAddress());
    }

    @Test
    public void testFindBooking() {
        String title = "Mr";
        String firstName = "yiming";
        String lastName = "zang";
        String telephoneNumber = "1234567890";
        String emailAddress = "yiming@outlook.com";

        boolean bookingFound = passengerDataManager.findBooking(title, firstName, lastName, telephoneNumber, emailAddress);
        assertTrue(bookingFound);
    }

    private static class MockBookingDatabase implements iBookingDB {
        @Override
        public void addBooking(iPassengerData passengerData) { // Mock implementation always returns true
        }

        @Override
        public boolean findBooking(iPassengerData passengerData) { // Mock implementation always returns true
            return true;
        }
    }
}

