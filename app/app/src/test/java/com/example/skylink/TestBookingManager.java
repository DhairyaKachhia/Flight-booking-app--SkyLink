package com.example.skylink;

import com.example.skylink.business.BookingManager;
import com.example.skylink.objects.Booking;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestBookingManager {
    private BookingManager bookingManager;

    @Before
    public void setUp() {
        System.out.println("Starting tests for BookingManager");
        bookingManager = new BookingManager();
    }

//    @Test
//    public void testAddBooking() {
//        System.out.println("\nTesting findBooking in BookingManager");
//
//        String title = "Mr";
//        String firstName = "Jeff";
//        String lastName = "Akan";
//        String telephoneNumber = "2045145629";
//        String emailAddress = "jeff.akan@example.com";
//
//        bookingManager.addBooking(title, firstName, lastName, telephoneNumber, emailAddress);
//
//        boolean retrievedBooking = bookingManager.findBooking(title, firstName, lastName, telephoneNumber, emailAddress);
//
//        assertTrue("The booking should not be null", retrievedBooking);
//
//    }
}
