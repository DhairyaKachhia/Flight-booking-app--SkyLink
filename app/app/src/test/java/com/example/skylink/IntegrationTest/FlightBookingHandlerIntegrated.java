package com.example.skylink.IntegrationTest;

import com.example.skylink.TestUtils.TestUtils;
import com.example.skylink.application.Services;
import com.example.skylink.business.Implementations.FlightBookingHandler;
import com.example.skylink.business.Implementations.UserHandler;
import com.example.skylink.business.Interface.IUserHandler;
import com.example.skylink.business.Interface.iFlightBookingHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.skylink.TestUtils.TestUtils;
import com.example.skylink.objects.Implementations.Flight;
import com.example.skylink.objects.Implementations.FlightInfo;
import com.example.skylink.objects.Implementations.PassengerData;
import com.example.skylink.objects.Implementations.UserProperties;
import com.example.skylink.objects.Interfaces.IUserProperties;
import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.objects.Interfaces.iFlightInfo;
import com.example.skylink.objects.Interfaces.iPassengerData;
import com.example.skylink.presentation.Session;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class FlightBookingHandlerIntegrated {
    private iFlightBookingHandler bookingHandler;
    private File tempDB;
    @Before
    public void setUp() throws IOException {
        System.out.println("Starting integration test for Booking Handler");
        this.tempDB = TestUtils.copyDB();
        this.bookingHandler = new FlightBookingHandler(true);
        assertNotNull(this.bookingHandler);
    }
    @Test
    public void testGetBookingDetails() {
        // Create a user and add a booking
        String userFullname = "Hello World";
        String userEmail = "wow@w.com";
        String userPassword = "password";
        String userRePassword = "password";
        long sessionUserID = 0;

        IUserProperties user = new UserProperties(userFullname, userEmail, userPassword);
        IUserHandler userHandler = new UserHandler(Services.getUserDatabase());

        try {
            userHandler.createUser(user, userRePassword);
            sessionUserID = Session.getInstance().getUser_id();
        } catch (UserHandler.UserCreationException e) {
            fail("User creation should not fail");
        }

        HashMap<String, iFlightInfo> flightInfo = new HashMap<>();
        iFlightInfo outboundFlightInfo = new FlightInfo();
        iFlightInfo inboundFlightInfo = new FlightInfo();

        // Create sample flight data
        List<iFlight> outboundFlights = new ArrayList<>();
        iFlight outboundFlight = new Flight("FL001", "JFK", "LHR", "2024-03-15", "2024-03-16", "Boeing 747", "GateA", "GateB", 100, 200);
        outboundFlights.add(outboundFlight);

        List<iFlight> inboundFlights = new ArrayList<>();
        iFlight inboundFlight = new Flight("FL002", "LHR", "JFK", "2024-03-20", "2024-03-21", "Airbus A380", "GateX", "GateY", 150, 250);
        inboundFlights.add(inboundFlight);

        outboundFlightInfo.setFlight(outboundFlights);
        outboundFlightInfo.setEconOrBus("Economy");

        inboundFlightInfo.setFlight(inboundFlights);
        inboundFlightInfo.setEconOrBus("Business");

        // Add sample passenger data
        iPassengerData passengerData = new PassengerData("Mr", "John", "Doe", "123456789", "john.doe@example.com");
        HashMap<iPassengerData, String> seatSelected = new HashMap<>();
        seatSelected.put(passengerData, "A1");

        outboundFlightInfo.setSeatSelected(seatSelected);

        flightInfo.put("Outbound", outboundFlightInfo);
        flightInfo.put("Inbound", inboundFlightInfo);

        // Add the booking
        String bookingNumber = bookingHandler.addConfirmBooking(sessionUserID, flightInfo);
        assertNotNull(bookingNumber);
        assertEquals(5, bookingNumber.length());


        // Perform the test: Get the booking details
        List<HashMap<String, HashMap<String, iFlightInfo>>> bookingDetailsList = bookingHandler.getBookingDetails(sessionUserID);

        // Verify the result
        assertNotNull(bookingDetailsList);
        assertFalse(bookingDetailsList.isEmpty());

        // Assuming there is one booking in the list
        HashMap<String, HashMap<String, iFlightInfo>> bookingDetails = bookingDetailsList.get(0);
        assertNotNull(bookingDetails);

        // Verify the outbound details
        HashMap<String, iFlightInfo> outboundDetails = bookingDetails.get(bookingNumber);
        assertNotNull(outboundDetails);
        assertTrue(outboundDetails.containsKey("Outbound"));

        // Verify the inbound details
        assertTrue(outboundDetails.containsKey("Inbound"));
    }

    @Test
    public void testAddConfirmBooking() {
        // User creation
        String userFullname = "Hello World";
        String userEmail = "wow@w.com";
        String userPassword = "password";
        String userRePassword = "password";
        long sessionUserID = 0;

        IUserProperties user = new UserProperties(userFullname, userEmail, userPassword);
        IUserHandler handler = new UserHandler(Services.getUserDatabase());


        try {
            handler.createUser(user, userRePassword);
            sessionUserID = Session.getInstance().getUser_id();
        } catch (UserHandler.UserCreationException e) {
            fail("User creation should not fail");
        }

        // Flight booking
        HashMap<String, iFlightInfo> flightInfo = new HashMap<>();
        iFlightInfo outboundFlightInfo = new FlightInfo();
        iFlightInfo inboundFlightInfo = new FlightInfo();

        // Create sample flight data
        List<iFlight> outboundFlights = new ArrayList<>();
        iFlight outboundFlight = new Flight("FL001", "JFK", "LHR", "2024-03-15", "2024-03-16", "Boeing 747", "GateA", "GateB", 100, 200);
        outboundFlights.add(outboundFlight);

        List<iFlight> inboundFlights = new ArrayList<>();
        iFlight inboundFlight = new Flight("FL002", "LHR", "JFK", "2024-03-20", "2024-03-21", "Airbus A380", "GateX", "GateY", 150, 250);
        inboundFlights.add(inboundFlight);

        outboundFlightInfo.setFlight(outboundFlights);
        outboundFlightInfo.setEconOrBus("Economy");

        inboundFlightInfo.setFlight(inboundFlights);
        inboundFlightInfo.setEconOrBus("Business");

        // Add sample passenger data
        iPassengerData passengerData = new PassengerData("Mr", "John", "Doe", "123456789", "john.doe@example.com");
        HashMap<iPassengerData, String> seatSelected = new HashMap<>();
        seatSelected.put(passengerData, "A1");

        outboundFlightInfo.setSeatSelected(seatSelected);


        flightInfo.put("Outbound", outboundFlightInfo);
        flightInfo.put("Inbound", inboundFlightInfo);

        // Perform the test
        String bookingNumber = bookingHandler.addConfirmBooking(sessionUserID, flightInfo);

        // Verify the result
        assertNotNull(bookingNumber);
        assertEquals(5, bookingNumber.length());
    }




    @After
    public void tearDown() {
        System.out.println("Reset database.");
        // reset DB
        this.tempDB.delete();

        // clear Services
        Services.clean();
    }

}
