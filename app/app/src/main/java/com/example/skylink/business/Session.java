package com.example.skylink.business;

import com.example.skylink.objects.Flight;
import com.example.skylink.objects.PassengerData;
import com.example.skylink.objects.FlightSearch;

import java.util.HashMap;
import java.util.List;

public class Session {

    private static Session instance;
    private String username;
    private String email;

    // Temporary storage for flights and bookings
    private FlightSearch flightSearch;
    private List<PassengerData> passengerData;

    private HashMap<String, List<List<Flight>>> selectedFlights;

    private Session() {
        // Private constructor to prevent instantiation

    }

    public static synchronized Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }


    public List<PassengerData> getPassengerData() {
        return passengerData;
    }

    public void setPassengerData(List<PassengerData> passengerData) {
        this.passengerData = passengerData;
    }

    public FlightSearch getFlightSearch() {
        return flightSearch;
    }

    public void setFlightSearch(FlightSearch flightSearch) {
        this.flightSearch = flightSearch;
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

    public HashMap<String, List<List<Flight>>> getSelectedFlights() {
        return selectedFlights;
    }

    public void setSelectedFlights(HashMap<String, List<List<Flight>>> selectedFlights) {
        this.selectedFlights = selectedFlights;
    }
}
