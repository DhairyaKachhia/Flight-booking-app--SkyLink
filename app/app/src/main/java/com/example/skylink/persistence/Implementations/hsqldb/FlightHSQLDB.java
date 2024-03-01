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
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class FlightHSQLDB implements IFlightDB {
    
    private final String dbPath;


    private final Graph<String, DefaultWeightedEdge> airportGraph;
    private String[] airports;
    private String[] distances;

    private Map<String, iAircraft> aircraftMap =  new HashMap<>();;

    public Map<String, iAircraft> getAircraftMap() {
        return aircraftMap;
    }


    private final String CREATE_TABLE = "CREATE TABLE FLIGHTS("
            + "flightNumber VARCHAR(10) PRIMARY KEY, "
            + "departure_icao VARCHAR(4), "
            + "arrival_icao VARCHAR(4), "
            + "flight_dept_date_time VARCHAR(20), "
            + "flight_arr_date_time VARCHAR(20), "
            + "airCraft_Type VARCHAR(20), "
            + "departure_Gate VARCHAR(10), "
            + "arr_Gate VARCHAR(10), "
            + "econPrice INT, "
            + "busnPrice INT"
            + ")";

    private final String[] COLUMNS = {
            "flightNumber",
            "departure_icao",
            "arrival_icao",
            "flight_dept_date_time",
            "flight_arr_date_time",
            "airCraft_Type",
            "departure_Gate",
            "arr_Gate",
            "econPrice",
            "busnPrice"
    };

    public Graph<String, DefaultWeightedEdge> getAirportGraph() {
        return airportGraph;
    }

    public FlightHSQLDB(String dbPath) {
        this.dbPath = dbPath;
        this.airportGraph = new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class);
        readConfig(Session.getInstance().getContext());
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath, "SA", "");
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

    private void addAirport(String airport) {
        airportGraph.addVertex(airport);
    }
    private void addConnection(String source, String target, double weight) {
        DefaultWeightedEdge edge = airportGraph.addEdge(source, target);
        airportGraph.setEdgeWeight(edge, weight);
    }

    public void addFlights() {
        try {
            AssetManager assetManager = Session.getInstance().getContext().getAssets();
            InputStream inputStream = assetManager.open("flight.json");

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(inputStream);

            JsonNode aircraftNode = rootNode.get("Aircraft");
            if (aircraftNode != null && aircraftNode.isObject()) {
                Iterator<Map.Entry<String, JsonNode>> aircraftIterator = aircraftNode.fields();
                while (aircraftIterator.hasNext()) {
                    Map.Entry<String, JsonNode> entry = aircraftIterator.next();
                    String aircraftName = entry.getKey();
                    JsonNode aircraftDetails = entry.getValue();

                    int numSeatPerRowBusiness = aircraftDetails.get("num_seat_per_row_business").asInt();
                    int numRowsBusiness = aircraftDetails.get("num_rows_business").asInt();
                    int numSeatPerRowEcon = aircraftDetails.get("num_seat_per_row_econ").asInt();
                    int numRowsEcon = aircraftDetails.get("num_rows_econ").asInt();
                    iAircraft aircraft = new Aircraft(aircraftName, numSeatPerRowBusiness, numRowsBusiness, numSeatPerRowEcon, numRowsEcon);
                    aircraftMap.put(aircraftName, aircraft);
                }
            }

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

                    iFlight flight1 = new Flight(flightNumber, departureIcao, arrivalIcao, departureDateTime, arrivalDateTime,
                            aircraftType, departureGate, arrivalGate, econPrice, busPrice);
                    addFlight(flight1);
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

        String sql = "SELECT * FROM FLIGHTS WHERE departure_icao = ? AND arrival_icao = ? AND flight_dept_date_time LIKE ?";

        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, departure);
            ps.setString(2, arrival);
            ps.setString(3, dept_time + "%"); // Assuming flight_dept_date_time format is like "18/02/2024 02:57"

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    results.add(new Flight(
                            rs.getString("flightNumber"),
                            rs.getString("departure_icao"),
                            rs.getString("arrival_icao"),
                            rs.getString("flight_dept_date_time"),
                            rs.getString("flight_arr_date_time"),
                            rs.getString("airCraft_Type"),
                            rs.getString("departure_Gate"),
                            rs.getString("arr_Gate"),
                            rs.getInt("econPrice"),
                            rs.getInt("busnPrice")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }




    private void addFlight(iFlight flight) {
        String sql = "INSERT INTO flights (flightNumber, departure_icao, arrival_icao, flight_dept_date_time, flight_arr_date_time, airCraft_Type, departure_Gate, arr_Gate, econPrice, busnPrice) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, flight.getFlightNumber());
            ps.setString(2, flight.getDeparture_icao());
            ps.setString(3, flight.getArrival_icao());
            ps.setString(4, flight.getFlight_dept_date_time());
            ps.setString(5, flight.getFlight_arr_date_time());
            ps.setString(6, flight.getAirCraft_Type());
            ps.setString(7, flight.getDepature_Gate());
            ps.setString(8, flight.getArr_Gate());
            ps.setInt(9, flight.getEconPrice());
            ps.setInt(10, flight.getBusnPrice());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public FlightHSQLDB initialize() {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            this.drop();
            stmt.executeUpdate(CREATE_TABLE);
            addFlights();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public IFlightDB drop() {
        String sql = "DROP TABLE FLIGHTS";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

}
