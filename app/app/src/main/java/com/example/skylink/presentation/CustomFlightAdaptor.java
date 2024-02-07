package com.example.skylink.presentation;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skylink.R;
import com.example.skylink.objects.Flight;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.skylink.Flight;
//import com.example.skylink.R;
//import com.example.skylink.presentation.Flight_search;
//import com.example.skylink.presentation.User_info;
//
//import java.util.ArrayList;

public class CustomFlightAdaptor extends ArrayAdapter<Flight> {

    private Context mContext;
    private Flight_search flightResult;
    private List<List<List<Flight>>> availableFlights;
    private boolean isOneWay;

    public CustomFlightAdaptor(Context context, List<List<List<Flight>>> availableFlights, boolean isOneWay) {
        super(context, 0);

        mContext = context;
        this.availableFlights = availableFlights;
        this.isOneWay = isOneWay;

        if (context instanceof Flight_search) {
            flightResult = (Flight_search) context;
        } else {
            flightResult = null;
        }
    }

    @Override
    public int getCount() {
        return availableFlights.size();
    }

    @Override
    public Flight getItem(int position) {
//        return availableFlights.get(position);

        return availableFlights.get(position).get(0).get(0);        // TODO: handle position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // TODO: handle position!!! {Debug}
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View listItemView;

        if (availableFlights.size() == 0) {

            listItemView = inflater.inflate(R.layout.flight_not_found, parent, false);

            TextView emptyTextView = listItemView.findViewById(R.id.noFlightTextV);
            emptyTextView.setText(R.string.flight_not_found);

            Toast.makeText(mContext, "NO FLIGHT: ", Toast.LENGTH_SHORT).show();



        } else {

            if (convertView == null) {
                listItemView = inflater.inflate(R.layout.flight_result, parent, false);
            } else {
                listItemView = convertView;
            }

            TextView orgCode = listItemView.findViewById(R.id.orgCodeTV);
            TextView midCode = listItemView.findViewById(R.id.midCodeTV);
            TextView destCode = listItemView.findViewById(R.id.destCodeTV);
            TextView timeTaken = listItemView.findViewById(R.id.timeTakenTV);
            TextView takeoffTime = listItemView.findViewById(R.id.takeoffTV);
            TextView landingTime = listItemView.findViewById(R.id.landingTimeTV);
            TextView econPrice = listItemView.findViewById(R.id.econPrice);
            TextView busnPrice = listItemView.findViewById(R.id.busnPrice);
            Button econBook = listItemView.findViewById(R.id.econPriceBtn);
            Button busnBook = listItemView.findViewById(R.id.busnPriceBtn);


            Flight tempFlight = availableFlights.get(position).get(0).get(0);

            if (availableFlights.get(position).size() >= 2) {

                String middleAirports = "";

                // loop from first to last second flight
                for (int i = 0; i <= availableFlights.get(position).size() -2; i++) {
                    Flight midFlights = availableFlights.get(position).get(i).get(0);

                    middleAirports += "  " + midFlights.getArrival_icao();


                }
                midCode.setText(middleAirports);

                Flight lastFlight = availableFlights.get(position).get(availableFlights.get(position).size() -1).get(0);

                destCode.setText(lastFlight.getArrival_icao());
                String getLandingTime = parseTime(lastFlight.getFlight_arr_date_time());

                if (getLandingTime != null) {
                    landingTime.setText(getLandingTime);
                }

            } else if (availableFlights.get(position).size() == 1) {
                midCode.setText("");

                destCode.setText(tempFlight.getArrival_icao());

                String getLandingTime = parseTime(tempFlight.getFlight_arr_date_time());
                if (getLandingTime != null) {
                    landingTime.setText(getLandingTime);
                }

            }

//        Flight tempFlight = (Flight) getItem(position);


            String getTakeOffTime = parseTime(tempFlight.getFlight_dept_date_time());


            orgCode.setText(tempFlight.getDeparture_icao());
//        timeTaken.setText(tempFlight.getTimeTaken());         // TODO
            econPrice.setText("$"+ tempFlight.getEconPrice());
            busnPrice.setText("$" + tempFlight.getBusnPrice());

            if (getTakeOffTime != null) {
                takeoffTime.setText(getTakeOffTime);
            }

            econBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                LinearLayout ll = (LinearLayout) v.getParent();
//                TextView tv = ll.findViewById(R.id.econPrice);
//                String ePrice = tv.getText().toString();

                    Toast.makeText(mContext, "Departure in Adaptor, econ btn: " + flightResult.getDepartureStatus() + " Price " + orgCode.getText().toString(), Toast.LENGTH_LONG).show();

                    if (isOneWay) {
                        toNextActivity();

                    } else {
                        if (flightResult != null) {

                            if (flightResult.getDepartureStatus()) {
                                toNextActivity();
                            } else {
                                flightResult.setDepartureStatus(true);
                                displayReturnFlight();
                            }
                        }

                    }
                }
            });

            busnBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                LinearLayout ll = (LinearLayout) v.getParent();
//                TextView tv = ll.findViewById(R.id.busnPrice);
//                String bPrice = tv.getText().toString();

                    Toast.makeText(mContext, "Departure in Adaptor, busn btn: " + flightResult.getDepartureStatus() + " Price " + busnPrice.getText().toString(), Toast.LENGTH_LONG).show();

                    if (isOneWay) {
                        toNextActivity();

                    } else {
                        if (flightResult != null) {

                            if (flightResult.getDepartureStatus()) {
                                toNextActivity();
                            } else {
                                flightResult.setDepartureStatus(true);
                                displayReturnFlight();
                            }
                        }
                    }
                }
            });

        }

        return listItemView;
    }

    private String parseTime (String dateTime) {
        String timeOnly = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            // Parse the string to obtain a Date object
            Date date = dateFormat.parse(dateTime);

            // Define the format for extracting time
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

            // Format the Date object to extract time only
            if (date != null) {
                timeOnly = timeFormat.format(date);
            }

        } catch (ParseException e) {
            // Print an error message if parsing fails
            System.err.println("Error parsing date: " + e.getMessage());
        }

        return timeOnly;

    }
//
////    View.OnClickListener onewayBook = new View.OnClickListener() {
////        @Override
////        public void onClick(View v) {
//////            LinearLayout ll = (LinearLayout) v.getRootView();
//////
//////            TextView orgAirport = ll.findViewById(R.id.orgCodeTV);
//////            String txt = orgAirport.getText().toString();
////
////            Toast.makeText(mContext.getApplicationContext(), "One Way booking. Going next activity.. ", Toast.LENGTH_SHORT).show();
////
////            if (flightResult != null) {
////                Intent nextPageIntent = new Intent(flightResult, User_info.class);
////                flightResult.startActivity(nextPageIntent);
////            }
////        }
////    };

    private void toNextActivity() {
        Toast.makeText(mContext.getApplicationContext(), "Going next activity.. ", Toast.LENGTH_SHORT).show();

        if (flightResult != null) {
            Intent nextPageIntent = new Intent(flightResult, User_info.class);
            flightResult.startActivity(nextPageIntent);
        }
    }

    private void displayReturnFlight() {
        Toast.makeText(mContext.getApplicationContext(), "Round trip origin booking. showing return..", Toast.LENGTH_SHORT).show();

        // Show return flights...
        flightResult.updateFlights();
    }

//    public ArrayList<Flight> getAvailableFlights() {
//        return availableFlights;
//    }
//
    public void setAvailableFlights(List<List<List<Flight>>> availableFlights) {
        this.availableFlights = availableFlights;
    }
//
//    //    View.OnClickListener roundtripOrigin = new View.OnClickListener() {
////        @Override
////        public void onClick(View v) {
////            Toast.makeText(mContext.getApplicationContext(), "Round trip origin booking. showing return..", Toast.LENGTH_SHORT).show();
////
////            // Show return flight...
////        }
////    };
////
////    View.OnClickListener roundtripReturn = new View.OnClickListener() {
////        @Override
////        public void onClick(View v) {
////            Toast.makeText(mContext.getApplicationContext(), "Round trip return booking. Going next activity..", Toast.LENGTH_SHORT).show();
////
////            if (flightResult != null) {
////                Intent nextPageIntent = new Intent(flightResult, User_info.class);
////                flightResult.startActivity(nextPageIntent);
////            }
////        }
////    };
//
}
