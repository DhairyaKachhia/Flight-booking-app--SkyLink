package com.example.skylink.business;

import com.example.skylink.data.Flight;
import java.util.HashMap;
import java.util.List;

public interface AirportPathInterface {

    List<List<String>> findAllPaths(String source, String destination);

    double calculatePathDistance(List<String> path);

    List<List<List<Flight>>> pullFlight(List<List<String>> allDeptFlight, String flightDeptDate);

    HashMap<String, List<List<List<Flight>>>> findFlights(String flightDept, String flightArrival, String flightDeptDate, String flightReturnDate);

}
