package com.example.skylink.business.Interface;

import com.example.skylink.objects.Interfaces.iFlightInfo;

import java.util.HashMap;
import java.util.List;

public interface iFlightBookingHandler {
    List<String> addConfirmBookings(long user_id, HashMap<String, iFlightInfo> flightInfo);
    List<HashMap<String, HashMap<String, iFlightInfo>>> getBookingDetails(long userID);
}
