package com.example.skylink.persistence.Interfaces;

import com.example.skylink.objects.Interfaces.iPassengerData;

public interface iBookingDB {

    void addBooking(iPassengerData passengerData);

    boolean findBooking(iPassengerData searchPassengerData);

}
