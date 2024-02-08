package com.example.skylink.presentation;

import android.annotation.SuppressLint;
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
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import android.app.DatePickerDialog;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

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
            "Toronto - YYZ",
            "Montreal - YUL",
            "Calgary - YYC",
            "Ottawa - YOW",
            "Edmonton - YEG",
            "Mississauga - YYZ",
            "Winnipeg - YWG",
            "Vancouver - YVR",
            "Brampton - YZZ",
            "Hamilton - YHM"
    };

    private EditText etDeparture, etReturn;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;

    private TextView tvTravelerCount;
    private Button btnIncrement, btnDecrement;
    private int travelerCount = 1;


    @SuppressLint("MissingInflatedId")
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

        updateAdapterItems(autoCompleteFrom, null);
        updateAdapterItems(autoCompleteTo, null);

        autoCompleteFrom.setOnItemClickListener((parent, view, position, id) -> {
            String selectedFromCity = (String) parent.getItemAtPosition(position);
            updateAdapterItems(autoCompleteTo, selectedFromCity);
        });

        autoCompleteTo.setOnItemClickListener((parent, view, position, id) -> {
            String selectedToCity = (String) parent.getItemAtPosition(position);
            updateAdapterItems(autoCompleteFrom, selectedToCity);
        });

        etDeparture = findViewById(R.id.et_departure);
        etReturn = findViewById(R.id.et_return);
        calendar = Calendar.getInstance();

        etDeparture.setOnClickListener(v -> showDatePickerDialog(etDeparture));
        etReturn.setOnClickListener(v -> showDatePickerDialog(etReturn));

        final TextInputLayout textInputLayoutReturn = findViewById(R.id.textInputLayout_return);
        RadioButton selectedTripType = findViewById(radioGroupTripType.getCheckedRadioButtonId());
        if (selectedTripType != null) {
            tripType = selectedTripType.getText().toString();
            textInputLayoutReturn.setVisibility("Round Trip".equals(tripType) ? View.VISIBLE : View.GONE);
        }

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

        tvTravelerCount = findViewById(R.id.tv_travelerCount);
        btnIncrement = findViewById(R.id.btn_increment);
        btnDecrement = findViewById(R.id.btn_decrement);

        btnIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                travelerCount++;
                updateTravelerCount();
            }
        });

        btnDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (travelerCount > 1) {
                    travelerCount--;
                    updateTravelerCount();
                }
            }
        });


        radioGroupTripType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                tripType = radioButton.getText().toString();

                if ("Round Trip".equals(tripType)) {
                    textInputLayoutReturn.setVisibility(View.VISIBLE);
                } else {
                    textInputLayoutReturn.setVisibility(View.GONE);
                    etReturn.setText("");
                }
            }
        });

    }

    private void updateAdapterItems(AutoCompleteTextView autoCompleteTextView, String excludeCity) {
        List<String> updatedCities = new ArrayList<>(Arrays.asList(cities));
        if (excludeCity != null && !excludeCity.isEmpty()) {
            // Remove the excludeCity from the list
            updatedCities.removeIf(city -> city.equals(excludeCity) || city.startsWith(excludeCity + " - "));
        }
        ArrayAdapter<String> adapterItems = new ArrayAdapter<>(this, R.layout.list_item, updatedCities);
        autoCompleteTextView.setAdapter(adapterItems);
    }

    private void showDatePickerDialog(EditText editText) {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, monthOfYear, dayOfMonth) -> {
            String selectedDate = String.format("%02d/%02d/%04d", dayOfMonth, monthOfYear + 1, year);
            editText.setText(selectedDate);
        };

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);

        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());

        datePickerDialog.show();
    }

    private void updateTravelerCount() {
        String travelerText = travelerCount + " " + (travelerCount > 1 ? "Passengers" : "Passenger");
        tvTravelerCount.setText(travelerText);
    }


    private String extractCityCode(String cityEntry) {
        if (cityEntry == null || !cityEntry.contains(" - ")) {
            return "";
        }

        String[] parts = cityEntry.split(" - ");
        return parts.length == 2 ? parts[1] : "";
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

        String fromCityCode = extractCityCode(autoCompleteFrom.getText().toString());
        String toCityCode = extractCityCode(autoCompleteTo.getText().toString());

        String departingCity = fromCityCode;
        String returningCity = toCityCode;
        String departingDate =  etDeparture.getText().toString();
        String returningDate = etReturn.getText().toString();
        int totalPassengers = travelerCount;
        boolean isOneWay = !tripType.equals("Round Trip");

        Bundle userInfoBundle = new Bundle();

        userInfoBundle.putString("departingCity", departingCity);
        userInfoBundle.putString("returningCity", returningCity);
        userInfoBundle.putString("departingDate", departingDate);
        userInfoBundle.putString("returningDate", returningDate);
        userInfoBundle.putInt("totalPassengers", totalPassengers);
        userInfoBundle.putBoolean("isOneWay", isOneWay);

        AirportPath path = new AirportPath();

        HashMap<String, List<List<List<Flight>>>> flightPathResults = path.findFlights(departingCity, returningCity, departingDate, returningDate, isOneWay);


        if (flightPathResults.isEmpty()) {
            Toast.makeText(MainActivity.this, "400: Could not resolve the request", Toast.LENGTH_SHORT).show();
        } else {
            Flights flightData = new Flights(flightPathResults);

            Intent intent = new Intent(MainActivity.this, Flight_search.class);
            intent.putExtra("flightData", flightData);
            intent.putExtras(userInfoBundle);

            startActivity(intent);
        }

    }

}
