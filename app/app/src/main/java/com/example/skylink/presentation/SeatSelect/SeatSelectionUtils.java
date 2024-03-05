package com.example.skylink.presentation.SeatSelect;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    public static LinearLayout setupSeatsLayout(String typeOfFlight, Context context, Map<String, SeatStatus> seatStatusMap, Map<iPassengerData, String> seatMap, AtomicReference<iPassengerData> selectedPassenger) {
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
                    addSeatsToLayout(context, seatStatusMap, flightLayout, plane_config, seatMap, selectedPassenger);
                }else{
                    plane_config = config.getPlaneConfiguration(firstFlight.getAirCraft_Type(),"Business");
                    addSeatsToLayout(context, seatStatusMap, flightLayout, plane_config, seatMap, selectedPassenger);
                }
            }
        }
        return flightLayout;
    }


    private static void addSeatsToLayout(Context context, Map<String, SeatStatus> seatStatusMap, LinearLayout flightLayout, String[] planeConfig, Map<iPassengerData, String> seatMap, AtomicReference<iPassengerData> selectedPassenger) {
        String numSeatBusi, numRowBusi, numSeatEcon, numRowEcon;
        numSeatBusi = planeConfig[1];
        numRowBusi = planeConfig[2];
        numSeatEcon = planeConfig[3];
        numRowEcon = planeConfig[4];
        int startSeatNum = Integer.parseInt(numSeatBusi) * Integer.parseInt(numRowBusi);
        addClassSeatsToLayout(context, seatStatusMap, flightLayout, Integer.parseInt(numRowBusi), Integer.parseInt(numSeatBusi), 1, true, seatMap, selectedPassenger);
        addClassSeatsToLayout(context, seatStatusMap, flightLayout, Integer.parseInt(numRowEcon), Integer.parseInt(numSeatEcon), startSeatNum, false, seatMap, selectedPassenger);
    }


    private static void addClassSeatsToLayout(Context context, Map<String, SeatStatus> seatStatusMap, LinearLayout flightLayout, int numRows, int numSeatsPerRow, int startSeatNumber, boolean isBusinessClass, Map<iPassengerData, String> seatMap, AtomicReference<iPassengerData> selectedPassenger) {
        Random random = new Random();

        for (int row = startSeatNumber; row < startSeatNumber + numRows; row++) {
            LinearLayout rowLayout = new LinearLayout(context);
            rowLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);
            rowLayout.setTag(String.valueOf(row)); // Set the row as a tag

            int halfNumSeatsPerRow = numSeatsPerRow / 2;

            for (int seatNumber = 1; seatNumber <= halfNumSeatsPerRow; seatNumber++) {
                boolean isSeatTaken = random.nextBoolean();
                addSeatToLayout(context, rowLayout, row, seatNumber, isBusinessClass, isSeatTaken, seatStatusMap, seatMap, selectedPassenger);
                seatStatusMap.put(getSeatKey(row, seatNumber), new SeatStatus(isBusinessClass, isSeatTaken));
            }

            addAisleToLayout(context, flightLayout);

            for (int seatNumber = halfNumSeatsPerRow + 1; seatNumber <= halfNumSeatsPerRow * 2; seatNumber++) {
                boolean isSeatTaken = random.nextBoolean();
                addSeatToLayout(context, rowLayout, row, seatNumber, isBusinessClass, isSeatTaken, seatStatusMap, seatMap, selectedPassenger);
                seatStatusMap.put(getSeatKey(row, seatNumber), new SeatStatus(isBusinessClass, isSeatTaken));
            }

            flightLayout.addView(rowLayout);
        }
    }


    private static void addAisleToLayout(Context context, LinearLayout flightLayout) {
        View aisleView = new View(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                80, // Aisle width
                LinearLayout.LayoutParams.MATCH_PARENT);
        aisleView.setLayoutParams(layoutParams);
        aisleView.setBackgroundColor(context.getResources().getColor(android.R.color.white));
        flightLayout.addView(aisleView);
    }

    private static void addSeatToLayout(Context context, LinearLayout flightLayout, int row, int seatNumber, boolean isBusinessClass, boolean isSeatTaken, Map<String, SeatStatus> seatStatusMap, Map<iPassengerData, String> seatMap, AtomicReference<iPassengerData> selectedPassenger) {
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

    private static void toggleSeat(ImageView seatContainer, int row, int seatNumber, Map<String, SeatStatus> seatStatusMap, Map<iPassengerData, String> seatMap, AtomicReference<iPassengerData> selectedPassenger, Context context) {
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

        if (isSelected) {
            seatContainer.setImageResource(seatType);
            seatContainer.setColorFilter(null); // Remove any existing color filter
            seatMap.put(passenger, "Not Selected");
        } else {
            // If not selected, set the seat image and apply green tint
            seatMap.put(passenger, row + getSeatLetter(seatNumber));
            seatContainer.setImageResource(seatType);
            seatContainer.setColorFilter(context.getResources().getColor(R.color.greenColor)); // Replace R.color.greenColor with the actual resource ID of your green color
        }

        // Toggle the selected state
        seatContainer.setSelected(!isSelected);
    }

    public static void setupConfirmButton(Context context, Button myButton, Activity activity, HashMap<iPassengerData, String> seatMap, boolean returnFlight) {
        myButton.setOnClickListener(v -> {
            boolean allNotSelected = seatMap.values().stream().allMatch(status -> status.equals("Not Selected"));
            if (allNotSelected) {
                if (returnFlight) {
                    Intent intent = new Intent(activity, InboundActivity.class);
                    activity.startActivity(intent);
                } else {
                    handlePaymentActivity(activity, seatMap);
                }
            } else {
                Toast.makeText(context, "Please select seats for all passengers", Toast.LENGTH_SHORT).show();
            }
        });
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





