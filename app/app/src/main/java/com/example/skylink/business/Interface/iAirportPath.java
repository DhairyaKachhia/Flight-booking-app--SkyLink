package com.example.skylink.business.Interface;

import com.example.skylink.objects.Implementations.Flight;
import com.example.skylink.objects.Implementations.FlightSearch;
import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.objects.Interfaces.iFlightSearch;

import java.util.HashMap;
import java.util.List;

public interface iAirportPath {

    List<List<String>> findAllPaths(String source, String destination);

    double calculatePathDistance(List<String> path);

    List<List<List<Flight>>> retrieveFlightsBasedOnPath(List<List<String>> paths, String flightDepartureDate);

    HashMap<String, List<List<List<iFlight>>>> findFlights(iFlightSearch flightSearch);


    List<List<String>> filterPaths(List<List<String>> allPaths);

    List<List<String>> reverseInnerLists(List<List<String>> outerList);

}
