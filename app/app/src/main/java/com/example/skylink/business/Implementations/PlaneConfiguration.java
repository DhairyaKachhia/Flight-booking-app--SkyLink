package com.example.skylink.business.Implementations;

import com.example.skylink.application.Services;
import com.example.skylink.business.Interface.iPlaneConfiguration;
import com.example.skylink.objects.Interfaces.iAircraft;
import com.example.skylink.persistence.Interfaces.IFlightDB;

import java.util.Map;

public class PlaneConfiguration implements iPlaneConfiguration {
    IFlightDB flightHSQLDB;
    public PlaneConfiguration(IFlightDB flightHSQLDB){
        this.flightHSQLDB = flightHSQLDB;
    }
    public String[] getPlaneConfiguration(String airCraftName, String econOrBus) {
        Map<String, iAircraft> allFlights = Services.getFlightDatabase().getAircraftMap();
        if (allFlights.containsKey(airCraftName)) {
            iAircraft aircraft = allFlights.get(airCraftName);
            int busnumSeatPerRow = aircraft.getNumSeatPerRowBusiness();
            int busnumRows = aircraft.getNumRowsBusiness();
            int econnumSeatPerRow = aircraft.getNumSeatPerRowEcon();
            int econnumRows = aircraft.getNumRowsEcon();
            if ("econ".equalsIgnoreCase(econOrBus)) {
                busnumSeatPerRow = aircraft.getNumSeatPerRowEcon();
                busnumRows = aircraft.getNumRowsEcon();
            } else if ("bus".equalsIgnoreCase(econOrBus)) {
                econnumSeatPerRow = aircraft.getNumSeatPerRowBusiness();
                econnumRows = aircraft.getNumRowsBusiness();
            } else {
                // Handle invalid econOrBus parameter
                return null;
            }
            return new String[]{airCraftName, String.valueOf(busnumSeatPerRow), String.valueOf(busnumRows),
                    String.valueOf(econnumSeatPerRow), String.valueOf(econnumRows)};
        }
        return null;
    }
}

