package com.example.skylink.persistence.Implementations.hsqldb;

import com.example.skylink.objects.Interfaces.iFlightInfo;
import com.example.skylink.objects.Interfaces.iPassengerData;
import com.example.skylink.persistence.Interfaces.iFlightBookingDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class FlightBookingHSQLDB implements iFlightBookingDB {
    private final String dbPath;
    private final String CREATE_TABLE = "CREATE MEMORY TABLE PUBLIC.BOOKINGS ("
            + "id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, "
            + "flightID VARCHAR(10) NOT NULL, "
            + "userID INT NOT NULL, "
            + "direction VARCHAR(10) NOT NULL, "
            + "price INT NOT NULL, "
            + "paid BOOLEAN NOT NULL, "
            + "FOREIGN KEY (userID) REFERENCES PUBLIC.USER (id), "
            + "FOREIGN KEY (flightID) REFERENCES PUBLIC.FLIGHTS (flightNumber)"
            + ")";

    public FlightBookingHSQLDB(String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    public long addFlightBooking(long user_id, String bound, iFlightInfo flightInfo, int price) {
        String sql = "INSERT INTO PUBLIC.FLIGHTBOOKINGS (flightID, userID, direction, price, paid) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, flightInfo.getFlight().getFlightNumber());
            ps.setLong(2, user_id);
            ps.setString(3, bound);
            ps.setInt(4, price);
            ps.setBoolean(5, true);

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating flight booking failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating flight booking failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



        public iFlightBookingDB initialize() {
            try (Connection conn = connect();
                 Statement stmt = conn.createStatement()) {

                stmt.executeUpdate(CREATE_TABLE);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return this;
        }

    public iFlightBookingDB drop() {
        String sql = "DROP TABLE BOOKINGS";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

}
