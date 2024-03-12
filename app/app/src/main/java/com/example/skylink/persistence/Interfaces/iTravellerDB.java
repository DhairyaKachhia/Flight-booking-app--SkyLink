package com.example.skylink.persistence.Interfaces;

import com.example.skylink.objects.Interfaces.iPassengerData;

public interface iTravellerDB {
    void insertIntoTravellers(String booking_id, iPassengerData passenger, String seatNumber);
}
