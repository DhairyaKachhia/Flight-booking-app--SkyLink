package com.example.skylink.data;

import com.example.skylink.objects.Flight;

import java.util.ArrayList;
import java.util.List;

public class FlightDatabase {
    private final List<Flight> flights;

    public FlightDatabase() {
        this.flights = new ArrayList<>();
        addFlights();
    }

    public void addFlight(String flightNumber, String departure_icao, String arrival_icao, String flight_dept_date_time, String flight_arr_date_time, String airCraft_Type, String depature_Gate, String arr_Gate, int econPrice, int busPrice) {
        flights.add(new Flight(flightNumber, departure_icao, arrival_icao, flight_dept_date_time, flight_arr_date_time, airCraft_Type, depature_Gate, arr_Gate,econPrice,busPrice));
    }

    public void addFlights() {
        addFlight("AC697", "YYZ", "YUL", "20/02/2024 02:57", "18/02/2024 12:12", "Boeing 737", "Gate4", "Gate5", 34, 1569);
        addFlight("AC333", "YYZ", "YYC", "18/02/2024 02:57", "18/02/2024 07:13", "Airbus A320", "Gate3", "Gate9", 79, 1105);
        addFlight("AC180", "YYZ", "YOW", "18/02/2024 02:57", "18/02/2024 11:34", "Boeing 737", "Gate6", "Gate8", 30, 1299);
        addFlight("AC724", "YYZ", "YEG", "18/02/2024 02:57", "18/02/2024 12:45", "Airbus A320", "Gate5", "Gate10", 103, 916);
        addFlight("AC918", "YYZ", "YYZ", "18/02/2024 02:57", "18/02/2024 11:15", "Boeing 737", "Gate10", "Gate10", 64, 993);
        addFlight("AC920", "YYZ", "YWG", "18/02/2024 02:57", "18/02/2024 04:26", "Boeing 737", "Gate2", "Gate10", 141, 1300);
        addFlight("AC343", "YYZ", "YVR", "18/02/2024 02:57", "18/02/2024 06:55", "Airbus A320", "Gate1", "Gate10", 36, 1592);
        addFlight("AC860", "YYZ", "YZZ", "18/02/2024 02:57", "18/02/2024 06:26", "Boeing 737", "Gate9", "Gate7", 117, 1483);
        addFlight("AC374", "YYZ", "YHM", "18/02/2024 02:57", "18/02/2024 07:50", "Boeing 737", "Gate1", "Gate6", 136, 1002);
        addFlight("AC539", "YUL", "YYZ", "18/02/2024 02:57", "18/02/2024 10:17", "Airbus A320", "Gate8", "Gate6", 68, 1420);
        addFlight("AC447", "YUL", "YOW", "18/02/2024 02:57", "18/02/2024 07:13", "Boeing 737", "Gate10", "Gate2", 130, 825);
        addFlight("AC279", "YUL", "YEG", "18/02/2024 02:57", "18/02/2024 12:02", "Boeing 737", "Gate10", "Gate6", 78, 1245);
        addFlight("AC511", "YUL", "YYZ", "18/02/2024 02:57", "18/02/2024 10:32", "Airbus A320", "Gate5", "Gate5", 28, 775);
        addFlight("AC291", "YUL", "YWG", "18/02/2024 02:57", "18/02/2024 07:37", "Airbus A320", "Gate10", "Gate6", 178, 1344);
        addFlight("AC847", "YUL", "YVR", "18/02/2024 02:57", "18/02/2024 10:18", "Boeing 737", "Gate3", "Gate4", 63, 1746);
        addFlight("AC417", "YUL", "YZZ", "18/02/2024 02:57", "18/02/2024 04:45", "Airbus A320", "Gate3", "Gate8", 72, 1233);
        addFlight("AC771", "YUL", "YHM", "18/02/2024 02:57", "18/02/2024 05:43", "Boeing 737", "Gate10", "Gate7", 42, 790);
        addFlight("AC482", "YYC", "YYZ", "18/02/2024 02:57", "18/02/2024 05:42", "Airbus A320", "Gate10", "Gate4", 105, 877);
        addFlight("AC562", "YYC", "YOW", "18/02/2024 02:57", "18/02/2024 07:35", "Airbus A320", "Gate8", "Gate6", 117, 531);
        addFlight("AC439", "YYC", "YYZ", "18/02/2024 02:57", "18/02/2024 12:13", "Airbus A320", "Gate1", "Gate8", 39, 1315);
        addFlight("AC123", "YYC", "YWG", "18/02/2024 02:57", "18/02/2024 07:33", "Airbus A320", "Gate7", "Gate2", 61, 827);
        addFlight("AC807", "YYC", "YVR", "18/02/2024 02:57", "18/02/2024 07:46", "Airbus A320", "Gate5", "Gate4", 162, 965);
        addFlight("AC645", "YYC", "YZZ", "18/02/2024 02:57", "18/02/2024 08:40", "Boeing 737", "Gate8", "Gate5", 124, 793);
        addFlight("AC154", "YOW", "YYZ", "18/02/2024 02:57", "18/02/2024 11:01", "Boeing 737", "Gate6", "Gate6", 21, 603);
        addFlight("AC133", "YOW", "YUL", "18/02/2024 02:57", "18/02/2024 05:06", "Airbus A320", "Gate5", "Gate3", 159, 625);
        addFlight("AC342", "YOW", "YYC", "18/02/2024 02:57", "18/02/2024 05:39", "Boeing 737", "Gate10", "Gate7", 103, 1760);
        addFlight("AC873", "YOW", "YEG", "18/02/2024 02:57", "18/02/2024 06:33", "Boeing 737", "Gate3", "Gate5", 175, 1055);
        addFlight("AC378", "YOW", "YWG", "18/02/2024 02:57", "18/02/2024 09:54", "Airbus A320", "Gate5", "Gate3", 84, 902);
        addFlight("AC484", "YOW", "YVR", "18/02/2024 02:57", "18/02/2024 08:41", "Airbus A320", "Gate4", "Gate4", 140, 554);
        addFlight("AC335", "YOW", "YZZ", "18/02/2024 02:57", "18/02/2024 11:16", "Airbus A320", "Gate10", "Gate5", 99, 1462);
        addFlight("AC652", "YOW", "YHM", "18/02/2024 02:57", "18/02/2024 04:13", "Boeing 737", "Gate3", "Gate6", 133, 772);
        addFlight("AC772", "YEG", "YYZ", "18/02/2024 02:57", "18/02/2024 06:16", "Airbus A320", "Gate4", "Gate4", 180, 1641);
        addFlight("AC906", "YEG", "YUL", "18/02/2024 02:57", "18/02/2024 11:33", "Boeing 737", "Gate4", "Gate9", 121, 768);
        addFlight("AC850", "YEG", "YOW", "18/02/2024 02:57", "18/02/2024 07:59", "Airbus A320", "Gate10", "Gate8", 21, 1500);
        addFlight("AC788", "YEG", "YYZ", "18/02/2024 02:57", "18/02/2024 08:37", "Boeing 737", "Gate3", "Gate9", 104, 1332);
        addFlight("AC330", "YEG", "YWG", "18/02/2024 02:57", "18/02/2024 06:02", "Airbus A320", "Gate3", "Gate4", 77, 1374);
        addFlight("AC629", "YEG", "YVR", "18/02/2024 02:57", "18/02/2024 06:51", "Boeing 737", "Gate7", "Gate10", 147, 1616);
        addFlight("AC576", "YEG", "YZZ", "18/02/2024 02:57", "18/02/2024 07:09", "Boeing 737", "Gate10", "Gate1", 173, 1389);
        addFlight("AC182", "YYZ", "YUL", "18/02/2024 02:57", "18/02/2024 09:26", "Airbus A320", "Gate2", "Gate4", 87, 1441);
        addFlight("AC682", "YYZ", "YYC", "18/02/2024 02:57", "18/02/2024 11:58", "Airbus A320", "Gate7", "Gate2", 59, 1729);
        addFlight("AC560", "YYZ", "YEG", "18/02/2024 02:57", "18/02/2024 05:08", "Boeing 737", "Gate8", "Gate1", 126, 1329);
        addFlight("AC174", "YYZ", "YWG", "18/02/2024 02:57", "18/02/2024 04:35", "Boeing 737", "Gate8", "Gate7", 106, 543);
        addFlight("AC592", "YYZ", "YVR", "18/02/2024 02:57", "18/02/2024 12:29", "Airbus A320", "Gate9", "Gate8", 84, 1765);
        addFlight("AC982", "YYZ", "YZZ", "18/02/2024 02:57", "18/02/2024 09:33", "Boeing 737", "Gate1", "Gate10", 119, 1117);
        addFlight("AC728", "YYZ", "YHM", "18/02/2024 02:57", "18/02/2024 06:42", "Boeing 737", "Gate6", "Gate8", 163, 663);
        addFlight("AC567", "YWG", "YYZ", "18/02/2024 02:57", "18/02/2024 05:14", "Boeing 737", "Gate4", "Gate7", 151, 1222);
        addFlight("AC520", "YWG", "YUL", "18/02/2024 02:57", "18/02/2024 04:46", "Boeing 737", "Gate7", "Gate9", 52, 1030);
        addFlight("AC649", "YWG", "YYC", "18/02/2024 02:57", "18/02/2024 11:06", "Airbus A320", "Gate2", "Gate1", 38, 774);
        addFlight("AC351", "YWG", "YOW", "18/02/2024 02:57", "18/02/2024 11:17", "Boeing 737", "Gate10", "Gate2", 36, 1523);
        addFlight("AC289", "YWG", "YVR", "18/02/2024 02:57", "18/02/2024 04:27", "Boeing 737", "Gate6", "Gate1", 122, 1384);
        addFlight("AC806", "YWG", "YZZ", "18/02/2024 02:57", "18/02/2024 08:14", "Airbus A320", "Gate7", "Gate9", 110, 1402);
        addFlight("AC967", "YWG", "YHM", "18/02/2024 02:57", "18/02/2024 04:56", "Airbus A320", "Gate2", "Gate4", 32, 1701);
        addFlight("AC274", "YVR", "YYZ", "18/02/2024 02:57", "18/02/2024 09:07", "Airbus A320", "Gate9", "Gate10", 131, 1280);
        addFlight("AC894", "YVR", "YUL", "18/02/2024 02:57", "18/02/2024 04:38", "Airbus A320", "Gate3", "Gate9", 127, 1627);
        addFlight("AC865", "YVR", "YYC", "18/02/2024 02:57", "18/02/2024 12:10", "Airbus A320", "Gate9", "Gate10", 133, 1299);
        addFlight("AC963", "YVR", "YOW", "18/02/2024 02:57", "18/02/2024 12:05", "Airbus A320", "Gate7", "Gate3", 112, 1750);
        addFlight("AC369", "YVR", "YWG", "18/02/2024 02:57", "18/02/2024 10:42", "Airbus A320", "Gate3", "Gate6", 29, 1216);
        addFlight("AC986", "YVR", "YZZ", "18/02/2024 02:57", "18/02/2024 05:04", "Airbus A320", "Gate9", "Gate3", 25, 1298);
        addFlight("AC528", "YZZ", "YYZ", "18/02/2024 02:57", "18/02/2024 12:07", "Boeing 737", "Gate4", "Gate2", 155, 1489);
        addFlight("AC371", "YZZ", "YUL", "18/02/2024 02:57", "18/02/2024 12:39", "Airbus A320", "Gate3", "Gate8", 144, 813);
        addFlight("AC289", "YZZ", "YYC", "18/02/2024 02:57", "18/02/2024 09:21", "Airbus A320", "Gate10", "Gate7", 135, 524);
        addFlight("AC646", "YZZ", "YOW", "18/02/2024 02:57", "18/02/2024 04:30", "Airbus A320", "Gate8", "Gate6", 96, 1242);
        addFlight("AC184", "YZZ", "YWG", "18/02/2024 02:57", "18/02/2024 05:20", "Boeing 737", "Gate2", "Gate5", 28, 504);
        addFlight("AC262", "YZZ", "YVR", "18/02/2024 02:57", "18/02/2024 07:26", "Airbus A320", "Gate8", "Gate7", 96, 598);
        addFlight("AC238", "YZZ", "YHM", "18/02/2024 02:57", "18/02/2024 10:09", "Airbus A320", "Gate5", "Gate3", 36, 1154);
        addFlight("AC243", "YHM", "YYZ", "18/02/2024 02:57", "18/02/2024 10:11", "Boeing 737", "Gate7", "Gate5", 84, 755);
        addFlight("AC553", "YHM", "YUL", "18/02/2024 02:57", "18/02/2024 08:11", "Boeing 737", "Gate4", "Gate6", 32, 1340);
        addFlight("AC971", "YHM", "YOW", "18/02/2024 02:57", "18/02/2024 12:05", "Boeing 737", "Gate6", "Gate5", 51, 902);
        addFlight("AC540", "YHM", "YYZ", "18/02/2024 02:57", "18/02/2024 11:56", "Boeing 737", "Gate3", "Gate9", 28, 761);
        addFlight("AC812", "YHM", "YWG", "18/02/2024 02:57", "18/02/2024 12:52", "Boeing 737", "Gate5", "Gate5", 148, 797);
        addFlight("AC650", "YHM", "YZZ", "18/02/2024 02:57", "18/02/2024 08:06", "Boeing 737", "Gate6", "Gate4", 54, 1411);

    }



    public List<Flight> findFlight(String departure, String arrival, String dept_time) {
        List<Flight> results = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getDeparture_icao().equals(departure) && flight.getArrival_icao().equals(arrival) && flight.getFlight_dept_date_time().split(" ")[0].equals(dept_time) ) {  //&& flight.getFlight_dept_date_time().split(" ")[0].equals(dept_time)
                results.add(flight);
            }
        }
        return results;
    }


}
