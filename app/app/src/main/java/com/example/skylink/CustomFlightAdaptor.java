package com.example.skylink;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomFlightAdaptor extends BaseAdapter {

    Context mContext;
    private Flight_search flightResult;
    ArrayList<Flight> availableFlights = new ArrayList<>();
    boolean isOneWay;

    public CustomFlightAdaptor(Context context, ArrayList<Flight> availableFlights, boolean isOneWay) {
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
    public Object getItem(int position) {
        return availableFlights.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.flight_result, parent, false);
        }

        Flight tempFlight = (Flight) getItem(position);

        TextView orgCode = convertView.findViewById(R.id.orgCodeTV);
        TextView destCode = convertView.findViewById(R.id.destCodeTV);
        TextView timeTaken = convertView.findViewById(R.id.timeTakenTV);
        TextView takeoffTime = convertView.findViewById(R.id.takeoffTV);
        TextView landingTime = convertView.findViewById(R.id.landingTimeTV);
        TextView econPrice = convertView.findViewById(R.id.econPrice);
        TextView busnPrice = convertView.findViewById(R.id.busnPrice);
        Button econBook = convertView.findViewById(R.id.econPriceBtn);
        Button busnBook = convertView.findViewById(R.id.busnPriceBtn);


        econBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LinearLayout ll = (LinearLayout) v.getParent();
                TextView tv = ll.findViewById(R.id.econPrice);
                String ePrice = tv.getText().toString();

                Toast.makeText(mContext, "Departure in Adaptor, econ btn: " + flightResult.getDepartureStatus() + " Price " + orgCode.getText().toString(), Toast.LENGTH_LONG).show();

                if (isOneWay) {
                    toNextActivity();

                } else {
                    if (flightResult != null) {

                        if (flightResult.getDepartureStatus()) {
                            toNextActivity();
                        } else {
                            displayReturnFlight();
                            flightResult.setDepartureStatus(true);
                        }
                    }

                }
            }
        });

        busnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LinearLayout ll = (LinearLayout) v.getParent();
                TextView tv = ll.findViewById(R.id.busnPrice);
                String bPrice = tv.getText().toString();

                Toast.makeText(mContext, "Departure in Adaptor, busn btn: " + flightResult.getDepartureStatus() + " Price " + bPrice, Toast.LENGTH_LONG).show();

                if (isOneWay) {
                    toNextActivity();

                } else {
                    if (flightResult != null) {

                        if (flightResult.getDepartureStatus()) {
                            toNextActivity();
                        } else {
                            displayReturnFlight();
                            flightResult.setDepartureStatus(true);
                        }
                    }
                }
            }
        });


//        if (isOneWay) {
//            econBook.setOnClickListener(onewayBook);
//            busnBook.setOnClickListener(onewayBook);
//        } else {
//
//            Toast.makeText(mContext, "Departure in Adaptor: " + flightResult.getDepartureStatus(), Toast.LENGTH_LONG).show();
//
//            if (flightResult != null) {
//
//                if (flightResult.getDepartureStatus()) {
//                    econBook.setOnClickListener(roundtripReturn);
//                    busnBook.setOnClickListener(roundtripReturn);
//                } else {
//
//
//                    econBook.setOnClickListener(roundtripOrigin);
//                    busnBook.setOnClickListener(roundtripOrigin);
//
//                    flightResult.setDepartureStatus(true);
//                }
//            }
//        }

        orgCode.setText(tempFlight.getOriginAirportCode());
        destCode.setText(tempFlight.getDestAirportCode());
        timeTaken.setText(tempFlight.getTimeTaken());
        takeoffTime.setText(tempFlight.getTakeoffTime());
        landingTime.setText(tempFlight.getLandingTime());
        econPrice.setText("$"+ tempFlight.getEconPrice());
        busnPrice.setText("$" + tempFlight.getBusnPrice());

        return convertView;
    }

//    View.OnClickListener onewayBook = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
////            LinearLayout ll = (LinearLayout) v.getRootView();
////
////            TextView orgAirport = ll.findViewById(R.id.orgCodeTV);
////            String txt = orgAirport.getText().toString();
//
//            Toast.makeText(mContext.getApplicationContext(), "One Way booking. Going next activity.. ", Toast.LENGTH_SHORT).show();
//
//            if (flightResult != null) {
//                Intent nextPageIntent = new Intent(flightResult, User_info.class);
//                flightResult.startActivity(nextPageIntent);
//            }
//        }
//    };

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

    }

//    View.OnClickListener roundtripOrigin = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Toast.makeText(mContext.getApplicationContext(), "Round trip origin booking. showing return..", Toast.LENGTH_SHORT).show();
//
//            // Show return flight...
//        }
//    };
//
//    View.OnClickListener roundtripReturn = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Toast.makeText(mContext.getApplicationContext(), "Round trip return booking. Going next activity..", Toast.LENGTH_SHORT).show();
//
//            if (flightResult != null) {
//                Intent nextPageIntent = new Intent(flightResult, User_info.class);
//                flightResult.startActivity(nextPageIntent);
//            }
//        }
//    };

}
