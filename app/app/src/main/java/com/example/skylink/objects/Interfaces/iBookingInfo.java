package com.example.skylink.objects.Interfaces;

public interface iBookingInfo {
    // Get the unique identifier of the booking
    long getId();

    // Set the unique identifier of the booking
    void setId(long id);

    // Get the booking number
    String getBookingNumber();

    // Set the booking number
    void setBookingNumber(String bookingNumber);

    // Get the type of booking (e.g., Economy, Business)
    String getEconBus();

    // Set the type of booking (e.g., Economy, Business)
    void setEconBus(String econBus);

    // Get the direction of the flight
    String getDirection();

    // Set the direction of the flight
    void setDirection(String direction);
}
