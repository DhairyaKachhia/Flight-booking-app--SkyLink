package com.example.skylink.business.Implementations;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.util.Collections;


import com.example.skylink.business.Interface.iAirportPath;
import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.persistence.Implementations.hsqldb.FlightDatabase;
import com.example.skylink.objects.Implementations.Flight;
import com.example.skylink.objects.Implementations.FlightSearch;

public class AirportPath implements iAirportPath {
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

        addConnection("YYZ", "YYC", 1520);
        addConnection("YYZ", "YUL", 2645);
        addConnection("YYZ", "YOW", 153);
        addConnection("YYZ", "YEG", 179);
        addConnection("YYZ", "YWG", 1221);
        addConnection("YYZ", "YVR", 2133);
        addConnection("YYZ", "YZZ", 1539);
        addConnection("YYZ", "YHM", 4875);
        addConnection("YYC", "YUL", 1065);
        addConnection("YYC", "YOW", 854);
        addConnection("YYC", "YEG", 4579);
        addConnection("YYC", "YWG", 3444);
        addConnection("YYC", "YVR", 514);
        addConnection("YYC", "YZZ", 4939);
        addConnection("YYC", "YHM", 2206);
        addConnection("YUL", "YOW", 4183);
        addConnection("YUL", "YEG", 1110);
        addConnection("YUL", "YWG", 2057);
        addConnection("YUL", "YVR", 3480);
        addConnection("YUL", "YZZ", 3072);
        addConnection("YUL", "YHM", 2272);
        addConnection("YOW", "YEG", 3669);
        addConnection("YOW", "YWG", 90);
        addConnection("YOW", "YVR", 3503);
        addConnection("YOW", "YZZ", 2943);
        addConnection("YOW", "YHM", 1902);
        addConnection("YEG", "YWG", 4064);
        addConnection("YEG", "YVR", 1054);
        addConnection("YEG", "YZZ", 3622);
        addConnection("YEG", "YHM", 790);
        addConnection("YWG", "YVR", 1958);
        addConnection("YWG", "YZZ", 100);
        addConnection("YWG", "YHM", 1178);
        addConnection("YVR", "YZZ", 3025);
        addConnection("YVR", "YHM", 3485);
        addConnection("YZZ", "YHM", 2283);



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

    @Override
    public List<List<List<Flight>>> retrieveFlightsBasedOnPath(List<List<String>> paths, String flightDepartureDate) {
        return null;
    }

    public List<List<List<iFlight>>> pullFlight(List<List<String>> all_dept_flight, String flight_dept_date){
        FlightDatabase flightDatabase = new FlightDatabase();
        boolean isDirectPath;
        List<List<List<iFlight>>> proposed_flight_path = new ArrayList<>();
        List<List<List<iFlight>>> dirFlight = new ArrayList<>();

        if (all_dept_flight == null || flight_dept_date == null || all_dept_flight.isEmpty()) {
            return null;
        }


        for (List<String> path : all_dept_flight) {
            String[] all_hubs_landing = path.toString().replaceAll("[\\[\\]]", "").split(", ");
            List<List<iFlight>> layover = new ArrayList<>();
            isDirectPath = (all_hubs_landing.length == 2);
            boolean allLegsHaveFlights = true;

            for (int i = 0; i < all_hubs_landing.length - 1; i++) {
                String currentHub = all_hubs_landing[i];
                String nextHub = all_hubs_landing[i + 1];
                List<iFlight> flights = flightDatabase.findFlight(currentHub, nextHub, flight_dept_date);

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

    private List<List<List<iFlight>>> addDirectFlight(List<iFlight> directFlightList) {

        List<List<List<iFlight>>> dirFlights = new ArrayList<>();

        for (int i = 0; i < directFlightList.size(); i++) {
            List<List<iFlight>> flightCard = new ArrayList<>();
            List<iFlight> currFlight = new ArrayList<>();

            currFlight.add(directFlightList.get(i));

            flightCard.add(currFlight);

            dirFlights.add(flightCard);
        }

        return dirFlights;

    }

    //String flight_dept, String flight_arrival, String flight_dept_date, String flight_return_date, boolean isOneWay
    public HashMap< String,List<List<List<iFlight>>>> findFlights(FlightSearch flightSearch) {
        HashMap<String, List<List<List<iFlight>>>> itinerary = new HashMap<>();
        List<List<String>> all_dept_flight = filterPaths(findAllPaths(flightSearch.getFlightDept(), flightSearch.getFlightArrival()),3);

        List<List<List<iFlight>>> out_bound_flights_found = pullFlight(all_dept_flight,flightSearch.getFlightDeptDate());

        if (out_bound_flights_found != null && !out_bound_flights_found.isEmpty()) {
            itinerary.put("Outbound", out_bound_flights_found);
        }


        if (!flightSearch.isOneWay()) {
            List<List<String>> all_arr_flight = filterPaths(reverseInnerLists(all_dept_flight), 3);
            List<List<List<iFlight>>> in_bound_flights_found = pullFlight(all_arr_flight, flightSearch.getFlightReturnDate());
            if (in_bound_flights_found != null && !in_bound_flights_found.isEmpty()) {
                itinerary.put("Inbound", in_bound_flights_found);
            }
        }
        // return empty hash map or an hash map that has outbound and inbound.
        return itinerary;
    }

    @Override
    public List<List<String>> filterPaths(List<List<String>> allPaths) {
        return null;
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

    public List<List<String>> reverseInnerLists(List<List<String>> outerList) {
        List<List<String>> reversedList = new ArrayList<>();

        for (List<String> innerList : outerList) {
            List<String> reversedInnerList = new ArrayList<>(innerList);
            Collections.reverse(reversedInnerList);
            reversedList.add(reversedInnerList);
        }

        return reversedList;
    }
}
