package com.example.skylink;

import static com.example.skylink.application.Services.setupForIntegrationTest;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


import com.example.skylink.application.Services;
import com.example.skylink.business.Implementations.PlaneConfiguration;
import com.example.skylink.objects.Implementations.Aircraft;
import com.example.skylink.objects.Interfaces.iAircraft;
import com.example.skylink.persistence.Interfaces.IFlightDB;
import com.example.skylink.presentation.User_Auth.SignInActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class PlaneConfigurationIntegrated {

    private PlaneConfiguration planeConfiguration;

    @Before
    public void setUp() {
        String dbDirectory = "/Users/akintundemayokun/Desktop/db";
        String dbName = "SC"; // Provide the desired name for your HSQLDB file
        setupForIntegrationTest(dbDirectory,dbName);
        IFlightDB mockFlightDB = Services.getFlightDatabase();
        planeConfiguration = new PlaneConfiguration(mockFlightDB);
    }

    @Test
    public void testGetPlaneConfigurationWithValidInput() {
        // Test with valid aircraft name and seat type
        String[] result = planeConfiguration.getPlaneConfiguration("Boeing 737", "Economy");

        // Verify
        assertNotNull("Result should not be null", result);
        assertEquals("Aircraft name should match", "Boeing 737", result[0]);
        assertEquals("Economy class seats should match", "5", result[1]);
        assertEquals("Business class seats should match", "7", result[2]);
        assertEquals("First class seats should match", "7", result[3]);
        assertEquals("Total seats should match", "13", result[4]);
    }

    @Test
    public void testGetPlaneConfigurationWithInvalidAircraftName() {
        // Test with invalid aircraft name
        String[] result = planeConfiguration.getPlaneConfiguration("Invalid Aircraft", "Economy");

        // Verify that the result is null for an invalid aircraft name
        assertNull("Result should be null for an invalid aircraft name", result);
    }

    @Test
    public void testGetPlaneConfigurationWithInvalidSeatType() {
        // Test with invalid seat type
        String[] result = planeConfiguration.getPlaneConfiguration("Boeing 737", "invalidSeatType");

        // Verify that the result is null for an invalid seat type
        assertNull("Result should be null for an invalid seat type", result);
    }

    @Test
    public void testGetPlaneConfigurationWithValidInputAndBusinessClass() {
        // Test with valid aircraft name and business class seat type
        String[] result = planeConfiguration.getPlaneConfiguration("Airbus A320", "Business");

        // Verify
        assertNotNull("Result should not be null", result);
        assertEquals("Aircraft name should match", "Airbus A320", result[0]);
        assertEquals("Economy class seats should match", "5", result[1]);
        assertEquals("Business class seats should match", "6", result[2]);
        assertEquals("First class seats should match", "8", result[3]);
        assertEquals("Total seats should match", "15", result[4]);
    }

    @Test
    public void testGetPlaneConfigurationWithInvalidSeatTypeAndInvalidAircraftName() {
        // Test with both invalid seat type and aircraft name
        String[] result = planeConfiguration.getPlaneConfiguration("Invalid Aircraft", "invalidSeatType");

        // Verify that the result is null for both invalid seat type and aircraft name
        assertNull("Result should be null for an invalid seat type and aircraft name", result);
    }

}
