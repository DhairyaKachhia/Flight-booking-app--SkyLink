package com.example.skylink.persistence.Implementations.stub;

import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.objects.Interfaces.iFlightInfo;
import com.example.skylink.objects.Interfaces.iPassengerData;
import com.example.skylink.persistence.Interfaces.iFlightBookingDB;

import java.util.HashMap;
import java.util.List;

public class FlightBookingStub implements iFlightBookingDB {

    @Override
    public void addFlightBooking(long user_id, String bound, iFlight flight , int price, String bookingNumber, String econBusiness) {
    }

    @Override
    public List<String> getBookingNumberByUserId(long userId) {
        return null;
    }

    @Override
    public List<String> getFlightsByUserId(String bound, String bookingNumberByUserId) {
        return null;
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
