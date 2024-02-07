package com.example.skylink.business;

import com.example.skylink.data.BookingDatabase;
import com.example.skylink.objects.Booking;
public class BookingManager {
    private BookingDatabase bookingdatabase;
    public BookingManager(){
        this.bookingdatabase = new BookingDatabase();

    }
}
