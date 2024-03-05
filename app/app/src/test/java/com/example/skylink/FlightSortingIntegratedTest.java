package com.example.skylink;

import static org.junit.Assert.assertTrue;

import com.example.skylink.business.Implementations.FlightSorting;
import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.persistence.Implementations.stub.FlightStub;
import com.example.skylink.persistence.Interfaces.IFlightDB;

import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FlightSortingIntegratedTest {
    @Test
    public void testSortByPrice() {
        IFlightDB flightDB = new FlightStub();
        FlightSorting sorting = new FlightSorting(FlightSorting.SortingOption.PRICE);

        // Assuming departure, arrival, and departure time for testing
        String departure = "YYZ";
        String arrival = "YUL";
        String dept_time = "01/03/2024";

        List<iFlight> foundFlights = flightDB.findFlight(departure, arrival, dept_time);

        // Sort the found flights using a custom Comparator
        Collections.sort(foundFlights, new Comparator<iFlight>() {
            @Override
            public int compare(iFlight f1, iFlight f2) {
                return Integer.compare(f1.getEconPrice(), f2.getEconPrice());
            }
        });

        // Add assertions to check if flights are sorted by price correctly
        assertTrue(foundFlights.size() > 0);
        for (int i = 0; i < foundFlights.size() - 1; i++) {
            assertTrue(foundFlights.get(i).getEconPrice() <= foundFlights.get(i+1).getEconPrice());
        }
    }

    // Add a test method for sorting by earliest departure with empty flight list
    @Test
    public void testSortByEarliestDepartureEmptyList() {
        IFlightDB flightDB = new FlightStub();
        FlightSorting sorting = new FlightSorting(FlightSorting.SortingOption.EARLIEST_DEPARTURE);

        // Assuming departure, arrival, and departure time for testing
        String departure = "YUL";
        String arrival = "YWG";
        String dept_time = "01/01/2024"; // Assuming no flights on this date

        List<iFlight> foundFlights = flightDB.findFlight(departure, arrival, dept_time);

        // Add assertion to check if the found flights list is empty
        assertTrue(foundFlights.isEmpty());
    }

    // Add a test method for sorting by price with duplicate flights
    @Test
    public void testSortByPriceDuplicateFlights() {
        IFlightDB flightDB = new FlightStub();
        FlightSorting sorting = new FlightSorting(FlightSorting.SortingOption.PRICE);

        // Assuming departure, arrival, and departure time for testing
        String departure = "YYZ";
        String arrival = "YUL";
        String dept_time = "01/03/2024";

        List<iFlight> foundFlights = flightDB.findFlight(departure, arrival, dept_time);

        // Add a duplicate flight to the found flights list
        iFlight duplicateFlight = foundFlights.get(0);
        foundFlights.add(duplicateFlight);

        // Sort the found flights using a custom Comparator
        Collections.sort(foundFlights, new Comparator<iFlight>() {
            @Override
            public int compare(iFlight f1, iFlight f2) {
                return Integer.compare(f1.getEconPrice(), f2.getEconPrice());
            }
        });

        // Add assertions to check if flights are sorted by price correctly
        assertTrue(foundFlights.size() > 0);
        for (int i = 0; i < foundFlights.size() - 1; i++) {
            assertTrue(foundFlights.get(i).getEconPrice() <= foundFlights.get(i+1).getEconPrice());
        }
    }
}
