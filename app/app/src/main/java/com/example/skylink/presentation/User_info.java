package com.example.skylink.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.skylink.R;
import com.example.skylink.business.BookingManager;

public class User_info extends AppCompatActivity {

    private EditText etTitle, etFirstName, etLastName, etTelephoneNumber, etEmailAddress;
    private BookingManager bookingManager; // Add this line

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        // Initialize BookingManager
        bookingManager = new BookingManager(); // Initialize it here

        // Initialize EditTexts
        etTitle = findViewById(R.id.etTitle);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etTelephoneNumber = findViewById(R.id.etTelephoneNumber);
        etEmailAddress = findViewById(R.id.etEmailAddress);

        Button addUserBtn = findViewById(R.id.addUserBtn);
        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve the data from inputs
                String title = etTitle.getText().toString();
                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();
                String telephoneNumber = etTelephoneNumber.getText().toString();
                String emailAddress = etEmailAddress.getText().toString();

                // Add the booking
                bookingManager.addBooking(title, firstName, lastName, telephoneNumber, emailAddress);

                // Show confirmation message
                Toast.makeText(User_info.this, "Booking Added Successfully", Toast.LENGTH_SHORT).show();

                // Optionally clear the fields or navigate away
            }
        });
    }
}