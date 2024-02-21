package com.example.skylink.business;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

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
        airportGraph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        createTable();
    }

    private void createTable() {
        addAirport("YYZ");
        addAirport("YYC");
        addAirport("YUL");
        addAirport("YOW");
        addAirport("YEG");
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

    public List<List<List<Flight>>> pullFlight(List<List<String>> all_dept_flight, String flight_dept_date){
        FlightDatabase flightDatabase = new FlightDatabase();
        boolean isDirectPath;
        List<List<List<Flight>>> proposed_flight_path = new ArrayList<>();
        List<List<List<Flight>>> dirFlight = new ArrayList<>();

        if (all_dept_flight == null || flight_dept_date == null || all_dept_flight.isEmpty()) {
            return null;
        }


        for (List<String> path : all_dept_flight) {
            String[] all_hubs_landing = path.toString().replaceAll("[\\[\\]]", "").split(", ");
            List<List<Flight>> layover = new ArrayList<>();
            isDirectPath = (all_hubs_landing.length == 2);
            boolean allLegsHaveFlights = true;

            for (int i = 0; i < all_hubs_landing.length - 1; i++) {
                String currentHub = all_hubs_landing[i];
                String nextHub = all_hubs_landing[i + 1];
                List<Flight> flights = flightDatabase.findFlight(currentHub, nextHub, flight_dept_date);

                if (flights != null && !flights.isEmpty()) {
                    layover.add(flights);

                } else{
                    allLegsHaveFlights = false;
                    break;
                }
            }
            if (allLegsHaveFlights && !layover.isEmpty()) {

                if (isDirectPath) {
                    proposed_flight_path.addAll(addDirectFlight(layover.get(0)));

                } else {
                    proposed_flight_path.add(layover);

                }
            }


        }
        return proposed_flight_path;
    }

    private List<List<List<Flight>>> addDirectFlight(List<Flight> directFlightList) {

        List<List<List<Flight>>> dirFlights = new ArrayList<>();

        for (int i = 0; i < directFlightList.size(); i++) {
            List<List<Flight>> flightCard = new ArrayList<>();
            List<Flight> currFlight = new ArrayList<>();

            currFlight.add(directFlightList.get(i));

            flightCard.add(currFlight);

            dirFlights.add(flightCard);
        }

        return dirFlights;

    }

    public HashMap< String,List<List<List<Flight>>>> findFlights(String flight_dept, String flight_arrival, String flight_dept_date, String flight_return_date, boolean isOneWay) {
        HashMap<String, List<List<List<Flight>>>> itinerary = new HashMap<>();
        List<List<String>> all_dept_flight = filterPaths(findAllPaths(flight_dept, flight_arrival),3);

        List<List<List<Flight>>> out_bound_flights_found = pullFlight(all_dept_flight,flight_dept_date);

        if (out_bound_flights_found != null && !out_bound_flights_found.isEmpty()) {
            itinerary.put("Outbound", out_bound_flights_found);
        }


        if (!isOneWay) {
            List<List<String>> all_arr_flight = filterPaths(reverseInnerLists(all_dept_flight), 3);
            List<List<List<Flight>>> in_bound_flights_found = pullFlight(all_arr_flight, flight_return_date);
            if (in_bound_flights_found != null && !in_bound_flights_found.isEmpty()) {
                itinerary.put("Inbound", in_bound_flights_found);
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

    public static List<List<String>> reverseInnerLists(List<List<String>> outerList) {
        List<List<String>> reversedList = new ArrayList<>();

        for (List<String> innerList : outerList) {
            List<String> reversedInnerList = new ArrayList<>(innerList);
            Collections.reverse(reversedInnerList);
            reversedList.add(reversedInnerList);
        }

        return reversedList;
    }
}
