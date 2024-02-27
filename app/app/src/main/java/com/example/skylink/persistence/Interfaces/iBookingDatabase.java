package com.example.skylink.persistence.Interfaces;

import com.example.skylink.objects.Implementations.PassengerData;
import com.example.skylink.objects.Interfaces.iPassengerData;

public interface iBookingDatabase {

    void addBooking(iPassengerData passengerData);

    boolean findBooking(iPassengerData searchPassengerData);

}
