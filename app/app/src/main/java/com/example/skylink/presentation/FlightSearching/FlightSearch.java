package com.example.skylink.presentation.FlightSearching;

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
import com.example.skylink.presentation.User_Auth.SignInActivity;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import android.app.DatePickerDialog;
import android.widget.EditText;

public class FlightSearch extends AppCompatActivity {

    private final int MAX_TRAVELERS = 4;
    private final int MIN_TRAVELERS = 1;

    private AutoCompleteTextView autoCompleteFrom;
    private AutoCompleteTextView autoCompleteTo;

    private CitiesRepository citiesRepository;

    private EditText etDeparture, etReturn;
    private Calendar calendar;
    private RadioGroup radioGroupTripType;
    private String tripType;



    private TextView tvTravelerCount;
    private Button btnIncrement, btnDecrement;
    private int travelerCount = MIN_TRAVELERS;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        setupAutoCompleteListeners();
        setupDatePickers();
        setupSwapButton();
        setupTripTypeChangeListener();
        setupSearchButton();
        setupTravelerCountButtons();
        setupDefaultTripType();
    }

    private void setupDefaultTripType() {
        // Hide return date and layout initially
        final TextInputLayout textInputLayoutReturn = findViewById(R.id.textInputLayout_return);
        etReturn.setVisibility(View.GONE);
        textInputLayoutReturn.setVisibility(View.GONE);
    }

    private void setupTripTypeChangeListener() {
        final TextInputLayout textInputLayoutReturn = findViewById(R.id.textInputLayout_return);

        radioGroupTripType.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = findViewById(checkedId);
            tripType = radioButton.getText().toString();

            if ("Round Trip".equals(tripType)) {
                textInputLayoutReturn.setVisibility(View.VISIBLE);
                etReturn.setVisibility(View.VISIBLE);
            } else {
                textInputLayoutReturn.setVisibility(View.GONE);
                etReturn.setVisibility(View.GONE);
                etReturn.setText("");
            }
        });
    }


    private void initializeViews() {
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

        ArrayAdapter<iCity> adapterItemsFrom = new ArrayAdapter<>(this, R.layout.list_item, cities);
        ArrayAdapter<iCity> adapterItemsTo = new ArrayAdapter<>(this, R.layout.list_item, cities);

        autoCompleteFrom.setAdapter(adapterItemsFrom);
        autoCompleteTo.setAdapter(adapterItemsTo);

        updateAdapterItems(autoCompleteFrom, null);
        updateAdapterItems(autoCompleteTo, null);
    }

    private void setupAutoCompleteListeners() {
        autoCompleteFrom.setOnItemClickListener((parent, view, position, id) -> {
            autoCompleteFrom.setError(null);
            iCity selectedFromCity = (iCity) parent.getItemAtPosition(position);
            updateAdapterItems(autoCompleteTo, selectedFromCity);
        });

        autoCompleteTo.setOnItemClickListener((parent, view, position, id) -> {
            autoCompleteTo.setError(null);
            iCity selectedToCity = (iCity) parent.getItemAtPosition(position);
            updateAdapterItems(autoCompleteFrom, selectedToCity);
        });
    }

    private void setupDatePickers() {
        etDeparture = findViewById(R.id.et_departure);
        etReturn = findViewById(R.id.et_return);
        calendar = Calendar.getInstance();

        etDeparture.setOnClickListener(v -> showDatePickerDialog(etDeparture));
        etReturn.setOnClickListener(v -> showDatePickerDialog(etReturn));
    }

    private void setupSwapButton() {
        Button swapButton = findViewById(R.id.swapBtn);
        swapButton.setOnClickListener(v -> swapCities());
    }

    private void setupSearchButton() {
        Button searchBtn = findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(v -> searchFlights());
    }

    private void setupTravelerCountButtons() {
        tvTravelerCount = findViewById(R.id.tv_travelerCount);
        btnIncrement = findViewById(R.id.btn_increment);
        btnDecrement = findViewById(R.id.btn_decrement);

        btnIncrement.setOnClickListener(v -> incrementTravelerCount());
        btnDecrement.setOnClickListener(v -> decrementTravelerCount());
    }

    private void swapCities() {
        CharSequence fromCity = autoCompleteFrom.getText();
        CharSequence toCity = autoCompleteTo.getText();
        autoCompleteFrom.setText(toCity);
        autoCompleteTo.setText(fromCity);

        iCity selectedFromCity = (iCity) autoCompleteFrom.getAdapter().getItem(0); // Get the selected 'From' city
        iCity selectedToCity = (iCity) autoCompleteTo.getAdapter().getItem(0);     // Get the selected 'To' city

        updateAdapterItems(autoCompleteTo, selectedFromCity);
        updateAdapterItems(autoCompleteFrom, selectedToCity);
    }


    private void incrementTravelerCount() {
        travelerCount++;
        updateTravelerCount();
    }

    private void decrementTravelerCount() {
        if (travelerCount > MIN_TRAVELERS) {
            travelerCount--;
            updateTravelerCount();
        }
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

            iFlightSearch flightSearch = new com.example.skylink.objects.Implementations.FlightSearch(departingCity, returningCity, departingDate, returningDate, totalPassengers, isOneWay);

            Session.getInstance().setFlightSearch(flightSearch);

            HashMap<String, List<List<List<iFlight>>>> flightPathResults = path.findFlights(flightSearch);

            Session.getInstance().setFlightPathResults(flightPathResults);

            Intent intent = new Intent(FlightSearch.this, FlightDisplay.class);
            startActivity(intent);
        }

    }

    public void goToSignInActivity(View view) {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
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

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000); // Set the minimum date to the current date

        datePickerDialog.show(); // Show the date picker dialog
        editText.setError(null);
    }

}
