package com.example.skylink.business.Interface;

import com.example.skylink.objects.Interfaces.iFlightInfo;

import java.util.HashMap;
import java.util.List;

public interface iFlightBookingHandler {
    String addConfirmBookings(long user_id, List<iFlightInfo> flightInfo);
    List<iFlightInfo> getBookingDetails(long userID);
}
