package com.example.skylink.UnitTest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.skylink.business.Implementations.FlightSorting;
import com.example.skylink.business.Interface.iFlightSorting;
import com.example.skylink.objects.Implementations.Flight;
import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.persistence.Implementations.stub.FlightStub;
import com.example.skylink.persistence.Interfaces.IFlightDB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FlightSortingUnit {

    private List<List<iFlight>> inner1;
    private List<List<iFlight>> inner2;
    private List<List<iFlight>> indirectFlight;


    @Before
    public void setup() {
        iFlight flight1 = new Flight("AC785", "YVR", "YEG", "08/03/2024 18:47", "12/03/2024 18:47", "Boeing 737", "Gate3", "Gate8", 1000, 1200);
        iFlight flight2 = new Flight("AC489", "YEG", "YVR", "06/03/2024 18:47", "12/03/2024 18:47", "Boeing 777", "Gate1", "Gate8", 900, 1100);

        iFlight flight3 = new Flight("AC392", "YEG", "YHM", "13/03/2024 18:47", "14/03/2024 18:47", "Boeing 777", "Gate9", "Gate9", 700, 950);

        List<iFlight> in1 = new ArrayList<>();
        in1.add(flight1);

        List<iFlight> in2 = new ArrayList<>();
        in2.add(flight2);

        List<iFlight> in3 = new ArrayList<>();
        in3.add(flight3);

        inner1 = new ArrayList<>();
        inner1.add(in1);            // this flight has YVR -> YEG flight

        inner2 = new ArrayList<>();
        inner2.add(in2);            // this flight has YEG -> YER flight

        indirectFlight = new ArrayList<>();
        indirectFlight.addAll(inner1);
        indirectFlight.add(in3);     // this flight has YVR -> YEG -> YHM flights
    }

    @Test
    public void testSortByEarliestDeparture() {
        iFlightSorting flightSorting = new FlightSorting(FlightSorting.SortingOption.EARLIEST_DEPARTURE);

        List<List<List<iFlight>>> flightToSort = new ArrayList<>();
        flightToSort.add(inner1);       // late departure flight added first
        flightToSort.add(inner2);       // early departure flight added last

        // sorted departure flights
        List<List<List<iFlight>>> sortedFlightList = new ArrayList<>();
        sortedFlightList.add(inner2);
        sortedFlightList.add(inner1);

        flightToSort.sort(flightSorting);      // should sort 'flightToSort' by earliest departure

        assertEquals(flightToSort, sortedFlightList);
    }

    @Test
    public void testSortByLowestPrice() {
        iFlightSorting flightSorting = new FlightSorting(FlightSorting.SortingOption.PRICE);

        List<List<List<iFlight>>> flightToSort = new ArrayList<>();
        flightToSort.add(inner1);       // high price is on top
        flightToSort.add(inner2);       // low price is on bottom

        // sorted price flights
        List<List<List<iFlight>>> sortedFlightList = new ArrayList<>();
        sortedFlightList.add(inner2);
        sortedFlightList.add(inner1);

        flightToSort.sort(flightSorting);      // should sort 'flightToSort' by lowest price

        assertEquals(flightToSort, sortedFlightList);

    }

    @Test
    public void testSortByDirectFlightsFirst() {
        iFlightSorting flightSorting = new FlightSorting(FlightSorting.SortingOption.DIRECT_FLIGHTS);

        List<List<List<iFlight>>> flightToSort = new ArrayList<>();
        flightToSort.add(indirectFlight);       // indirect flight added first
        flightToSort.add(inner2);       // direct flight added last

        // sorted direct flight
        List<List<List<iFlight>>> sortedFlightList = new ArrayList<>();
        sortedFlightList.add(inner2);
        sortedFlightList.add(indirectFlight);

        flightToSort.sort(flightSorting);      // should sort 'flightToSort' by direct flight first

        assertEquals(flightToSort, sortedFlightList);

    }

}
