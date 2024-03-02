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
        iFlightSearch flightSearch = new FlightSearch("YZZ", "YYC", "01/03/2024", "04/03/2024", 2, true);
        HashMap<String, List<List<List<iFlight>>>> itinerary = airportPath.findFlights(flightSearch);

        assertNotNull(itinerary);
    }

    @Test
    public void testFindFlights_Return() {
        iFlightSearch flightSearch = new FlightSearch("YZZ", "YYC", "2024-03-02", "2024-03-02", 2, true);
        HashMap<String, List<List<List<iFlight>>>> itinerary = airportPath.findFlights(flightSearch);

        assertNotNull(itinerary);
    }

    @Test
    public void testFindFlights_NoPaths() {
        iFlightSearch flightSearch = new FlightSearch("YVR", "YUL", "2024-03-02", "2024-03-02", 2, true);
        HashMap<String, List<List<List<iFlight>>>> itinerary = airportPath.findFlights(flightSearch);
        assertNotNull(itinerary);
    }

    @Test
    public void testFindFlights_EmptyPaths() {
        iFlightSearch flightSearch = new FlightSearch("YYZ", "YVR", "2024-03-02", "2024-03-02", 2, true);
        HashMap<String, List<List<List<iFlight>>>> itinerary = airportPath.findFlights(flightSearch);
        assertNotNull(itinerary);
    }
}
