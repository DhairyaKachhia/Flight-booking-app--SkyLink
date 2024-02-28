package com.example.skylink.persistence.Interfaces;

import com.example.skylink.objects.Implementations.Flight;
import com.example.skylink.objects.Interfaces.iFlight;

import java.util.List;

public interface IFlightHSQLDB {

    /**
     * Initializes the database.
     */
    IFlightHSQLDB initialize();

    /**
     * Drops the database.
     */
    IFlightHSQLDB drop();

    /**
     * Adds a new flight to the database.
     * @param flight The flight object to add.
     */
    void addFlight(iFlight flight);

    /**
     * Retrieves all flights from the database.
     * @return A list of all flights.
     */
    List<Flight> getAllFlights();
}