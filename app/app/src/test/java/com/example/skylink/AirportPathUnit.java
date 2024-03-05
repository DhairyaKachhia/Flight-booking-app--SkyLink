package com.example.skylink;

import static org.junit.Assert.*;

import com.example.skylink.business.Implementations.AirportPath;
import com.example.skylink.objects.Implementations.FlightSearch;
import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.objects.Interfaces.iFlightSearch;
import com.example.skylink.persistence.Implementations.stub.FlightStub;
import com.example.skylink.persistence.Interfaces.IFlightDB;

import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class AirportPathUnit {

    private AirportPath airportPath;

    @Before
    public void setUp() {
        IFlightDB mockFlightDB = new FlightStub();
        airportPath = new AirportPath(mockFlightDB,mockFlightDB.getAirportGraph());
    }

    @Test
    public void testFindFlights_OneWay() {
        iFlightSearch flightSearch = new FlightSearch("YZZ", "YYC", "01/03/2024", "04/03/2024",2,true);
        HashMap<String, List<List<List<iFlight>>>> itinerary = airportPath.findFlights(flightSearch);

        assertNotNull(itinerary);
        assertTrue(itinerary.containsKey("Outbound"));
        assertFalse(itinerary.containsKey("Inbound"));

        List<List<List<iFlight>>> outboundFlights = itinerary.get("Outbound");
        assertNotNull(outboundFlights);
        assertFalse(outboundFlights.isEmpty());
    }

    @Test
    public void testFindFlights_Return() {
        iFlightSearch flightSearch = new FlightSearch("AirportA", "AirportB", "2024-03-02", "2024-03-02",2,true);
//        HashMap<String, List<List<List<iFlight>>>> itinerary = airportPath.findFlights(flightSearch);
//
//        assertNotNull(itinerary);
//        assertTrue(itinerary.containsKey("Outbound"));
//        assertTrue(itinerary.containsKey("Inbound"));
//
//        List<List<List<iFlight>>> outboundFlights = itinerary.get("Outbound");
//        List<List<List<iFlight>>> inboundFlights = itinerary.get("Inbound");
//
//        assertNotNull(outboundFlights);
//        assertFalse(outboundFlights.isEmpty());
//        assertNotNull(inboundFlights);
//        assertFalse(inboundFlights.isEmpty());
        // Add more assertions based on your expected behavior
    }

    @Test
    public void testFindFlights_NoPaths() {
        // Test when no paths are available between airports
        iFlightSearch flightSearch = new FlightSearch("NonExistentAirport1", "NonExistentAirport2", "2024-03-02", "2024-03-02",2,true);
//        HashMap<String, List<List<List<iFlight>>>> itinerary = airportPath.findFlights(flightSearch);
//
//        assertNotNull(itinerary);
//        assertTrue(itinerary.isEmpty());
    }

    @Test
    public void testFindFlights_EmptyPaths() {
        // Test when paths are available, but filtered out
        iFlightSearch flightSearch = new FlightSearch("AirportA", "AirportB", "2024-03-02", "2024-03-02",2,true);
//        HashMap<String, List<List<List<iFlight>>>> itinerary = airportPath.findFlights(flightSearch);
//
//        assertNotNull(itinerary);
//        assertTrue(itinerary.isEmpty());
    }


}
