package com.example.skylink;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private SQLiteDatabase profileDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);
        Button fetchDataButton = findViewById(R.id.fetchDataButton);

        fetchDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchDataAndDisplay();
            }
        });

        initializeSQLiteDB();
    }

    private void initializeSQLiteDB() {
        try {
            // Create or open the SQLite database
            profileDatabase = openOrCreateDatabase("profiledb", MODE_PRIVATE, null);

            // Creating a DatabaseManager instance
            DatabaseManager databaseManager = new DatabaseManager(profileDatabase);
            databaseManager.setTables(); // Set up tables.
            databaseManager.insertAirport(this);





        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void fetchDataAndDisplay() {
        try {
            // Modify the cursor query to select all airports
            Cursor cursor = profileDatabase.rawQuery("SELECT * FROM Airport", null);

            // Display the details in the TextView
            StringBuilder result = new StringBuilder();

            // Check if the cursor has data
            if (cursor.moveToFirst()) {
                // Assuming the columns in the Airport table are named "Airport_ID", "ICAO", "Airport_Name"
                int airportIdIndex = cursor.getColumnIndex("Airport_ID");
                int icaoIndex = cursor.getColumnIndex("ICAO");
                int airportNameIndex = cursor.getColumnIndex("Airport_Name");

                do {
                    if (airportIdIndex != -1) {
                        int airportId = cursor.getInt(airportIdIndex);
                        result.append("Airport ID: ").append(airportId).append("\n");
                    }

                    if (icaoIndex != -1) {
                        String icao = cursor.getString(icaoIndex);
                        result.append("ICAO: ").append(icao).append("\n");
                    }

                    if (airportNameIndex != -1) {
                        String airportName = cursor.getString(airportNameIndex);
                        result.append("Airport Name: ").append(airportName).append("\n\n");
                    }
                } while (cursor.moveToNext());
            } else {
                // Handle the case where the cursor is empty
                result.append("No data found in the Airport table.\n");
            }

            resultTextView.setText(result.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        // Close the database when the activity is destroyed
        if (profileDatabase != null) {
            profileDatabase.close();
        }
        super.onDestroy();
    }
}
