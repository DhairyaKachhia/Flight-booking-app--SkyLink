package com.example.skylink;

import com.example.skylink.business.Implementations.PlaneConfiguration;
import com.example.skylink.objects.Implementations.Aircraft;
import com.example.skylink.persistence.Implementations.hsqldb.FlightStub;
import com.example.skylink.persistence.Interfaces.IFlightDB;
import com.example.skylink.objects.Interfaces.iAircraft;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

public class PlaneConfigurationTest {

    private IFlightDB mockFlightDB;

    private PlaneConfiguration planeConfiguration;

    @Before
    public void setUp() {
        mockFlightDB = new FlightStub();
        planeConfiguration = new PlaneConfiguration(mockFlightDB);
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
}
