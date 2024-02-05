package com.example.skylink.business;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;
import java.util.HashMap;
import com.example.skylink.data.Flight;

public class AirportPathTest{

    @Test
    public void testFindAllPaths() {
        // Arrange
        AirportPathInterface airportPath = new AirportPath();

        // Act
        List<List<String>> paths = airportPath.findAllPaths("YYZ", "YUL");

        // Assert
        assertNotNull(paths);
        // Add more assertions based on expectations
    }

    @Test
    public void testCalculatePathDistance() {
        // Arrange
        AirportPathInterface airportPath = new AirportPath();

        // Act
        double distance = airportPath.calculatePathDistance(List.of("YYZ", "YUL", "YVR"));

        // Assert
        assertEquals(541 + 3307, distance, 0.001); // Assuming weights are in kilometers
    }

    @Test
    public void testPullFlight() {
        // Arrange
        AirportPathInterface airportPath = new AirportPath();
        List<List<String>> allDeptFlight = airportPath.findAllPaths("YYZ", "YUL");
        String flightDeptDate = "02/02/2024";

        // Act
        List<List<List<Flight>>> flights = airportPath.pullFlight(allDeptFlight, flightDeptDate);

        // Assert
        assertNotNull(flights);
        // Add more assertions based on expectations
    }

    @Test
    public void testFindFlights() {
        // Arrange
        AirportPathInterface airportPath = new AirportPath();
        String flightDept = "YYZ";
        String flightArrival = "YUL";
        String flightDeptDate = "02/02/2024";
        String flightReturnDate = "02/03/2024";

        // Act
        HashMap<String, List<List<List<Flight>>>> itinerary = airportPath.findFlights(flightDept, flightArrival, flightDeptDate, flightReturnDate);

        // Assert
        assertNotNull(itinerary);
        // Add more assertions based on expectations
    }
}
