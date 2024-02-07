package com.example.skylink.presentation;
import com.example.skylink.Trip;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.skylink.R;
import com.example.skylink.business.AirportPath;
import com.example.skylink.objects.Flight;
import com.example.skylink.objects.Flights;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private SQLiteDatabase profileDatabase;

    private RadioGroup radioGroupTripType;
    private String tripType;

    AutoCompleteTextView autoCompleteFrom;
    AutoCompleteTextView autoCompleteTo;

    ArrayAdapter<String> adapterItemsFrom;
    ArrayAdapter<String> adapterItemsTo;
    String[] cities = {
            "Toronto",
            "Montreal",
            "Calgary",
            "Ottawa",
            "Edmonton",
            "Mississauga",
            "Winnipeg",
            "Vancouver",
            "Brampton",
            "Hamilton"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoCompleteFrom = findViewById(R.id.autoComplete_from);
        autoCompleteTo = findViewById(R.id.autoComplete_to);
        radioGroupTripType = findViewById(R.id.radioGroupTripType);

        // Check if a RadioButton is initially selected
        int selectedTripTypeId = radioGroupTripType.getCheckedRadioButtonId();
        if (selectedTripTypeId != -1) { // -1 means no id is selected
            RadioButton selectedTripType = findViewById(selectedTripTypeId);
            tripType = selectedTripType.getText().toString();
        }

        // Initialize adapters for AutoCompleteTextViews
        ArrayAdapter<String> adapterItemsFrom = new ArrayAdapter<>(this, R.layout.list_item, cities);
        ArrayAdapter<String> adapterItemsTo = new ArrayAdapter<>(this, R.layout.list_item, cities);
        autoCompleteFrom.setAdapter(adapterItemsFrom);
        autoCompleteTo.setAdapter(adapterItemsTo);

        // Set an OnCheckedChangeListener to the radio group to listen for changes
        radioGroupTripType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Handle the change
                RadioButton radioButton = findViewById(checkedId);
                if (radioButton != null) {
                    tripType = radioButton.getText().toString();
                }
            }
        });

        Button searchBtn = findViewById(R.id.searchBtn);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchFlights();
            }
        });
    }

    private void retrieveLocations() {
        String fromLocation = autoCompleteFrom.getText().toString();
        String toLocation = autoCompleteTo.getText().toString();
    }

    @Override
    protected void onDestroy() {
        // Close the database when the activity is destroyed
        if (profileDatabase != null) {
            profileDatabase.close();
        }
        super.onDestroy();
    }

    // TODO: sends user input data and flight results to Flight_search class activity
    private void searchFlights() {


        String departingCountry = "YYZ";
        String returningCountry = "YYC";
        String departingDate = "09/02/2024";
        String returningDate = "12/02/2024";
        int totalPassengers = 1;
        boolean isOneWay = false;


        Bundle userInfoBundle = new Bundle();

        userInfoBundle.putString("departingCountry", departingCountry);
        userInfoBundle.putString("returningCountry", returningCountry);
        userInfoBundle.putString("departingDate", departingDate);
        userInfoBundle.putString("returningDate", returningDate);
        userInfoBundle.putInt("totalPassengers", totalPassengers);
        userInfoBundle.putBoolean("isOneWay", isOneWay);

        AirportPath path = new AirportPath();

        HashMap<String, List<List<List<Flight>>>> p = path.findFlights(departingCountry, returningCountry, departingDate, returningDate, isOneWay);

        Flights flightData = new Flights(p);

        Intent intent = new Intent(MainActivity.this, Flight_search.class);
        intent.putExtra("flightData", flightData);
        intent.putExtras(userInfoBundle);


        startActivity(intent);

    }

}
