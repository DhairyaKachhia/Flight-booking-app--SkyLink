package com.example.skylink.business.Implementations;

import com.example.skylink.application.Services;
import com.example.skylink.business.Interface.iFlightBookingHandler;
import com.example.skylink.objects.Implementations.Flight;
import com.example.skylink.objects.Implementations.FlightInfo;
import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.objects.Interfaces.iFlightInfo;
import com.example.skylink.objects.Interfaces.iPassengerData;
import com.example.skylink.persistence.Interfaces.iBookingDB;
import com.example.skylink.persistence.Interfaces.iFlightBookingDB;
import com.example.skylink.persistence.Interfaces.IFlightDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FlightBookingHandler implements iFlightBookingHandler {
    private iFlightBookingDB flightBookingDB;
    private iBookingDB bookingDB;
    private IFlightDB flightDB;

    public FlightBookingHandler(iFlightBookingDB flightBookingDB, iBookingDB bookingDB, IFlightDB flightDB) {
        this.flightBookingDB = flightBookingDB;
        this.bookingDB = bookingDB;
        this.flightDB = flightDB;
    }

    public FlightBookingHandler(boolean forProduction) {
        this.flightBookingDB = Services.getFlightBookingDB();

    }

    public String addConfirmBooking(long user_id, HashMap<String, iFlightInfo> flightInfo) {
        String bookingNumber = generateBookingNumber();
        if(flightInfo != null){
            if(flightInfo.get("Outbound") != null && flightInfo.containsKey("Outbound")){
                iFlightInfo flight_Info = flightInfo.get("Outbound");

                if(flight_Info != null){
                    int price = calculateTotalPrice(flight_Info);
                    for (iFlight flight : flight_Info.getFlight()) {
                        flightBookingDB.addFlightBooking(user_id, "Outbound",flight,price, bookingNumber, flight_Info.getEconOrBus());
                    }
                }
            }
            if(flightInfo.get("Inbound") != null && flightInfo.containsKey("Inbound")){
                iFlightInfo flight_Info = flightInfo.get("Inbound");
                if(flight_Info != null) {
                    int price = calculateTotalPrice(flight_Info);
                    for (iFlight flight : flight_Info.getFlight()) {
                        flightBookingDB.addFlightBooking(user_id, "Inbound",flight,price, bookingNumber, flight_Info.getEconOrBus());
                    }
                }
            }

            iFlightInfo flightInfoMap = flightInfo.get("Outbound");
            if (flightInfoMap != null) {
                for (Map.Entry<iPassengerData, String> entry : flightInfoMap.getSeatSelected().entrySet()) {
                    bookingDB.updateBookingInformation(entry.getKey().getEmailAddress(), user_id, bookingNumber, entry.getValue());
                }
            }
        }
        return bookingNumber;
    }

    public List<HashMap<String, HashMap<String, iFlightInfo>>> getBookingDetails(long userID) {
        List<HashMap<String, HashMap<String, iFlightInfo>>> bookingDetailsList = new ArrayList<>();

        // Get all booking numbers for the user
        List<String> bookingNumbers = flightBookingDB.getBookingNumberByUserId(userID);

        for (String bookingNumber : bookingNumbers) {
            HashMap<String, iFlightInfo> boundBookingDetails = new HashMap<>();

            // Outbound Booking
            iFlightInfo outboundFlightInfo = prepareFlightInfo("Outbound", bookingNumber);
            if (outboundFlightInfo != null) {
                boundBookingDetails.put("Outbound", outboundFlightInfo);
            }

            // Inbound Booking
            iFlightInfo inboundFlightInfo = prepareFlightInfo("Inbound", bookingNumber);
            if (inboundFlightInfo != null) {
                boundBookingDetails.put("Inbound", inboundFlightInfo);
            }

            if (!boundBookingDetails.isEmpty()) {
                HashMap<String, HashMap<String, iFlightInfo>> bookingDetails = new HashMap<>();
                bookingDetails.put(bookingNumber, boundBookingDetails);
                bookingDetailsList.add(bookingDetails);
            }
        }

        return bookingDetailsList.isEmpty() ? null : bookingDetailsList;
    }


    private iFlightInfo prepareFlightInfo(String direction, String bookingNumber) {
        String econOrBus = "Economy";

        List<String> flightBookingBound = flightBookingDB.getFlightsByUserId(direction, bookingNumber);

        if (flightBookingBound.isEmpty()) {
            return null; // No bookings for the specified direction
        }

        List<iFlight> flightBound = flightDB.getFlightsByFlightNumbers(flightBookingBound);
        HashMap<iPassengerData, String> passengers = bookingDB.getPassengersWithSeatNumbers(bookingNumber);

        iFlightInfo flightInfo = new FlightInfo();
        flightInfo.setFlight(flightBound);
        flightInfo.setEconOrBus(econOrBus);
        flightInfo.setSeatSelected(passengers);

        return flightInfo;
    }




    private String generateBookingNumber() {
        String bookingNumber = UUID.randomUUID().toString().replaceAll("-", "");
        bookingNumber = bookingNumber.substring(0, 5);
        return bookingNumber;
    }

    private int calculateTotalPrice(iFlightInfo flightInfo) {
        int price = 0;
        int unitCost = 0;

        if (flightInfo.getEconOrBus().equals("Economy")) {
            for (iFlight flight : flightInfo.getFlight()) {
                unitCost += flight.getEconPrice();
            }
            price = flightInfo.getSeatSelected().size() * unitCost;
        } else if (flightInfo.getEconOrBus().equals("Business")) {
            for (iFlight flight : flightInfo.getFlight()) {
                unitCost += flight.getBusnPrice();
            }
            price = flightInfo.getSeatSelected().size() * unitCost;
        }

        return price;
    }


}
