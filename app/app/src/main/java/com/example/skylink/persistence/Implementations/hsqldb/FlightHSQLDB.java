package com.example.skylink.persistence.Implementations.hsqldb;

import com.example.skylink.objects.Implementations.Flight;
import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.persistence.Interfaces.IFlightHSQLDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightHSQLDB implements IFlightHSQLDB {
    
    private final String dbPath;

    private final String CREATE_TABLE = "CREATE TABLE FLIGHTS("
            + "flightNumber VARCHAR(10) PRIMARY KEY, "
            + "departure_icao VARCHAR(4), "
            + "arrival_icao VARCHAR(4), "
            + "flight_dept_date_time VARCHAR(20), "
            + "flight_arr_date_time VARCHAR(20), "
            + "airCraft_Type VARCHAR(20), "
            + "departure_Gate VARCHAR(10), "
            + "arr_Gate VARCHAR(10), "
            + "econPrice INT, "
            + "busnPrice INT"
            + ")";

    private final String[] COLUMNS = {
            "flightNumber",
            "departure_icao",
            "arrival_icao",
            "flight_dept_date_time",
            "flight_arr_date_time",
            "airCraft_Type",
            "departure_Gate",
            "arr_Gate",
            "econPrice",
            "busnPrice"
    };

    public FlightHSQLDB(String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath, "SA", "");
    }

    public void addFlight(iFlight flight) {
        String sql = "INSERT INTO flights (flightNumber, departure_icao, arrival_icao, flight_dept_date_time, flight_arr_date_time, airCraft_Type, departure_Gate, arr_Gate, econPrice, busnPrice) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, flight.getFlightNumber());
            ps.setString(2, flight.getDeparture_icao());
            ps.setString(3, flight.getArrival_icao());
            ps.setString(4, flight.getFlight_dept_date_time());
            ps.setString(5, flight.getFlight_arr_date_time());
            ps.setString(6, flight.getAirCraft_Type());
            ps.setString(7, flight.getDepature_Gate());
            ps.setString(8, flight.getArr_Gate());
            ps.setInt(9, flight.getEconPrice());
            ps.setInt(10, flight.getBusnPrice());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();
        String sql = "SELECT * FROM flights";

        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                flights.add(new Flight(
                        rs.getString("flightNumber"),
                        rs.getString("departure_icao"),
                        rs.getString("arrival_icao"),
                        rs.getString("flight_dept_date_time"),
                        rs.getString("flight_arr_date_time"),
                        rs.getString("airCraft_Type"),
                        rs.getString("departure_Gate"),
                        rs.getString("arr_Gate"),
                        rs.getInt("econPrice"),
                        rs.getInt("busnPrice")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    @Override
    public FlightHSQLDB initialize() {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            this.drop();
            stmt.executeUpdate(CREATE_TABLE);

            addFlight(new Flight("AC697", "YYZ", "YUL", "18/02/2024 02:57", "18/02/2024 12:12", "Boeing 737", "Gate4", "Gate5", 34, 1569));
            addFlight(new Flight("AC333", "YYZ", "YYC", "18/02/2024 02:57", "18/02/2024 07:13", "Airbus A320", "Gate3", "Gate9", 79, 1105));
            addFlight(new Flight("AC180", "YYZ", "YOW", "18/02/2024 02:57", "18/02/2024 11:34", "Boeing 737", "Gate6", "Gate8", 30, 1299));
            addFlight(new Flight("AC724", "YYZ", "YEG", "18/02/2024 02:57", "18/02/2024 12:45", "Airbus A320", "Gate5", "Gate10", 103, 916));
            addFlight(new Flight("AC918", "YYZ", "YYZ", "18/02/2024 02:57", "18/02/2024 11:15", "Boeing 737", "Gate10", "Gate10", 64, 993));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public IFlightHSQLDB drop() {
        String sql = "DROP TABLE FLIGHTS";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

}
