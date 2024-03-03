package com.example.skylink;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;
import com.example.skylink.application.Services;
import com.example.skylink.business.Implementations.PlaneConfiguration;
import com.example.skylink.objects.Implementations.Aircraft;
import com.example.skylink.objects.Interfaces.iAircraft;
import com.example.skylink.persistence.Interfaces.IFlightDB;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class PlaneConfigurationIntegratedTest {
    private IFlightDB flightDatabase;
    private PlaneConfiguration planeConfiguration;

    @Before
    public void setUp() {
        flightDatabase = Services.getFlightDatabase();
        planeConfiguration = new PlaneConfiguration(flightDatabase);
    }
    @Test
    public void testGetPlaneConfigurationWithValidInput() {
        // Mock data
        iAircraft mockAircraft = new Aircraft("Boeing 777", 6, 6, 6, 19);
        Map<String, iAircraft> aircraftMap = new HashMap<>();
        aircraftMap.put("Boeing 777", mockAircraft);

        // Test
        String[] result = planeConfiguration.getPlaneConfiguration("Boeing 777", "bus");

        // Verify
        assertArrayEquals(new String[]{"Boeing 777", "6", "6", "6", "6"}, result);
    }

    @Test
    public void testGetPlaneConfigurationWithInvalidAircraftName() {
        // Mock data
        iAircraft mockAircraft = new Aircraft("Boeing 777", 6, 6, 6, 19);
        Map<String, iAircraft> aircraftMap = new HashMap<>();
        aircraftMap.put("Boeing 777", mockAircraft);

        // Test with invalid aircraft name
        String[] result = planeConfiguration.getPlaneConfiguration("Invalid Aircraft", "bus");

        // Verify that the result is null for an invalid aircraft name
        assertNull(result);
    }

    @Test
    public void testGetPlaneConfigurationWithInvalidSeatType() {
        // Mock data
        iAircraft mockAircraft = new Aircraft("Boeing 777", 6, 6, 6, 19);
        Map<String, iAircraft> aircraftMap = new HashMap<>();
        aircraftMap.put("Boeing 777", mockAircraft);

        // Test with invalid seat type
        String[] result = planeConfiguration.getPlaneConfiguration("Boeing 777", "invalidSeatType");

        // Verify that the result is null for an invalid seat type
        assertNull(result);
    }

    @Test
    public void testGetPlaneConfigurationWithEmptyAircraftName() {
        // Test with empty aircraft name
        String[] result = planeConfiguration.getPlaneConfiguration("", "bus");

        // Verify that the result is null for an empty aircraft name
        assertNull(result);
    }

    @Test
    public void testGetPlaneConfigurationWithEmptySeatType() {
        // Mock data
        iAircraft mockAircraft = new Aircraft("Boeing 777", 6, 6, 6, 19);
        Map<String, iAircraft> aircraftMap = new HashMap<>();
        aircraftMap.put("Boeing 777", mockAircraft);

        // Test with empty seat type
        String[] result = planeConfiguration.getPlaneConfiguration("Boeing 777", "");

        // Verify that the result is null for an empty seat type
        assertNull(result);
    }

    @Test
    public void testGetPlaneConfigurationWithNullAircraftName() {
        // Test with null aircraft name
        String[] result = planeConfiguration.getPlaneConfiguration(null, "bus");

        // Verify that the result is null for a null aircraft name
        assertNull(result);
    }

    @Test
    public void testGetPlaneConfigurationWithNullSeatType() {
        // Mock data
        iAircraft mockAircraft = new Aircraft("Boeing 777", 6, 6, 6, 19);
        Map<String, iAircraft> aircraftMap = new HashMap<>();
        aircraftMap.put("Boeing 777", mockAircraft);

        // Test with null seat type
        String[] result = planeConfiguration.getPlaneConfiguration("Boeing 777", null);

        // Verify that the result is null for a null seat type
        assertNull(result);
    }
}
