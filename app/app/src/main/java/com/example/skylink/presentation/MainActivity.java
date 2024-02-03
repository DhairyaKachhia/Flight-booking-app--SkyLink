package com.example.skylink.presentation;
import com.example.skylink.data.DatabaseManager;
import  com.example.skylink.business.FetchAirport;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.skylink.R;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private SQLiteDatabase profileDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);
        Button fetchDataButton = findViewById(R.id.fetchDataButton);

        profileDatabase = openOrCreateDatabase("profiledb", MODE_PRIVATE, null);

        DatabaseManager databaseManager = new DatabaseManager(profileDatabase);
        databaseManager.setTables();
        databaseManager.insertAirport(this);

        FetchAirport fetchAirport = new FetchAirport(profileDatabase);

        fetchDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                resultTextView.setText(fetchAirport.fetchDataAndDisplay());
            }
        });

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
