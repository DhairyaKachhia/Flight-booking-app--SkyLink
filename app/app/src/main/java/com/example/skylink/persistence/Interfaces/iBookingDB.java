package com.example.skylink.persistence.Interfaces;

import com.example.skylink.objects.Interfaces.iPassengerData;

public interface iBookingDB {

    void addBooking(iPassengerData passengerData, long userId);

    boolean findBooking(iPassengerData searchPassengerData, long userId);

    void updateBookingInformation(String emailAddress, long userId, String bookingNumber, String seatNumber);

}
