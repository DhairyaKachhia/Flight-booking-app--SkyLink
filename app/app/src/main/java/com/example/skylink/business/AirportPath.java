package com.example.skylink.business;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
        addAirport("YUL");
        addAirport("YVR");
        addAirport("YYC");
        addAirport("YOW");

        addConnection("YYZ", "YUL", 541);
        addConnection("YYZ", "YVR", 3361);
        addConnection("YUL", "YVR", 3307);
        addConnection("YUL", "YYC", 2699);
        addConnection("YVR", "YYC", 971);
        addConnection("YYZ", "YOW", 269);
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
        List<List<List<Flight>>> proposed_flight_path = new ArrayList<>();
        for (List<String> path : all_dept_flight) {
            String[] all_hubs_landing = path.toString().replaceAll("[\\[\\]]", "").split(", ");
            List<List<Flight>> layover = new ArrayList<>();
            for (int i = 0; i < all_hubs_landing.length - 1; i++) {
                String currentHub = all_hubs_landing[i];
                String nextHub = all_hubs_landing[i + 1];
                layover.add(flightDatabase.findFlight(currentHub, nextHub,"02/02/2024"));
            }
            proposed_flight_path.add(layover);
        }
        return proposed_flight_path;
    }


    public HashMap< String,List<List<List<Flight>>>> findFlights(String flight_dept, String flight_arrival, String flight_dept_date, String flight_return_date, boolean isOneWay) {
        HashMap<String, List<List<List<Flight>>>> itinerary = new HashMap<>();
        List<List<String>> all_dept_flight = findAllPaths(flight_dept, flight_arrival);
        itinerary.put("Outbound",pullFlight(all_dept_flight,flight_dept_date));
        if (isOneWay) {
            List<List<String>> all_arr_flight = findAllPaths(flight_arrival, flight_dept);
            itinerary.put("Inbound",pullFlight(all_arr_flight,flight_return_date));
        }
        return itinerary;
    }


}
