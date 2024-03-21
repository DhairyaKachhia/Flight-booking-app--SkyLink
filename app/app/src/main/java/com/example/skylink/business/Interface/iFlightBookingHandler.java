package com.example.skylink.business.Interface;

import com.example.skylink.objects.Interfaces.iFlightInfo;

import java.util.HashMap;
import java.util.List;

public interface iFlightBookingHandler {

    public void storeAddons(int bagNumber, int petNumber, int wifiOption, int wheelchairOption, List<iFlightInfo> flightInfoList);
    String addConfirmBookings(long user_id, List<iFlightInfo> flightInfo);
    List<iFlightInfo> getBookingDetails(long userID);
}
