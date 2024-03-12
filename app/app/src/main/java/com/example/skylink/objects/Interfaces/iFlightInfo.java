package com.example.skylink.objects.Interfaces;

import java.util.HashMap;

public interface iFlightInfo {
    String getEconOrBus();

    void setEconOrBus(String econOrBus);

    HashMap<iPassengerData, String> getSeatSelected();

    void setSeatSelected(HashMap<iPassengerData, String> seatSelected);

    iFlight getFlight();
    void setFlight(iFlight flight);
}
