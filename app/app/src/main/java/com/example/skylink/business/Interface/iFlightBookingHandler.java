package com.example.skylink.business.Interface;

import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.objects.Interfaces.iFlightInfo;
import com.example.skylink.objects.Interfaces.iPassengerData;

import java.util.HashMap;
import java.util.List;

public interface iFlightBookingHandler {
    boolean addConfirmBooking(long user_id, HashMap<String, iFlightInfo> flightInfo);
}
