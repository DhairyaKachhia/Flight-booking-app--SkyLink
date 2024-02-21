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
import com.example.skylink.business.AirportPath;
import com.example.skylink.business.validations.IValidateSearchInput;
import com.example.skylink.business.validations.ValidateSearchInput;
import com.example.skylink.business.Session;
import com.example.skylink.data.CitiesRepository;
import com.example.skylink.objects.City;
import com.example.skylink.objects.Flight;
import com.example.skylink.objects.Flights;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.app.DatePickerDialog;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private final int MAX_TRAVELERS = 4;
    private final int MIN_TRAVELERS = 1;
    private RadioGroup radioGroupTripType;
    private String tripType;

    private AutoCompleteTextView autoCompleteFrom;
    private AutoCompleteTextView autoCompleteTo;

    private ArrayAdapter<String> adapterItemsFrom;
    private ArrayAdapter<String> adapterItemsTo;
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
        List<City> cities = citiesRepository.getCities();

        // Check if a RadioButton is initially selected
        int selectedTripTypeId = radioGroupTripType.getCheckedRadioButtonId();
        if (selectedTripTypeId != -1) { // -1 means no id is selected
            RadioButton selectedTripType = findViewById(selectedTripTypeId);
            tripType = selectedTripType.getText().toString();
        }

        // Initialize adapters for AutoCompleteTextViews
        ArrayAdapter<City> adapterItemsFrom = new ArrayAdapter<>(this, R.layout.list_item, cities);
        ArrayAdapter<City> adapterItemsTo = new ArrayAdapter<>(this, R.layout.list_item, cities);

        autoCompleteFrom.setAdapter(adapterItemsFrom);
        autoCompleteTo.setAdapter(adapterItemsTo);

        updateAdapterItems(autoCompleteFrom, null);
        updateAdapterItems(autoCompleteTo, null);

        autoCompleteFrom.setOnItemClickListener((parent, view, position, id) -> {
            autoCompleteFrom.setError(null);
            City selectedFromCity = (City) parent.getItemAtPosition(position);
            updateAdapterItems(autoCompleteTo, selectedFromCity); // This will update the 'To' field
        });

        autoCompleteTo.setOnItemClickListener((parent, view, position, id) -> {
            autoCompleteTo.setError(null);
            City selectedToCity = (City) parent.getItemAtPosition(position);
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
        swapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence fromCity = autoCompleteFrom.getText();
                CharSequence toCity = autoCompleteTo.getText();
                autoCompleteFrom.setText(toCity);
                autoCompleteTo.setText(fromCity);
            }
        });

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
                if (travelerCount > MIN_TRAVELERS) {
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

    private void updateAdapterItems(AutoCompleteTextView autoCompleteTextView, City excludeCity) {
        List<City> updatedCities = new ArrayList<>(citiesRepository.getCities());
        if (excludeCity != null) {
            // Remove the excluded city from the list based on the city code comparison
            updatedCities.removeIf(city -> city.getCode().equals(excludeCity.getCode()));
        }
        ArrayAdapter<City> adapterItems = new ArrayAdapter<>(this, R.layout.list_item, updatedCities);
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
            AirportPath path = new AirportPath();

            // Set session data (e.g., during login)
            Session.getInstance().setEmail("123");
            Session.getInstance().setUsername("JohnDoe");

            HashMap<String, List<List<List<Flight>>>> flightPathResults = path.findFlights(departingCity, returningCity, departingDate, returningDate, isOneWay);

            Flights flightData = new Flights(flightPathResults);

            Intent intent = new Intent(MainActivity.this, Flight_search.class);
            intent.putExtra("flightData", flightData);
            intent.putExtras(userInfoBundle);

            startActivity(intent);
        }

    }

}
