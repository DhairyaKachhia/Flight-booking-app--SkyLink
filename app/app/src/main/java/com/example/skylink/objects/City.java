package com.example.skylink.objects;

public class City {
    private String name;
    private String code;

    // Constructor
    public City(String name, String code) {
        this.name = name;
        this.code = code;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return name + " - " + code;
    }
}
