package com.example.skylink;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.skylink.business.BookingManager;
import com.example.skylink.objects.Booking;

public class TestBookingManager {
    private BookingManager bookingmanager;
    @Before
    public void setUp(){
        System.out.println("Starting tests for BookingManager");
        bookingmanager = new BookingManager();
    }

    @Test
    public void testAddBooking(){
        System.out.println("\n testing addBooking in BookingManager");
        bookingmanager.addBooking("Jeff", "Montreal", "07/09/2025");
        Booking booking = bookingmanager.findBooking("Jeff", "Montreal", "07/09/2025");
        assertNotNull("This should not be null",booking);
    }
}
