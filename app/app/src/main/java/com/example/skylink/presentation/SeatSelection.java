package com.example.skylink.presentation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.skylink.R;
import com.example.skylink.business.Implementations.Payment;
import com.example.skylink.business.Implementations.Session;
import com.example.skylink.business.Interface.iPayment;
import com.example.skylink.objects.Implementations.Flight;
import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.objects.Interfaces.iPassengerData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SeatSelection extends AppCompatActivity {

    private Map<String, SeatStatus> seatStatusMap = new HashMap<>();
    private iPassengerData selectedPassenger;

    // Declare the TextViews
    private TextView departingAirportTextView;
    private TextView arrivingAirportTextView;
    private TextView departureTimeTextView;
    private TextView arrivalTimeTextView;
    HashMap<iPassengerData, String> seatMap = new HashMap<>();


    // Declare the TextViews
    private TextView departingAirportTextView;
    private TextView arrivingAirportTextView;
    private TextView departureTimeTextView;
    private TextView arrivalTimeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.seat_selection);

        departingAirportTextView = findViewById(R.id.departingAirportTextView);
        arrivingAirportTextView = findViewById(R.id.arrivingAirportTextView);
        departureTimeTextView = findViewById(R.id.departureTimeTextView);
        arrivalTimeTextView = findViewById(R.id.arrivalTimeTextView);

        HashMap<String, List<List<iFlight>>> selectedFlights = Session.getInstance().getSelectedFlights();

        if (selectedFlights != null && selectedFlights.containsKey("Outbound")) {
             List<List<iFlight>> inboundFlights = selectedFlights.get("Outbound");

            if (inboundFlights != null && !inboundFlights.isEmpty()) {
                iFlight firstFlight = inboundFlights.get(0).get(0);

                // Extract relevant information from the Flight object
                String departAirport = firstFlight.getDeparture_icao();
                String arriveAirport = firstFlight.getArrival_icao();
                String departureTime = firstFlight.getFlight_dept_date_time();
                String arrivalTime = firstFlight.getFlight_arr_date_time();

                // Example: Update the Departing Airport TextView
                updateTextView(departingAirportTextView, "Departing Airport: " + departAirport);
                // Example: Update the Arriving Airport TextView
                updateTextView(arrivingAirportTextView, "Arriving Airport: " + arriveAirport);
                // Example: Update the Departure Time TextView
                updateTextView(departureTimeTextView, "Departure Time: " + departureTime);
                // Example: Update the Arrival Time TextView
                updateTextView(arrivalTimeTextView, "Arrival Time: " + arrivalTime);
            }



            List<iPassengerData> passengers = Session.getInstance().getPassengerData();
            Spinner namesSpinner = findViewById(R.id.namesSpinner);

            List<String> passengerNames = new ArrayList<>();

            for (iPassengerData passenger : passengers) {
                seatMap.put(passenger, "Not Selected");
                passengerNames.add(passenger.getFirstName());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_spinner_item,
                    passengerNames
            );

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            namesSpinner.setAdapter(adapter);

            namesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    // Handle the selected passenger here
                    selectedPassenger = passengers.get(position);
                    // You can perform any action with the selected passenger
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // Do nothing here if nothing is selected
                }
            });



            String[][] planeConfigurations = {
                    {"Boeing 737", "4", "6", "6", "18"},
                    {"Airbus A320", "4", "7", "6", "14"},
                    {"Embraer E190", "4", "4", "6", "10"},
                    {"Boeing 777", "4", "8", "6", "120"},
                    {"Bombardier Q400", "4", "4", "6", "8"}
            };

            addSeatsToLayout(planeConfigurations[2]);
            Button myButton = findViewById(R.id.myButton);
            myButton.setOnClickListener(v -> {
                boolean allNotSelected = true;
                for (String seatStatus : seatMap.values()) {
                    if (!seatStatus.equals("Not Selected")) {
                        allNotSelected = false;
                        break;
                    }
                }
                if (allNotSelected) {
                    Session.getInstance().setSeatMap(seatMap);
                    iPayment pay = new Payment();
                    Session.getInstance().setPay(pay);
                    pay.generateInvoice();
                    Intent intent = new Intent(SeatSelection.this, CreditCardPaymentActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(SeatSelection.this, "Please select seats for all passengers", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void addSeatsToLayout(String[] planeConfig) {
        LinearLayout flightLayout = findViewById(R.id.Flight_Layout);

        String numSeatBusi, numRowBusi, numSeatEcon, numRowEcon;
        numSeatBusi = planeConfig[1];
        numRowBusi = planeConfig[2];
        numSeatEcon = planeConfig[3];
        numRowEcon = planeConfig[4];

        addClassSeatsToLayout(flightLayout, Integer.parseInt(numRowBusi), Integer.parseInt(numSeatBusi), 1, true);

        addClassSeatsToLayout(flightLayout, Integer.parseInt(numRowEcon), Integer.parseInt(numSeatEcon), 6, false);
    }

    private void addClassSeatsToLayout(LinearLayout flightLayout, int numRows, int numSeatsPerRow, int startSeatNumber, boolean isBusinessClass) {
        Random random = new Random();

        for (int row = startSeatNumber; row < startSeatNumber + numRows; row++) {
            LinearLayout rowLayout = new LinearLayout(this);
            rowLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);
            rowLayout.setTag(String.valueOf(row)); // Set the row as a tag

            int halfNumSeatsPerRow = numSeatsPerRow / 2;

            for (int seatNumber = 1; seatNumber <= halfNumSeatsPerRow; seatNumber++) {
                boolean isSeatTaken = random.nextBoolean();
                addSeatToLayout(rowLayout, row, seatNumber, isBusinessClass, isSeatTaken);
                // Save the seat status in the Map
                seatStatusMap.put(getSeatKey(row, seatNumber), new SeatStatus(isBusinessClass, isSeatTaken));
            }

            // Add aisle
            addAisleToLayout(rowLayout);

            for (int seatNumber = halfNumSeatsPerRow + 1; seatNumber <= halfNumSeatsPerRow * 2; seatNumber++) {
                boolean isSeatTaken = random.nextBoolean();
                addSeatToLayout(rowLayout, row, seatNumber, isBusinessClass, isSeatTaken);
                // Save the seat status in the Map
                seatStatusMap.put(getSeatKey(row, seatNumber), new SeatStatus(isBusinessClass, isSeatTaken));
            }

            flightLayout.addView(rowLayout);
        }
    }

    @SuppressLint("SetTextI18n")
    private void addSeatToLayout(LinearLayout flightLayout, int row, int seatNumber, boolean isBusinessClass, boolean isSeatTaken) {
        // Create a vertical linear layout to contain the seat image and seat number
        LinearLayout seatContainer = new LinearLayout(this);
        seatContainer.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        seatContainer.setOrientation(LinearLayout.VERTICAL);

        // Create the TextView for the seat number
        TextView seatNumberText = new TextView(this);
        seatNumberText.setText(String.valueOf(row) + getSeatLetter(seatNumber));
        seatNumberText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        seatContainer.addView(seatNumberText);

        // Create the ImageView for the seat image
        ImageView seatImage = new ImageView(this);
        LinearLayout.LayoutParams imageLayoutParams = new LinearLayout.LayoutParams(
                150, 150); // Adjust the width and height as needed
        imageLayoutParams.setMargins(25, 25, 25, 25); // Adjust margins as needed
        seatImage.setLayoutParams(imageLayoutParams);

        // Set the seat image based on whether it's a business class seat and whether it's taken
        if (isBusinessClass) {
            seatImage.setImageResource(R.drawable.firstclass_seat);
        } else {
            seatImage.setImageResource(R.drawable.economy_seat);
        }

        // Check if the seat is taken
        if (isSeatTaken) {
            seatImage.setColorFilter(getResources().getColor(R.color.redTint)); // Replace R.color.redTint with the actual resource ID of your red color
        } else {
            seatImage.setColorFilter(null); // Remove any existing color filter
        }

        seatImage.setOnClickListener(view -> toggleSeat(seatImage, row, seatNumber));
        seatContainer.addView(seatImage);

        // Add the vertical linear layout to the main layout (flightLayout)
        flightLayout.addView(seatContainer);
    }

    // Function to get a unique key for each seat based on row and seatNumber
    private String getSeatKey(int row, int seatNumber) {
        return row + "_" + seatNumber;
    }

    private void toggleSeat(ImageView seatContainer, int row, int seatNumber) {
        String seatKey = getSeatKey(row, seatNumber);

        SeatStatus seatStatus = seatStatusMap.get(seatKey);

        if (seatStatus == null || seatStatus.isTaken()) {
            return;
        }

        int seatType = seatStatus.isBusinessClass() ? R.drawable.firstclass_seat : R.drawable.economy_seat;
        boolean isSelected = seatContainer.isSelected();

        // Retrieve the passenger for the selected seat
        iPassengerData passenger = selectedPassenger;

        if (passenger == null) {
            return;
        }
        seatContainer.setSelected(!isSelected);
        if (isSelected) {
            // If already selected, set the default seat image with no tint
            seatContainer.setImageResource(seatType);
            seatContainer.setColorFilter(null); // Remove any existing color filter
            seatMap.put(passenger, "Not Selected");
        } else {
            // If not selected, set the seat image and apply red tint
            seatMap.put(passenger, row + getSeatLetter(seatNumber));
            seatContainer.setImageResource(seatType);
            seatContainer.setColorFilter(getResources().getColor(R.color.greenColor)); // Replace R.color.redTint with the actual resource ID of your red color
        }
    }

    private void addAisleToLayout(LinearLayout flightLayout) {
        View aisleView = new View(this);

        // Set layout parameters for a horizontal aisle
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                80, // Aisle width
                LinearLayout.LayoutParams.MATCH_PARENT);
        aisleView.setLayoutParams(layoutParams);

        aisleView.setBackgroundColor(getResources().getColor(android.R.color.white)); // Aisle color
        flightLayout.addView(aisleView);
    }

    private String getSeatLetter(int seatNumber) {
        char seatLetter = (char) ('A' + (seatNumber - 1));
        return String.valueOf(seatLetter);
    }

    // Class to represent seat status
    private static class SeatStatus {
        private boolean isBusinessClass;
        private boolean isTaken;

        SeatStatus(boolean isBusinessClass, boolean isTaken) {
            this.isBusinessClass = isBusinessClass;
            this.isTaken = isTaken;
        }

        boolean isBusinessClass() {
            return isBusinessClass;
        }

        boolean isTaken() {
            return isTaken;
        }
    }
    private void updateTextView(TextView textView, String newText) {
        textView.setText(newText);
    }
}
