package com.example.skylink.business;

import com.example.skylink.objects.Flight;

public class Session {
    private static Session instance;
    private String username;
    private String email;



    private Flight depatureFlight;
    private Flight ArrivalFlight;


    private Session() {
        // Private constructor to prevent instantiation
    }

    public static synchronized Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public Flight getDepatureFlight() {
        return depatureFlight;
    }

    public void setDepatureFlight(Flight depatureFlight) {
        this.depatureFlight = depatureFlight;
    }

    public Flight getArrivalFlight() {
        return ArrivalFlight;
    }

    public void setArrivalFlight(Flight arrivalFlight) {
        ArrivalFlight = arrivalFlight;
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
}
