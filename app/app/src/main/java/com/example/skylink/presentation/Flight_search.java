package com.example.skylink.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.skylink.R;
import com.example.skylink.business.FlightSorting;
import com.example.skylink.objects.Flights;
import com.example.skylink.objects.Flight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Flight_search extends AppCompatActivity {

    private ListView showFlightLV;
    private Spinner sortingOptions;
    private TextView noFlightTV;
    private List<List<List<Flight>>> availableFlights = new ArrayList<>();
    private boolean isOneWay = true;
    private CustomFlightAdaptor customFlightAdaptor;
    private CustomFlightAdaptor returnAdaptor;
    private CustomFlightAdaptor currAdaptor;
    private FlightSorting flightSorting;
    private boolean isDepartureSelected;
    private List<List<List<Flight>>> tripOutbound = null;
    private List<List<List<Flight>>> tripInbound = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_search);

        Intent intent = getIntent();

        noFlightTV = findViewById(R.id.noFlightTextV);
        showFlightLV = findViewById(R.id.flightListView);

        Flights flightData = (Flights) intent.getSerializableExtra("flightData");
        HashMap<String, List<List<List<Flight>>>> receivedData = null;

        if (flightData != null && !flightData.getData().isEmpty()) {

            Bundle userInput = intent.getExtras();
            displayUserSelection(userInput);

            receivedData = flightData.getData();

            extractFlightData(receivedData, isOneWay);

            sortingOptions = setupSpinner();

            setupListview();

            sortingOptions.setOnItemSelectedListener(new spinnerItemSelectListner());

        } else {
            noFlightTV.setVisibility(View.VISIBLE);
            showFlightLV.setVisibility(View.GONE);
        }

    }



    @SuppressLint("SetTextI18n")
    private void displayUserSelection(Bundle userInput) {
        if (userInput != null) {
            String departingCity = userInput.getString("departingCity");
            String returningCity = userInput.getString("returningCity");
            String departingDate = userInput.getString("departingDate");
            String returningDate = userInput.getString("returningDate");
            int totalPassengers = userInput.getInt("totalPassengers");
            boolean isOneWay = userInput.getBoolean("isOneWay");

            this.isOneWay = isOneWay;

            TextView toLocTV = findViewById(R.id.toLocTV);
            toLocTV.setText(departingCity);

            TextView fromLocTV = findViewById(R.id.fromLocTV);
            fromLocTV.setText(returningCity);

            TextView departDateTV = findViewById(R.id.departDateTV);
            departDateTV.setText(departingDate);

            LinearLayout returnDateSection = findViewById(R.id.returnDateLayout);
            TextView returnDateTV = findViewById(R.id.returnDateTV);

            TextView tripWayTV = findViewById(R.id.tripWayTV);

            if (isOneWay) {
                tripWayTV.setText("Oneway");
                returnDateSection.setVisibility(View.GONE);
            } else {
                tripWayTV.setText("Round-trip");
                returnDateSection.setVisibility(View.VISIBLE);
                returnDateTV.setText(returningDate);
            }

            TextView totalGuestTV = findViewById(R.id.totalGuestTV);
            totalGuestTV.setText(totalPassengers + " Traveler");

        }
    }

    private Spinner setupSpinner () {
        Spinner sortingOptions = findViewById(R.id.sortingListOption);
        ArrayAdapter<CharSequence> sortAdapter = ArrayAdapter.createFromResource(this, R.array.sorting_list_option, R.layout.custom_spinner_text);

        // Specify the layout to use when the list of choices appears.
        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        sortingOptions.setAdapter(sortAdapter);

        return sortingOptions;
    }

    private void extractFlightData (HashMap< String,List<List<List<Flight>>>>  receivedData, boolean isOneWay) {

        if (receivedData != null) {

            if (!isOneWay) {
                // Retrieve and process outbound flights
                tripInbound = receivedData.get("Inbound");
            }


            // Retrieve and process inbound flights
            tripOutbound = receivedData.get("Outbound");

        }
    }

    private void setupListview () {

        isDepartureSelected = false;

        customFlightAdaptor = new CustomFlightAdaptor(Flight_search.this, tripOutbound, isOneWay);
        returnAdaptor = new CustomFlightAdaptor(Flight_search.this, tripInbound, isOneWay);
        currAdaptor = customFlightAdaptor;

        availableFlights = new ArrayList<>(tripOutbound);

        checkAdapter(availableFlights);
    }

    public class spinnerItemSelectListner implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            // Perform actions when an item is selected
            String selectedItem = parent.getItemAtPosition(position).toString();

            List<List<List<Flight>>> filteredFlights = new ArrayList<>(availableFlights);

            if (filteredFlights.size() > 0) {
                if (selectedItem.equals("Lowest price")) {

                    flightSorting = new FlightSorting(FlightSorting.SortingOption.PRICE);


                } else if (selectedItem.equals("Direct flight")) {
                    flightSorting = new FlightSorting(FlightSorting.SortingOption.DIRECT_FLIGHTS);


                } else {                //sorting on Earliest departure
                    flightSorting = new FlightSorting(FlightSorting.SortingOption.EARLIEST_DEPARTURE);

                }

                Collections.sort(filteredFlights, flightSorting);

                if (filteredFlights.size() > 0) {

                    currAdaptor.setAvailableFlights(filteredFlights);
                    currAdaptor.notifyDataSetChanged();

                }
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            // Do nothing if nothing is selected
        }
    }

    private int getTotalPrice(List<List<Flight>> flightList) {
        int totalPrice = 0;
        for (List<Flight> flights : flightList) {
            for (Flight flight : flights) {
                totalPrice += flight.getEconPrice();
            }
        }
        return totalPrice;
    }

    public void updateFlights() {
        if (!isOneWay) {
            if (isDepartureSelected) {
                currAdaptor = returnAdaptor;

                availableFlights = new ArrayList<>(tripInbound);

            } else {
                currAdaptor = customFlightAdaptor;

                availableFlights = new ArrayList<>(tripOutbound);
            }

            checkAdapter(availableFlights);

            sortingOptions.setOnItemSelectedListener(new spinnerItemSelectListner());
        }
    }


    private void checkAdapter(List<List<List<Flight>>> availableFlights) {
        if (availableFlights.isEmpty()) {
            noFlightTV.setVisibility(View.VISIBLE);
            showFlightLV.setVisibility(View.GONE);

        } else {
            noFlightTV.setVisibility(View.GONE);
            showFlightLV.setVisibility(View.VISIBLE);

            showFlightLV.setAdapter(currAdaptor);

        }

    }

    @Override
    public void onBackPressed() {

        if (!isOneWay) {
            // if showing return flights, show departure flight
            if (isDepartureSelected) {
                isDepartureSelected = false;

                updateFlights();
            } else {
                super.onBackPressed();

            }

        } else {
            super.onBackPressed();

        }

    }

    public boolean getDepartureStatus() {
        return isDepartureSelected;
    }

    public void setDepartureStatus(boolean departureStatus) {
        isDepartureSelected = departureStatus;
    }
}