package com.example.skylink.presentation;

import android.content.Context;
import com.example.skylink.objects.Interfaces.iAircraft;
import com.example.skylink.objects.Interfaces.iBookingInfo;
import com.example.skylink.objects.Interfaces.iCity;
import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.objects.Interfaces.iFlightInfo;
import com.example.skylink.objects.Interfaces.iFlightSearch;
import com.example.skylink.objects.Interfaces.iFlights;
import com.example.skylink.objects.Interfaces.iPassengerData;
import com.example.skylink.objects.Interfaces.ITripInvoice;
import com.example.skylink.objects.Interfaces.IUserProperties;
import java.util.List;

public interface ISession {
    iAircraft getAircraft();
    void setAircraft(iAircraft aircraft);

    iCity getCity();
    void setCity(iCity city);

    iFlight getFlight();
    void setFlight(iFlight flight);

    iFlightInfo getFlightInfo();
    void setFlightInfo(iFlightInfo flightInfo);

    iFlights getFlights();
    void setFlights(iFlights flights);

    IUserProperties getUserProperties();
    void setUserProperties(IUserProperties userProperties);

    String getUsername();
    void setUsername(String username);

    String getEmail();
    void setEmail(String email);

    long getUserId();
    void setUserId(long userId);

    Context getContext();
    void setContext(Context context);

    iBookingInfo getBookingInfo();
    void setBookingInfo(iBookingInfo bookingInfo);

    iFlightSearch getFlightSearch();
    void setFlightSearch(iFlightSearch flightSearch);

    List<iPassengerData> getPassengerData();
    void setPassengerData(List<iPassengerData> passengerData);

    ITripInvoice getTripInvoice();
    void setTripInvoice(ITripInvoice tripInvoice);
}
