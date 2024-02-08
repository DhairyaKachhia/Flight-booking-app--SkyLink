package com.example.skylink.business;

import com.example.skylink.objects.Flight;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FlightSorting implements Comparator<List<List<Flight>>> {

    public enum SortingOption {
        PRICE,
        DIRECT_FLIGHTS,
        EARLIEST_DEPARTURE
    }

    private SortingOption sortingOption;


    public FlightSorting(SortingOption sortingOption) {
        this.sortingOption = sortingOption;
    }

    @Override
    public int compare(List<List<Flight>> flight1, List<List<Flight>> flight2) {
        switch (sortingOption) {
            case PRICE:
                return compareByPrice(flight1, flight2);
            case DIRECT_FLIGHTS:
                return compareByDirectFlights(flight1, flight2);
            case EARLIEST_DEPARTURE:
                return compareByEarliestDeparture(flight1, flight2);
            default:
//                throw new IllegalArgumentException("Invalid sorting option");
        }
        return 0;

    }


    private int compareByPrice(List<List<Flight>> flightList1, List<List<Flight>> flightList2) {
        // Create a comparator to compare lists of flights based on their total price

        int price1 = getTotalPrice(flightList1);
        int price2 = getTotalPrice(flightList2);
        return Integer.compare(price1, price2);

    }

    private int getTotalPrice (List<List<Flight>> flightList) {

        int totalPrice = 0;
//        for (List<Flight> flights : flightList) {
//            for (Flight flight : flights) {
//                totalPrice += flight.getEconPrice();
//            }
//        }

        // get price of main flight
        if (!flightList.isEmpty() && !flightList.get(0).isEmpty()) {
            totalPrice += flightList.get(0).get(0).getEconPrice();
        }

        return totalPrice;

    }

    private int compareByDirectFlights(List<List<Flight>> flightList1, List<List<Flight>> flightList2) {

        boolean hasDirectFlight1 = hasDirectFlight(flightList1);
        boolean hasDirectFlight2 = hasDirectFlight(flightList2);

        // Sort direct flights first, followed by multi-stop flights
        if (hasDirectFlight1 && !hasDirectFlight2) {
            return -1;
        } else if (!hasDirectFlight1 && hasDirectFlight2) {
            return 1;
        } else {
            // If both flights have direct or multi-stop flights, sort by number of stops
            return Integer.compare(flightList1.size(), flightList2.size());
        }

    }

    private boolean hasDirectFlight(List<List<Flight>> flightList) {
        // Check if the flight list contains only one flight (direct flight)
        return flightList.size() == 1;
    }

    private int compareByEarliestDeparture(List<List<Flight>> flightList1, List<List<Flight>> flightList2) {


        return 0;
    }


}
