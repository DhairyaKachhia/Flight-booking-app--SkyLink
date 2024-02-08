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

    public Booking findBooking(Booking booking){
        Booking result = null;
        for(Booking b : bookings){
            if(b.equals(booking)){
                result = booking;
            }
        }
        return result;
    }
}
