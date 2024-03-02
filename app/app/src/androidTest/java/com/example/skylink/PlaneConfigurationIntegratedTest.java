package com.example.skylink;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.skylink.application.Services;
import com.example.skylink.business.Implementations.PlaneConfiguration;
import com.example.skylink.objects.Implementations.Aircraft;
import com.example.skylink.objects.Interfaces.iAircraft;
import com.example.skylink.persistence.Implementations.hsqldb.FlightStub;
import com.example.skylink.persistence.Interfaces.IFlightDB;
import com.example.skylink.presentation.User_Auth.SignInActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class PlaneConfigurationIntegratedTest {
    private IFlightDB mockFlightDB;

    private PlaneConfiguration planeConfiguration;

    @Rule
    public ActivityScenarioRule<SignInActivity> activityRule =
            new ActivityScenarioRule<>(SignInActivity.class);

    @Before
    public void setUp() {
        mockFlightDB = Services.getFlightDatabase();
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
