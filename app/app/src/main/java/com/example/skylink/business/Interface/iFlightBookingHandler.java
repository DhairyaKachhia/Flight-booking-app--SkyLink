package com.example.skylink.business.Interface;

import com.example.skylink.objects.Interfaces.iFlightInfo;

import java.util.HashMap;

public interface iFlightBookingHandler {
    boolean addConfirmBooking(long user_id, HashMap<String, iFlightInfo> flightInfo);
}
