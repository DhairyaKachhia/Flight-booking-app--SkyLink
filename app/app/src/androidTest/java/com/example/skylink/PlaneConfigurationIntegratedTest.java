package com.example.skylink;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import com.example.skylink.application.Services;
import com.example.skylink.business.Implementations.PlaneConfiguration;
import com.example.skylink.objects.Implementations.Flight;
import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.persistence.Interfaces.IFlightDB;
import org.junit.Before;
import org.junit.Test;

public class PlaneConfigurationIntegratedTest {
    private IFlightDB flightDatabase;
    private PlaneConfiguration planeConfiguration;

    @Before
    public void setUp() {
        flightDatabase = Services.getFlightDatabase();
        planeConfiguration = new PlaneConfiguration(flightDatabase);
    }

    @Test
    public void testGetPlaneConfigurationWithInvalidAircraftName() {
        // Test with invalid aircraft name
        String[] result = planeConfiguration.getPlaneConfiguration("Invalid Aircraft", "bus");

        // Verify that the result is null for an invalid aircraft name
        assertNull(result);
    }

    @Test
    public void testGetPlaneConfigurationWithInvalidSeatType() {
        // Test with invalid seat type
        String[] result = planeConfiguration.getPlaneConfiguration("Boeing 777", "invalidSeatType");

        // Verify that the result is null for an invalid seat type
        assertNull(result);
    }
}
