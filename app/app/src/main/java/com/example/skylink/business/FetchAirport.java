package com.example.skylink.business;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.skylink.business.AirportPath;
import java.util.List;

public class FetchAirport {
    private SQLiteDatabase db;
    public FetchAirport(SQLiteDatabase db){
        this.db = db;
    }

    public String fetchDataAndDisplay() {
        StringBuilder answerBuilder = new StringBuilder();
        AirportPath paths = new AirportPath();
        String sourceAirport = "YYZ";
        String destinationAirport = "YUL";
        List<List<String>> allPaths = paths.findAllPaths(sourceAirport, destinationAirport);

        for (List<String> path : allPaths) {
            answerBuilder.append("Path: ").append(path);
            double distance = paths.calculatePathDistance(path);
            answerBuilder.append("Distance: ").append(distance).append(" km\n");

        }

        return answerBuilder.toString();
    }

}
