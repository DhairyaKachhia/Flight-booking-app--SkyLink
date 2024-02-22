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
import com.example.skylink.business.validations.IValidatePassgnData;
import com.example.skylink.business.PassengerDataManager;
import com.example.skylink.business.validations.ValidatePassgnData;
import com.example.skylink.objects.PassengerData;

import java.util.ArrayList;
import java.util.List;

public class User_info extends AppCompatActivity {

    private PassengerDataManager passengerDataManager;
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

        userFormList.setFastScrollEnabled(false);
        userFormAdapter = new CustomUserFormAdapter(getApplicationContext(), userInput);
        userFormList.setAdapter(userFormAdapter);

        passengerDataManager = new PassengerDataManager();
        passengers = new ArrayList<>();

        IValidatePassgnData validator = new ValidatePassgnData();

        submitBtn.setOnClickListener(v -> {

            boolean allValidForm = true;

            // Iterate through the form fields and get the values from the EditText fields
            for (int i = 0; i < userFormList.getChildCount(); i++) {
                boolean success = true;

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

                String error = "";

                error = validator.validTitle(title);
                if (!error.isEmpty()) {
                    titleEditText.setError(error);
                    success = false;
                }
                error = validator.validFirstname(firstname);
                if (!error.isEmpty()) {
                    firstNameEditText.setError(error);
                    success = false;
                }
                error = validator.validLastname(lastname);
                if (!error.isEmpty()) {
                    lastNameEditText.setError(error);
                    success = false;
                }
                error = validator.validEmail(email);
                if (!error.isEmpty()) {
                    emailEditText.setError(error);
                    success = false;
                }
                error = validator.validPhoneNum(phoneNum);
                if (!error.isEmpty()) {
                    phoneNumberEditText.setError(error);
                    success = false;
                }



                // Add the booking
                if (success) {
                    PassengerData newPassenger = passengerDataManager.addBooking(title, firstname, lastname, phoneNum, email);

                    passengers.add(newPassenger);

                } else {
                    allValidForm = false;
                }


            }

            if (allValidForm) {
                // Show confirmation message
                Toast.makeText(User_info.this, "Passenger Data Added Successfully", Toast.LENGTH_SHORT).show();

                // Pass the list to the next activity
            Intent nextActivityIntent = new Intent(this, SeatSelection.class);
            startActivity(nextActivityIntent);
            } else {
                // Show error message
                Toast.makeText(User_info.this, "Passenger data invalid", Toast.LENGTH_SHORT).show();

            }


        });
    }
}