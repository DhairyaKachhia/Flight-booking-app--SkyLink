package com.example.skylink.objects.Implementations;

import java.util.HashMap;
import java.util.List;

import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.objects.Interfaces.iPassengerData;
import com.example.skylink.objects.Interfaces.iFlightInfo;

public class FlightInfo implements iFlightInfo{
    private String econOrBus;
    private String bound;
    private String bookingNum;
    private HashMap<iPassengerData, String> seatSelected;
    private List<iFlight> flight;
    public FlightInfo(){

    }

    public FlightInfo(String econOrBus, HashMap<iPassengerData, String> seatSelected, List<iFlight> flight) {
        this.econOrBus = econOrBus;
        this.seatSelected = seatSelected;
        this.flight = flight;
    }

    // Getters and setters for the class variables

    public String getBookingNum() {
        return bookingNum;
    }

    public void setBookingNum(String bookingNum) {
        this.bookingNum = bookingNum;
    }

    public String getEconOrBus() {
        return econOrBus;
    }

    public void setEconOrBus(String econOrBus) {
        this.econOrBus = econOrBus;
    }

    public HashMap<iPassengerData, String> getSeatSelected() {
        return seatSelected;
    }

    public void setSeatSelected(HashMap<iPassengerData, String> seatSelected) {
        this.seatSelected = seatSelected;
    }

    public String getBound() {
        return bound;
    }

    public void setBound(String bound) {
        this.bound = bound;
    }

    public List<iFlight> getFlight() {
        return flight;
    }

    public void setFlight(List<iFlight> flight) {
        this.flight = flight;
    }
}
