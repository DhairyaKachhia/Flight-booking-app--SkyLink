package com.example.skylink.business;

import com.example.skylink.objects.Flight;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class TestAirportPath {
    private AirportPath airportPath;
    @Before
    public void setUp() {
        System.out.println("Starting tests for AirportPath");
        airportPath = new AirportPath();
    }
    @Test
    public void testFindAllPaths() {
        System.out.println("\nStarting test for findAllPaths in AirportPath");
        List<List<String>> paths = airportPath.findAllPaths("YYZ", "YVR");
        assertNotNull(paths);
        assertFalse(paths.isEmpty());
        assertEquals(32, paths.size());
    }

    @Test
    public void testCalculatePathDistance() {
        System.out.println("\nStarting test for calculatePathDistance in AirportPath");
        List<String> path = Arrays.asList("YYZ", "YUL", "YVR");
        double distance = airportPath.calculatePathDistance(path);
        assertEquals(6125, distance, 0.1);
    }

    @Test
    public void testPullFlight() {
        System.out.println("\nStarting test for pullFlight in AirportPath");
        List<List<String>> allDeptFlight = Arrays.asList(
                Arrays.asList("YYC", "YEG", "YOW"),
                Arrays.asList("YVR","YYZ")
        );
        List<List<List<Flight>>> proposedFlightPath = airportPath.pullFlight(allDeptFlight, "10/02/2024 23:56");
        assertNotNull(proposedFlightPath);
    }

    @Test
    public void testFindFlights() {
        System.out.println("\nStarting test for findFlights in AirportPath");
        HashMap<String, List<List<List<Flight>>>> itinerary = airportPath.findFlights("YOW", "YVR", "02/02/2024", "02/02/2024", true);
        assertNotNull(itinerary);
    }

}
