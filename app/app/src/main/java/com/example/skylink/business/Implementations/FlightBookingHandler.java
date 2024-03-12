package com.example.skylink.business.Implementations;

import com.example.skylink.application.Services;
import com.example.skylink.business.Interface.iFlightBookingHandler;
import com.example.skylink.objects.Interfaces.iFlightInfo;
import com.example.skylink.objects.Interfaces.iPassengerData;
import com.example.skylink.persistence.Interfaces.iFlightBookingDB;
import com.example.skylink.persistence.Interfaces.iTravellerDB;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FlightBookingHandler implements iFlightBookingHandler {
    private iFlightBookingDB flightBookingDB;
    private iTravellerDB travelDB;

    public FlightBookingHandler(iFlightBookingDB flightBookingDB, iTravellerDB travelDB) {
        this.flightBookingDB = flightBookingDB;
        this.travelDB = travelDB;
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
                    flightBookingDB.addFlightBooking(user_id, "Outbound",flight_Info,price, bookingNumber);
                }
            }
            if(flightInfo.get("Inbound") != null && flightInfo.containsKey("Inbound")){
                iFlightInfo flight_Info = flightInfo.get("Outbound");
                if(flight_Info != null) {
                    int price = calculateTotalPrice(flight_Info);
                    flightBookingDB.addFlightBooking(user_id, "Inbound", flight_Info,price, bookingNumber);
                }
            }

            for (Map.Entry<iPassengerData, String> entry :flightInfo.get("Outbound").getSeatSelected().entrySet()) {
                iPassengerData passengerData = entry.getKey();
                String seatNumber = entry.getValue();
                travelDB.insertIntoTravellers(bookingNumber, passengerData, seatNumber);
            }
        }
        return bookingNumber;
    }

    private String generateBookingNumber() {
        // Generate a random UUID and remove hyphens
        String bookingNumber = UUID.randomUUID().toString().replaceAll("-", "");

        // Take the first 10 characters to get a shorter unique booking number
        bookingNumber = bookingNumber.substring(0, 5);

        return bookingNumber;
    }


    public int calculateTotalPrice(iFlightInfo flightInfo){
        int price = 0;
        if(flightInfo.getEconOrBus().equals("Economy")){
            price = flightInfo.getSeatSelected().size() * flightInfo.getFlight().getEconPrice();
        }else if(flightInfo.getEconOrBus().equals("Business")){
            price = flightInfo.getSeatSelected().size() * flightInfo.getFlight().getBusnPrice();
        }
        return price;
    }

}
