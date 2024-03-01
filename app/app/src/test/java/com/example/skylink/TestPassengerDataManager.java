package com.example.skylink;

import com.example.skylink.application.Services;
import com.example.skylink.business.Implementations.PassengerDataManager;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestPassengerDataManager {
    private PassengerDataManager passengerDataManager;

    @Before
    public void setUp() {
        System.out.println("Starting tests for PassengerDataManager");
        passengerDataManager = new PassengerDataManager(Services.getBookDatabase());
    }

    @Test
    public void testAddBooking() {
        System.out.println("\nTesting findBooking in PassengerDataManager");

        String title = "Mr";
        String firstName = "Jeff";
        String lastName = "Akan";
        String telephoneNumber = "2045145629";
        String emailAddress = "jeff.akan@example.com";

        passengerDataManager.addBooking(title, firstName, lastName, telephoneNumber, emailAddress);

        boolean retrievedBooking = passengerDataManager.findBooking(title, firstName, lastName, telephoneNumber, emailAddress);

        assertTrue("The booking should not be null", retrievedBooking);

    }
}
