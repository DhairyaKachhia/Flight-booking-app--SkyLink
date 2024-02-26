package com.example.skylink.persistence;

import com.example.skylink.objects.Flight;
import java.util.List;

public interface IFlightDatabase {

    /**
     * Initializes the database.
     */
    IFlightDatabase initialize();

    /**
     * Drops the database.
     */
    IFlightDatabase drop();

    /**
     * Adds a new flight to the database.
     * @param flight The flight object to add.
     */
    void addFlight(Flight flight);

    /**
     * Retrieves all flights from the database.
     * @return A list of all flights.
     */
    List<Flight> getAllFlights();
}