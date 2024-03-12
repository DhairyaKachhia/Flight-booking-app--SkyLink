package com.example.skylink.business.Implementations;

import com.example.skylink.application.Services;
import com.example.skylink.business.Interface.iFlightBookingHandler;
import com.example.skylink.objects.Interfaces.iFlightInfo;
import com.example.skylink.persistence.Interfaces.iFlightBookingDB;

import java.util.HashMap;
import java.util.List;

public class FlightBookingHandler implements iFlightBookingHandler {
    private iFlightBookingDB flightBookingDB;

    public FlightBookingHandler(iFlightBookingDB flightBookingDB) {
        this.flightBookingDB = flightBookingDB;
    }

    public FlightBookingHandler(boolean forProduction) {
        this.flightBookingDB = Services.getFlightBookingDB();

    }

    public boolean addConfirmBooking(long user_id, HashMap<String, iFlightInfo> flightInfo) {

        if(flightInfo.get("Outbound").equals("Outbound")){
            iFlightInfo flight_Info = flightInfo.get("Outbound");
            int price = 0;
            flightBookingDB.addFlightBooking(user_id, "Outbound",flight_Info,price);
        }
        if(flightInfo.get("Inbound").equals("Inbound")){
            iFlightInfo flight_Info = flightInfo.get("Outbound");
            int price = 0;
            flightBookingDB.addFlightBooking(user_id, "Inbound", flight_Info,price);
        }

        return true;
    }

}
