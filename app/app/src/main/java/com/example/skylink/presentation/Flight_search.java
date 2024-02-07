package com.example.skylink.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.skylink.R;
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
    private boolean isDepartureSelected;
    private List<List<List<Flight>>> tripOutbound = null;
    private List<List<List<Flight>>> tripInbound = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_search);

        Intent intent = getIntent();

        Bundle userInput = intent.getExtras();

        displayUserSelection(userInput);

        Flights flightData = (Flights) intent.getSerializableExtra("flightData");
        HashMap<String, List<List<List<com.example.skylink.objects.Flight>>>> receivedData = null;

        if (flightData != null) {
            receivedData = flightData.getData();
        }

        extractFlightData(receivedData, isOneWay);

        sortingOptions = setupSpinner();

        noFlightTV = findViewById(R.id.noFlightTextV);
        showFlightLV = findViewById(R.id.flightListView);
        setupListview();

        sortingOptions.setOnItemSelectedListener(new spinnerItemSelectListner());

    }



    @SuppressLint("SetTextI18n")
    private void displayUserSelection(Bundle userInput) {
        if (userInput != null) {
            String departingCountry = userInput.getString("departingCountry");
            String returningCountry = userInput.getString("returningCountry");
            String departingDate = userInput.getString("departingDate");
            String returningDate = userInput.getString("returningDate");
            int totalPassengers = userInput.getInt("totalPassengers");
            boolean isOneWay = userInput.getBoolean("isOneWay");


            this.isOneWay = isOneWay;

            TextView toLocTV = findViewById(R.id.toLocTV);
            toLocTV.setText(departingCountry);

            TextView fromLocTV = findViewById(R.id.fromLocTV);
            fromLocTV.setText(returningCountry);

            TextView departDateTV = findViewById(R.id.departDateTV);
            departDateTV.setText(departingDate);

            TextView returnDateLabelTV = findViewById(R.id.returnDateLabelTV); // TextView for "Return date" label
            TextView returnDateTV = findViewById(R.id.returnDateTV);
            if (returningDate != null) {
                returnDateLabelTV.setVisibility(View.VISIBLE);
                returnDateTV.setText(returningDate);
            } else {
                returnDateLabelTV.setVisibility(View.GONE);
                returnDateTV.setText("");
            }

            TextView totalGuestTV = findViewById(R.id.totalGuestTV);
            totalGuestTV.setText(totalPassengers + " Traveler");

            if (isOneWay) {
                returnDateTV.setVisibility(View.GONE);
            }
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
//                    filteredFlights = (List<List<List<Flight>>>) availableFlights.stream().sorted(Comparator.comparing(Flight::getEconPrice)).collect(Collectors.toList());

                    sortFlightsByPrice(filteredFlights);

                    if (filteredFlights.size() > 0) {

                        currAdaptor.setAvailableFlights(filteredFlights);
                        currAdaptor.notifyDataSetChanged();

                    }

                } else if (selectedItem.equals("Time taken")) {

                }
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            // Do nothing if nothing is selected
        }
    }


    private void sortFlightsByPrice(List<List<List<Flight>>> flightsList) {
        // Create a comparator to compare lists of flights based on their total price
        Comparator<List<List<Flight>>> comparator = new Comparator<List<List<Flight>>>() {
            @Override
            public int compare(List<List<Flight>> flightList1, List<List<Flight>> flightList2) {
                int price1 = getTotalPrice(flightList1);
                int price2 = getTotalPrice(flightList2);
                return Integer.compare(price1, price2);
            }
        };

        // Sort the flightsList based on the total price of flights using the comparator
        Collections.sort(flightsList, comparator);
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