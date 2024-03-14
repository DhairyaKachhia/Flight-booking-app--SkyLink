package com.example.skylink.business.Interface;

import com.example.skylink.objects.Interfaces.iFlightInfo;

import java.util.List;

public interface iFlightBookingHandlers {
    String addConfirmBookings(long user_id, List<iFlightInfo> flightInfo);
    List<iFlightInfo> getBookingDetails(long userID);
}
