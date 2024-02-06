package com.example.skylink.data;

import com.example.skylink.objects.Flight;

import java.util.ArrayList;
import java.util.List;

public class FlightDatabase {
    private final List<Flight> flights;

    public FlightDatabase() {
        this.flights = new ArrayList<>();
        addFlights();
    }

    public void addFlight(String flightNumber, String departure_icao, String arrival_icao, String flight_dept_date_time, String flight_arr_date_time, String airCraft_Type, String depature_Gate, String arr_Gate) {
        flights.add(new Flight(flightNumber, departure_icao, arrival_icao, flight_dept_date_time, flight_arr_date_time, airCraft_Type, depature_Gate, arr_Gate));
    }

    public void addFlights() {
        addFlight("AC400", "YYZ", "YVR", "02/02/2024 09:00", "02/02/2024 12:00", "Airbus A330", "Gate7", "Gate8");
        addFlight("AC100", "YYZ", "YUL", "02/02/2024 08:00", "02/02/2024 10:30", "Airbus A320", "Gate1", "Gate2");
        addFlight("AC200", "YUL", "YVR", "02/02/2024 12:00", "02/02/2024 16:00", "Boeing 737", "Gate3", "Gate4");
        addFlight("AC300", "YVR", "YYC", "02/02/2024 18:00", "02/02/2024 20:30", "Embraer E190", "Gate5", "Gate6");
        addFlight("AC500", "YUL", "YYC", "02/02/2024 11:30", "02/02/2024 14:00", "Boeing 777", "Gate9", "Gate10");
        addFlight("AC600", "YYZ", "YYC", "02/02/2024 10:00", "02/02/2024 14:30", "Boeing 747", "Gate11", "Gate12");
        addFlight("AC700", "YYC", "ABC", "02/02/2024 11:00", "02/02/2024 14:30", "Airbus A380", "Gate13", "Gate14");
    }



    public List<Flight> findFlight(String departure, String arrival, String dept_time) {
        List<Flight> results = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getDeparture_icao().equals(departure) && flight.getArrival_icao().equals(arrival) ) {  //&& flight.getFlight_dept_date_time().split(" ")[0].equals(dept_time)
                results.add(flight);
            }
        }
        return results;
    }


}
