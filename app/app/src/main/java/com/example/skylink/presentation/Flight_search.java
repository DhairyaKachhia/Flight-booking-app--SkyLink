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


    private boolean isDepartureSelected;

    private ArrayList<Flight> tripOutbound;
    private ArrayList<Flight> tripInbound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_search);

        Intent intent = getIntent();

        Bundle userInput = intent.getExtras();

        designDisplay(userInput);

        Flights flightData = (Flights) intent.getSerializableExtra("flightData");
        HashMap<String, List<List<List<com.example.skylink.objects.Flight>>>> receivedData = null;

        if (flightData != null) {
            receivedData = flightData.getData();
        }

        extractFlightData(receivedData, isOneWay);

    }


    @SuppressLint("SetTextI18n")
    private void designDisplay(Bundle userInput) {
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