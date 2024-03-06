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
        addFlight("AC785", "YVR", "YEG", "08/03/2024 18:47", "12/03/2024 18:47", "Boeing 737", "Gate3", "Gate8", 1269, 1546);
        addFlight("AC489", "YEG", "YVR", "06/03/2024 18:47", "12/03/2024 18:47", "Boeing 777", "Gate1", "Gate8", 646, 997);
        addFlight("AC266", "YYC", "YOW", "07/03/2024 18:47", "11/03/2024 18:47", "Embraer E190", "Gate8", "Gate2", 1466, 1793);
        addFlight("AC392", "YEG", "YHM", "08/03/2024 18:47", "10/03/2024 18:47", "Boeing 777", "Gate9", "Gate9", 894, 1304);
        addFlight("AC267", "YYZ", "YWG", "06/03/2024 18:47", "10/03/2024 18:47", "Boeing 777", "Gate10", "Gate6", 901, 1159);
        addFlight("AC199", "YOW", "YHM", "07/03/2024 18:47", "10/03/2024 18:47", "Boeing 777", "Gate4", "Gate7", 873, 1077);
        addFlight("AC795", "YVR", "YHM", "08/03/2024 18:47", "11/03/2024 18:47", "Embraer E190", "Gate1", "Gate2", 849, 1214);
        addFlight("AC245", "YYZ", "YHM", "06/03/2024 18:47", "12/03/2024 18:47", "Boeing 737", "Gate8", "Gate5", 1053, 1360);
        addFlight("AC172", "YWG", "YZZ", "07/03/2024 18:47", "09/03/2024 18:47", "Airbus A320", "Gate4", "Gate6", 842, 1237);
        addFlight("AC989", "YOW", "YHM", "06/03/2024 18:47", "09/03/2024 18:47", "Embraer E190", "Gate6", "Gate10", 1325, 1622);
        addFlight("AC449", "YVR", "YZZ", "08/03/2024 18:47", "09/03/2024 18:47", "Boeing 737", "Gate4", "Gate1", 524, 1008);
        addFlight("AC350", "YYZ", "YVR", "07/03/2024 18:47", "09/03/2024 18:47", "Embraer E190", "Gate7", "Gate5", 1240, 1673);
        addFlight("AC750", "YZZ", "YHM", "06/03/2024 18:47", "09/03/2024 18:47", "Bombardier Q400", "Gate4", "Gate8", 871, 1298);
        addFlight("AC944", "YYZ", "YOW", "06/03/2024 18:47", "11/03/2024 18:47", "Boeing 777", "Gate9", "Gate3", 873, 1168);
        addFlight("AC203", "YOW", "YHM", "08/03/2024 18:47", "12/03/2024 18:47", "Embraer E190", "Gate5", "Gate9", 836, 1297);
        addFlight("AC163", "YEG", "YHM", "07/03/2024 18:47", "09/03/2024 18:47", "Boeing 737", "Gate6", "Gate5", 1407, 1723);
        addFlight("AC943", "YEG", "YZZ", "08/03/2024 18:47", "09/03/2024 18:47", "Boeing 777", "Gate4", "Gate6", 702, 1152);
        addFlight("AC663", "YYC", "YHM", "07/03/2024 18:47", "09/03/2024 18:47", "Boeing 737", "Gate4", "Gate3", 1462, 1819);
        addFlight("AC366", "YOW", "YEG", "08/03/2024 18:47", "10/03/2024 18:47", "Boeing 777", "Gate7", "Gate10", 774, 1200);
        addFlight("AC264", "YUL", "YVR", "08/03/2024 18:47", "12/03/2024 18:47", "Boeing 737", "Gate1", "Gate6", 1471, 1828);
        addFlight("AC586", "YUL", "YEG", "08/03/2024 18:47", "12/03/2024 18:47", "Embraer E190", "Gate4", "Gate7", 1263, 1709);
        addFlight("AC920", "YYC", "YZZ", "06/03/2024 18:47", "09/03/2024 18:47", "Boeing 777", "Gate9", "Gate2", 585, 968);
        addFlight("AC439", "YUL", "YOW", "06/03/2024 18:47", "10/03/2024 18:47", "Embraer E190", "Gate2", "Gate7", 1006, 1376);
        addFlight("AC715", "YEG", "YVR", "08/03/2024 18:47", "10/03/2024 18:47", "Airbus A320", "Gate2", "Gate7", 938, 1178);
        addFlight("AC109", "YYC", "YUL", "07/03/2024 18:47", "10/03/2024 18:47", "Airbus A320", "Gate8", "Gate10", 602, 903);
        addFlight("AC846", "YUL", "YEG", "08/03/2024 18:47", "10/03/2024 18:47", "Boeing 777", "Gate2", "Gate7", 1363, 1604);
        addFlight("AC914", "YUL", "YVR", "06/03/2024 18:47", "11/03/2024 18:47", "Embraer E190", "Gate7", "Gate10", 853, 1146);
        addFlight("AC190", "YWG", "YHM", "06/03/2024 18:47", "12/03/2024 18:47", "Boeing 737", "Gate10", "Gate2", 820, 1312);
        addFlight("AC875", "YUL", "YHM", "07/03/2024 18:47", "12/03/2024 18:47", "Airbus A320", "Gate7", "Gate5", 1265, 1595);
        addFlight("AC929", "YUL", "YEG", "07/03/2024 18:47", "10/03/2024 18:47", "Airbus A320", "Gate9", "Gate5", 1144, 1468);
        addFlight("AC572", "YWG", "YZZ", "06/03/2024 18:47", "09/03/2024 18:47", "Airbus A320", "Gate5", "Gate2", 1393, 1610);
        addFlight("AC263", "YYC", "YUL", "08/03/2024 18:47", "12/03/2024 18:47", "Boeing 777", "Gate4", "Gate10", 1302, 1536);
        addFlight("AC975", "YEG", "YWG", "07/03/2024 18:47", "12/03/2024 18:47", "Airbus A320", "Gate9", "Gate8", 664, 1067);
        addFlight("AC239", "YUL", "YOW", "07/03/2024 18:47", "11/03/2024 18:47", "Embraer E190", "Gate5", "Gate4", 650, 1070);
        addFlight("AC335", "YYZ", "YYC", "06/03/2024 18:47", "09/03/2024 18:47", "Boeing 737", "Gate7", "Gate4", 855, 1285);
        addFlight("AC149", "YYC", "YWG", "07/03/2024 18:47", "10/03/2024 18:47", "Bombardier Q400", "Gate7", "Gate7", 995, 1212);
        addFlight("AC900", "YYC", "YUL", "08/03/2024 18:47", "09/03/2024 18:47", "Boeing 777", "Gate7", "Gate2", 1340, 1793);
        addFlight("AC755", "YUL", "YYZ", "06/03/2024 18:47", "11/03/2024 18:47", "Embraer E190", "Gate9", "Gate7", 602, 989);
        addFlight("AC456", "YWG", "YVR", "07/03/2024 18:47", "12/03/2024 18:47", "Airbus A320", "Gate3", "Gate4", 1318, 1657);
        addFlight("AC998", "YUL", "YOW", "07/03/2024 18:47", "12/03/2024 18:47", "Bombardier Q400", "Gate6", "Gate7", 1455, 1924);
        addFlight("AC862", "YWG", "YZZ", "08/03/2024 18:47", "11/03/2024 18:47", "Boeing 777", "Gate1", "Gate7", 1131, 1445);
        addFlight("AC146", "YOW", "YVR", "07/03/2024 18:47", "11/03/2024 18:47", "Embraer E190", "Gate3", "Gate9", 730, 1195);
        addFlight("AC418", "YYC", "YOW", "07/03/2024 18:47", "09/03/2024 18:47", "Airbus A320", "Gate3", "Gate8", 611, 1047);
        addFlight("AC977", "YEG", "YZZ", "08/03/2024 18:47", "10/03/2024 18:47", "Bombardier Q400", "Gate1", "Gate10", 766, 1231);
        addFlight("AC937", "YYC", "YHM", "07/03/2024 18:47", "11/03/2024 18:47", "Boeing 737", "Gate9", "Gate9", 1469, 1869);
        addFlight("AC737", "YOW", "YVR", "07/03/2024 18:47", "10/03/2024 18:47", "Boeing 777", "Gate9", "Gate5", 683, 975);
        addFlight("AC872", "YYZ", "YYC", "06/03/2024 18:47", "11/03/2024 18:47", "Airbus A320", "Gate2", "Gate2", 576, 899);
        addFlight("AC119", "YEG", "YVR", "07/03/2024 18:47", "10/03/2024 18:47", "Bombardier Q400", "Gate5", "Gate2", 623, 1075);
        addFlight("AC699", "YWG", "YHM", "08/03/2024 18:47", "12/03/2024 18:47", "Boeing 737", "Gate8", "Gate5", 1478, 1945);
        addFlight("AC665", "YYZ", "YZZ", "06/03/2024 18:47", "11/03/2024 18:47", "Airbus A320", "Gate9", "Gate10", 1204, 1516);
        addFlight("AC162", "YEG", "YHM", "06/03/2024 18:47", "10/03/2024 18:47", "Embraer E190", "Gate10", "Gate7", 1443, 1780);
        addFlight("AC385", "YWG", "YVR", "07/03/2024 18:47", "10/03/2024 18:47", "Airbus A320", "Gate7", "Gate10", 1079, 1474);
        addFlight("AC645", "YYZ", "YHM", "06/03/2024 18:47", "11/03/2024 18:47", "Boeing 737", "Gate7", "Gate2", 905, 1178);
        addFlight("AC297", "YUL", "YWG", "07/03/2024 18:47", "09/03/2024 18:47", "Bombardier Q400", "Gate2", "Gate7", 673, 1084);
        addFlight("AC261", "YYC", "YUL", "06/03/2024 18:47", "09/03/2024 18:47", "Embraer E190", "Gate9", "Gate3", 783, 1121);
        addFlight("AC320", "YOW", "YEG", "07/03/2024 18:47", "10/03/2024 18:47", "Embraer E190", "Gate2", "Gate4", 611, 1072);
        addFlight("AC403", "YZZ", "YHM", "08/03/2024 18:47", "09/03/2024 18:47", "Boeing 737", "Gate6", "Gate7", 533, 745);
        addFlight("AC831", "YYZ", "YYC", "06/03/2024 18:47", "09/03/2024 18:47", "Bombardier Q400", "Gate8", "Gate2", 752, 995);
        addFlight("AC620", "YYC", "YHM", "06/03/2024 18:47", "10/03/2024 18:47", "Embraer E190", "Gate2", "Gate1", 975, 1288);
        addFlight("AC788", "YUL", "YWG", "08/03/2024 18:47", "12/03/2024 18:47", "Boeing 737", "Gate1", "Gate8", 584, 1034);
        addFlight("AC229", "YYC", "YHM", "08/03/2024 18:47", "09/03/2024 18:47", "Boeing 777", "Gate4", "Gate1", 944, 1338);
        addFlight("AC130", "YYC", "YUL", "06/03/2024 18:47", "12/03/2024 18:47", "Bombardier Q400", "Gate1", "Gate2", 1111, 1384);
        addFlight("AC753", "YYZ", "YYC", "06/03/2024 18:47", "12/03/2024 18:47", "Airbus A320", "Gate7", "Gate4", 1124, 1617);
        addFlight("AC408", "YUL", "YVR", "07/03/2024 18:47", "11/03/2024 18:47", "Boeing 737", "Gate10", "Gate6", 539, 1034);
        addFlight("AC690", "YEG", "YWG", "07/03/2024 18:47", "11/03/2024 18:47", "Bombardier Q400", "Gate10", "Gate3", 805, 1029);
        addFlight("AC321", "YYZ", "YHM", "08/03/2024 18:47", "12/03/2024 18:47", "Bombardier Q400", "Gate2", "Gate7", 794, 1222);
        addFlight("AC363", "YUL", "YHM", "06/03/2024 18:47", "11/03/2024 18:47", "Boeing 777", "Gate6", "Gate8", 1318, 1731);
        addFlight("AC360", "YYZ", "YUL", "06/03/2024 18:47", "12/03/2024 18:47", "Boeing 777", "Gate3", "Gate5", 943, 1427);
        addFlight("AC891", "YYZ", "YEG", "07/03/2024 18:47", "10/03/2024 18:47", "Embraer E190", "Gate9", "Gate8", 511, 969);
        addFlight("AC849", "YOW", "YEG", "06/03/2024 18:47", "12/03/2024 18:47", "Boeing 737", "Gate6", "Gate4", 550, 805);
        addFlight("AC379", "YYC", "YHM", "08/03/2024 18:47", "09/03/2024 18:47", "Airbus A320", "Gate3", "Gate5", 539, 915);
        addFlight("AC679", "YYC", "YHM", "08/03/2024 18:47", "12/03/2024 18:47", "Boeing 777", "Gate3", "Gate6", 639, 952);
        addFlight("AC963", "YYC", "YVR", "06/03/2024 18:47", "10/03/2024 18:47", "Embraer E190", "Gate8", "Gate9", 601, 881);
        addFlight("AC538", "YYC", "YHM", "08/03/2024 18:47", "10/03/2024 18:47", "Boeing 737", "Gate4", "Gate1", 562, 763);
        addFlight("AC952", "YVR", "YHM", "08/03/2024 18:47", "12/03/2024 18:47", "Airbus A320", "Gate1", "Gate6", 1340, 1647);
        addFlight("AC138", "YYC", "YOW", "06/03/2024 18:47", "09/03/2024 18:47", "Boeing 737", "Gate3", "Gate10", 575, 991);
        addFlight("AC768", "YOW", "YWG", "08/03/2024 18:47", "10/03/2024 18:47", "Embraer E190", "Gate10", "Gate8", 1266, 1697);
        addFlight("AC981", "YOW", "YWG", "07/03/2024 18:47", "11/03/2024 18:47", "Boeing 777", "Gate1", "Gate5", 1295, 1714);
        addFlight("AC700", "YOW", "YEG", "07/03/2024 18:47", "11/03/2024 18:47", "Boeing 777", "Gate7", "Gate2", 1051, 1494);
        addFlight("AC517", "YYZ", "YVR", "06/03/2024 18:47", "12/03/2024 18:47", "Bombardier Q400", "Gate8", "Gate1", 1315, 1542);
        addFlight("AC967", "YYZ", "YVR", "08/03/2024 18:47", "12/03/2024 18:47", "Boeing 777", "Gate1", "Gate2", 696, 1088);
        addFlight("AC447", "YWG", "YZZ", "08/03/2024 18:47", "09/03/2024 18:47", "Bombardier Q400", "Gate2", "Gate10", 911, 1248);
        addFlight("AC338", "YYZ", "YOW", "08/03/2024 18:47", "12/03/2024 18:47", "Boeing 777", "Gate7", "Gate9", 671, 1050);
        addFlight("AC135", "YEG", "YHM", "06/03/2024 18:47", "12/03/2024 18:47", "Airbus A320", "Gate1", "Gate4", 960, 1445);
        addFlight("AC406", "YUL", "YOW", "06/03/2024 18:47", "11/03/2024 18:47", "Boeing 777", "Gate10", "Gate5", 877, 1228);
        addFlight("AC826", "YUL", "YHM", "08/03/2024 18:47", "12/03/2024 18:47", "Boeing 737", "Gate4", "Gate3", 688, 1151);
        addFlight("AC853", "YVR", "YZZ", "06/03/2024 18:47", "12/03/2024 18:47", "Embraer E190", "Gate4", "Gate6", 1341, 1612);
        addFlight("AC387", "YUL", "YOW", "08/03/2024 18:47", "11/03/2024 18:47", "Airbus A320", "Gate6", "Gate9", 642, 1132);
        addFlight("AC132", "YYZ", "YZZ", "08/03/2024 18:47", "09/03/2024 18:47", "Airbus A320", "Gate5", "Gate8", 697, 1065);
        addFlight("AC866", "YYZ", "YWG", "08/03/2024 18:47", "09/03/2024 18:47", "Boeing 777", "Gate2", "Gate6", 1008, 1211);
        addFlight("AC271", "YOW", "YHM", "06/03/2024 18:47", "12/03/2024 18:47", "Boeing 737", "Gate10", "Gate4", 914, 1330);
        addFlight("AC165", "YYZ", "YHM", "08/03/2024 18:47", "12/03/2024 18:47", "Boeing 777", "Gate7", "Gate5", 868, 1110);
        addFlight("AC609", "YVR", "YHM", "06/03/2024 18:47", "12/03/2024 18:47", "Embraer E190", "Gate4", "Gate3", 765, 1013);
        addFlight("AC182", "YYC", "YEG", "07/03/2024 18:47", "12/03/2024 18:47", "Embraer E190", "Gate1", "Gate1", 517, 786);
        addFlight("AC342", "YUL", "YEG", "07/03/2024 18:47", "11/03/2024 18:47", "Boeing 777", "Gate1", "Gate9", 620, 970);
        addFlight("AC455", "YYC", "YZZ", "07/03/2024 18:47", "09/03/2024 18:47", "Boeing 737", "Gate8", "Gate10", 812, 1290);
        addFlight("AC991", "YYC", "YWG", "06/03/2024 18:47", "11/03/2024 18:47", "Boeing 777", "Gate7", "Gate10", 810, 1230);
        addFlight("AC969", "YYC", "YVR", "08/03/2024 18:47", "11/03/2024 18:47", "Boeing 737", "Gate1", "Gate7", 1291, 1571);
        addFlight("AC781", "YYC", "YZZ", "08/03/2024 18:47", "12/03/2024 18:47", "Boeing 777", "Gate10", "Gate8", 1467, 1837);
        addFlight("AC915", "YYZ", "YOW", "08/03/2024 18:47", "09/03/2024 18:47", "Boeing 777", "Gate6", "Gate4", 1408, 1788);

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
