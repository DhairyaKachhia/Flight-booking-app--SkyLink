package com.example.skylink.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.skylink.R;
import com.example.skylink.business.Session;
import com.example.skylink.objects.Flight;
import java.util.ArrayList;
import java.util.List;

public class PaymentSuccessfulActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_successful);
        Session session = Session.getInstance();
        displaySessionInfo(session);
        Button buttonMainMenu = findViewById(R.id.buttonMainMenu);
        buttonMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentSuccessfulActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void displaySessionInfo(Session session) {

        String passengerName = "Yiming";
        String gender = "Male";
        String dob = "01/01/1980";
        String address = "Winnipeg, MB";

        List<Flight> fakeFlights = generateFakeFlights();
        String flightDetails = generateFlightDetails(fakeFlights);
        TextView textViewName = findViewById(R.id.textViewName);
        textViewName.setText("Passenger Name: " + passengerName);

        TextView textViewGender = findViewById(R.id.textViewGender);
        textViewGender.setText("Gender: " + gender);

        TextView textViewDOB = findViewById(R.id.textViewDOB);
        textViewDOB.setText("Date of Birth: " + dob);

        TextView textViewAddress = findViewById(R.id.textViewAddress);
        textViewAddress.setText("Billing Address: " + address);

        TextView textViewFlightDetails = findViewById(R.id.textViewFlightDetails);
        textViewFlightDetails.setText(flightDetails);
    }

    private List<Flight> generateFakeFlights() {
        List<Flight> fakeFlights = new ArrayList<>();
        // Fake flight info
        fakeFlights.add(new Flight("AC26", "PVG", "YVR", "2024-02-21 09:00", "2024-02-22 12:00","boeing737","1","5",500,1000));
        return fakeFlights;
    }

    private String generateFlightDetails(List<Flight> flights) {
        StringBuilder sb = new StringBuilder();
        for (Flight flight : flights) {
            sb.append("Flight ").append(flight.getFlightNumber()).append("\n")
                    .append("From: ").append(flight.getDeparture_icao()).append("\n")
                    .append("To: ").append(flight.getArrival_icao()).append("\n")
                    .append("Departure: ").append(flight.getFlight_dept_date_time()).append("\n")
                    .append("Arrival: ").append(flight.getFlight_arr_date_time()).append("\n\n");
        }
        return sb.toString();
    }
}
