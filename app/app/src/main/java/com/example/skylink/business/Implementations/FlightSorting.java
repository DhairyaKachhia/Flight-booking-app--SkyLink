package com.example.skylink.business.Implementations;

import com.example.skylink.business.Interface.iFlightSorting;
import com.example.skylink.objects.Interfaces.iFlight;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FlightSorting implements iFlightSorting {

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
    public int compare(List<List<iFlight>> flight1, List<List<iFlight>> flight2) {
        switch (sortingOption) {
            case PRICE:
                return compareByPrice(flight1, flight2);
            case DIRECT_FLIGHTS:
                return compareByDirectFlights(flight1, flight2);
            case EARLIEST_DEPARTURE:
                return compareByEarliestDeparture(flight1, flight2);
        }
        return 0;

    }


    private int compareByPrice(List<List<iFlight>> flightList1, List<List<iFlight>> flightList2) {
        int price1 = getTotalPrice(flightList1);
        int price2 = getTotalPrice(flightList2);
        return Integer.compare(price1, price2);

    }

    private int getTotalPrice (List<List<iFlight>> flightList) {
        int totalPrice = 0;
        if (!flightList.isEmpty() && !flightList.get(0).isEmpty()) {
            totalPrice += flightList.get(0).get(0).getEconPrice();
        }

        return totalPrice;

    }

    private int compareByDirectFlights(List<List<iFlight>> flightList1, List<List<iFlight>> flightList2) {
        boolean hasDirectFlight1 = hasDirectFlight(flightList1);
        boolean hasDirectFlight2 = hasDirectFlight(flightList2);
        if (hasDirectFlight1 && !hasDirectFlight2) {
            return -1;
        } else if (!hasDirectFlight1 && hasDirectFlight2) {
            return 1;
        } else {
            return Integer.compare(flightList1.size(), flightList2.size());
        }

    }

    private boolean hasDirectFlight(List<List<iFlight>> flightList) {
        return flightList.size() == 1;
    }

    private int compareByEarliestDeparture(List<List<iFlight>> flightList1, List<List<iFlight>> flightList2) {
        long earliestDeparture1 = getEarliestDeparture(flightList1);
        long earliestDeparture2 = getEarliestDeparture(flightList2);
        return Long.compare(earliestDeparture1, earliestDeparture2);
    }

    private long getEarliestDeparture(List<List<iFlight>> flightList) {
        long earliestDeparture = Long.MAX_VALUE;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        if (!flightList.isEmpty() && !flightList.get(0).isEmpty()) {
            String flightDeparture = flightList.get(0).get(0).getFlight_dept_date_time();

            try {
                Date departureDateTime = dateFormat.parse(flightDeparture);
                long departureTime = departureDateTime.getTime();
                earliestDeparture = departureTime;
            } catch (ParseException e) {
                // if try fails, earliestDeparture will have Max Value
                e.printStackTrace();
                return earliestDeparture;
            }
        }

        return earliestDeparture;
    }



}
