package com.example.skylink.persistence.Implementations.hsqldb;

import android.content.Context;
import android.content.res.AssetManager;

import com.example.skylink.objects.Implementations.Aircraft;
import com.example.skylink.objects.Implementations.Flight;
import com.example.skylink.objects.Interfaces.iAircraft;
import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.persistence.Interfaces.IFlightDB;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class FlightStub implements IFlightDB {
    private final List<iFlight> flights;
    private final Graph<String, DefaultWeightedEdge> airportGraph;
    private Map<String, iAircraft> aircraftMap =  new HashMap<>();;


    public FlightStub() {
        this.airportGraph = new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class);;
        this.flights = new ArrayList<>();
        addFlights();
        readConfig();
        addAircrafts();
    }

    @Override
    public void addFlights() {
        addFlight("AC551", "YZZ", "YYC", "01/03/2024 12:51", "04/03/2024 12:51", "Boeing 777", "Gate2", "Gate1", 958, 1399);
        addFlight("AC904", "YZZ", "YUL", "02/03/2024 12:51", "07/03/2024 12:51", "Bombardier Q400", "Gate2", "Gate8", 1447, 1684);
        addFlight("AC744", "YOW", "YYC", "03/03/2024 12:51", "06/03/2024 12:51", "Airbus A320", "Gate7", "Gate8", 523, 846);
        addFlight("AC124", "YYC", "YOW", "02/03/2024 12:51", "05/03/2024 12:51", "Bombardier Q400", "Gate6", "Gate2", 980, 1434);
        addFlight("AC589", "YYZ", "YUL", "01/03/2024 12:51", "06/03/2024 12:51", "Bombardier Q400", "Gate5", "Gate2", 891, 1118);
        addFlight("AC115", "YUL", "YYZ", "02/03/2024 12:51", "05/03/2024 12:51", "Bombardier Q400", "Gate5", "Gate7", 1243, 1506);
        addFlight("AC893", "YHM", "YEG", "01/03/2024 12:51", "05/03/2024 12:51", "Boeing 737", "Gate5", "Gate4", 561, 996);
    }



    @Override
    public List<iFlight> findFlight(String departure, String arrival, String dept_time) {
        List<iFlight> results = new ArrayList<>();
        for (iFlight flight : flights) {
            if (flight.getDeparture_icao().equals(departure) && flight.getArrival_icao().equals(arrival) && flight.getFlight_dept_date_time().split(" ")[0].equals(dept_time) ) {  //&& flight.getFlight_dept_date_time().split(" ")[0].equals(dept_time)
                results.add(flight);
            }
        }
        return results;
    }

    @Override
    public Graph<String, DefaultWeightedEdge> getAirportGraph() {
        return airportGraph;
    }

    private void addAircrafts() {
        addAircraft("Boeing 737", 5, 7, 7, 13);
        addAircraft("Airbus A320", 5, 6, 8, 15);
        addAircraft("Embraer E190", 5, 9, 7, 15);
        addAircraft("Boeing 777", 6, 6, 6, 19);
        addAircraft("Bombardier Q400", 4, 9, 7, 12);
    }

    private void addAircraft(String aircraftName, int numSeatPerRowBusiness, int numRowsBusiness,
                             int numSeatPerRowEcon, int numRowsEcon) {
        Aircraft aircraft = new Aircraft(aircraftName, numSeatPerRowBusiness, numRowsBusiness, numSeatPerRowEcon, numRowsEcon);
        aircraftMap.put(aircraftName, aircraft);
    }


    @Override
    public Map<String, iAircraft>  getAircraftMap() {
        return aircraftMap;
    }

    private void readConfig() {
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
        addConnection("YUL", "YYZ", 2301);
    }

    private void addFlight(String flightNumber, String departure_icao, String arrival_icao, String flight_dept_date_time, String flight_arr_date_time, String airCraft_Type, String depature_Gate, String arr_Gate, int econPrice, int busPrice) {
        flights.add(new Flight(flightNumber, departure_icao, arrival_icao, flight_dept_date_time, flight_arr_date_time, airCraft_Type, depature_Gate, arr_Gate, econPrice, busPrice));
    }

    // Helper Functions

    private void addAirport(String airport) {
        airportGraph.addVertex(airport);
    }

    private void addConnection(String source, String target, double weight) {
        DefaultWeightedEdge edge = airportGraph.addEdge(source, target);
        airportGraph.setEdgeWeight(edge, weight);
    }
}
