package com.example.skylink.presentation;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.example.skylink.business.Implementations.AirportPath;
import com.example.skylink.business.Interface.iAirportPath;
import com.example.skylink.business.validations.IValidateSearchInput;
import com.example.skylink.business.validations.ValidateSearchInput;
import com.example.skylink.business.Implementations.Session;
import com.example.skylink.objects.Interfaces.iCity;
import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.objects.Interfaces.iFlightSearch;
import com.example.skylink.persistence.Implementations.hsqldb.CitiesRepository;
import com.example.skylink.objects.Implementations.City;
import com.example.skylink.objects.Implementations.Flight;
import com.example.skylink.objects.Implementations.FlightSearch;
import com.example.skylink.objects.Implementations.Flights;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.DatePickerDialog;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private final int MAX_TRAVELERS = 4;
    private final int MIN_TRAVELERS = 1;
    private RadioGroup radioGroupTripType;
    private String tripType;

    private AutoCompleteTextView autoCompleteFrom;
    private AutoCompleteTextView autoCompleteTo;

    private CitiesRepository citiesRepository;

    private EditText etDeparture, etReturn;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;

    private TextView tvTravelerCount;
    private Button btnIncrement, btnDecrement;
    private int travelerCount = MIN_TRAVELERS;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoCompleteFrom = findViewById(R.id.autoComplete_from);
        autoCompleteTo = findViewById(R.id.autoComplete_to);
        radioGroupTripType = findViewById(R.id.radioGroupTripType);

        citiesRepository = new CitiesRepository();
        List<iCity> cities = citiesRepository.getCities();

        int selectedTripTypeId = radioGroupTripType.getCheckedRadioButtonId();
        if (selectedTripTypeId != -1) {
            RadioButton selectedTripType = findViewById(selectedTripTypeId);
            tripType = selectedTripType.getText().toString();
        }

        // Initialize adapters for AutoCompleteTextViews
        ArrayAdapter<iCity> adapterItemsFrom = new ArrayAdapter<>(this, R.layout.list_item, cities);
        ArrayAdapter<iCity> adapterItemsTo = new ArrayAdapter<>(this, R.layout.list_item, cities);

        autoCompleteFrom.setAdapter(adapterItemsFrom);
        autoCompleteTo.setAdapter(adapterItemsTo);

        updateAdapterItems(autoCompleteFrom, null);
        updateAdapterItems(autoCompleteTo, null);

        autoCompleteFrom.setOnItemClickListener((parent, view, position, id) -> {
            autoCompleteFrom.setError(null);
            iCity selectedFromCity = (iCity) parent.getItemAtPosition(position);
            updateAdapterItems(autoCompleteTo, selectedFromCity); // This will update the 'To' field
        });

        autoCompleteTo.setOnItemClickListener((parent, view, position, id) -> {
            autoCompleteTo.setError(null);
            iCity selectedToCity = (iCity) parent.getItemAtPosition(position);
            updateAdapterItems(autoCompleteFrom, selectedToCity); // This will update the 'From' field
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

        Button swapButton = findViewById(R.id.swapBtn);
        swapButton.setOnClickListener(v -> {
            CharSequence fromCity = autoCompleteFrom.getText();
            CharSequence toCity = autoCompleteTo.getText();
            autoCompleteFrom.setText(toCity);
            autoCompleteTo.setText(fromCity);
        });

        // Set an OnCheckedChangeListener to the radio group to listen for changes
        radioGroupTripType.setOnCheckedChangeListener((group, checkedId) -> {
            // Handle the change
            RadioButton radioButton = findViewById(checkedId);
            if (radioButton != null) {
                tripType = radioButton.getText().toString();
            }
        });

        Button searchBtn = findViewById(R.id.searchBtn);

        searchBtn.setOnClickListener(v -> searchFlights());

        tvTravelerCount = findViewById(R.id.tv_travelerCount);
        btnIncrement = findViewById(R.id.btn_increment);
        btnDecrement = findViewById(R.id.btn_decrement);

        btnIncrement.setOnClickListener(v -> {
            travelerCount++;
            updateTravelerCount();
        });

        btnDecrement.setOnClickListener(v -> {
            if (travelerCount > MIN_TRAVELERS) {
                travelerCount--;
                updateTravelerCount();
            }
        });


        radioGroupTripType.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = findViewById(checkedId);
            tripType = radioButton.getText().toString();

            if ("Round Trip".equals(tripType)) {
                textInputLayoutReturn.setVisibility(View.VISIBLE);
            } else {
                textInputLayoutReturn.setVisibility(View.GONE);
                etReturn.setText("");
            }
        });

    }

    private void updateAdapterItems(AutoCompleteTextView autoCompleteTextView, iCity excludeCity) {
        List<iCity> updatedCities = new ArrayList<>(citiesRepository.getCities());
        if (excludeCity != null) {
            // Remove the excluded city from the list based on the city code comparison
            updatedCities.removeIf(icity -> icity.getCode().equals(excludeCity.getCode()));
        }
        ArrayAdapter<iCity> adapterItems = new ArrayAdapter<>(this, R.layout.list_item, updatedCities);
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
        editText.setError(null);
    }

    private void updateTravelerCount() {
        String travelerText = travelerCount + " " + (travelerCount > MIN_TRAVELERS ? "Passengers" : "Passenger");
        tvTravelerCount.setText(travelerText);

        checkTravelerCount();
    }

    private void checkTravelerCount() {
        btnIncrement.setEnabled(travelerCount != MAX_TRAVELERS);
        btnDecrement.setEnabled(travelerCount != MIN_TRAVELERS);
    }


    private String extractCityCode(String cityEntry) {
        if (cityEntry == null || !cityEntry.contains(" - ")) {
            return "";
        }

        String[] parts = cityEntry.split(" - ");
        return parts.length == 2 ? parts[1] : "";
    }

    private void searchFlights() {

        String departingCity = extractCityCode(autoCompleteFrom.getText().toString());;
        String returningCity = extractCityCode(autoCompleteTo.getText().toString());
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

        boolean isValid = true;

        IValidateSearchInput validator = new ValidateSearchInput();
        String error = "";

        error = validator.validAirportFrom(departingCity);
        if (!error.isEmpty()) {
            autoCompleteFrom.setError(error);
            isValid = false;
        }
        error = validator.validAirportTo(returningCity);
        if (!error.isEmpty()) {
            autoCompleteTo.setError(error);
            isValid = false;
        }
        error = validator.validDepartureDate(departingDate);
        if (!error.isEmpty()) {
            etDeparture.setError(error);
            isValid = false;
        }

        if (!isOneWay) {
            error = validator.validReturnDate(departingDate, returningDate);
            if (!error.isEmpty()) {
                etReturn.setError(error);
                isValid = false;
            }
        }

        if (isValid) {
            iAirportPath path = new AirportPath();

            // Set session data (e.g., during login)
            Session.getInstance().setEmail("123");
            Session.getInstance().setUsername("JohnDoe");

            iFlightSearch flightSearch = new FlightSearch(departingCity, returningCity, departingDate, returningDate, totalPassengers, isOneWay);

            HashMap<String, List<List<List<iFlight>>>> flightPathResults = path.findFlights(flightSearch);

            // Save User Flight Search Information in a session.
            Session.getInstance().setFlightPathResults(flightPathResults);

            Intent intent = new Intent(MainActivity.this, Flight_search.class);
            startActivity(intent);
        }

    }

    public void goToSignInActivity(View view) {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

}