package com.example.skylink.business;

import com.example.skylink.objects.Flight;
import com.example.skylink.objects.Booking;
import com.example.skylink.objects.FlightSearch;

import java.util.ArrayList;
import java.util.List;

public class Session {

    private static Session instance;
    private String username;
    private String email;

    // Temporary storage for flights and bookings
    private FlightSearch flightSearch;
    private Booking[] Bookings;

    public com.example.skylink.objects.Booking[] getBooking() {
        return Bookings;
    }

    public void setBookings(com.example.skylink.objects.Booking[] booking) {
        Bookings = booking;
    }




    public FlightSearch getFlightSearch() {
        return flightSearch;
    }

    public void setFlightSearch(FlightSearch flightSearch) {
        this.flightSearch = flightSearch;
    }

    private Session() {
        // Private constructor to prevent instantiation

    }

    public static synchronized Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
