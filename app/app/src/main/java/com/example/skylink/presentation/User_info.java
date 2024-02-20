package com.example.skylink.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.skylink.R;
import com.example.skylink.business.BookingManager;
import com.example.skylink.business.Session;
import com.example.skylink.objects.Booking;

public class User_info extends AppCompatActivity {

    private EditText etTitle, etFirstName, etLastName, etTelephoneNumber, etEmailAddress;
    private BookingManager bookingManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);


        bookingManager = new BookingManager();

        etTitle = findViewById(R.id.etTitle);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etTelephoneNumber = findViewById(R.id.etTelephoneNumber);
        etEmailAddress = findViewById(R.id.etEmailAddress);

        Button addUserBtn = findViewById(R.id.addUserBtn);
        addUserBtn.setOnClickListener(view -> {
            // Retrieve the data from inputs
            String title = etTitle.getText().toString();
            String firstName = etFirstName.getText().toString();
            String lastName = etLastName.getText().toString();
            String telephoneNumber = etTelephoneNumber.getText().toString();
            String emailAddress = etEmailAddress.getText().toString();

            Booking[] bookings = new Booking[0];

            bookings[0] = new Booking(title, firstName, lastName, telephoneNumber, emailAddress);

            // Add the booking
            bookingManager.addBooking(bookings[0]);
            Session.getInstance().setBookings(bookings);

            // Show confirmation message
            Toast.makeText(User_info.this, "Booking Added Successfully", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(User_info.this, SeatSelection.class);
            startActivity(intent);
        });
    }
}