package com.example.skylink;


import static org.junit.Assert.assertNotNull;

import android.content.Context;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.skylink.application.Services;
import com.example.skylink.business.Implementations.AirportPath;
import com.example.skylink.business.Interface.iAirportPath;
import com.example.skylink.objects.Implementations.FlightSearch;
import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.objects.Interfaces.iFlightSearch;
import com.example.skylink.persistence.Implementations.hsqldb.FlightStub;
import com.example.skylink.persistence.Interfaces.IFlightDB;
import com.example.skylink.presentation.User_Auth.SignInActivity;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class AirportPathIntegratedTest {

    iAirportPath airportPath;
    @Rule
    public ActivityScenarioRule<SignInActivity> activityRule =
            new ActivityScenarioRule<>(SignInActivity.class);

    @Before
    public void setUp() {
        IFlightDB mockFlightDB = Services.getFlightDatabase();
        Graph<String, DefaultWeightedEdge> airportGraph  = mockFlightDB.getAirportGraph();
        airportPath = new AirportPath(mockFlightDB,airportGraph);
    }


    @Test
    public void testFindFlights_OneWay() {
        iFlightSearch flightSearch = new FlightSearch("YYC", "YOW", "02/03/2024", "04/03/2024",2,true);
        HashMap<String, List<List<List<iFlight>>>> itinerary = airportPath.findFlights(flightSearch);

        assertNotNull(itinerary);
//        assertTrue(itinerary.containsKey("Outbound"));
//        assertFalse(itinerary.containsKey("Inbound"));
//
//        List<List<List<iFlight>>> outboundFlights = itinerary.get("Outbound");
//        assertNotNull(outboundFlights);
//        assertFalse(outboundFlights.isEmpty());
    }

    @Test
    public void testFindFlights_Return() {
        iFlightSearch flightSearch = new FlightSearch("AirportA", "AirportB", "2024-03-02", "2024-03-02",2,true);
//        HashMap<String, List<List<List<iFlight>>>> itinerary = airportPath.findFlights(flightSearch);
//
//        assertNotNull(itinerary);
//        assertTrue(itinerary.containsKey("Outbound"));
//        assertTrue(itinerary.containsKey("Inbound"));
//
//        List<List<List<iFlight>>> outboundFlights = itinerary.get("Outbound");
//        List<List<List<iFlight>>> inboundFlights = itinerary.get("Inbound");
//
//        assertNotNull(outboundFlights);
//        assertFalse(outboundFlights.isEmpty());
//        assertNotNull(inboundFlights);
//        assertFalse(inboundFlights.isEmpty());
        // Add more assertions based on your expected behavior
    }

    @Test
    public void testFindFlights_NoPaths() {
        // Test when no paths are available between airports
        iFlightSearch flightSearch = new FlightSearch("NonExistentAirport1", "NonExistentAirport2", "2024-03-02", "2024-03-02",2,true);
//        HashMap<String, List<List<List<iFlight>>>> itinerary = airportPath.findFlights(flightSearch);
//
//        assertNotNull(itinerary);
//        assertTrue(itinerary.isEmpty());
    }

    @Test
    public void testFindFlights_EmptyPaths() {
        // Test when paths are available, but filtered out
        iFlightSearch flightSearch = new FlightSearch("AirportA", "AirportB", "2024-03-02", "2024-03-02",2,true);
//        HashMap<String, List<List<List<iFlight>>>> itinerary = airportPath.findFlights(flightSearch);
//
//        assertNotNull(itinerary);
//        assertTrue(itinerary.isEmpty());
    }

//    @Test
//    public void testSignIn() {
//        // Access the application context using InstrumentationRegistry
//        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//
//        // Alternatively, you can use ApplicationProvider for obtaining the context
//        // Context appContext = ApplicationProvider.getApplicationContext();
//
//        // Example of using the context (you can use it for other purposes in your test)
//        String appName = appContext.getString(R.string.app_name);
//        System.out.println("App Name: " + appName);
//
//        // Type email
//        Espresso.onView(ViewMatchers.withId(R.id.etEmail))
//                .perform(ViewActions.typeText("user@example.com"));
//
//        // Type password
//        Espresso.onView(ViewMatchers.withId(R.id.etPassword))
//                .perform(ViewActions.typeText("password"));
//
//        // Press the sign-in button
//        Espresso.onView(ViewMatchers.withId(R.id.btnSignIn))
//                .perform(ViewActions.click());
//
//        // Add any additional verifications/assertions based on your expected behavior
//    }
}
