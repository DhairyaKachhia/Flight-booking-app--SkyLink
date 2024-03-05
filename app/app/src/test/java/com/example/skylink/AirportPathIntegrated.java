package com.example.skylink;


import static com.example.skylink.application.Services.setupForIntegrationTest;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.example.skylink.application.Services;
import com.example.skylink.business.Implementations.AirportPath;
import com.example.skylink.business.Interface.iAirportPath;
import com.example.skylink.objects.Implementations.FlightSearch;
import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.objects.Interfaces.iFlightSearch;
import com.example.skylink.persistence.Interfaces.IFlightDB;
import com.example.skylink.presentation.User_Auth.SignInActivity;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class AirportPathIntegrated {

    iAirportPath airportPath;
    @Before
    public void setUp() throws IOException {
        String dbDirectory = "/Users/akintundemayokun/Desktop/db";
        String dbName = "SC"; // Provide the desired name for your HSQLDB file
        setupForIntegrationTest(dbDirectory,dbName);
        IFlightDB mockFlightDB = Services.getFlightDatabase();
        mockFlightDB.addFlightsPC(new FileInputStream("/Users/akintundemayokun/Desktop/db/flights.sql"));
        Graph<String, DefaultWeightedEdge> airportGraph  = mockFlightDB.getAirportGraph();
        airportPath = new AirportPath(mockFlightDB,airportGraph);
    }


    @Test
    public void testFindFlights_OneWay() {
        iFlightSearch flightSearch = new FlightSearch("YYC", "YOW", "06/03/2024", "09/03/2024", 2, true);
        HashMap<String, List<List<List<iFlight>>>> itinerary = airportPath.findFlights(flightSearch);

        assertNotNull(itinerary);
        assertTrue(itinerary.containsKey("Outbound"));
        assertFalse(itinerary.containsKey("Inbound"));

        List<List<List<iFlight>>> outboundFlights = itinerary.get("Outbound");
        assertNotNull(outboundFlights);
        assertFalse(outboundFlights.isEmpty());
        // Add more assertions based on your expected behavior
    }

    @Test
    public void testFindFlights_Return() {
        iFlightSearch flightSearch = new FlightSearch("YYZ", "YUL", "06/03/2024", "08/03/2024", 2, true);
        HashMap<String, List<List<List<iFlight>>>> itinerary = airportPath.findFlights(flightSearch);

        assertNotNull(itinerary);
        assertTrue(itinerary.containsKey("Outbound"));
        assertTrue(itinerary.containsKey("Inbound"));

        List<List<List<iFlight>>> outboundFlights = itinerary.get("Outbound");
        List<List<List<iFlight>>> inboundFlights = itinerary.get("Inbound");

        assertNotNull(outboundFlights);
        assertFalse(outboundFlights.isEmpty());
        assertNotNull(inboundFlights);
        assertFalse(inboundFlights.isEmpty());
        // Add more assertions based on your expected behavior
    }

    @Test
    public void testFindFlights_NoPaths() {
        // Test when no paths are available between airports
        iFlightSearch flightSearch = new FlightSearch("NonExistentAirport1", "NonExistentAirport2", "08/03/2024", "09/03/2024", 2, true);
        HashMap<String, List<List<List<iFlight>>>> itinerary = airportPath.findFlights(flightSearch);

        assertNotNull(itinerary);
        assertTrue(itinerary.isEmpty());
    }

    @Test
    public void testFindFlights_EmptyPaths() {
        // Test when paths are available, but filtered out
        iFlightSearch flightSearch = new FlightSearch("YYC", "YEG", "08/03/2024", "09/03/2024", 2, true);
        HashMap<String, List<List<List<iFlight>>>> itinerary = airportPath.findFlights(flightSearch);

        assertNotNull(itinerary);
        assertTrue(itinerary.isEmpty());
    }
}
