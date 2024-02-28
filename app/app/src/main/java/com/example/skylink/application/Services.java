package com.example.skylink.application;

import android.app.Activity;
import android.content.Context;

import java.io.File;

import com.example.skylink.application.Main;
import com.example.skylink.persistence.Implementations.hsqldb.FlightHSQLDB;
import com.example.skylink.persistence.Implementations.hsqldb.UserHSQLDB;

public class Services {

    private static FlightHSQLDB flightDatabase = null;
    private static UserHSQLDB userDatabase = null;

    public static void setup(Activity activity) {
        final String DB_PATH = "db";
        Context context = activity.getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        Main.setDBPathName(dataDirectory.getAbsolutePath());
    }

    public static synchronized FlightHSQLDB getFlightDatabase() {
        if (flightDatabase == null) {
            flightDatabase = new FlightHSQLDB(Main.getDBPathName()).initialize();
        }
        return flightDatabase;
    }

    public static synchronized UserHSQLDB getUserDatabase() {
        if (userDatabase == null) {
            userDatabase = new UserHSQLDB(Main.getDBPathName()).initialize();
        }
        return userDatabase;
    }
}
