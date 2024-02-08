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
        addFlight("AC749", "YVR", "YZZ", "02/11/2024 07:34", "02/11/2024 13:02", "Airbus A320", "Gate20", "Gate13", 140, 40);
        addFlight("AC394", "YEG", "YYZ", "02/16/2024 20:26", "02/16/2024 23:07", "Airbus A380", "Gate20", "Gate4", 260, 29);
        addFlight("AC647", "YVR", "YZZ", "02/27/2024 13:50", "02/27/2024 18:32", "Boeing 737", "Gate13", "Gate16", 134, 13);
        addFlight("AC595", "YUL", "YVR", "03/01/2024 08:47", "03/01/2024 12:13", "Boeing 747", "Gate4", "Gate16", 295, 50);
        addFlight("AC119", "YOW", "YYC", "02/29/2024 05:57", "02/29/2024 07:04", "Airbus A380", "Gate3", "Gate7", 266, 37);
        addFlight("AC373", "YYZ", "YZZ", "03/01/2024 14:23", "03/01/2024 15:27", "Airbus A330", "Gate16", "Gate7", 178, 44);
        addFlight("AC652", "YEG", "YYZ", "02/13/2024 04:54", "02/13/2024 06:49", "Embraer E190", "Gate5", "Gate16", 176, 43);
        addFlight("AC745", "YUL", "YVR", "02/16/2024 08:11", "02/16/2024 12:57", "Boeing 737", "Gate16", "Gate13", 276, 31);
        addFlight("AC378", "YHM", "YWG", "02/09/2024 17:35", "02/09/2024 22:33", "Boeing 747", "Gate4", "Gate9", 124, 29);
        addFlight("AC733", "YHM", "YOW", "02/27/2024 06:43", "02/27/2024 07:59", "Embraer E190", "Gate14", "Gate13", 135, 29);
        addFlight("AC882", "YOW", "YHM", "02/15/2024 20:15", "02/16/2024 00:03", "Airbus A380", "Gate9", "Gate4", 238, 28);
        addFlight("AC985", "YWG", "YVR", "02/15/2024 01:49", "02/15/2024 03:17", "Embraer E190", "Gate3", "Gate15", 263, 26);
        addFlight("AC246", "YWG", "YEG", "02/20/2024 05:38", "02/20/2024 10:28", "Boeing 737", "Gate1", "Gate8", 175, 13);
        addFlight("AC118", "YUL", "YZZ", "02/15/2024 06:17", "02/15/2024 11:26", "Airbus A380", "Gate18", "Gate11", 144, 43);
        addFlight("AC819", "YYC", "YEG", "02/12/2024 09:26", "02/12/2024 14:40", "Boeing 737", "Gate2", "Gate10", 128, 24);
        addFlight("AC216", "YVR", "YYC", "02/16/2024 16:29", "02/16/2024 20:13", "Embraer E190", "Gate4", "Gate10", 90, 38);
        addFlight("AC431", "YUL", "YYC", "02/08/2024 23:26", "02/09/2024 00:46", "Boeing 747", "Gate14", "Gate15", 168, 18);
        addFlight("AC926", "YEG", "YZZ", "03/06/2024 11:56", "03/06/2024 13:58", "Embraer E190", "Gate17", "Gate10", 237, 47);
        addFlight("AC787", "YYC", "YUL", "03/02/2024 03:27", "03/02/2024 07:14", "Boeing 747", "Gate18", "Gate11", 105, 37);
        addFlight("AC714", "YYC", "YUL", "03/01/2024 01:31", "03/01/2024 04:58", "Boeing 777", "Gate9", "Gate9", 216, 37);
        addFlight("AC748", "YYZ", "YYC", "02/24/2024 00:22", "02/24/2024 03:46", "Boeing 747", "Gate7", "Gate1", 236, 49);
        addFlight("AC989", "YYZ", "YZZ", "02/27/2024 11:41", "02/27/2024 17:23", "Airbus A330", "Gate5", "Gate9", 159, 48);
        addFlight("AC377", "YEG", "YYZ", "02/28/2024 07:33", "02/28/2024 10:49", "Boeing 747", "Gate4", "Gate18", 143, 28);
        addFlight("AC936", "YYZ", "YOW", "03/01/2024 04:37", "03/01/2024 06:07", "Boeing 777", "Gate20", "Gate16", 85, 25);
        addFlight("AC864", "YHM", "YYZ", "02/15/2024 18:12", "02/15/2024 19:49", "Boeing 777", "Gate14", "Gate14", 201, 35);
        addFlight("AC989", "YWG", "YOW", "03/01/2024 20:11", "03/01/2024 23:25", "Boeing 777", "Gate5", "Gate4", 241, 17);
        addFlight("AC282", "YYZ", "YHM", "03/05/2024 06:39", "03/05/2024 11:22", "Airbus A380", "Gate8", "Gate1", 70, 30);
        addFlight("AC476", "YEG", "YYZ", "02/19/2024 10:41", "02/19/2024 15:36", "Boeing 737", "Gate8", "Gate8", 60, 18);
        addFlight("AC603", "YVR", "YWG", "02/18/2024 15:56", "02/18/2024 18:29", "Boeing 777", "Gate9", "Gate18", 141, 49);
        addFlight("AC166", "YEG", "YZZ", "02/22/2024 21:08", "02/22/2024 22:22", "Airbus A330", "Gate2", "Gate19", 139, 11);
        addFlight("AC162", "YOW", "YVR", "02/16/2024 17:17", "02/16/2024 21:06", "Boeing 737", "Gate10", "Gate15", 283, 33);
        addFlight("AC469", "YWG", "YYC", "02/26/2024 01:14", "02/26/2024 03:21", "Embraer E190", "Gate9", "Gate15", 193, 40);
        addFlight("AC430", "YUL", "YHM", "03/05/2024 14:07", "03/05/2024 20:04", "Boeing 737", "Gate20", "Gate10", 284, 31);
        addFlight("AC393", "YOW", "YEG", "03/04/2024 08:21", "03/04/2024 13:47", "Airbus A380", "Gate9", "Gate18", 181, 45);
        addFlight("AC193", "YYC", "YVR", "02/29/2024 13:35", "02/29/2024 14:51", "Boeing 777", "Gate17", "Gate11", 116, 31);
        addFlight("AC106", "YZZ", "YUL", "02/21/2024 15:37", "02/21/2024 18:53", "Boeing 737", "Gate9", "Gate3", 73, 47);
        addFlight("AC303", "YUL", "YWG", "03/01/2024 23:46", "03/02/2024 02:34", "Airbus A320", "Gate17", "Gate17", 97, 40);
        addFlight("AC630", "YEG", "YYC", "02/11/2024 23:02", "02/12/2024 02:49", "Airbus A320", "Gate1", "Gate16", 57, 42);
        addFlight("AC988", "YOW", "YEG", "02/29/2024 17:54", "02/29/2024 23:33", "Boeing 747", "Gate20", "Gate2", 216, 50);
        addFlight("AC771", "YZZ", "YEG", "03/05/2024 05:08", "03/05/2024 10:25", "Boeing 737", "Gate5", "Gate20", 184, 44);
        addFlight("AC594", "YYC", "YOW", "02/11/2024 07:17", "02/11/2024 09:02", "Airbus A380", "Gate9", "Gate13", 167, 14);
        addFlight("AC248", "YOW", "YVR", "02/29/2024 18:14", "02/29/2024 19:24", "Airbus A380", "Gate19", "Gate13", 93, 36);
        addFlight("AC512", "YHM", "YVR", "02/15/2024 16:34", "02/15/2024 22:07", "Boeing 747", "Gate4", "Gate17", 97, 13);
        addFlight("AC945", "YZZ", "YUL", "02/15/2024 15:48", "02/15/2024 20:07", "Boeing 777", "Gate16", "Gate5", 171, 37);
        addFlight("AC387", "YYZ", "YZZ", "02/21/2024 01:54", "02/21/2024 07:44", "Boeing 777", "Gate4", "Gate2", 62, 21);
        addFlight("AC106", "YYZ", "YEG", "03/02/2024 19:55", "03/02/2024 23:08", "Airbus A320", "Gate7", "Gate1", 198, 17);
        addFlight("AC251", "YUL", "YEG", "02/09/2024 21:53", "02/10/2024 01:24", "Boeing 747", "Gate5", "Gate6", 279, 35);
        addFlight("AC376", "YOW", "YUL", "03/05/2024 20:34", "03/05/2024 22:59", "Boeing 777", "Gate7", "Gate9", 192, 43);
        addFlight("AC434", "YUL", "YOW", "03/07/2024 13:05", "03/07/2024 17:52", "Boeing 747", "Gate8", "Gate10", 52, 48);
        addFlight("AC935", "YZZ", "YOW", "03/02/2024 14:04", "03/02/2024 16:54", "Boeing 777", "Gate13", "Gate8", 250, 19);
        addFlight("AC154", "YWG", "YHM", "02/14/2024 03:35", "02/14/2024 08:23", "Boeing 737", "Gate3", "Gate20", 214, 10);
        addFlight("AC416", "YHM", "YVR", "02/19/2024 04:00", "02/19/2024 07:36", "Boeing 777", "Gate13", "Gate1", 167, 24);
        addFlight("AC114", "YVR", "YYC", "02/16/2024 17:06", "02/16/2024 21:32", "Airbus A330", "Gate12", "Gate15", 224, 34);
        addFlight("AC462", "YOW", "YEG", "03/02/2024 12:40", "03/02/2024 15:27", "Boeing 737", "Gate12", "Gate18", 92, 25);
        addFlight("AC306", "YYZ", "YYC", "02/29/2024 20:24", "02/29/2024 23:57", "Embraer E190", "Gate1", "Gate4", 241, 47);
        addFlight("AC336", "YYZ", "YHM", "02/11/2024 01:48", "02/11/2024 04:17", "Airbus A330", "Gate13", "Gate3", 100, 37);
        addFlight("AC222", "YWG", "YUL", "03/05/2024 14:46", "03/05/2024 18:36", "Boeing 777", "Gate9", "Gate2", 283, 11);
        addFlight("AC955", "YHM", "YEG", "02/20/2024 06:48", "02/20/2024 08:35", "Boeing 747", "Gate8", "Gate12", 82, 22);
        addFlight("AC521", "YYC", "YUL", "02/24/2024 20:33", "02/24/2024 23:19", "Airbus A330", "Gate16", "Gate19", 180, 24);
        addFlight("AC995", "YYC", "YYZ", "02/24/2024 01:24", "02/24/2024 04:39", "Boeing 777", "Gate3", "Gate11", 259, 27);
        addFlight("AC298", "YVR", "YEG", "03/07/2024 08:07", "03/07/2024 11:43", "Boeing 737", "Gate2", "Gate16", 160, 39);
        addFlight("AC977", "YVR", "YZZ", "03/03/2024 19:39", "03/03/2024 21:41", "Airbus A320", "Gate9", "Gate7", 203, 46);
        addFlight("AC978", "YYZ", "YUL", "03/04/2024 07:39", "03/04/2024 09:13", "Boeing 737", "Gate9", "Gate9", 79, 46);
        addFlight("AC842", "YWG", "YUL", "02/20/2024 17:54", "02/20/2024 20:32", "Boeing 747", "Gate18", "Gate11", 120, 21);
        addFlight("AC803", "YYC", "YYZ", "02/23/2024 10:54", "02/23/2024 16:33", "Boeing 777", "Gate7", "Gate15", 153, 48);
        addFlight("AC985", "YYZ", "YHM", "02/24/2024 23:58", "02/25/2024 03:27", "Embraer E190", "Gate2", "Gate6", 246, 27);
        addFlight("AC236", "YVR", "YYC", "03/08/2024 22:59", "03/09/2024 02:21", "Boeing 737", "Gate14", "Gate19", 295, 29);
        addFlight("AC302", "YHM", "YYZ", "03/02/2024 05:15", "03/02/2024 07:46", "Embraer E190", "Gate3", "Gate7", 247, 47);
        addFlight("AC368", "YWG", "YOW", "02/26/2024 15:28", "02/26/2024 20:28", "Airbus A330", "Gate11", "Gate9", 70, 33);
        addFlight("AC446", "YZZ", "YEG", "02/19/2024 12:56", "02/19/2024 15:52", "Boeing 777", "Gate18", "Gate7", 298, 14);
        addFlight("AC200", "YWG", "YOW", "03/01/2024 20:37", "03/01/2024 22:54", "Boeing 737", "Gate11", "Gate7", 164, 25);
        addFlight("AC244", "YHM", "YEG", "03/03/2024 00:10", "03/03/2024 03:48", "Boeing 747", "Gate18", "Gate15", 250, 10);
        addFlight("AC960", "YOW", "YUL", "02/17/2024 03:16", "02/17/2024 07:07", "Airbus A330", "Gate2", "Gate14", 195, 33);
        addFlight("AC552", "YOW", "YEG", "02/15/2024 00:59", "02/15/2024 03:34", "Embraer E190", "Gate17", "Gate9", 223, 23);
        addFlight("AC206", "YUL", "YYC", "02/22/2024 23:34", "02/23/2024 04:03", "Boeing 747", "Gate4", "Gate15", 205, 12);
        addFlight("AC813", "YVR", "YHM", "03/06/2024 07:17", "03/06/2024 09:24", "Embraer E190", "Gate10", "Gate20", 184, 45);
        addFlight("AC269", "YVR", "YUL", "03/05/2024 04:49", "03/05/2024 08:53", "Boeing 747", "Gate9", "Gate5", 203, 26);
        addFlight("AC201", "YOW", "YHM", "03/02/2024 06:36", "03/02/2024 12:22", "Boeing 737", "Gate5", "Gate8", 259, 46);
        addFlight("AC650", "YZZ", "YOW", "02/26/2024 02:15", "02/26/2024 08:11", "Airbus A380", "Gate2", "Gate10", 270, 16);
        addFlight("AC752", "YHM", "YYC", "02/14/2024 03:47", "02/14/2024 06:26", "Airbus A330", "Gate11", "Gate1", 127, 30);
        addFlight("AC169", "YVR", "YZZ", "03/01/2024 16:43", "03/01/2024 20:35", "Airbus A320", "Gate1", "Gate10", 201, 32);
        addFlight("AC626", "YHM", "YYZ", "02/13/2024 02:47", "02/13/2024 07:59", "Boeing 737", "Gate7", "Gate14", 208, 43);
        addFlight("AC602", "YEG", "YOW", "02/21/2024 22:41", "02/22/2024 04:28", "Boeing 737", "Gate17", "Gate2", 163, 24);
        addFlight("AC544", "YYZ", "YEG", "02/13/2024 00:19", "02/13/2024 04:39", "Airbus A320", "Gate4", "Gate13", 115, 25);
        addFlight("AC341", "YVR", "YYZ", "02/14/2024 14:39", "02/14/2024 17:33", "Embraer E190", "Gate1", "Gate18", 197, 46);
        addFlight("AC178", "YZZ", "YWG", "02/18/2024 06:29", "02/18/2024 08:22", "Boeing 777", "Gate20", "Gate7", 113, 40);
        addFlight("AC836", "YYC", "YYZ", "02/16/2024 23:17", "02/17/2024 01:21", "Boeing 747", "Gate16", "Gate1", 93, 17);
        addFlight("AC910", "YWG", "YZZ", "03/01/2024 12:13", "03/01/2024 17:00", "Airbus A320", "Gate10", "Gate6", 73, 49);
        addFlight("AC746", "YYC", "YYZ", "03/06/2024 01:05", "03/06/2024 06:25", "Boeing 737", "Gate1", "Gate6", 122, 42);
        addFlight("AC269", "YWG", "YZZ", "02/13/2024 20:46", "02/13/2024 23:18", "Embraer E190", "Gate16", "Gate11", 71, 50);
        addFlight("AC271", "YEG", "YYC", "02/22/2024 21:36", "02/23/2024 00:18", "Airbus A380", "Gate7", "Gate19", 214, 30);
        addFlight("AC705", "YWG", "YYC", "03/02/2024 23:05", "03/03/2024 00:25", "Embraer E190", "Gate10", "Gate12", 183, 49);
        addFlight("AC462", "YUL", "YYC", "03/07/2024 09:51", "03/07/2024 12:57", "Boeing 737", "Gate6", "Gate17", 158, 14);
        addFlight("AC231", "YWG", "YYC", "02/22/2024 10:48", "02/22/2024 11:55", "Boeing 747", "Gate6", "Gate13", 58, 27);
        addFlight("AC354", "YZZ", "YWG", "03/03/2024 13:04", "03/03/2024 14:48", "Boeing 737", "Gate3", "Gate11", 98, 19);
        addFlight("AC714", "YWG", "YYC", "02/23/2024 17:46", "02/23/2024 22:25", "Embraer E190", "Gate5", "Gate17", 155, 33);
        addFlight("AC366", "YUL", "YHM", "03/08/2024 19:49", "03/08/2024 21:46", "Boeing 777", "Gate12", "Gate9", 282, 20);
        addFlight("AC124", "YYC", "YWG", "02/11/2024 19:46", "02/11/2024 23:25", "Boeing 737", "Gate2", "Gate6", 90, 14);
        addFlight("AC416", "YWG", "YYC", "02/09/2024 23:36", "02/10/2024 00:47", "Boeing 737", "Gate7", "Gate8", 50, 20);
        addFlight("AC436", "YWG", "YYZ", "02/25/2024 19:55", "02/25/2024 23:47", "Airbus A330", "Gate18", "Gate19", 275, 44);

    }



    public List<Flight> findFlight(String departure, String arrival, String dept_time) {
        List<Flight> results = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getDeparture_icao().equals(departure) && flight.getArrival_icao().equals(arrival) ) {  //&& flight.getFlight_dept_date_time().split(" ")[0].equals(dept_time)
                results.add(flight);
            }
        }
        return results;
    }


}
