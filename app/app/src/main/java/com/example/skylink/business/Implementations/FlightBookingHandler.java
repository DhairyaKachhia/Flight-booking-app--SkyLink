package com.example.skylink.business.Implementations;

import com.example.skylink.application.Services;
import com.example.skylink.business.Interface.iFlightBookingHandler;
import com.example.skylink.objects.Interfaces.ITripInvoice;
import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.objects.Interfaces.iPassengerData;
import com.example.skylink.persistence.Interfaces.IPaymentDB;
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

    public boolean addConfirmBooking(HashMap<String, List<List<iFlight>>> flights, long user_id, HashMap<iPassengerData, String> seatMapOutbound, HashMap<iPassengerData, String> seatMapInbound) {
        return true;
    }
}
