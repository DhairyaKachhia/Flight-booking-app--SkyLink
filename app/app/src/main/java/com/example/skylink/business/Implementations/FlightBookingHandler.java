package com.example.skylink.business.Implementations;

import com.example.skylink.application.Services;
import com.example.skylink.business.Interface.iFlightBookingHandler;
import com.example.skylink.objects.Interfaces.iFlightInfo;
import com.example.skylink.objects.Interfaces.iPassengerData;
import com.example.skylink.persistence.Interfaces.iBookingDB;
import com.example.skylink.persistence.Interfaces.iFlightBookingDB;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FlightBookingHandler implements iFlightBookingHandler {
    private iFlightBookingDB flightBookingDB;
    private iBookingDB bookingDB;

    public FlightBookingHandler(iFlightBookingDB flightBookingDB, iBookingDB bookingDB) {
        this.flightBookingDB = flightBookingDB;
        this.bookingDB = bookingDB;
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
                bookingDB.updateBookingInformation(entry.getKey().getEmailAddress(),user_id,bookingNumber,entry.getValue());
            }
        }
        return bookingNumber;
    }

    public Map<String, Object> getBookingDetails(long userID) {
        List<String> flights = flightBookingDB.getFlightIdsByUserId(userID);
//        From FLIGHTBOOKINGS select * from FLIGHTBOOKINGS where userID = userID
//        From Flights, Select * from FLIGHT
//        Finding booking that match bookingNumber
        return null;
    }

    private String generateBookingNumber() {
        // Generate a random UUID and remove hyphens
        String bookingNumber = UUID.randomUUID().toString().replaceAll("-", "");

        // Take the first 10 characters to get a shorter unique booking number
        bookingNumber = bookingNumber.substring(0, 5);

        return bookingNumber;
    }


    private int calculateTotalPrice(iFlightInfo flightInfo){
        int price = 0;
        if(flightInfo.getEconOrBus().equals("Economy")){
            price = flightInfo.getSeatSelected().size() * flightInfo.getFlight().getEconPrice();
        }else if(flightInfo.getEconOrBus().equals("Business")){
            price = flightInfo.getSeatSelected().size() * flightInfo.getFlight().getBusnPrice();
        }
        return price;
    }

}
