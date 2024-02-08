package com.example.skylink.business;
import com.example.skylink.objects.Flight;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;
import static java.util.List.*;
public class AirportPathTest {//Test all 4 methods in airport path.(For java 8 or older and Junit 4).
    @Test
    public void testFindAllPaths() {//Test find all paths, I used YYZ and YVR as an example, we can enter any
        // number in assertEquals,for this one we are supposed to have 2 paths from YYZ to YVR, if the result is 3, this test fails.
        AirportPath airportPath = new AirportPath();
        List<List<String>> paths = airportPath.findAllPaths("YYZ", "YVR");
        assertNotNull(paths);
        assertFalse(paths.isEmpty());
        assertEquals(2, paths.size());
    }
    @Test
    public void testCalculatePathDistance() {//Test calculate path distance, I used Arrays.aslist to make a path from YYZ-
        //-YUL-YVR, the distance between them is 3848, if we get 3848 as a result, then the test passes.
        AirportPath airportPath = new AirportPath();
        List<String> path = Arrays.asList("YYZ", "YUL", "YVR");
        double distance = airportPath.calculatePathDistance(path);
        assertEquals(3848, distance, 0.1);
    }
    @Test
    public void testPullFlight() {//Test to see if we can get flight info from the database.For now since we are
        //using hardcoded flight data, there is not much we can do for this test since it only test
        //if we are getting info from the database. But we can change it in a later iteration. I used array.aslist
        //to make a path and test the code.
        AirportPath airportPath = new AirportPath();
        List<List<String>> all_dept_flight = Arrays.asList(of("YYC", "YUL", "YVR"));
        List<List<List<Flight>>> proposed_flight_path = airportPath.pullFlight(all_dept_flight, "02/02/2024");
        assertNotNull(proposed_flight_path);
        assertFalse(proposed_flight_path.isEmpty());
    }
    @Test
    public void testFindFlights() {//Test searching for flights.Again we save this for later iteration. Only test itinerary for now.
        AirportPath airportPath = new AirportPath();
        HashMap<String, List<List<List<Flight>>>> itinerary = airportPath.findFlights("YOW", "YVR", "02/02/2024", "02/02/2024", true);
        assertNotNull(itinerary);
        assertFalse(itinerary.isEmpty());
    }
}
