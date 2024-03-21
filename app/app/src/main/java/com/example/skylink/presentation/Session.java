package com.example.skylink.presentation;

import android.content.Context;
import com.example.skylink.objects.Interfaces.iAircraft;
import com.example.skylink.objects.Interfaces.iBookingInfo;
import com.example.skylink.objects.Interfaces.iCity;
import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.objects.Interfaces.iFlightInfo;
import com.example.skylink.objects.Interfaces.iFlights;
import com.example.skylink.objects.Interfaces.iFlightSearch;
import com.example.skylink.objects.Interfaces.iPassengerData;
import com.example.skylink.objects.Interfaces.ITripInvoice;
import com.example.skylink.objects.Interfaces.IUserProperties;

import java.util.HashMap;
import java.util.List;
import java.util.List;

public class Session implements ISession {

    private static Session instance;
    private iAircraft aircraft;
    private iCity city;
    private iFlight  flight;
    private iFlightInfo flightInfo;
    private iFlights iFlights;
    private IUserProperties iUserProperties;
    private String username;
    private String email;
    private long userId;
    private Context context;
    private iBookingInfo bookingInfo;
    private iFlightSearch flightSearch;
    private List<iPassengerData> passengerData;
    private ITripInvoice tripInvoice;
    HashMap<String, List<List<List<iFlight>>>> flightPathResults;
    private Session() {
        // Private constructor to prevent instantiation
    }

    public static synchronized Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }
    @Override
    public iAircraft getAircraft() {
        return aircraft;
    }
    @Override
    public void setAircraft(iAircraft aircraft) {
        this.aircraft = aircraft;
    }
    @Override
    public iCity getCity() {
        return city;
    }

    @Override
    public void setCity(iCity city) {
        this.city = city;
    }
    @Override
    public iFlight getFlight() {
        return flight;
    }
    @Override
    public void setFlight(iFlight flight) {
        this.flight = flight;
    }
    @Override
    public iFlightInfo getFlightInfo() {
        return flightInfo;
    }
    @Override
    public void setFlightInfo(iFlightInfo flightInfo) {
        this.flightInfo = flightInfo;
    }
    @Override
    public iFlights getFlights() {
        return iFlights;
    }
    @Override
    public void setFlights(iFlights iFlights) {
        this.iFlights = iFlights;
    }
    @Override
    public IUserProperties getUserProperties() {
        return iUserProperties;
    }
    @Override
    public void setUserProperties(IUserProperties iUserProperties) {
        this.iUserProperties = iUserProperties;
    }
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public iBookingInfo getBookingInfo() {
        return bookingInfo;
    }

    @Override
    public void setBookingInfo(iBookingInfo bookingInfo) {
        this.bookingInfo = bookingInfo;
    }

    @Override
    public iFlightSearch getFlightSearch() {
        return flightSearch;
    }

    @Override
    public void setFlightSearch(iFlightSearch flightSearch) {
        this.flightSearch = flightSearch;
    }

    @Override
    public List<iPassengerData> getPassengerData() {
        return passengerData;
    }

    @Override
    public void setPassengerData(List<iPassengerData> passengerData) {
        this.passengerData = passengerData;
    }

    @Override
    public ITripInvoice getTripInvoice() {
        return tripInvoice;
    }

    @Override
    public void setTripInvoice(ITripInvoice tripInvoice) {
        this.tripInvoice = tripInvoice;
    }
    public void setFlightPathResults(HashMap<String, List<List<List<iFlight>>>> flightPathResults) {
        this.flightPathResults = flightPathResults;
    }

    public HashMap<String, List<List<List<iFlight>>>> getFlightPathResults() {
        return flightPathResults;
    }
}
