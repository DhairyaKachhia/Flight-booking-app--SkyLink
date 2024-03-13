package com.example.skylink.persistence.Interfaces;

import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.objects.Interfaces.iFlightInfo;
import com.example.skylink.objects.Interfaces.iPassengerData;

import java.util.HashMap;
import java.util.List;

public interface iFlightBookingDB {
    long addFlightBooking(long user_id, String bound, iFlight flight , int price, String bookingNumber, String econBusiness);
    List<Long> getBookingNumberByUserId(long userId);
    List<String> getFlightsByUserId(String bound, String bookingNumberByUserId);
    iFlightBookingDB initialize();
    iFlightBookingDB drop();
}
