package com.example.skylink.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.skylink.R;
import com.example.skylink.business.BookingManager;
import com.example.skylink.objects.PassengerData;

import java.util.ArrayList;
import java.util.List;

public class User_info extends AppCompatActivity {

    private BookingManager bookingManager;
    private CustomUserFormAdapter userFormAdapter;
    private ListView userFormList;
    private Button submitBtn;
    private List<PassengerData> passengers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        Intent intent = getIntent();
        Bundle userInput = intent.getExtras();

        userFormList = findViewById(R.id.lvUserForms);
        submitBtn = findViewById(R.id.submitBtn);

        userFormAdapter = new CustomUserFormAdapter(getApplicationContext(), userInput);
        userFormList.setAdapter(userFormAdapter);

        bookingManager = new BookingManager();
        passengers = new ArrayList<>();

        submitBtn.setOnClickListener(v -> {

            // Iterate through the form fields and get the values from the EditText fields
            for (int i = 0; i < userFormList.getChildCount(); i++) {
                View innerForm = userFormList.getChildAt(i);
                EditText titleEditText = innerForm.findViewById(R.id.etTitle);
                EditText firstNameEditText = innerForm.findViewById(R.id.etFirstName);
                EditText lastNameEditText = innerForm.findViewById(R.id.etLastName);
                EditText phoneNumberEditText = innerForm.findViewById(R.id.etTelephoneNumber);
                EditText emailEditText = innerForm.findViewById(R.id.etEmailAddress);

                String title = titleEditText.getText().toString();
                String firstname = firstNameEditText.getText().toString();
                String lastname = lastNameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String phoneNum = phoneNumberEditText.getText().toString();

                // Add the booking
                passengers.add(bookingManager.addBooking(title, firstname, lastname, phoneNum, email));

            }


            // Show confirmation message
            Toast.makeText(User_info.this, "PassengerData Added Successfully", Toast.LENGTH_SHORT).show();

            // Pass the list to the next activity
//            Intent nextActivityIntent = new Intent(this, NextActivity.class);
//            nextActivityIntent.putExtra("travelers", (Serializable) travelers);
//            startActivity(nextActivityIntent);



        });
    }
}