//package com.example.skylink;
//
//import com.example.skylink.business.AirportPath;
//import com.example.skylink.business.PassengerDataManager;
//import com.example.skylink.objects.Flight;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//public class AllTests {
//
//    private PassengerDataManager passengerDataManager;
//
//    @Before
//    public void setUp() {
//        System.out.println("Starting tests for PassengerDataManager");
//        passengerDataManager = new PassengerDataManager();
//    }
//
//    @Test
//    public void testAddBooking() {
//        System.out.println("\nTesting findBooking in PassengerDataManager");
//
//        String title = "Mr";
//        String firstName = "Jeff";
//        String lastName = "Akan";
//        String telephoneNumber = "2045145629";
//        String emailAddress = "jeff.akan@example.com";
//
//        passengerDataManager.addBooking(title, firstName, lastName, telephoneNumber, emailAddress);
//
//        boolean retrievedBooking = passengerDataManager.findBooking(title, firstName, lastName, telephoneNumber, emailAddress);
//
//        assertTrue("The booking should not be null", retrievedBooking);
//    }
//
//    @Test
//    public void testFindAllPaths() {
//        AirportPath airportPath = new AirportPath();
//        List<List<String>> paths = airportPath.findAllPaths("YYZ", "YVR");
//        assertNotNull(paths);
//        assertFalse(paths.isEmpty());
//        assertEquals(32, paths.size());
//    }
//
//    @Test
//    public void testCalculatePathDistance() {
//        AirportPath airportPath = new AirportPath();
//        List<String> path = Arrays.asList("YYZ", "YUL", "YVR");
//        double distance = airportPath.calculatePathDistance(path);
//        assertEquals(6125, distance, 0.1);
//    }
//
//    @Test
//    public void testPullFlight() {
//        AirportPath airportPath = new AirportPath();
//        List<List<String>> allDeptFlight = Arrays.asList(
//                Arrays.asList("YYC", "YEG", "YOW"),
//                Arrays.asList("YVR","YYZ")
//        );
//        List<List<List<Flight>>> proposedFlightPath = airportPath.pullFlight(allDeptFlight, "10/02/2024 23:56");
//        assertNotNull(proposedFlightPath);
//    }
//
//    @Test
//    public void testFindFlights() {
//        AirportPath airportPath = new AirportPath();
//        HashMap<String, List<List<List<Flight>>>> itinerary = airportPath.findFlights("YOW", "YVR", "02/02/2024", "02/02/2024", true);
//        assertNotNull(itinerary);
//    }
//
//}
