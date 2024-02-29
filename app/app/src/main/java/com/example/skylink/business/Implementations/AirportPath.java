package com.example.skylink.business.Implementations;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.example.skylink.application.Services;
import com.example.skylink.business.Interface.iAirportPath;
import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.objects.Interfaces.iFlightSearch;
import com.example.skylink.persistence.Interfaces.IFlightDB;

public class AirportPath implements iAirportPath {
    IFlightDB flightHSQLDB;
    Graph<String, DefaultWeightedEdge> airportGraph;

    public AirportPath() {
        flightHSQLDB = Services.getFlightDatabase();
        airportGraph = flightHSQLDB.getAirportGraph();
    }

    private List<List<String>> findAllPaths(String source, String destination) {
        List<List<String>> allPaths = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        List<String> path = new ArrayList<>();
        path.add(source);
        findAllPathsDFS(source, destination, visited, path, allPaths);
        return allPaths;
    }

    private void findAllPathsDFS(String current, String destination, Set<String> visited, List<String> path, List<List<String>> allPaths) {
        visited.add(current);

        if (current.equals(destination)) {
            allPaths.add(new ArrayList<>(path));
        } else {
            for (DefaultWeightedEdge edge : airportGraph.outgoingEdgesOf(current)) {
                String neighbor = airportGraph.getEdgeTarget(edge);
                if (!visited.contains(neighbor)) {
                    path.add(neighbor);
                    findAllPathsDFS(neighbor, destination, visited, path, allPaths);
                    path.remove(path.size() - 1);
                }
            }
        }

        visited.remove(current);
    }

    private List<List<List<iFlight>>> pullFlights(List<List<String>> flightPaths, String date) {

        if (flightPaths == null || flightPaths.isEmpty() || date == null) {
            return null;
        }

        List<List<List<iFlight>>> flightsFound = new ArrayList<>();



        for (List<String> path : flightPaths) {
            List<List<iFlight>> pathFlights = new ArrayList<>();

            for (int i = 0; i < path.size() - 1; i++) {
                String currentHub = path.get(i);
                String nextHub = path.get(i + 1);

                List<iFlight> flights = flightHSQLDB.findFlight(currentHub, nextHub, date);

                // Check if flights are found for the current segment
                if (flights != null && !flights.isEmpty()) {
                    pathFlights.add(flights);
                } else {
                    // If no flights are found for a segment, break out of the loop for this path
                    pathFlights.clear();
                    break;
                }
            }

            // Add the complete path with its segments to the result
            if (!pathFlights.isEmpty()) {
                flightsFound.add(pathFlights);
            }
        }

        return flightsFound.isEmpty() ? null : flightsFound;
    }



    private List<List<List<iFlight>>> findFlight(String flight_dept, String flight_arrival, String date){
        // Find All Possible Ways You can Travel from  flight_dept to flight_arrival with a max of just one lay over.
        List<List<String>> findAllPossiblePathsFromOriginToDestination = findAllPaths(flight_dept, flight_arrival);

        if (findAllPossiblePathsFromOriginToDestination.isEmpty()) {
            return null;
        }

        // This will only give us one layover or a direct flight.
        List<List<String>> pathsFromOriginToDestination = filterPaths(findAllPossiblePathsFromOriginToDestination);

        if (pathsFromOriginToDestination.isEmpty()) {
            return null;
        }

        // Find the flights.
        return pullFlights(pathsFromOriginToDestination, date);
    }

    private List<List<String>> filterPaths(List<List<String>> allPaths) {
        List<List<String>> filteredPaths = new ArrayList<>();

        for (List<String> path : allPaths) {
            if (path.size() <= 3) {
                filteredPaths.add(path);

            }
        }
        return filteredPaths;
    }

    public HashMap< String,List<List<List<iFlight>>>> findFlights(iFlightSearch flightSearch) {
        HashMap<String, List<List<List<iFlight>>>> itinerary = new HashMap<>();
        // Get the outbound flights.
        List<List<List<iFlight>>> outBoundFlights = findFlight(flightSearch.getFlightDept(), flightSearch.getFlightArrival(), flightSearch.getFlightDeptDate());
        if (outBoundFlights != null) {
            itinerary.put("Outbound", outBoundFlights);
        }
        // If there is a return and the outbound flight is not null.
        if (!flightSearch.isOneWay() && outBoundFlights != null) {
            // Get the inbound flights.
            List<List<List<iFlight>>> inBoundFlights = findFlight(flightSearch.getFlightArrival(), flightSearch.getFlightDept(), flightSearch.getFlightReturnDate());
            if (inBoundFlights != null) {
                itinerary.put("Inbound", outBoundFlights);
            }
        }
        // return empty hash map or an hash map that has outbound and inbound.
        return itinerary;
    }
}
