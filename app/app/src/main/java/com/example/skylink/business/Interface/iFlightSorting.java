package com.example.skylink.business.Interface;

import com.example.skylink.objects.Implementations.Flight;

import java.util.List;

public interface iFlightSorting {
    int compare(List<List<Flight>> flight1, List<List<Flight>> flight2);
}
