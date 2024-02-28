package com.example.skylink.persistence.Interfaces;

import com.example.skylink.objects.Implementations.Flight;
import com.example.skylink.objects.Interfaces.iFlight;

import java.util.List;

public interface iFlightDatabase {

    void addFlight(String flightNumber, String departure_icao, String arrival_icao, String flight_dept_date_time, String flight_arr_date_time, String airCraft_Type, String depature_Gate, String arr_Gate, int econPrice, int busPrice);

    void addFlights();

    List<iFlight> findFlight(String departure, String arrival, String dept_time);

}
