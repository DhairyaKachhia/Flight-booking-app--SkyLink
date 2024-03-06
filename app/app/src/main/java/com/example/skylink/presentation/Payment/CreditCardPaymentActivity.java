package com.example.skylink.presentation.Payment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.skylink.R;
import com.example.skylink.objects.Session;
import com.example.skylink.objects.ISession;
import com.example.skylink.business.validations.IValidatePayment;
import com.example.skylink.business.validations.ValidatePayment;
import com.example.skylink.objects.Interfaces.iFlight;
import com.example.skylink.objects.Interfaces.iFlightSearch;
import com.google.android.material.card.MaterialCardView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CreditCardPaymentActivity extends AppCompatActivity {

    private EditText cardNum, expiryDate, cvv, cardholderName, billingAddress;
    private ISession session = Session.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card_payment);

        checkTripWay();

        updateReviewSection();

        cardNum = findViewById(R.id.etCreditCardNumber);
        expiryDate = findViewById(R.id.etExpirationDate);
        cvv = findViewById(R.id.etCVV);
        cardholderName = findViewById(R.id.etCardholderName);
        billingAddress = findViewById(R.id.etBillingAddress);


        Button btnPay = findViewById(R.id.btnPay);
        btnPay.setOnClickListener(v -> {

            if (isValid()) {
                addToSession();

                Intent intent = new Intent(CreditCardPaymentActivity.this, PaymentSuccessfulActivity.class);
                startActivity(intent);
            }
        });
    }

    private void checkTripWay() {
        iFlightSearch flightSearch = session.getFlightSearch();

        if (flightSearch != null) {
            boolean isOneWay = flightSearch.isOneWay();

            MaterialCardView inboundCard = findViewById(R.id.inboundCard);

            if (isOneWay) {
                inboundCard.setVisibility(View.GONE);
            } else {
                inboundCard.setVisibility(View.VISIBLE);
            }

        }
    }

    private void updateReviewSection() {

        TextView departOriginCode, departTakeoffTime, departMidCode, departDestCode, departLandingTime;
        TextView returnOriginCode, returnTakeoffTime, returnMidCode, returnDestCode, returnLandingTime;
        TextView totalPrice;

        departOriginCode = findViewById(R.id.departOrgCodeTV);
        departTakeoffTime = findViewById(R.id.departTakeoffTV);
        departMidCode = findViewById(R.id.departMidCodeTV);
        departDestCode = findViewById(R.id.departDestCodeTV);
        departLandingTime = findViewById(R.id.departLandingTimeTV);

        returnOriginCode = findViewById(R.id.returnOrgCodeTV);
        returnTakeoffTime = findViewById(R.id.returnTakeoffTV);
        returnMidCode = findViewById(R.id.returnMidCodeTV);
        returnDestCode = findViewById(R.id.returnDestCodeTV);
        returnLandingTime = findViewById(R.id.returnLandingTimeTV);

        totalPrice = findViewById(R.id.totalPrice);

        MaterialCardView inboundCard = findViewById(R.id.inboundCard);
        HashMap<String, List<List<iFlight>>> selectedFlight = session.getSelectedFlights();

        // setup outbound card view
        if (selectedFlight != null && selectedFlight.containsKey("Outbound")) {
            List<List<iFlight>> outboundFlights = selectedFlight.get("Outbound");

            if (outboundFlights != null) {

                iFlight firstFlight = outboundFlights.get(0).get(0);

                departOriginCode.setText(firstFlight.getDeparture_icao());
                departTakeoffTime.setText(parseTime(firstFlight.getFlight_dept_date_time()));

                if (outboundFlights.size() > 1) {
                    String middleAirports = "";

                    middleAirports += firstFlight.getArrival_icao();
                    departMidCode.setText(middleAirports);

                    iFlight lastFlight = outboundFlights.get(1).get(0);

                    departDestCode.setText(lastFlight.getArrival_icao());
                    String getLandingTime = parseTime(lastFlight.getFlight_arr_date_time());

                    if (getLandingTime != null) {
                        departLandingTime.setText(getLandingTime);
                    }

                } else {
                    departMidCode.setText("");

                    departDestCode.setText(firstFlight.getArrival_icao());

                    String getLandingTime = parseTime(firstFlight.getFlight_arr_date_time());
                    if (getLandingTime != null) {
                        departLandingTime.setText(getLandingTime);
                    }
                }
            }

        }

        // setup inbound card view
        if (selectedFlight != null && (inboundCard.getVisibility() == View.VISIBLE) && selectedFlight.containsKey("Inbound")) {
            List<List<iFlight>> inboundFlights = selectedFlight.get("Inbound");

            if (inboundFlights != null) {

                iFlight firstFlight = inboundFlights.get(0).get(0);

                returnOriginCode.setText(firstFlight.getDeparture_icao());
                returnTakeoffTime.setText(parseTime(firstFlight.getFlight_dept_date_time()));

                if (inboundFlights.size() > 1) {
                    String middleAirports = "";

                    middleAirports += firstFlight.getArrival_icao();
                    returnMidCode.setText(middleAirports);

                    iFlight lastFlight = inboundFlights.get(1).get(0);

                    returnDestCode.setText(lastFlight.getArrival_icao());
                    String getLandingTime = parseTime(lastFlight.getFlight_arr_date_time());

                    if (getLandingTime != null) {
                        returnLandingTime.setText(getLandingTime);
                    }

                } else {
                    returnMidCode.setText("");

                    returnDestCode.setText(firstFlight.getArrival_icao());

                    String getLandingTime = parseTime(firstFlight.getFlight_arr_date_time());
                    if (getLandingTime != null) {
                        returnLandingTime.setText(getLandingTime);
                    }
                }
            }

        }


        totalPrice.setText("$" + session.getTotalPrice());
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

    private void addToSession() {

        session.setCardNum(cardNum.getText().toString());
        session.setExpiryDate(expiryDate.getText().toString());
        session.setCvv(cvv.getText().toString());
        session.setCardholderName(cardholderName.getText().toString());
        session.setBillingAddress(billingAddress.getText().toString());

    }

//    Client Side Validation.
    private boolean isValid(){
        boolean isValid = true;

        IValidatePayment validatePayment = new ValidatePayment();
        String error = "";

        error = validatePayment.validCardNum(cardNum.getText().toString());
        if (!error.isEmpty()) {
            cardNum.setError(error);
            isValid = false;
        }

        error = validatePayment.validExpiryDate(expiryDate.getText().toString());
        if (!error.isEmpty()) {
            expiryDate.setError(error);
            isValid = false;
        }

        error = validatePayment.validCVV(cvv.getText().toString());
        if (!error.isEmpty()) {
            cvv.setError(error);
            isValid = false;
        }

        error = validatePayment.validCardholderName(cardholderName.getText().toString());
        if (!error.isEmpty()) {
            cardholderName.setError(error);
            isValid = false;
        }

        error = validatePayment.validBillingAddress(billingAddress.getText().toString());
        if (!error.isEmpty()) {
            billingAddress.setError(error);
            isValid = false;
        }

        return isValid;
    }
}

