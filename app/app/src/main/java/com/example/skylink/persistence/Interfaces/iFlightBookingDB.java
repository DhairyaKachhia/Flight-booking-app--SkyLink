package com.example.skylink.persistence.Interfaces;

import com.example.skylink.objects.Interfaces.iFlightInfo;
import com.example.skylink.objects.Interfaces.iPassengerData;

import java.util.HashMap;
import java.util.List;

public interface iFlightBookingDB {
    void addFlightBooking(long user_id, String bound,  iFlightInfo flightInfo, int price, String bookingNumber);
    List<String> getFlightIdsByUserId(long userId);
    iFlightBookingDB initialize();
    iFlightBookingDB drop();
}
