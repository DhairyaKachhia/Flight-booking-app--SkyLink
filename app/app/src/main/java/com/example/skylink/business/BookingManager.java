package com.example.skylink.business;

import com.example.skylink.data.BookingDatabase;
import com.example.skylink.objects.Booking;
public class BookingManager {
    private BookingDatabase bookingdatabase;
    public BookingManager(){
        this.bookingdatabase = new BookingDatabase();

    }

    public void addBooking(String name, String destination, String date){
        bookingdatabase.addBooking(new Booking(name, destination, date));
    }

    public Booking findBooking(String name, String destination, String date){
        return bookingdatabase.findBooking(new Booking(name,destination,date));
    }
}
