package com.example.skylink.application;

import android.app.Activity;
import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.skylink.persistence.Implementations.hsqldb.BookingHSQLDB;
import com.example.skylink.persistence.Implementations.hsqldb.FlightHSQLDB;
import com.example.skylink.persistence.Implementations.hsqldb.PaymentHSQLDB;
import com.example.skylink.persistence.Implementations.hsqldb.UserHSQLDB;
import com.example.skylink.persistence.Interfaces.IFlightDB;
import com.example.skylink.persistence.Interfaces.IPaymentDB;
import com.example.skylink.persistence.Interfaces.IUserDB;
import com.example.skylink.persistence.Interfaces.iBookingDB;

public class Services {

    private static IFlightDB flightDatabase = null;
    private static IUserDB userDatabase = null;
    private static iBookingDB bookDatabase = null;
    private static IPaymentDB paymentDatabase = null;

    public static void setup(Activity activity) {
        final String DB_PATH = "db";
        Context context = activity.getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        Main.setDBPathName(dataDirectory.getAbsolutePath());
    }

    public static void setupForIntegrationTest(String dbDirectory,String dbName) {
        // Use a directory on your laptop to store the HSQLDB files
               File dataDirectory = new File(dbDirectory);

        try {
            if (!dataDirectory.exists()) {
                if (!dataDirectory.mkdirs()) {
                    throw new IllegalStateException("Failed to create data directory: " + dataDirectory.getAbsolutePath());
                }
            }

            String dbPath = dataDirectory.getAbsolutePath() + "/" + dbName;
            Main.setDBPathName(dbPath);

            flightDatabase = new FlightHSQLDB(Main.getDBPathName()).initialize();
            userDatabase = new UserHSQLDB(Main.getDBPathName()).initialize();
            bookDatabase = new BookingHSQLDB(Main.getDBPathName()).initialize();
            paymentDatabase = new PaymentHSQLDB(Main.getDBPathName()).initialize();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to set up databases for integration test.", e);
        }
    }

    public static synchronized IFlightDB getFlightDatabase() {
        if (flightDatabase == null) {
            flightDatabase = new FlightHSQLDB(Main.getDBPathName()).initialize();
        }
        return flightDatabase;
    }

    public static synchronized IUserDB getUserDatabase() {
        if (userDatabase == null) {
            userDatabase = new UserHSQLDB(Main.getDBPathName()).initialize();
        }
        return userDatabase;
    }

    public static synchronized iBookingDB getBookDatabase() {
        if (bookDatabase == null) {
            bookDatabase = new BookingHSQLDB(Main.getDBPathName()).initialize();
        }
        return bookDatabase;
    }

    public static synchronized IPaymentDB getPaymentDatabase() {
        if (paymentDatabase == null) {
            paymentDatabase = new PaymentHSQLDB(Main.getDBPathName()).initialize();
        }
        return paymentDatabase;
    }
}
