package com.example.skylink.business.Implementations;

import com.example.skylink.application.Services;
import com.example.skylink.business.Interface.iFlightBookingHandler;
import com.example.skylink.objects.Interfaces.iFlightInfo;
import com.example.skylink.objects.Interfaces.iPassengerData;
import com.example.skylink.persistence.Interfaces.iFlightBookingDB;
import com.example.skylink.persistence.Interfaces.iTravellerDB;

import java.util.HashMap;
import java.util.Map;

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

    public boolean addConfirmBooking(long user_id, HashMap<String, iFlightInfo> flightInfo) {
        if(flightInfo != null){
            if(flightInfo.get("Outbound") != null && flightInfo.get("Outbound").equals("Outbound")){
                iFlightInfo flight_Info = flightInfo.get("Outbound");
                if(flight_Info != null){
                    int price = calculateTotalPrice(flight_Info);
                    long booking_id = flightBookingDB.addFlightBooking(user_id, "Outbound",flight_Info,price);
                    for (Map.Entry<iPassengerData, String> entry :flight_Info.getSeatSelected().entrySet()) {
                        iPassengerData passengerData = entry.getKey();
                        String seatNumber = entry.getValue();
                        travelDB.insertIntoTravellers(booking_id, passengerData, seatNumber);
                    }
                }
                return true;
            }
            if(flightInfo.get("Inbound") != null && flightInfo.get("Inbound").equals("Inbound")){
                iFlightInfo flight_Info = flightInfo.get("Outbound");
                if(flight_Info != null) {
                    int price = calculateTotalPrice(flight_Info);
                    long booking_id = flightBookingDB.addFlightBooking(user_id, "Inbound", flight_Info,price);
                    for (Map.Entry<iPassengerData, String> entry :flight_Info.getSeatSelected().entrySet()) {
                        iPassengerData passengerData = entry.getKey();
                        String seatNumber = entry.getValue();
                        travelDB.insertIntoTravellers(booking_id, passengerData, seatNumber);
                    }
                }
                return true;
            }
        }
        return false;
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
