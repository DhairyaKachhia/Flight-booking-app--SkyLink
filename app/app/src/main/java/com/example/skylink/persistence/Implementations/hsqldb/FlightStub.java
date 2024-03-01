package com.example.skylink.persistence.Implementations.hsqldb;

import android.content.Context;
import android.content.res.AssetManager;

import com.example.skylink.objects.Implementations.Flight;
import com.example.skylink.objects.Interfaces.iAircraft;
import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.persistence.Interfaces.IFlightDB;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class FlightStub implements IFlightDB {
    private final List<iFlight> flights;
    private final Graph<String, DefaultWeightedEdge> airportGraph;
    private String[] airports;
    private String[] distances;
    private Context context;  // Added context as an instance variable

    public FlightStub(Context context, Graph<String, DefaultWeightedEdge> airportGraph) {
        this.context = context;  // Initialize context
        this.airportGraph = airportGraph;
        this.flights = new ArrayList<>();
        readConfig(context);
        addFlights();
    }

    @Override
    public void addFlights() {
        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("flight.json");

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(inputStream);

            JsonNode flightsNode = rootNode.get("Flights");
            if (flightsNode != null && flightsNode.isArray()) {
                for (JsonNode flight : flightsNode) {
                    String flightNumber = flight.get("flightNumber").asText();
                    String departureIcao = flight.get("departure_icao").asText();
                    String arrivalIcao = flight.get("arrival_icao").asText();
                    String departureDateTime = flight.get("flight_dept_date_time").asText();
                    String arrivalDateTime = flight.get("flight_arr_date_time").asText();
                    String aircraftType = flight.get("airCraft_Type").asText();
                    String departureGate = flight.get("depature_Gate").asText();
                    String arrivalGate = flight.get("arr_Gate").asText();
                    int econPrice = flight.get("econPrice").asInt();
                    int busPrice = flight.get("busPrice").asInt();

                    addFlight(flightNumber, departureIcao, arrivalIcao, departureDateTime, arrivalDateTime,
                            aircraftType, departureGate, arrivalGate, econPrice, busPrice);
                }
            } else {
                System.out.println("Flights not found in JSON file.");
            }

            // Close the InputStream
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public IFlightDB initialize() {
        // ... (your existing code)
        return this;
    }

    @Override
    public IFlightDB drop() {
        // ... (your existing code)
        return this;
    }

    @Override
    public Graph<String, DefaultWeightedEdge> getAirportGraph() {
        return null;
    }

    @Override
    public Map<String, iAircraft> getAircraftMap() {
        return null;
    }

    private void readConfig(Context context) {
        try {
            AssetManager assetManager = context.getAssets();
            InputStream input = assetManager.open("flight-config.properties");

            Properties prop = new Properties();
            prop.load(input);

            airports = prop.getProperty("airports").split(",");
            for (String airport : airports) {
                addAirport(airport);
            }

            distances = prop.getProperty("distances").split(",");
            for (String distance : distances) {
                String[] parts = distance.split("-");
                addConnection(parts[0], parts[1], Double.parseDouble(parts[2]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
