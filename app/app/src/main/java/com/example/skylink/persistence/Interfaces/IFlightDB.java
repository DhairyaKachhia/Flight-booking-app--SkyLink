package com.example.skylink.persistence.Interfaces;

import com.example.skylink.objects.Interfaces.iAircraft;
import com.example.skylink.objects.Interfaces.iFlight;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.List;
import java.util.Map;

public interface IFlightDB {
    void addFlights();

    List<iFlight> findFlight(String departure, String arrival, String dept_time);

    IFlightDB initialize();

    IFlightDB drop();
    Graph<String, DefaultWeightedEdge> getAirportGraph();
    Map<String, iAircraft> getAircraftMap();
}
