package com.example.skylink.presentation.User_Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.skylink.R;
import com.example.skylink.application.Services;
import com.example.skylink.objects.Session;
import com.example.skylink.business.Implementations.UserHandler;
import com.example.skylink.business.Interface.IUserHandler;
import com.example.skylink.objects.Interfaces.IUserProperties;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.skylink.presentation.FlightSearching.FlightSearchP;

public class UpdateUserProfileActivity extends AppCompatActivity {

    private EditText address, city, province, phone, dateOfBirth, gender;
    private Button submit;
    IUserHandler handler;
    IUserProperties user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_profile);
        initializeViews();
        handler = new UserHandler(Services.getUserDatabase());
        fetchUserData();
        setupSubmitClickListener();

        submit = findViewById(R.id.btnSubmit);
    }



    private void setupSubmitClickListener() {
        submit.setOnClickListener(v -> {
            handleSubmitClick();
        });
    }


    private void handleSubmitClick() {
        String addressText = address.getText().toString();
        String cityText = city.getText().toString();
        String provinceText = province.getText().toString();
        String phoneText = phone.getText().toString();
        String dobText = dateOfBirth.getText().toString();
        String genderText = gender.getText().toString();

        if (addressText.isEmpty() || cityText.isEmpty() || provinceText.isEmpty() || phoneText.isEmpty() || dobText.isEmpty() || genderText.isEmpty()) {
            Toast.makeText(UpdateUserProfileActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        updateUserProfile(addressText, cityText, provinceText, phoneText, dobText, genderText);

        Toast.makeText(UpdateUserProfileActivity.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();

        Intent flightSearchIntent = new Intent(UpdateUserProfileActivity.this, FlightSearchP.class);
        startActivity(flightSearchIntent);
    }

    private void updateUserProfile(String addressText, String cityText, String provinceText, String phoneText, String dobText, String genderText) {
        user.setAddress(addressText + ", " + cityText + ", " + provinceText);
        user.setPhone(phoneText);
        user.setDateOfBirth(dobText);
        user.setGender(genderText);
        handler.updateUserProfile(user);
    }

    private void initializeViews() {
        address = findViewById(R.id.etAddress);
        city = findViewById(R.id.etCity);
        province = findViewById(R.id.etProvince);
        phone = findViewById(R.id.etPhone);
        dateOfBirth = findViewById(R.id.etDoB);
        gender = findViewById(R.id.etGender);
        submit = findViewById(R.id.btnSubmit);
    }
    private void fetchUserData() {
        String userEmail = Session.getInstance().getEmail();

        user = handler.getUserByEmail(userEmail);

        if (user != null) {
            updateWelcomeMessage(user.getFullName());
        } else {
            handleUserNotFound();
        }
    }
    private void updateWelcomeMessage(String userName) {
        TextView tvWelcomeTitle = findViewById(R.id.tvWelcomeTitle);
        tvWelcomeTitle.setText("Hello, " + userName);
    }

    private void handleUserNotFound() {
        Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
        Intent fallbackIntent = new Intent(this, SignUpActivity.class);
        startActivity(fallbackIntent);
    }

}