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
<<<<<<< HEAD
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
=======
        addFlight("AC128", "YYC", "YEG", "10/02/2024 23:56", "11/02/2024 02:19", "Embraer E190", "Gate4", "Gate1", 227, 26);
        addFlight("AC303", "YOW", "YUL", "28/02/2024 21:19", "28/02/2024 23:10", "Airbus A320", "Gate3", "Gate8", 255, 46);
        addFlight("AC942", "YWG", "YHM", "23/02/2024 04:14", "23/02/2024 09:26", "Boeing 747", "Gate12", "Gate9", 154, 44);
        addFlight("AC248", "YYC", "YEG", "24/02/2024 16:07", "24/02/2024 20:02", "Boeing 777", "Gate9", "Gate4", 235, 45);
        addFlight("AC540", "YEG", "YYC", "26/02/2024 02:47", "26/02/2024 03:50", "Airbus A380", "Gate12", "Gate9", 170, 26);
        addFlight("AC586", "YZZ", "YHM", "02/03/2024 11:39", "02/03/2024 13:08", "Airbus A320", "Gate10", "Gate10", 140, 35);
        addFlight("AC256", "YWG", "YHM", "21/02/2024 12:27", "21/02/2024 17:52", "Boeing 737", "Gate16", "Gate11", 242, 26);
        addFlight("AC822", "YHM", "YVR", "28/02/2024 09:57", "28/02/2024 11:49", "Boeing 777", "Gate19", "Gate8", 57, 17);
        addFlight("AC447", "YVR", "YYZ", "22/02/2024 22:21", "23/02/2024 01:44", "Boeing 737", "Gate16", "Gate4", 58, 31);
        addFlight("AC881", "YUL", "YEG", "16/02/2024 07:20", "16/02/2024 09:03", "Airbus A380", "Gate6", "Gate2", 221, 47);
        addFlight("AC792", "YUL", "YVR", "12/02/2024 10:57", "12/02/2024 12:38", "Airbus A320", "Gate8", "Gate6", 94, 13);

        addFlight("AC792", "YUL", "YVR", "12/02/2024 11:57", "12/02/2024 12:38", "Airbus A320", "Gate8", "Gate6", 194, 43);

        addFlight("AC792", "YUL", "YYZ", "12/02/2024 15:57", "12/02/2024 16:38", "Airbus A320", "Gate8", "Gate6", 204, 50);
        addFlight("AC792", "YYZ", "YVR", "12/02/2024 18:57", "12/02/2024 19:38", "Airbus A320", "Gate8", "Gate6", 104, 32);

        addFlight("AC792", "YUL", "YZZ", "20/02/2024 15:57", "12/02/2024 16:38", "Airbus A320", "Gate8", "Gate6", 204, 50);
        addFlight("AC792", "YZZ", "YHM", "20/02/2024 18:57", "12/02/2024 19:38", "Airbus A320", "Gate8", "Gate6", 104, 32);

        addFlight("AC792", "YUL", "YZZ", "20/02/2024 5:57", "12/02/2024 6:38", "Airbus A320", "Gate8", "Gate6", 104, 50);
        addFlight("AC792", "YZZ", "YHM", "20/02/2024 8:57", "12/02/2024 9:38", "Airbus A320", "Gate8", "Gate6", 104, 32);

        addFlight("AC792", "YUL", "YHM", "20/02/2024 12:57", "12/02/2024 13:38", "Airbus A320", "Gate8", "Gate6", 304, 70);
        addFlight("AC792", "YUL", "YHM", "20/02/2024 20:57", "12/02/2024 23:38", "Airbus A320", "Gate8", "Gate6", 304, 70);


        addFlight("AC792", "YHM", "YUL", "21/02/2024 4:50", "12/02/2024 5:40", "Airbus A320", "Gate8", "Gate6", 304, 70);

        addFlight("AC792", "YHM", "YZZ", "21/02/2024 5:57", "12/02/2024 6:38", "Airbus A320", "Gate8", "Gate6", 204, 50);
        addFlight("AC792", "YZZ", "YUL", "21/02/2024 8:57", "12/02/2024 9:38", "Airbus A320", "Gate8", "Gate6", 104, 32);

        addFlight("AC175", "YOW", "YWG", "14/02/2024 13:22", "14/02/2024 18:35", "Airbus A320", "Gate8", "Gate2", 123, 37);
        addFlight("AC445", "YZZ", "YYZ", "27/02/2024 05:11", "27/02/2024 10:14", "Boeing 747", "Gate3", "Gate16", 96, 18);
        addFlight("AC930", "YUL", "YWG", "28/02/2024 23:02", "29/02/2024 00:45", "Airbus A320", "Gate17", "Gate14", 62, 24);
        addFlight("AC981", "YZZ", "YOW", "17/02/2024 09:05", "17/02/2024 15:00", "Airbus A380", "Gate18", "Gate12", 54, 37);
        addFlight("AC977", "YEG", "YOW", "12/02/2024 23:54", "13/02/2024 04:13", "Boeing 777", "Gate11", "Gate17", 160, 17);
        addFlight("AC171", "YYC", "YEG", "08/03/2024 13:39", "08/03/2024 15:03", "Airbus A380", "Gate15", "Gate7", 85, 36);
        addFlight("AC367", "YUL", "YYZ", "03/03/2024 11:37", "03/03/2024 14:01", "Airbus A380", "Gate10", "Gate11", 228, 21);
        addFlight("AC904", "YYC", "YUL", "02/03/2024 09:25", "02/03/2024 14:53", "Airbus A380", "Gate3", "Gate4", 244, 19);
        addFlight("AC360", "YYZ", "YOW", "01/03/2024 10:44", "01/03/2024 11:52", "Boeing 737", "Gate10", "Gate6", 154, 41);
        addFlight("AC510", "YYC", "YUL", "05/03/2024 20:46", "06/03/2024 02:31", "Airbus A330", "Gate15", "Gate1", 60, 20);
        addFlight("AC580", "YZZ", "YHM", "12/02/2024 04:38", "12/02/2024 08:16", "Boeing 777", "Gate2", "Gate13", 136, 39);
        addFlight("AC635", "YHM", "YVR", "23/02/2024 04:11", "23/02/2024 09:55", "Boeing 747", "Gate18", "Gate1", 66, 15);
        addFlight("AC426", "YYC", "YYZ", "02/03/2024 02:07", "02/03/2024 06:12", "Airbus A330", "Gate13", "Gate11", 179, 28);
        addFlight("AC191", "YUL", "YZZ", "24/02/2024 20:11", "24/02/2024 21:57", "Airbus A320", "Gate19", "Gate10", 63, 29);
        addFlight("AC407", "YVR", "YUL", "07/03/2024 10:08", "07/03/2024 16:05", "Airbus A330", "Gate16", "Gate1", 150, 38);
        addFlight("AC731", "YVR", "YUL", "24/02/2024 23:07", "25/02/2024 04:23", "Airbus A380", "Gate17", "Gate14", 108, 32);
        addFlight("AC625", "YUL", "YHM", "06/03/2024 19:40", "07/03/2024 00:32", "Boeing 737", "Gate20", "Gate1", 276, 15);
        addFlight("AC417", "YYC", "YWG", "01/03/2024 16:06", "01/03/2024 18:38", "Boeing 747", "Gate5", "Gate9", 255, 15);
        addFlight("AC324", "YEG", "YWG", "29/02/2024 05:49", "29/02/2024 10:26", "Airbus A320", "Gate10", "Gate15", 227, 23);
        addFlight("AC724", "YEG", "YVR", "27/02/2024 12:38", "27/02/2024 17:30", "Boeing 747", "Gate19", "Gate6", 94, 10);
        addFlight("AC562", "YWG", "YOW", "23/02/2024 00:44", "23/02/2024 05:34", "Boeing 747", "Gate3", "Gate13", 100, 17);
        addFlight("AC974", "YOW", "YUL", "29/02/2024 17:35", "29/02/2024 22:05", "Boeing 737", "Gate19", "Gate2", 158, 47);
        addFlight("AC861", "YVR", "YZZ", "16/02/2024 16:52", "16/02/2024 21:51", "Airbus A320", "Gate9", "Gate8", 95, 17);
        addFlight("AC340", "YWG", "YYC", "25/02/2024 08:39", "25/02/2024 10:52", "Boeing 737", "Gate10", "Gate15", 142, 12);
        addFlight("AC102", "YEG", "YZZ", "07/03/2024 12:42", "07/03/2024 13:50", "Boeing 747", "Gate16", "Gate19", 291, 18);
        addFlight("AC766", "YHM", "YVR", "20/02/2024 15:48", "20/02/2024 20:04", "Embraer E190", "Gate10", "Gate3", 158, 37);
        addFlight("AC410", "YUL", "YEG", "11/02/2024 09:21", "11/02/2024 11:22", "Boeing 747", "Gate4", "Gate16", 210, 46);
        addFlight("AC314", "YVR", "YYC", "06/03/2024 12:44", "06/03/2024 17:05", "Airbus A380", "Gate11", "Gate19", 218, 44);
        addFlight("AC762", "YHM", "YWG", "16/02/2024 16:47", "16/02/2024 18:08", "Boeing 747", "Gate13", "Gate16", 135, 15);
        addFlight("AC499", "YVR", "YZZ", "10/02/2024 06:42", "10/02/2024 08:28", "Airbus A330", "Gate11", "Gate15", 153, 20);
        addFlight("AC216", "YOW", "YWG", "03/03/2024 08:40", "03/03/2024 10:05", "Airbus A320", "Gate11", "Gate17", 213, 34);
        addFlight("AC227", "YYC", "YUL", "15/02/2024 23:55", "16/02/2024 05:39", "Boeing 777", "Gate18", "Gate18", 68, 28);
        addFlight("AC934", "YZZ", "YVR", "25/02/2024 22:52", "26/02/2024 02:49", "Boeing 737", "Gate4", "Gate12", 91, 39);
        addFlight("AC878", "YYZ", "YZZ", "29/02/2024 22:36", "01/03/2024 00:56", "Airbus A380", "Gate4", "Gate15", 118, 14);
        addFlight("AC967", "YEG", "YVR", "25/02/2024 13:44", "25/02/2024 17:45", "Boeing 737", "Gate14", "Gate7", 94, 32);
        addFlight("AC669", "YYC", "YEG", "08/03/2024 18:51", "08/03/2024 23:14", "Airbus A330", "Gate14", "Gate5", 126, 49);
        addFlight("AC506", "YYZ", "YZZ", "22/02/2024 02:00", "22/02/2024 06:37", "Airbus A320", "Gate7", "Gate5", 51, 46);
        addFlight("AC676", "YYZ", "YOW", "08/03/2024 20:24", "08/03/2024 22:07", "Airbus A380", "Gate13", "Gate18", 142, 48);
        addFlight("AC483", "YYZ", "YWG", "08/03/2024 23:36", "09/03/2024 01:13", "Boeing 737", "Gate5", "Gate16", 122, 21);
        addFlight("AC677", "YUL", "YHM", "25/02/2024 08:41", "25/02/2024 13:23", "Airbus A330", "Gate6", "Gate20", 226, 19);
        addFlight("AC358", "YZZ", "YEG", "03/03/2024 21:09", "04/03/2024 00:30", "Boeing 737", "Gate5", "Gate3", 272, 49);
        addFlight("AC564", "YZZ", "YOW", "24/02/2024 23:36", "25/02/2024 03:20", "Airbus A380", "Gate11", "Gate7", 216, 49);
        addFlight("AC461", "YVR", "YYC", "27/02/2024 13:32", "27/02/2024 17:39", "Boeing 777", "Gate4", "Gate11", 179, 12);
        addFlight("AC816", "YWG", "YYC", "21/02/2024 21:43", "21/02/2024 22:43", "Airbus A380", "Gate3", "Gate9", 64, 50);
        addFlight("AC969", "YZZ", "YUL", "25/02/2024 02:36", "25/02/2024 07:01", "Boeing 777", "Gate20", "Gate14", 246, 23);
        addFlight("AC345", "YHM", "YVR", "02/03/2024 21:03", "02/03/2024 23:34", "Boeing 777", "Gate15", "Gate15", 234, 12);
        addFlight("AC337", "YYZ", "YZZ", "27/02/2024 20:40", "27/02/2024 22:10", "Embraer E190", "Gate19", "Gate19", 276, 36);
        addFlight("AC107", "YZZ", "YEG", "05/03/2024 05:27", "05/03/2024 10:31", "Airbus A380", "Gate17", "Gate16", 103, 21);
        addFlight("AC311", "YYC", "YEG", "21/02/2024 04:41", "21/02/2024 06:52", "Boeing 737", "Gate14", "Gate1", 240, 12);
        addFlight("AC393", "YYZ", "YUL", "20/02/2024 16:08", "20/02/2024 17:23", "Embraer E190", "Gate8", "Gate10", 71, 13);
        addFlight("AC857", "YHM", "YYZ", "21/02/2024 16:43", "21/02/2024 19:35", "Airbus A320", "Gate12", "Gate11", 238, 23);
        addFlight("AC380", "YZZ", "YHM", "16/02/2024 16:15", "16/02/2024 17:40", "Airbus A330", "Gate7", "Gate2", 57, 50);
        addFlight("AC879", "YYZ", "YYC", "23/02/2024 08:02", "23/02/2024 13:53", "Boeing 737", "Gate17", "Gate14", 272, 21);
        addFlight("AC649", "YWG", "YHM", "27/02/2024 12:44", "27/02/2024 16:15", "Boeing 747", "Gate14", "Gate20", 78, 45);
        addFlight("AC698", "YYC", "YVR", "28/02/2024 02:14", "28/02/2024 04:01", "Airbus A330", "Gate16", "Gate5", 284, 14);
        addFlight("AC137", "YEG", "YOW", "09/02/2024 16:52", "09/02/2024 18:26", "Airbus A380", "Gate6", "Gate9", 195, 14);
        addFlight("AC454", "YWG", "YHM", "28/02/2024 12:42", "28/02/2024 15:56", "Boeing 737", "Gate2", "Gate6", 176, 14);
        addFlight("AC973", "YEG", "YOW", "14/02/2024 23:07", "15/02/2024 04:58", "Boeing 777", "Gate8", "Gate2", 196, 17);
        addFlight("AC644", "YVR", "YZZ", "04/03/2024 04:36", "04/03/2024 07:54", "Embraer E190", "Gate18", "Gate9", 70, 42);
        addFlight("AC187", "YWG", "YOW", "05/03/2024 09:21", "05/03/2024 10:49", "Airbus A330", "Gate5", "Gate14", 207, 17);
        addFlight("AC210", "YEG", "YYZ", "09/03/2024 20:34", "09/03/2024 22:28", "Boeing 747", "Gate15", "Gate16", 280, 23);
        addFlight("AC417", "YEG", "YZZ", "01/03/2024 12:53", "01/03/2024 17:03", "Boeing 747", "Gate13", "Gate12", 274, 29);
        addFlight("AC413", "YHM", "YYC", "01/03/2024 05:05", "01/03/2024 09:57", "Airbus A380", "Gate12", "Gate9", 190, 17);
        addFlight("AC945", "YUL", "YWG", "02/03/2024 05:35", "02/03/2024 09:55", "Embraer E190", "Gate11", "Gate17", 242, 35);
        addFlight("AC741", "YZZ", "YYZ", "23/02/2024 11:05", "23/02/2024 12:34", "Airbus A330", "Gate16", "Gate12", 102, 18);
        addFlight("AC228", "YEG", "YZZ", "12/02/2024 13:09", "12/02/2024 17:55", "Boeing 737", "Gate5", "Gate5", 265, 48);
        addFlight("AC291", "YVR", "YWG", "23/02/2024 00:33", "23/02/2024 02:10", "Boeing 747", "Gate6", "Gate12", 292, 43);
        addFlight("AC549", "YYZ", "YVR", "16/02/2024 09:02", "16/02/2024 13:44", "Boeing 737", "Gate7", "Gate18", 171, 49);
        addFlight("AC381", "YVR", "YYZ", "16/02/2024 09:40", "16/02/2024 13:23", "Boeing 777", "Gate9", "Gate9", 250, 42);
        addFlight("AC714", "YVR", "YHM", "23/02/2024 20:42", "23/02/2024 23:03", "Boeing 737", "Gate11", "Gate13", 233, 45);
        addFlight("AC704", "YOW", "YVR", "25/02/2024 18:30", "25/02/2024 20:28", "Boeing 747", "Gate14", "Gate2", 137, 34);
        addFlight("AC384", "YHM", "YYZ", "15/02/2024 18:11", "15/02/2024 21:12", "Boeing 747", "Gate8", "Gate9", 127, 36);
        addFlight("AC768", "YOW", "YZZ", "04/03/2024 17:41", "04/03/2024 21:59", "Boeing 747", "Gate9", "Gate2", 260, 46);
        addFlight("AC653", "YYZ", "YOW", "16/02/2024 01:38", "16/02/2024 03:22", "Embraer E190", "Gate13", "Gate12", 67, 29);
        addFlight("AC802", "YYC", "YWG", "04/03/2024 12:39", "04/03/2024 17:42", "Boeing 747", "Gate17", "Gate7", 289, 14);
        addFlight("AC234", "YZZ", "YYZ", "18/02/2024 14:07", "18/02/2024 16:08", "Airbus A320", "Gate16", "Gate2", 228, 33);
        addFlight("AC409", "YYZ", "YZZ", "15/02/2024 21:24", "16/02/2024 00:09", "Boeing 777", "Gate11", "Gate13", 206, 13);
        addFlight("AC429", "YYC", "YOW", "22/02/2024 06:18", "22/02/2024 09:56", "Embraer E190", "Gate15", "Gate7", 96, 20);
        addFlight("AC641", "YZZ", "YHM", "24/02/2024 21:55", "25/02/2024 02:19", "Airbus A380", "Gate11", "Gate18", 261, 46);
        addFlight("AC200", "YOW", "YWG", "04/03/2024 23:34", "05/03/2024 04:57", "Boeing 777", "Gate16", "Gate16", 55, 27);
        addFlight("AC842", "YZZ", "YWG", "21/02/2024 12:43", "21/02/2024 16:02", "Airbus A330", "Gate16", "Gate18", 191, 31);
        addFlight("AC980", "YYZ", "YEG", "29/02/2024 17:34", "29/02/2024 18:41", "Airbus A380", "Gate9", "Gate8", 79, 44);
        addFlight("AC281", "YEG", "YOW", "15/02/2024 14:18", "15/02/2024 17:23", "Airbus A320", "Gate9", "Gate6", 233, 47);
        addFlight("AC151", "YWG", "YYZ", "09/02/2024 21:50", "10/02/2024 01:07", "Boeing 747", "Gate14", "Gate17", 62, 49);
        addFlight("AC598", "YZZ", "YOW", "08/03/2024 01:10", "08/03/2024 04:07", "Airbus A380", "Gate11", "Gate3", 101, 38);
        addFlight("AC771", "YWG", "YVR", "02/03/2024 00:35", "02/03/2024 02:51", "Boeing 777", "Gate7", "Gate2", 109, 13);
        addFlight("AC627", "YUL", "YWG", "12/02/2024 05:58", "12/02/2024 11:34", "Boeing 747", "Gate9", "Gate10", 276, 21);
        addFlight("AC668", "YOW", "YWG", "04/03/2024 19:00", "04/03/2024 22:39", "Airbus A320", "Gate9", "Gate3", 74, 22);
        addFlight("AC246", "YYZ", "YYC", "28/02/2024 10:27", "28/02/2024 15:10", "Embraer E190", "Gate9", "Gate13", 137, 30);
>>>>>>> origin/Improvement_User_info

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
