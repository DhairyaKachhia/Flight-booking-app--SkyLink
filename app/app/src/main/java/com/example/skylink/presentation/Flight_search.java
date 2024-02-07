package com.example.skylink.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.skylink.Flight;
import com.example.skylink.R;
import com.example.skylink.Trip;
import com.example.skylink.objects.Flights;
import com.example.skylink.objects.Flight;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Flight_search extends AppCompatActivity {


//    ArrayList<Flight> outFlights = new ArrayList<>();
//    ArrayList<Flight> inFlights = new ArrayList<>();
//    private Trip trip = new Trip();


    private ListView showFlightLV;
    private Spinner sortingOptions;
    private List<List<List<Flight>>> availableFlights = new ArrayList<>();
    private boolean isOneWay = false;
    private CustomFlightAdaptor customFlightAdaptor;
    private CustomFlightAdaptor returnAdaptor;
    private CustomFlightAdaptor currAdaptor;
    private boolean isDepartureSelected;

//    private ArrayList<Flight> tripOutbound = null;
//    private ArrayList<Flight> tripInbound = null;

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


        // *************************


        sortingOptions = setupSpinner();

        showFlightLV = setupListview();

//        Button createDepartFlightBtn = findViewById(R.id.createDepartFlightBtn);
//        Button createReturnFlightBtn = findViewById(R.id.createReturnFlightBtn);
//        Button showFlightBtn = findViewById(R.id.showFlightBtn);



//        createDepartFlightBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                createDptFlight();
//            }
//        });
//
//        createReturnFlightBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                createRetFlight();
//            }
//        });

//        showFlightBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {

                // Initialize trip object.      ** To be Removed **
//                trip.setOutbound(outFlights);
//                trip.setInbound(inFlights);
//
//                isOneWay = trip.getInbound().isEmpty();
////                isOneWay = trip.isOneway();           // TODO: right way
//                availableFlights = trip.getOutbound();
//                // ** To be Removed **
//
//                isDepartureSelected = false;
//
//                tripOutbound = trip.getOutbound();
//                tripInbound = trip.getInbound();
//
//                Toast.makeText(getApplicationContext(), "Is search Oneway?: " + isOneWay, Toast.LENGTH_LONG).show();
//
//                customFlightAdaptor = new CustomFlightAdaptor(Flight_search.this, tripOutbound, isOneWay);
//                returnAdaptor = new CustomFlightAdaptor(Flight_search.this, tripInbound, isOneWay);
//                currAdaptor = customFlightAdaptor;
//
//                showFlightLV.setAdapter(currAdaptor);

//                showFlights(tripOutbound);
//            }
//        });

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
//                if (inboundFlights != null) {
//                    processFlights(inboundFlights, tripInbound);
//                }

            }


            // Retrieve and process inbound flights
            tripOutbound = receivedData.get("Outbound");
//            if (outboundFlights != null) {
//                processFlights(outboundFlights, tripOutbound);
//            }


        }

//        if (receivedData.keySet().equals("Inbound")) {
//            List<List<List<Flight>>> flights = receivedData.get(key);
//            for (List<List<Flight>> flightList : flights) {
//                for (List<Flight> flightDetails : flightList) {
//
//                    String flightNumber = flightDetails.toString();
//
//                    Toast.makeText(getApplicationContext(), "search: " + flightNumber, Toast.LENGTH_LONG).show();
//
//
//                }
//            }
//        }
//        if (receivedData.keySet().equals("Outbound") andeceivedData.keySet() is not null ){
//            List<List<List<Flight>>> flights = receivedData.get(key);
//            for (List<List<Flight>> flightList : flights) {
//                for (List<Flight> flightDetails : flightList) {
//
//                    String flightNumber = flightDetails.toString();
//
//                    Toast.makeText(getApplicationContext(), "search: " + flightNumber, Toast.LENGTH_LONG).show();
//
//
//                }
//            }
//        }else{
//            "no flight"
//        }
    }

    private void processFlights(List<List<List<Flight>>> flightsList, List<Flight> storeFlights ) {
        for (List<List<Flight>> flightLists : flightsList) {
            for (List<Flight> flights : flightLists) {
                // Process each flight

                if (flights != null) {
                    storeFlights.addAll(flights);
                    Toast.makeText(getApplicationContext(), "search: " + storeFlights, Toast.LENGTH_LONG).show();
                }

//                String flightResult = flights.toString();

            }
        }
    }


    private ListView setupListview () {

        isDepartureSelected = false;

        ListView showFlightLV = findViewById(R.id.flightListView);

        customFlightAdaptor = new CustomFlightAdaptor(Flight_search.this, tripOutbound, isOneWay);
        returnAdaptor = new CustomFlightAdaptor(Flight_search.this, tripInbound, isOneWay);
        currAdaptor = customFlightAdaptor;

        availableFlights = new ArrayList<>(tripOutbound);
        showFlightLV.setAdapter(currAdaptor);


        return showFlightLV;
    }

    public class spinnerItemSelectListner implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            // Perform actions when an item is selected
            String selectedItem = parent.getItemAtPosition(position).toString();
            Toast.makeText(getApplicationContext(), "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();

            List<List<List<Flight>>> filteredFlights = new ArrayList<>(availableFlights);

//            String selectedItem = sortingOptions.getSelectedItem().toString();

//            Toast.makeText(getApplicationContext(), "You have selected: " + selectedItem, Toast.LENGTH_LONG).show();

            if (selectedItem.equals("Lowest price")) {
//                    availableFlights.sort(Comparator.comparing(availableFlights::get));
                if (availableFlights.size() > 0) {
//                        filteredFlights = (List<List<List<Flight>>>) availableFlights.stream().sorted(Comparator.comparing(Flight::getEconPrice)).collect(Collectors.toList());

                    sortFlightsByPrice(filteredFlights);
                }

                if (filteredFlights.size() > 0) {

                    currAdaptor.setAvailableFlights(filteredFlights);
                    currAdaptor.notifyDataSetChanged();

//                        showFlights(filteredFlights);
                }

            } else if (selectedItem.equals("Time taken")) {

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


    //
//    private void createRetFlight() {
////        Flight flight = new Flight();
//
//        Random r = new Random();
//
//
//        for (int i = 0; i < 10; i++) {
//            Flight flight = new Flight("YYZ" + i, "YWG" + i, "17:00", "18:30", "1 hrs. 30 mins",
//                    r.nextInt(450-300)+300, r.nextInt(450-300)+300);
//            inFlights.add(flight);
//        }
//    }
//
//
//    private void createDptFlight() {
////        Flight flight = new Flight();
//
//        Random r = new Random();
//
//
//        for (int i = 0; i < 10; i++) {
//            Flight flight = new Flight("YWG" + i, "YYZ" + i, "11:00", "13:30", "2 hrs. 30 mins",
//                    r.nextInt(450-300)+300, r.nextInt(450-300)+300);
//            outFlights.add(flight);
//        }
//
//    }
//
//    private void showFlights(ArrayList<Flight> toDisplayFlights) {
//
//        //TODO: Need new param: isOneway. Based on this create two new method.
//        //      If oneway -> diff CustomFlightAdaptor and note the button listener to next activity
//        //      If round -> diff CustomFlightAdaptor and button click show next flight and finally next activity!
//
////        if (isOneWay) {
////            showOneway(toDisplayFlights);
////        } else {
////            showRoundtrip(toDisplayFlights);
////        }
//
//        customFlightAdaptor = new CustomFlightAdaptor(Flight_search.this, toDisplayFlights, isOneWay);
//        returnAdaptor = new CustomFlightAdaptor(Flight_search.this, toDisplayFlights, isOneWay);
//
//        showFlightLV.setAdapter(customFlightAdaptor);
//
//    }
//
//    private void showOneway(ArrayList<Flight> toDisplayFlights) {
////        CustomFlightAdaptor customFlightAdaptor = new CustomFlightAdaptor(MainActivity.this, toDisplayFlights);
////
////        showFlightLV.setAdapter(customFlightAdaptor);
//    }
//
//    private void showRoundtrip(ArrayList<Flight> toDisplayFlights) {
////        CustomFlightAdaptor customFlightAdaptor = new CustomFlightAdaptor(MainActivity.this, toDisplayFlights);
////
////        showFlightLV.setAdapter(customFlightAdaptor);
//    }
//
//
//    public void onClassPriceBtn(View v) {
//        if(!isOneWay) {
//
//            availableFlights = trip.getInbound();
//            showFlights(trip.getInbound());
//
//        } else {
//
////            Button econBook = findViewById(v.getId());
////
////            econBook.setOnClickListener(onewayBook);
////            Intent nextPageIntent = new Intent(this, UserInfo.class);
////            startActivities(nextPageIntent);
//        }
//    }
//
    public void updateFlights() {
        if (!isOneWay) {
            if (isDepartureSelected) {
//            customFlightAdaptor.clear();
//            customFlightAdaptor.addAll(tripInbound);
                currAdaptor = returnAdaptor;

                availableFlights = new ArrayList<>(tripInbound);
            } else {
//            customFlightAdaptor.clear();
//            customFlightAdaptor.addAll(tripOutbound);
                currAdaptor = customFlightAdaptor;

                availableFlights = new ArrayList<>(tripOutbound);
            }

            showFlightLV.setAdapter(currAdaptor);

            sortingOptions.setOnItemSelectedListener(new spinnerItemSelectListner());
        }
//        customFlightAdaptor.notifyDataSetChanged();
    }

    public boolean getDepartureStatus() {
        return isDepartureSelected;
    }

    public void setDepartureStatus(boolean departureStatus) {
        isDepartureSelected = departureStatus;
    }

    @Override
    public void onBackPressed() {

        if (!isOneWay) {
            // if showing return flights, show departure flight
            if (isDepartureSelected) {
                isDepartureSelected = false;

                Toast.makeText(getApplicationContext(), "BACK BTN, if - Departure: " + isDepartureSelected, Toast.LENGTH_LONG).show();
                // Update your listView to show departing flights

                updateFlights();
            } else {
                Toast.makeText(getApplicationContext(), "BACK BTN, else - Departure: " + isDepartureSelected, Toast.LENGTH_LONG).show();

                super.onBackPressed();

            }

        } else {
            Toast.makeText(getApplicationContext(), "BACK BTN, else - Departure: " + isDepartureSelected, Toast.LENGTH_LONG).show();

            super.onBackPressed();

        }

    }
}