package com.example.skylink.business.Implementations;

import com.example.skylink.business.Interface.iPayment;
import com.example.skylink.objects.Interfaces.iFlight;

import java.util.HashMap;
import java.util.List;

public class Payment implements iPayment {
    private String cardNumber;
    private String expirationDate;
    private String cvv;


    @Override
    public boolean makePayment(String cardNumber, String expirationDate, String cvv, double amount) {
        return true;
    }

    @Override
    public HashMap<String, Object> generateInvoice() {
        // Passenger 1 = 250CAD.
        // Passenger 2 = 250CAD.
        // Total = 500CAD.
//        HashMap<String, List<List<iFlight>>> selectedFlights = Session.getInstance().getSelectedFlights();
//        if (selectedFlights != null && selectedFlights.containsKey("Outbound")) {
//            List<List<iFlight>> inboundFlights = selectedFlights.get("Outbound");
//
//            if (inboundFlights != null && !inboundFlights.isEmpty()) {
//                iFlight firstFlight = inboundFlights.get(0).get(0);
//
//                // Extract relevant information from the Flight object
//                String departAirport = firstFlight.getDeparture_icao();
//                String arriveAirport = firstFlight.getArrival_icao();
//                String departureTime = firstFlight.getFlight_dept_date_time();
//                String arrivalTime = firstFlight.getFlight_arr_date_time();
//                String pay =
//            }
//        }
//        // Inbound
//        HashMap<iPassengerData, String> passengers = Session.getInstance().getSeatMap();
//
//        // Outbound.
//
        return null;
    }
}
