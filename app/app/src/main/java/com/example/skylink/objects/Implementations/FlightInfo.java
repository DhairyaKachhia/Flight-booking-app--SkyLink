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
    private int bagCount, petCount, wifiOption, wheelchairOption;

    private boolean checkedIn;

    public FlightInfo(){

    }
    private String buttonText;
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

    @Override
    public int getBagCount() {
        return bagCount;
    }

    @Override
    public void setBagCount(int bagNumber) {
        this.bagCount = bagNumber;
    }

    @Override
    public int getPetCount() {
        return petCount;
    }

    @Override
    public void setPetCount(int petNumber) {
        this.petCount = petNumber;
    }

    @Override
    public int getWifiOption() {
        return wifiOption;
    }

    @Override
    public void setWifiOption(int wifiOption) {
        this.wifiOption = wifiOption;
    }

    @Override
    public int getWheelchairOption() {
        return wheelchairOption;
    }

    @Override
    public void setWheelchairOption(int wheelchairOption) {
        this.wheelchairOption = wheelchairOption;
    }

    @Override
    public String getButtonText() {
        return buttonText;
    }

    @Override
    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    @Override
    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }
}
