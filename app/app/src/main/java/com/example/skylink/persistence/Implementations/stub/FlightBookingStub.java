package com.example.skylink.persistence.Implementations.stub;

import com.example.skylink.objects.Interfaces.iFlightInfo;
import com.example.skylink.objects.Interfaces.iPassengerData;
import com.example.skylink.persistence.Interfaces.iFlightBookingDB;

import java.util.HashMap;

public class FlightBookingStub implements iFlightBookingDB {

    @Override
    public void addFlightBooking(long user_id, String bound, iFlightInfo flightInfo, int price) {

    }

    @Override
    public iFlightBookingDB initialize() {
        return null;
    }

    @Override
    public iFlightBookingDB drop() {
        return null;
    }
}
