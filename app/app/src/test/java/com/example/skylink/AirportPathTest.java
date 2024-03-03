package com.example.skylink;

import static org.junit.Assert.*;

import com.example.skylink.business.Implementations.AirportPath;
import com.example.skylink.objects.Implementations.FlightSearch;
import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.objects.Interfaces.iFlightSearch;
import com.example.skylink.persistence.Interfaces.IFlightDB;
import com.example.skylink.persistence.Implementations.hsqldb.FlightStub;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class AirportPathTest {

    private AirportPath airportPath;

    @Before
    public void setUp() {
        IFlightDB mockFlightDB = new FlightStub();
        Graph<String, DefaultWeightedEdge> airportGraph = mockFlightDB.getAirportGraph();
        airportPath = new AirportPath(mockFlightDB, airportGraph);
    }

    @Test
    public void testFindFlights_OneWay() {
        // Test with one-way flight search
        iFlightSearch flightSearch = new FlightSearch("YZZ", "YYC", "01/03/2024", "04/03/2024", 2, true);
        HashMap<String, List<List<List<iFlight>>>> itinerary = airportPath.findFlights(flightSearch);

        assertNotNull(itinerary);
    }

    @Test
    public void testFindFlights_Return() {
        // Test with return flight search
        iFlightSearch flightSearch = new FlightSearch("YZZ", "YYC", "2024-03-02", "2024-03-02", 2, true);
        HashMap<String, List<List<List<iFlight>>>> itinerary = airportPath.findFlights(flightSearch);

        assertNotNull(itinerary);
    }

    @Test
    public void testFindFlights_NoPaths() {
        // Test with no paths available
        iFlightSearch flightSearch = new FlightSearch("YVR", "PVG", "2024-03-02", "2024-03-02", 2, true);
        HashMap<String, List<List<List<iFlight>>>> itinerary = airportPath.findFlights(flightSearch);

        assertNotNull(itinerary);
    }

    @Test
    public void testFindFlights_EmptyPaths() {
        // Test with empty paths available
        iFlightSearch flightSearch = new FlightSearch("YYZ", "YYT", "2024-03-02", "2024-03-02", 2, true);
        HashMap<String, List<List<List<iFlight>>>> itinerary = airportPath.findFlights(flightSearch);

        assertNotNull(itinerary);
    }

    @Test
    public void testFindFlights_InvalidDate() {
        // Test with invalid date format
        iFlightSearch flightSearch = new FlightSearch("YZZ", "YYC", "2024/01/02", "2024/01/02", 2, true);
        HashMap<String, List<List<List<iFlight>>>> itinerary = airportPath.findFlights(flightSearch);
        assertEquals(0, itinerary.size());
        assertNotNull(itinerary);
    }

    @Test
    public void testFindFlights_InvalidPassengerCount() {
        // Test with invalid passenger count
        iFlightSearch flightSearch = new FlightSearch("YZZ", "YYC", "2024-03-02", "2024-03-02", -2, true);
        HashMap<String, List<List<List<iFlight>>>> itinerary = airportPath.findFlights(flightSearch);
        assertEquals(0, itinerary.size());
        assertNotNull(itinerary);
    }

    @Test
    public void testFindFlights_InvalidFlightType() {
        // Test with invalid flight type
        iFlightSearch flightSearch = new FlightSearch("YZZ", "YYC", "2024-03-02", "2024-03-02", 2, false);
        HashMap<String, List<List<List<iFlight>>>> itinerary = airportPath.findFlights(flightSearch);
        assertEquals(0, itinerary.size());
        assertNotNull(itinerary);
    }

    @Test
    public void testFindFlights_OneWay_InvalidPath() {
        // Test with one-way flight search and no valid path
        iFlightSearch flightSearch = new FlightSearch("YZZ", "YYC", "2024-03-02", null, 2, true);
        HashMap<String, List<List<List<iFlight>>>> itinerary = airportPath.findFlights(flightSearch);

        assertNotNull(itinerary);
        assertEquals(0, itinerary.size());
    }

    @Test
    public void testFindFlights_Return_InvalidPath() {
        // Test with return flight search and no valid path
        iFlightSearch flightSearch = new FlightSearch("YVR", "PVG", "2024-03-02", "2024-03-05", 2, false);
        HashMap<String, List<List<List<iFlight>>>> itinerary = airportPath.findFlights(flightSearch);

        assertNotNull(itinerary);
        assertEquals(0, itinerary.size());
    }
}
