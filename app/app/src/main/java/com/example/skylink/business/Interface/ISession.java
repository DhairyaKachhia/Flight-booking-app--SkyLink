package com.example.skylink.business.Interface;


import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.objects.Interfaces.iFlightSearch;
import com.example.skylink.objects.Interfaces.iPassengerData;

import java.util.HashMap;
import java.util.List;

public interface ISession {
    List<iPassengerData> getPassengerData();
    void setPassengerData(List<iPassengerData> passengerData);

    long getUser_id();
    void setUser_id(long user_id);

    iFlightSearch getFlightSearch();
    void setFlightSearch(iFlightSearch flightSearch);

    String getUsername();
    void setUsername(String username);

    String getEmail();
    void setEmail(String email);

    HashMap<String, List<List<iFlight>>> getSelectedFlights();
    void setSelectedFlights(HashMap<String, List<List<iFlight>>> selectedFlights);
}
