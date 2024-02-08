package com.example.skylink.data;

import com.example.skylink.objects.Booking;

import java.util.ArrayList;
import java.util.List;
public class BookingDatabase {
    private final List<Booking> bookings;

    public BookingDatabase(){
        this.bookings = new ArrayList<>();
    }

    public void addBooking(Booking booking){
        bookings.add(booking);
    }

    public boolean findBooking(Booking searchBooking) {
        Booking result = null;
        for (Booking b : bookings) {
            if (b.getTitle().equals(searchBooking.getTitle()) &&
                    b.getFirstName().equals(searchBooking.getFirstName()) &&
                    b.getLastName().equals(searchBooking.getLastName()) &&
                    b.getTelephoneNumber().equals(searchBooking.getTelephoneNumber()) &&
                    b.getEmailAddress().equals(searchBooking.getEmailAddress())) {
                return  true;
            }
        }
        return false;
    }

}
