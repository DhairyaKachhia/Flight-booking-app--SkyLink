package com.example.skylink.business.Interface;

import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.objects.Interfaces.iPassengerData;

import java.util.HashMap;
import java.util.List;

public interface iFlightBookingHandler {
    boolean addConfirmBooking(HashMap<String, List<List<iFlight>>> flights, long user_id, HashMap<iPassengerData, String> seatMapOutband, HashMap<iPassengerData, String> seatInbound);
}
