package com.example.skylink.data;

import com.example.skylink.objects.City;

import java.util.ArrayList;
import java.util.List;

public class CitiesRepository {
    public List<City> getCities() {
        List<City> cities = new ArrayList<>();
        cities.add(new City("Toronto", "YYZ"));
        cities.add(new City("Montreal", "YUL"));
        cities.add(new City("Calgary", "YYC"));
        cities.add(new City("Ottawa", "YOW"));
        cities.add(new City("Edmonton", "YEG"));
        cities.add(new City("Mississauga", "YYZ"));
        cities.add(new City("Winnipeg", "YWG"));
        cities.add(new City("Vancouver", "YVR"));
        cities.add(new City("Brampton", "YZZ"));
        cities.add(new City("Hamilton", "YHM"));
        return cities;
    }
}
