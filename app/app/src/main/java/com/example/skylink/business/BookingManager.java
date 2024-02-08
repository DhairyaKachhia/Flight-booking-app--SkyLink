package com.example.skylink.business;

import com.example.skylink.data.BookingDatabase;
import com.example.skylink.objects.Booking;

public class BookingManager {
    private BookingDatabase bookingDatabase;

    public BookingManager() {
        this.bookingDatabase = new BookingDatabase();
    }

    public void addBooking(String title, String firstName, String lastName, String telephoneNumber, String emailAddress) {
        Booking newBooking = new Booking(title, firstName, lastName, telephoneNumber, emailAddress);
        bookingDatabase.addBooking(newBooking);
    }

    public boolean findBooking(String title, String firstName, String lastName, String telephoneNumber, String emailAddress) {
        Booking searchBooking = new Booking(title, firstName, lastName, telephoneNumber, emailAddress);
            if (bookingDatabase.findBooking(searchBooking)){
                return true;
            }
        return false;
    }
}
