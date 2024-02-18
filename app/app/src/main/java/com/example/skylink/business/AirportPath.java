package com.example.skylink.business;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.util.Collections;


import com.example.skylink.data.FlightDatabase;
import com.example.skylink.objects.Flight;

public class AirportPath {
    private final Graph<String, DefaultWeightedEdge> airportGraph;

    public AirportPath() {
        airportGraph = new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class);
        createTable();
    }



    private void createTable() {
        addAirport("YYZ");
        addAirport("YUL");
        addAirport("YYC");
        addAirport("YOW");
        addAirport("YEG");
        addAirport("YYZ");
        addAirport("YWG");
        addAirport("YVR");
        addAirport("YZZ");
        addAirport("YHM");

        addConnection("YYZ", "YUL", 230);
        addConnection("YYZ", "YYC", 1520);
        addConnection("YYZ", "YOW", 360);
        addConnection("YYZ", "YEG", 120);
        addConnection("YYZ", "YWG", 123);
        addConnection("YYZ", "YVR", 111);
        addConnection("YYZ", "YZZ", 221);
        addConnection("YYZ", "YHM", 110);

        addConnection("YUL", "YYZ", 230);
        addConnection("YUL", "YYC", 0);
        addConnection("YUL", "YOW", 239);
        addConnection("YUL", "YEG", 190);
        addConnection("YUL", "YYZ", 209);
        addConnection("YUL", "YWG", 142);
        addConnection("YUL", "YVR", 231);
        addConnection("YUL", "YZZ", 562);
        addConnection("YUL", "YHM", 111);

        addConnection("YYC", "YYZ", 1520);
        addConnection("YYC", "YUL", 0);
        addConnection("YYC", "YOW", 111);
        addConnection("YYC", "YEG", 0);
        addConnection("YYC", "YYZ", 20);
        addConnection("YYC", "YWG", 120);
        addConnection("YYC", "YVR", 432);
        addConnection("YYC", "YZZ", 411);
        addConnection("YYC", "YHM", 0);

        addConnection("YOW", "YYZ", 460);
        addConnection("YOW", "YUL", 239);
        addConnection("YOW", "YYC", 111);
        addConnection("YOW", "YEG", 422);
        addConnection("YOW", "YYZ", 0);
        addConnection("YOW", "YWG", 321);
        addConnection("YOW", "YVR", 232);
        addConnection("YOW", "YZZ", 1123);
        addConnection("YOW", "YHM", 132);

        addConnection("YEG", "YYZ", 120);
        addConnection("YEG", "YUL", 190);
        addConnection("YEG", "YYC", 0);
        addConnection("YEG", "YOW", 422);
        addConnection("YEG", "YYZ", 256);
        addConnection("YEG", "YWG", 422);
        addConnection("YEG", "YVR", 521);
        addConnection("YEG", "YZZ", 253);
        addConnection("YEG", "YHM", 0);


        addConnection("YYZ", "YUL", 209);
        addConnection("YYZ", "YYC", 20);
        addConnection("YYZ", "YOW", 0);
        addConnection("YYZ", "YEG", 256);
        addConnection("YYZ", "YWG", 123);
        addConnection("YYZ", "YVR", 674);
        addConnection("YYZ", "YZZ", 902);
        addConnection("YYZ", "YHM", 111);

        addConnection("YWG", "YYZ", 123);
        addConnection("YWG", "YUL", 142);
        addConnection("YWG", "YYC", 120);
        addConnection("YWG", "YOW", 321);
        addConnection("YWG", "YEG", 422);
        addConnection("YWG", "YYZ", 123);
        addConnection("YWG", "YVR", 522);
        addConnection("YWG", "YZZ", 671);
        addConnection("YWG", "YHM", 192);

        addConnection("YVR", "YYZ", 111);
        addConnection("YVR", "YUL", 231);
        addConnection("YVR", "YYC", 432);
        addConnection("YVR", "YOW", 232);
        addConnection("YVR", "YEG", 521);
        addConnection("YVR", "YYZ", 674);
        addConnection("YVR", "YWG", 522);
        addConnection("YVR", "YZZ", 201);
        addConnection("YVR", "YHM", 0);

        addConnection("YZZ", "YYZ", 221);
        addConnection("YZZ", "YUL", 562);
        addConnection("YZZ", "YYC", 411);
        addConnection("YZZ", "YOW", 1123);
        addConnection("YZZ", "YEG", 253);
        addConnection("YZZ", "YYZ", 902);
        addConnection("YZZ", "YWG", 671);
        addConnection("YZZ", "YVR", 201);
        addConnection("YZZ", "YHM", 222);

        addConnection("YHM", "YYZ", 110);
        addConnection("YHM", "YUL", 111);
        addConnection("YHM", "YYC", 0);
        addConnection("YHM", "YOW", 132);
        addConnection("YHM", "YEG", 0);
        addConnection("YHM", "YYZ", 111);
        addConnection("YHM", "YWG", 192);
        addConnection("YHM", "YVR", 0);
        addConnection("YHM", "YZZ", 222);

    }

    private void addAirport(String airport) {
        airportGraph.addVertex(airport);
    }

    private void addConnection(String source, String target, double weight) {
        DefaultWeightedEdge edge = airportGraph.addEdge(source, target);
        airportGraph.setEdgeWeight(edge, weight);
    }

    public List<List<String>> findAllPaths(String source, String destination) {
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

    public double calculatePathDistance(List<String> path) {
        double distance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            String source = path.get(i);
            String target = path.get(i + 1);
            DefaultWeightedEdge edge = airportGraph.getEdge(source, target);
            distance += airportGraph.getEdgeWeight(edge);
        }
        return distance;
    }


    public List<List<List<Flight>>> pullFlights(List<List<String>> flightPaths, String date) {
        FlightDatabase flightDatabase = new FlightDatabase();

        if (flightPaths == null || flightPaths.isEmpty() || date == null) {
            return null;
        }

        List<List<List<Flight>>> flightsFound = new ArrayList<>();

        for (List<String> path : flightPaths) {
            List<List<Flight>> pathFlights = new ArrayList<>();

            for (int i = 0; i < path.size() - 1; i++) {
                String currentHub = path.get(i);
                String nextHub = path.get(i + 1);

                List<Flight> flights = flightDatabase.findFlight(currentHub, nextHub, date);

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



    public List<List<List<Flight>>> findFlight(String flight_dept, String flight_arrival, String date){
        // Find All Possible Ways You can Travel from  flight_dept to flight_arrival with a max of just one lay over.
        List<List<String>> findAllPossiblePathsFromOriginToDestination = findAllPaths(flight_dept, flight_arrival);

        if (findAllPossiblePathsFromOriginToDestination == null || findAllPossiblePathsFromOriginToDestination.isEmpty()) {
            return null;
        }

        // This will only give us one layover or a direct flight.
        List<List<String>> pathsFromOriginToDestination = filterPaths(findAllPossiblePathsFromOriginToDestination,3);

        if (pathsFromOriginToDestination == null || pathsFromOriginToDestination.isEmpty()) {
            return null;
        }

        // Find the flights.
        return pullFlights(pathsFromOriginToDestination, date);
    }

    public HashMap< String,List<List<List<Flight>>>> findFlights(String flight_dept, String flight_arrival, String flight_dept_date, String flight_return_date, boolean isOneWay) {
        HashMap<String, List<List<List<Flight>>>> itinerary = new HashMap<>();
        // Get the outbound flights.
        List<List<List<Flight>>> outBoundFlights = findFlight(flight_dept, flight_arrival,flight_dept_date);
        if (outBoundFlights != null) {
            itinerary.put("Outbound", outBoundFlights);
        }
        // If there is a return and the outbound flight is not null.
        if (!isOneWay && outBoundFlights != null) {
            // Get the inbound flights.
            List<List<List<Flight>>> inBoundFlights = findFlight(flight_arrival, flight_dept,flight_return_date);
            if (inBoundFlights != null) {
                itinerary.put("Inbound", outBoundFlights);
            }
        }

        // return empty hash map or an hash map that has outbound and inbound.
        return itinerary;
    }

    public List<List<String>> filterPaths(List<List<String>> allPaths, int maxLayovers) {
        List<List<String>> filteredPaths = new ArrayList<>();

        for (List<String> path : allPaths) {
            if (path.size() <= maxLayovers) {
                filteredPaths.add(path);

            }
        }
        return filteredPaths;
    }
}
