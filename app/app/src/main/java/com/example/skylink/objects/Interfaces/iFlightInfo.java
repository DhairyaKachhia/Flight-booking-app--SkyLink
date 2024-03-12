package com.example.skylink.objects.Interfaces;

import java.util.HashMap;
import java.util.List;

public interface iFlightInfo {
    String getEconOrBus();

    void setEconOrBus(String econOrBus);

    HashMap<iPassengerData, String> getSeatSelected();

    void setSeatSelected(HashMap<iPassengerData, String> seatSelected);

    List<iFlight> getFlight();
    void setFlight(List<iFlight> flight);
}
