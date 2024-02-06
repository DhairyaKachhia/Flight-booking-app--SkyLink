package com.example.skylink.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

//import com.example.skylink.Flight;
import com.example.skylink.R;
import com.example.skylink.Trip;
import com.example.skylink.objects.Flights;
import com.example.skylink.objects.Flight;



import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Flight_search extends AppCompatActivity {

    private ListView showFlightLV;
    ArrayList<Flight> availableFlights = new ArrayList<>();
    ArrayList<Flight> outFlights = new ArrayList<>();
    ArrayList<Flight> inFlights = new ArrayList<>();
    private Trip trip = new Trip();
    private boolean isOneWay;
//    private CustomFlightAdaptor customFlightAdaptor;
//    private CustomFlightAdaptor returnAdaptor;
//    private CustomFlightAdaptor currAdaptor;

    private boolean isDepartureSelected;

    private ArrayList<Flight> tripOutbound;
    private ArrayList<Flight> tripInbound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_search);


        Intent intent = getIntent();

        Bundle userInput = intent.getExtras();
        if (userInput != null) {
            String departingCountry = userInput.getString("departingCountry");
            String returningCountry = userInput.getString("returningCountry");
            String departingDate = userInput.getString("departingDate");
            String returningDate = userInput.getString("returningDate");
            int totalPassengers = userInput.getInt("totalPassengers");
            boolean isOneWay = userInput.getBoolean("isOneWay");
            // Extract flight data if added to the bundle
            // Flights flightData = (Flights) extras.getSerializable("flightData");
        }



        Flights flightData = (Flights) intent.getSerializableExtra("flightData");
        HashMap<String, List<List<List<com.example.skylink.objects.Flight>>>> receivedData = null;

        if (flightData != null) {
            receivedData = flightData.getData();
//           addCards(receivedData);
        }

        extractFlightData(receivedData, isOneWay);

//        Intent intent = getIntent();
//        Trip userInput = intent.getParcelableExtra("user_input");

//        if (userInput != null) {
//            TextView tv = findViewById(R.id.flightResult);
//
//            tv.setText(userInput.getFlyingFrom());
//
//        }


        Spinner sortingOptions = findViewById(R.id.sortingListOption);
        ArrayAdapter<CharSequence> sortAdapter = ArrayAdapter.createFromResource(this, R.array.sorting_list_option, R.layout.custom_spinner_text);

        // Specify the layout to use when the list of choices appears.
        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        sortingOptions.setAdapter(sortAdapter);


        Button createDepartFlightBtn = findViewById(R.id.createDepartFlightBtn);
        Button createReturnFlightBtn = findViewById(R.id.createReturnFlightBtn);
        Button showFlightBtn = findViewById(R.id.showFlightBtn);
        showFlightLV = findViewById(R.id.flightListView);

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

        showFlightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
            }
        });

        sortingOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<Flight> filteredFlights = new ArrayList<>();

                String selectedItem = sortingOptions.getSelectedItem().toString();

                Toast.makeText(getApplicationContext(), "You have selected: " + selectedItem, Toast.LENGTH_LONG).show();
                
                if (selectedItem.equals("Lowest price")) {
//                    availableFlights.sort(Comparator.comparing(availableFlights::get));
                    if (availableFlights.size() > 0) {
                        filteredFlights = (ArrayList<Flight>) availableFlights.stream().sorted(Comparator.comparing(Flight::getEconPrice)).collect(Collectors.toList());

                    }

                    if (filteredFlights.size() > 0) {

//                        currAdaptor.setAvailableFlights(filteredFlights);
//                        currAdaptor.notifyDataSetChanged();

//                        showFlights(filteredFlights);
                    }
                    
                } else if (selectedItem.equals("Time taken")) {

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void extractFlightData (HashMap< String,List<List<List<Flight>>>>  receivedData, boolean isOneWay) {

        if (receivedData != null) {

            if (!isOneWay) {
                // Retrieve and process outbound flights
                List<List<List<Flight>>> inboundFlights = receivedData.get("inbound");
                if (inboundFlights != null) {
                    processFlights(inboundFlights);
                }

            }


            // Retrieve and process inbound flights
            List<List<List<Flight>>> outboundFlights = receivedData.get("outbound");
            if (outboundFlights != null) {
                processFlights(outboundFlights);
            }




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

    private void processFlights(List<List<List<Flight>>> flightsList) {
        for (List<List<Flight>> flightLists : flightsList) {
            for (List<Flight> flights : flightLists) {
                for (Flight flight : flights) {
                    // Process each flight
                }
            }
        }
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
//    public void updateFlights() {
//        if (!isOneWay) {
//            if (isDepartureSelected) {
////            customFlightAdaptor.clear();
////            customFlightAdaptor.addAll(tripInbound);
//                currAdaptor = returnAdaptor;
//
//                availableFlights = tripInbound;
//            } else {
////            customFlightAdaptor.clear();
////            customFlightAdaptor.addAll(tripOutbound);
//                currAdaptor = customFlightAdaptor;
//
//                availableFlights = tripOutbound;
//            }
//
//            showFlightLV.setAdapter(currAdaptor);
//        }
////        customFlightAdaptor.notifyDataSetChanged();
//    }
//
//    public boolean getDepartureStatus() {
//        return isDepartureSelected;
//    }
//
//    public void setDepartureStatus(boolean departureStatus) {
//        isDepartureSelected = departureStatus;
//    }
//
//    @Override
//    public void onBackPressed() {
//
//        if (!isOneWay) {
//            // if showing return flights, show departure flight
//            if (isDepartureSelected) {
//                isDepartureSelected = false;
//
//                Toast.makeText(getApplicationContext(), "BACK BTN, if - Departure: " + isDepartureSelected, Toast.LENGTH_LONG).show();
//                // Update your listView to show departing flights
//
//                updateFlights();
//            } else {
//                Toast.makeText(getApplicationContext(), "BACK BTN, else - Departure: " + isDepartureSelected, Toast.LENGTH_LONG).show();
//
//                super.onBackPressed();
//
//            }
//
//        } else {
//            Toast.makeText(getApplicationContext(), "BACK BTN, else - Departure: " + isDepartureSelected, Toast.LENGTH_LONG).show();
//
//            super.onBackPressed();
//
//        }
//
//    }
}