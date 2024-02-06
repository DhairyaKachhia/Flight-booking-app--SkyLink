package com.example.skylink.objects;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class Flights implements Serializable {
    private HashMap<String, List<List<List<Flight>>>> data;

    public Flights(HashMap<String, List<List<List<Flight>>>> data) {
        this.data = data;
    }

    public HashMap<String, List<List<List<Flight>>>> getData() {
        return data;
    }
}
