package com.example.skylink.business.Implementations;

import com.example.skylink.business.Interface.ISession;
import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.objects.Interfaces.iFlightSearch;
import com.example.skylink.objects.Interfaces.iPassengerData;

import java.util.HashMap;
import java.util.List;

public class Session implements ISession {

    private static Session instance;
    private String username;
    private String email;

    // Temporary storage for flights and bookings
    private iFlightSearch flightSearch;
    private List<iPassengerData> passengerData;

    private HashMap<String, List<List<iFlight>>> selectedFlights;


    HashMap<String, List<List<List<iFlight>>>> flightPathResults;

    private long user_id;

    private Session() {
        // Private constructor to prevent instantiation

    }

    public static synchronized Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }


    public List<iPassengerData> getPassengerData() {
        return passengerData;
    }

    public void setPassengerData(List<iPassengerData> passengerData) {
        this.passengerData = passengerData;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public iFlightSearch getFlightSearch() {
        return flightSearch;
    }

    public void setFlightSearch(iFlightSearch flightSearch) {
        this.flightSearch = flightSearch;
    }

    public HashMap<String, List<List<List<iFlight>>>> getFlightPathResults() {
        return flightPathResults;
    }

    public void setFlightPathResults(HashMap<String, List<List<List<iFlight>>>> flightPathResults) {
        this.flightPathResults = flightPathResults;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HashMap<String, List<List<iFlight>>> getSelectedFlights() {
        return selectedFlights;
    }

    public void setSelectedFlights(HashMap<String, List<List<iFlight>>> selectedFlights) {
        this.selectedFlights = selectedFlights;
    }
}
