package com.example.skylink.persistence.Implementations.stub;


import com.example.skylink.objects.Implementations.Aircraft;
import com.example.skylink.objects.Implementations.Flight;
import com.example.skylink.objects.Interfaces.iAircraft;
import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.persistence.Interfaces.IFlightDB;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightStub implements IFlightDB {
    private final List<iFlight> flights;
    private final Graph<String, DefaultWeightedEdge> airportGraph;
    private Map<String, iAircraft> aircraftMap =  new HashMap<>();


    public FlightStub() {
        this.airportGraph = new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class);
        this.flights = new ArrayList<>();
        addFlights();
        readConfig();
        addAircrafts();
    }

    public void addFlights() {
        // Existing flights
        addFlight("AC551", "YZZ", "YYC", "01/03/2024 12:51", "04/03/2024 12:51", "Boeing 777", "Gate2", "Gate1", 958, 1399);
        addFlight("AC904", "YZZ", "YUL", "02/03/2024 12:51", "07/03/2024 12:51", "Bombardier Q400", "Gate2", "Gate8", 1447, 1684);
        addFlight("AC744", "YOW", "YYC", "03/03/2024 12:51", "06/03/2024 12:51", "Airbus A320", "Gate7", "Gate8", 523, 846);
        addFlight("AC124", "YYC", "YOW", "02/03/2024 12:51", "05/03/2024 12:51", "Bombardier Q400", "Gate6", "Gate2", 980, 1434);
        addFlight("AC589", "YYZ", "YUL", "01/03/2024 12:51", "06/03/2024 12:51", "Bombardier Q400", "Gate5", "Gate2", 891, 1118);
        addFlight("AC115", "YUL", "YYZ", "02/03/2024 12:51", "05/03/2024 12:51", "Bombardier Q400", "Gate5", "Gate7", 1243, 1506);
        addFlight("AC893", "YHM", "YEG", "01/03/2024 12:51", "05/03/2024 12:51", "Boeing 737", "Gate5", "Gate4", 561, 996);

        // Add new flights
        addFlight("AC207", "YUL", "YZZ", "07/03/2024 11:31", "11/03/2024 11:31", "Boeing 777", "Gate4", "Gate2", 925, 1178);
        addFlight("AC153", "YYC", "YEG", "07/03/2024 11:31", "09/03/2024 11:31", "Airbus A320", "Gate8", "Gate7", 1306, 1593);
        addFlight("AC462", "YZZ", "YWG", "07/03/2024 11:31", "09/03/2024 11:31", "Boeing 777", "Gate2", "Gate3", 1057, 1339);
        addFlight("AC209", "YWG", "YYC", "05/03/2024 11:31", "09/03/2024 11:31", "Bombardier Q400", "Gate8", "Gate3", 839, 1083);
        addFlight("AC920", "YUL", "YYC", "07/03/2024 11:31", "09/03/2024 11:31", "Airbus A320", "Gate1", "Gate5", 1470, 1687);
        addFlight("AC957", "YVR", "YOW", "06/03/2024 11:31", "09/03/2024 11:31", "Boeing 737", "Gate1", "Gate5", 941, 1347);
        addFlight("AC301", "YZZ", "YVR", "07/03/2024 11:31", "10/03/2024 11:31", "Bombardier Q400", "Gate8", "Gate8", 924, 1241);
        addFlight("AC948", "YHM", "YVR", "06/03/2024 11:31", "08/03/2024 11:31", "Airbus A320", "Gate9", "Gate5", 738, 959);
        addFlight("AC640", "YHM", "YYC", "06/03/2024 11:31", "08/03/2024 11:31", "Boeing 777", "Gate2", "Gate4", 520, 729);
        addFlight("AC867", "YZZ", "YYZ", "06/03/2024 11:31", "10/03/2024 11:31", "Boeing 737", "Gate7", "Gate9", 692, 1021);
        addFlight("AC122", "YWG", "YUL", "06/03/2024 11:31", "10/03/2024 11:31", "Embraer E190", "Gate6", "Gate7", 1256, 1634);
        addFlight("AC344", "YOW", "YYZ", "05/03/2024 11:31", "10/03/2024 11:31", "Airbus A320", "Gate3", "Gate6", 1013, 1378);
        addFlight("AC502", "YYZ", "YEG", "07/03/2024 11:31", "11/03/2024 11:31", "Bombardier Q400", "Gate5", "Gate2", 1175, 1499);
        addFlight("AC906", "YEG", "YUL", "05/03/2024 11:31", "08/03/2024 11:31", "Embraer E190", "Gate4", "Gate10", 963, 1209);
        addFlight("AC442", "YVR", "YOW", "06/03/2024 11:31", "09/03/2024 11:31", "Airbus A320", "Gate2", "Gate8", 1215, 1478);
        addFlight("AC710", "YUL", "YOW", "06/03/2024 11:31", "09/03/2024 11:31", "Boeing 777", "Gate6", "Gate3", 1389, 1756);
        addFlight("AC823", "YEG", "YYZ", "05/03/2024 11:31", "09/03/2024 11:31", "Bombardier Q400", "Gate8", "Gate4", 1038, 1367);
        addFlight("AC570", "YYC", "YHM", "05/03/2024 11:31", "10/03/2024 11:31", "Embraer E190", "Gate3", "Gate7", 762, 1019);
        addFlight("AC670", "YOW", "YVR", "06/03/2024 11:31", "09/03/2024 11:31", "Bombardier Q400", "Gate4", "Gate2", 1246, 1568);
        addFlight("AC165", "YUL", "YVR", "07/03/2024 11:31", "11/03/2024 11:31", "Boeing 777", "Gate5", "Gate9", 1420, 1769);
        addFlight("AC480", "YYC", "YEG", "07/03/2024 11:31", "11/03/2024 11:31", "Airbus A320", "Gate3", "Gate8", 1075, 1432);
        addFlight("AC892", "YWG", "YHM", "07/03/2024 11:31", "10/03/2024 11:31", "Bombardier Q400", "Gate10", "Gate4", 931, 1276);
        addFlight("AC708", "YYZ", "YUL", "06/03/2024 11:31", "09/03/2024 11:31", "Boeing 737", "Gate1", "Gate6", 1429, 1794);
        addFlight("AC934", "YOW", "YEG", "07/03/2024 11:31", "09/03/2024 11:31", "Embraer E190", "Gate3", "Gate5", 1087, 1347);
        addFlight("AC772", "YHM", "YYZ", "06/03/2024 11:31", "10/03/2024 11:31", "Boeing 777", "Gate5", "Gate9", 1240, 1516);
        addFlight("AC629", "YVR", "YWG", "07/03/2024 11:31", "09/03/2024 11:31", "Airbus A320", "Gate9", "Gate3", 1053, 1376);
        addFlight("AC491", "YYC", "YOW", "05/03/2024 11:31", "09/03/2024 11:31", "Boeing 737", "Gate3", "Gate10", 883, 1204);
        addFlight("AC396", "YWG", "YUL", "05/03/2024 11:31", "09/03/2024 11:31", "Embraer E190", "Gate2", "Gate5", 965, 1301);
        addFlight("AC220", "YEG", "YYZ", "07/03/2024 11:31", "11/03/2024 11:31", "Boeing 737", "Gate6", "Gate8", 1147, 1486);
        addFlight("AC978", "YYZ", "YVR", "05/03/2024 11:31", "08/03/2024 11:31", "Airbus A320", "Gate10", "Gate7", 1255, 1582);
        addFlight("AC819", "YVR", "YHM", "07/03/2024 11:31", "10/03/2024 11:31", "Bombardier Q400", "Gate8", "Gate4", 1123, 1469);
        addFlight("AC570", "YUL", "YYZ", "05/03/2024 11:31", "09/03/2024 11:31", "Boeing 777", "Gate7", "Gate3", 973, 1296);
        addFlight("AC421", "YOW", "YEG", "06/03/2024 11:31", "10/03/2024 11:31", "Embraer E190", "Gate5", "Gate8", 832, 1167);
        addFlight("AC721", "YVR", "YWG", "05/03/2024 11:31", "08/03/2024 11:31", "Bombardier Q400", "Gate9", "Gate2", 1190, 1487);
        addFlight("AC903", "YYC", "YUL", "07/03/2024 11:31", "11/03/2024 11:31", "Airbus A320", "Gate6", "Gate9", 1346, 1692);
        addFlight("AC255", "YWG", "YZZ", "06/03/2024 11:31", "10/03/2024 11:31", "Embraer E190", "Gate7", "Gate9", 1138, 1562);
        addFlight("AC499", "YZZ", "YWG", "07/03/2024 11:31", "08/03/2024 11:31", "Bombardier Q400", "Gate1", "Gate1", 601, 801);
        addFlight("AC181", "YZZ", "YHM", "06/03/2024 11:31", "11/03/2024 11:31", "Bombardier Q400", "Gate2", "Gate10", 595, 970);
        addFlight("AC480", "YYC", "YYZ", "07/03/2024 11:31", "11/03/2024 11:31", "Boeing 737", "Gate8", "Gate8", 1117, 1498);
        addFlight("AC320", "YUL", "YEG", "06/03/2024 11:31", "11/03/2024 11:31", "Boeing 777", "Gate2", "Gate7", 508, 714);
        addFlight("AC759", "YOW", "YVR", "06/03/2024 11:31", "08/03/2024 11:31", "Embraer E190", "Gate10", "Gate6", 907, 1301);
        addFlight("AC264", "YEG", "YYC", "06/03/2024 11:31", "10/03/2024 11:31", "Bombardier Q400", "Gate7", "Gate3", 585, 983);
        addFlight("AC730", "YUL", "YUL", "07/03/2024 11:31", "09/03/2024 11:31", "Airbus A320", "Gate6", "Gate7", 1271, 1532);
        addFlight("AC611", "YOW", "YWG", "07/03/2024 11:31", "10/03/2024 11:31", "Boeing 737", "Gate1", "Gate5", 960, 1391);
        addFlight("AC770", "YOW", "YYC", "07/03/2024 11:31", "08/03/2024 11:31", "Bombardier Q400", "Gate5", "Gate3", 1313, 1657);
        addFlight("AC942", "YZZ", "YYZ", "07/03/2024 11:31", "08/03/2024 11:31", "Boeing 737", "Gate3", "Gate6", 1161, 1507);
        addFlight("AC943", "YUL", "YWG", "07/03/2024 11:31", "11/03/2024 11:31", "Bombardier Q400", "Gate3", "Gate7", 1378, 1623);
        addFlight("AC988", "YUL", "YHM", "05/03/2024 11:31", "11/03/2024 11:31", "Bombardier Q400", "Gate1", "Gate6", 860, 1109);
        addFlight("AC637", "YYZ", "YOW", "07/03/2024 11:31", "10/03/2024 11:31", "Bombardier Q400", "Gate9", "Gate7", 1425, 1704);
        addFlight("AC409", "YVR", "YOW", "06/03/2024 11:31", "09/03/2024 11:31", "Embraer E190", "Gate6", "Gate9", 1228, 1522);
        addFlight("AC571", "YZZ", "YVR", "06/03/2024 11:31", "08/03/2024 11:31", "Boeing 737", "Gate7", "Gate6", 1278, 1491);
        addFlight("AC832", "YYC", "YVR", "07/03/2024 11:31", "09/03/2024 11:31", "Embraer E190", "Gate9", "Gate3", 1484, 1698);
        addFlight("AC633", "YVR", "YOW", "05/03/2024 11:31", "08/03/2024 11:31", "Airbus A320", "Gate9", "Gate5", 1424, 1699);
    }

    @Override
    public void addFlightsPC(InputStream inputStream) {

    }


    @Override
    public List<iFlight> findFlight(String departure, String arrival, String dept_time) {
        List<iFlight> results = new ArrayList<>();
        for (iFlight flight : flights) {
            if (flight.getDeparture_icao().equals(departure) && flight.getArrival_icao().equals(arrival) && flight.getFlight_dept_date_time().split(" ")[0].equals(dept_time) ) {  //&& flight.getFlight_dept_date_time().split(" ")[0].equals(dept_time)
                results.add(flight);
            }
        }
        return results;
    }

    @Override
    public Graph<String, DefaultWeightedEdge> getAirportGraph() {
        return airportGraph;
    }

    private void addAircrafts() {
        addAircraft("Boeing 737", 5, 7, 7, 13);
        addAircraft("Airbus A320", 5, 6, 8, 15);
        addAircraft("Embraer E190", 5, 9, 7, 15);
        addAircraft("Boeing 777", 6, 6, 6, 19);
        addAircraft("Bombardier Q400", 4, 9, 7, 12);
    }

    private void addAircraft(String aircraftName, int numSeatPerRowBusiness, int numRowsBusiness,
                             int numSeatPerRowEcon, int numRowsEcon) {
        Aircraft aircraft = new Aircraft(aircraftName, numSeatPerRowBusiness, numRowsBusiness, numSeatPerRowEcon, numRowsEcon);
        aircraftMap.put(aircraftName, aircraft);
    }


    @Override
    public Map<String, iAircraft>  getAircraftMap() {
        return aircraftMap;
    }

    private void readConfig() {
        addAirport("YYZ");
        addAirport("YYC");
        addAirport("YUL");
        addAirport("YOW");
        addAirport("YEG");
        addAirport("YWG");
        addAirport("YVR");
        addAirport("YZZ");
        addAirport("YHM");

        addConnection("YYZ", "YYC", 1520);
        addConnection("YYZ", "YUL", 2645);
        addConnection("YYZ", "YOW", 153);
        addConnection("YYZ", "YEG", 179);
        addConnection("YYZ", "YWG", 1221);
        addConnection("YYZ", "YVR", 2133);
        addConnection("YYZ", "YZZ", 1539);
        addConnection("YYZ", "YHM", 4875);
        addConnection("YYC", "YUL", 1065);
        addConnection("YYC", "YOW", 854);
        addConnection("YYC", "YEG", 4579);
        addConnection("YYC", "YWG", 3444);
        addConnection("YYC", "YVR", 514);
        addConnection("YYC", "YZZ", 4939);
        addConnection("YYC", "YHM", 2206);
        addConnection("YUL", "YOW", 4183);
        addConnection("YUL", "YEG", 1110);
        addConnection("YUL", "YWG", 2057);
        addConnection("YUL", "YVR", 3480);
        addConnection("YUL", "YZZ", 3072);
        addConnection("YUL", "YHM", 2272);
        addConnection("YOW", "YEG", 3669);
        addConnection("YOW", "YWG", 90);
        addConnection("YOW", "YVR", 3503);
        addConnection("YOW", "YZZ", 2943);
        addConnection("YOW", "YHM", 1902);
        addConnection("YEG", "YWG", 4064);
        addConnection("YEG", "YVR", 1054);
        addConnection("YEG", "YZZ", 3622);
        addConnection("YEG", "YHM", 790);
        addConnection("YWG", "YVR", 1958);
        addConnection("YWG", "YZZ", 100);
        addConnection("YWG", "YHM", 1178);
        addConnection("YVR", "YZZ", 3025);
        addConnection("YVR", "YHM", 3485);
        addConnection("YZZ", "YHM", 2283);
        addConnection("YUL", "YYZ", 2301);
    }


    private void addFlight(String flightNumber, String departure_icao, String arrival_icao, String flight_dept_date_time, String flight_arr_date_time, String airCraft_Type, String depature_Gate, String arr_Gate, int econPrice, int busPrice) {
        flights.add(new Flight(flightNumber, departure_icao, arrival_icao, flight_dept_date_time, flight_arr_date_time, airCraft_Type, depature_Gate, arr_Gate, econPrice, busPrice));
    }

    // Helper Functions

    private void addAirport(String airport) {
        airportGraph.addVertex(airport);
    }

    private void addConnection(String source, String target, double weight) {
        DefaultWeightedEdge edge = airportGraph.addEdge(source, target);
        airportGraph.setEdgeWeight(edge, weight);
    }
}
