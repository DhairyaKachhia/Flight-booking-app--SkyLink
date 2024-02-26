package com.example.skylink.persistence.hsqldb;

import com.example.skylink.objects.Flight;
import com.example.skylink.persistence.IFlightDatabase;
import com.example.skylink.persistence.IUser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UserHSQLDB implements IUser {

    private final String dbPath;

    private final String CREATE_TABLE = "CREATE TABLE USER("
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

    public UserHSQLDB(String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath, "SA", "");
    }

    @Override
    public UserHSQLDB initialize() {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {

            this.drop();
            stmt.executeUpdate(CREATE_TABLE);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public UserHSQLDB drop() {
        String sql = "DROP TABLE USER";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

}
