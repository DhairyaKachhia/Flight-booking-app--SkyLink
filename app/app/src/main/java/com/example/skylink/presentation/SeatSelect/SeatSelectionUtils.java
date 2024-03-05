package com.example.skylink.presentation.SeatSelect;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.skylink.R;
import com.example.skylink.application.Services;
import com.example.skylink.business.Implementations.Payment;
import com.example.skylink.business.Implementations.PlaneConfiguration;
import com.example.skylink.business.Implementations.Session;
import com.example.skylink.business.Interface.iPayment;
import com.example.skylink.business.Interface.iPlaneConfiguration;
import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.objects.Interfaces.iPassengerData;
import com.example.skylink.presentation.Payment.CreditCardPaymentActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;
import android.widget.Toast;

public class SeatSelectionUtils {

    public static void setupFlightInformation(
            String typeOfFlight,
            TextView departingAirportTextView,
            TextView arrivingAirportTextView,
            TextView departureTimeTextView,
            TextView arrivalTimeTextView
    ) {
        HashMap<String, List<List<iFlight>>> selectedFlights = Session.getInstance().getSelectedFlights();

        if (selectedFlights != null && selectedFlights.containsKey(typeOfFlight)) {
            List<List<iFlight>> outboundFlights = selectedFlights.get(typeOfFlight);

            if (outboundFlights != null && !outboundFlights.isEmpty()) {
                iFlight firstFlight = outboundFlights.get(0).get(0);
                updateTextView(departingAirportTextView, "Departing Airport: " + firstFlight.getDeparture_icao());
                updateTextView(arrivingAirportTextView, "Arriving Airport: " + firstFlight.getArrival_icao());
                updateTextView(departureTimeTextView, "Departure Time: " + firstFlight.getFlight_dept_date_time());
                updateTextView(arrivalTimeTextView, "Arrival Time: " + firstFlight.getFlight_arr_date_time());

            }
        }
    }
    public static void setupPassengerSpinner(
            Spinner namesSpinner,
            HashMap<iPassengerData, String> seatMap,
            Activity activity,
            AtomicReference<iPassengerData> selectedPassenger
    ) {

        List<iPassengerData> passengers = Session.getInstance().getPassengerData();
        List<String> passengerNames = new ArrayList<>();

        for (iPassengerData passenger : passengers) {
            seatMap.put(passenger, "Not Selected");
            passengerNames.add(passenger.getFirstName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                activity,
                android.R.layout.simple_spinner_item,
                passengerNames
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        namesSpinner.setAdapter(adapter);

        namesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedPassenger.set(passengers.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here if nothing is selected
            }
        });
    }

    public static LinearLayout setupSeatsLayout(String typeOfFlight, Context context, Map<String, SeatStatus> seatStatusMap, HashMap<iPassengerData, String> seatMap, AtomicReference<iPassengerData> selectedPassenger) {
        LinearLayout flightLayout = new LinearLayout(context);
        flightLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        flightLayout.setOrientation(LinearLayout.VERTICAL);

        String econOrBus =  Session.getInstance().getpriceType().get("Price");
        iPlaneConfiguration config = new PlaneConfiguration(Services.getFlightDatabase());
        String [] plane_config;
        HashMap<String, List<List<iFlight>>> selectedFlight = Session.getInstance().getSelectedFlights();
        if (selectedFlight != null && selectedFlight.containsKey(typeOfFlight)) {
            List<List<iFlight>> outboundFlights = selectedFlight.get(typeOfFlight);
            if (outboundFlights != null) {
                iFlight firstFlight = outboundFlights.get(0).get(0);
                if(econOrBus != null && econOrBus.equals("Economy")){
                    plane_config = config.getPlaneConfiguration(firstFlight.getAirCraft_Type(),"Economy");
                    addSeatsToLayout(context, seatStatusMap, flightLayout, plane_config, seatMap, selectedPassenger,"FillUpBusiness");
                }else{
                    plane_config = config.getPlaneConfiguration(firstFlight.getAirCraft_Type(),"Business");
                    addSeatsToLayout(context, seatStatusMap, flightLayout, plane_config, seatMap, selectedPassenger, "FillUpEconomy");
                }
            }
        }
        return flightLayout;
    }


    private static void addSeatsToLayout(Context context, Map<String, SeatStatus> seatStatusMap, LinearLayout flightLayout, String[] planeConfig, HashMap<iPassengerData, String> seatMap, AtomicReference<iPassengerData> selectedPassenger, String fillup) {
        String numSeatBusi, numRowBusi, numSeatEcon, numRowEcon;
        numSeatBusi  = planeConfig[1];
        numRowBusi  = planeConfig[2];
        numSeatEcon = planeConfig[3];
        numRowEcon = planeConfig[4];

        if(fillup.equals("FillUpBusiness")){
            addClassSeatsToLayout(context, seatStatusMap, flightLayout, Integer.parseInt(numRowBusi), Integer.parseInt(numSeatBusi), 1, true, seatMap, selectedPassenger, true);
            addClassSeatsToLayout(context, seatStatusMap, flightLayout, Integer.parseInt(numRowEcon), Integer.parseInt(numSeatEcon), Integer.parseInt(numRowBusi), false, seatMap, selectedPassenger, false);
        }else if (fillup.equals("FillUpEconomy")){
            addClassSeatsToLayout(context, seatStatusMap, flightLayout, Integer.parseInt(numRowBusi), Integer.parseInt(numSeatBusi), 1, true, seatMap, selectedPassenger, false);
            addClassSeatsToLayout(context, seatStatusMap, flightLayout, Integer.parseInt(numRowEcon), Integer.parseInt(numSeatEcon), Integer.parseInt(numRowBusi), false, seatMap, selectedPassenger, true);
        }
    }

    private static void addClassSeatsToLayout(Context context, Map<String, SeatStatus> seatStatusMap, LinearLayout flightLayout, int numRows, int numSeatsPerRow, int startSeatNumber, boolean isBusinessClass, HashMap<iPassengerData, String> seatMap, AtomicReference<iPassengerData> selectedPassenger, Boolean randomVal) {
        Random random = new Random();

        for (int row = startSeatNumber; row < startSeatNumber + numRows; row++) {
            LinearLayout rowLayout = new LinearLayout(context);
            rowLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);
            rowLayout.setTag(String.valueOf(row));

            int halfNumSeatsPerRow = numSeatsPerRow / 2;

            seatVal(context, rowLayout, row, 1, (halfNumSeatsPerRow+1), isBusinessClass, randomVal, random, seatStatusMap, seatMap, selectedPassenger);

            addAisleToLayout(context, flightLayout, 120); // Adjust the aisle width as needed

            seatVal(context, rowLayout, row, halfNumSeatsPerRow, halfNumSeatsPerRow * 2, isBusinessClass, randomVal, random, seatStatusMap, seatMap, selectedPassenger);

            flightLayout.addView(rowLayout);
        }
    }

    private static void seatVal(Context context, LinearLayout rowLayout, int row, int start, int end, boolean isBusinessClass, boolean randomVal, Random random, Map<String, SeatStatus> seatStatusMap, HashMap<iPassengerData, String> seatMap, AtomicReference<iPassengerData> selectedPassenger) {
        for (int seatNumber = start + 1; seatNumber <= end; seatNumber++) {
            boolean isSeatTaken = randomVal ? true : random.nextBoolean();
            addSeatToLayout(context, rowLayout, row, seatNumber, isBusinessClass, isSeatTaken, seatStatusMap, seatMap, selectedPassenger);
            seatStatusMap.put(getSeatKey(row, seatNumber), new SeatStatus(isBusinessClass, isSeatTaken));
        }
    }


    private static void addAisleToLayout(Context context, LinearLayout flightLayout, int aisleWidth) {
        Space aisleSpace = new Space(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                aisleWidth,
                LinearLayout.LayoutParams.MATCH_PARENT);
        aisleSpace.setLayoutParams(layoutParams);
        flightLayout.addView(aisleSpace);
    }



    private static void addSeatToLayout(Context context, LinearLayout flightLayout, int row, int seatNumber, boolean isBusinessClass, boolean isSeatTaken, Map<String, SeatStatus> seatStatusMap, HashMap<iPassengerData, String> seatMap, AtomicReference<iPassengerData> selectedPassenger) {
        LinearLayout seatContainer = new LinearLayout(context);
        seatContainer.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        seatContainer.setOrientation(LinearLayout.VERTICAL);

        TextView seatNumberText = new TextView(context);
        seatNumberText.setText(String.valueOf(row) + getSeatLetter(seatNumber));
        seatNumberText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        seatContainer.addView(seatNumberText);

        ImageView seatImage = new ImageView(context);
        LinearLayout.LayoutParams imageLayoutParams = new LinearLayout.LayoutParams(
                150, 150);
        imageLayoutParams.setMargins(25, 25, 25, 25);
        seatImage.setLayoutParams(imageLayoutParams);

        if (isBusinessClass) {
            seatImage.setImageResource(R.drawable.firstclass_seat);
        } else {
            seatImage.setImageResource(R.drawable.economy_seat);
        }

        if (isSeatTaken) {
            seatImage.setColorFilter(context.getResources().getColor(R.color.redTint));
        } else {
            seatImage.setColorFilter(null);
        }

        seatImage.setOnClickListener(view -> toggleSeat(seatImage, row, seatNumber, seatStatusMap, seatMap, selectedPassenger, context));
        seatContainer.addView(seatImage);

        flightLayout.addView(seatContainer);
    }

    private static void toggleSeat(ImageView seatContainer, int row, int seatNumber, Map<String, SeatStatus> seatStatusMap, HashMap<iPassengerData, String> seatMap, AtomicReference<iPassengerData> selectedPassenger, Context context) {
        String seatKey = getSeatKey(row, seatNumber);

        SeatStatus seatStatus = seatStatusMap.get(seatKey);

        if (seatStatus == null || seatStatus.isTaken()) {
            return;
        }

        int seatType = seatStatus.isBusinessClass() ? R.drawable.firstclass_seat : R.drawable.economy_seat;
        boolean isSelected = seatContainer.isSelected();

        // Retrieve the passenger for the selected seat
        iPassengerData passenger = selectedPassenger.get();

        if (passenger == null) {
            return;
        }

        if (!isSelected) {
            String currentSeat = seatMap.get(passenger);

            // Check if the passenger already has a selected seat
            if (currentSeat == null || currentSeat.equals("Not Selected")) {
                seatMap.put(passenger, row + getSeatLetter(seatNumber));
                seatContainer.setImageResource(seatType);
                seatContainer.setColorFilter(context.getResources().getColor(R.color.greenColor));
                seatContainer.setSelected(true);
            } else {
                // Passenger already has a selected seat, handle accordingly (maybe show a message)
                Toast.makeText(context, "Passenger already has a selected seat", Toast.LENGTH_SHORT).show();
            }
        } else {
            seatMap.put(passenger, "Not Selected");
            seatContainer.setImageResource(seatType);
            seatContainer.setColorFilter(null);
            seatContainer.setSelected(false);
        }


    }

    public static void setupConfirmButton(Context context, Button myButton, Activity activity, HashMap<iPassengerData, String> seatMap, boolean returnFlight) {
        myButton.setOnClickListener(v -> {
            long notSelectedCount = countNotSelected(seatMap);
            if (notSelectedCount > 0) {
                Toast.makeText(context, "Please select seats for all passengers", Toast.LENGTH_SHORT).show();
            } else {
                if (!returnFlight) {
                    Intent intent = new Intent(activity, InboundActivity.class);
                    activity.startActivity(intent);
                } else {
                    handlePaymentActivity(activity, seatMap);
                }
            }
        });
    }

    private static long countNotSelected(HashMap<iPassengerData, String> seatMap){
        return seatMap.values().stream().filter(status -> status.equals("Not Selected")).count();
    }

    private static void handlePaymentActivity(Activity activity, HashMap<iPassengerData, String> seatMap) {
        Session.getInstance().setSeatMap(seatMap);
        iPayment pay = new Payment();
        Session.getInstance().setPay(pay);
        pay.generateInvoice();
        Intent intent = new Intent(activity, CreditCardPaymentActivity.class);
        activity.startActivity(intent);
    }




    private static String getSeatLetter(int seatNumber) {
        char seatLetter = (char) ('A' + (seatNumber - 1));
        return String.valueOf(seatLetter);
    }

    private static String getSeatKey(int row, int seatNumber) {
        return row + getSeatLetter(seatNumber);
    }
    private static void updateTextView(TextView textView, String newText) {
        textView.setText(newText);
    }

}





